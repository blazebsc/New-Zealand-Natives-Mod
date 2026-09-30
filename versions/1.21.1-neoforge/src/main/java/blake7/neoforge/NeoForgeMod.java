package blake7.neoforge;

import blake7.newzealandnativesmod.client.renderer.*;
import blake7.newzealandnativesmod.entity.HaastsEagleEntity;
import blake7.newzealandnativesmod.entity.HectorsDolphinEntity;
import blake7.newzealandnativesmod.entity.KatipoEntity;
import blake7.newzealandnativesmod.entity.KiwiEntity;
import blake7.newzealandnativesmod.entity.NativesEntity;
import blake7.newzealandnativesmod.entity.RuruEntity;
import blake7.newzealandnativesmod.registry.NativesBlocks;
import blake7.newzealandnativesmod.registry.NativesConfig;
import blake7.newzealandnativesmod.registry.NativesEntities;
import blake7.newzealandnativesmod.registry.NativesItems;
import blake7.newzealandnativesmod.registry.NativesSounds;
import blake7.newzealandnativesmod.registry.SpeciesEntities;
import blake7.newzealandnativesmod.worldgen.FallenLogFeature;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// NeoForge entrypoint. Shared registry classes build raw instances here;
// DeferredRegisters below attach them to the vanilla registries.
// Pilot scope: blocks/items/entities/sounds/tab/attributes/renderers.
// Biome spawns + worldgen injection (BiomeModifications on Fabric) need
// biome-modifier JSONs as a follow-up and are intentionally unwired.
@Mod(NeoForgeMod.MOD_ID)
public class NeoForgeMod {
    public static final String MOD_ID = "newzealandnatives";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public NeoForgeMod(IEventBus modBus) {
        NativesConfig.load();
        NativesEntities.register();
        NativesItems.register();
        NativesBlocks.register();
        NativesSounds.register();

        DeferredRegister.Blocks blocks = DeferredRegister.createBlocks(MOD_ID);
        blocks.register("rotten_log", () -> NativesBlocks.ROTTEN_LOG);
        blocks.register("kowhai_log", () -> NativesBlocks.KOWHAI_LOG);
        blocks.register("kowhai_leaves", () -> NativesBlocks.KOWHAI_LEAVES);
        blocks.register("kowhai_sapling", () -> NativesBlocks.KOWHAI_SAPLING);
        blocks.register(modBus);

        DeferredRegister.Items items = DeferredRegister.createItems(MOD_ID);
        items.register("rotten_log", () -> new BlockItem(NativesBlocks.ROTTEN_LOG, new Item.Properties()));
        items.register("kowhai_log", () -> new BlockItem(NativesBlocks.KOWHAI_LOG, new Item.Properties()));
        items.register("kowhai_leaves", () -> new BlockItem(NativesBlocks.KOWHAI_LEAVES, new Item.Properties()));
        items.register("kowhai_sapling", () -> new BlockItem(NativesBlocks.KOWHAI_SAPLING, new Item.Properties()));
        items.register("huhu_grub", () -> NativesItems.HUHU_GRUB);
        for (var entry : NativesItems.spawnEggs().entrySet()) {
            String eggId = entry.getKey() + "_spawn_egg";
            Item egg = entry.getValue();
            items.register(eggId, () -> egg);
        }
        items.register(modBus);

        DeferredRegister<EntityType<?>> entityTypes = DeferredRegister.create(Registries.ENTITY_TYPE, MOD_ID);
        var types = NativesEntities.all();
        var species = NativesEntities.SPECIES;
        for (int i = 0; i < species.length && i < types.size(); i++) {
            EntityType<?> type = types.get(i);
            String id = species[i].shortId();
            entityTypes.register(id, () -> type);
        }
        entityTypes.register(modBus);

        DeferredRegister<SoundEvent> soundEvents = DeferredRegister.create(Registries.SOUND_EVENT, MOD_ID);
        for (String path : NativesSounds.paths()) {
            soundEvents.register(path, () -> NativesSounds.byPath(path));
        }
        soundEvents.register(modBus);

        DeferredRegister<CreativeModeTab> tabs = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
        tabs.register("natives", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .title(Component.translatable("itemGroup.newzealandnatives.natives"))
                .icon(() -> new ItemStack(NativesItems.KIWI_SPAWN_EGG))
                .displayItems((params, output) -> {
                    for (var egg : NativesItems.spawnEggs().values()) {
                        output.accept(new ItemStack(egg));
                    }
                    output.accept(new ItemStack(NativesItems.HUHU_GRUB));
                    output.accept(new ItemStack(NativesBlocks.ROTTEN_LOG));
                    output.accept(new ItemStack(NativesBlocks.KOWHAI_LOG));
                    output.accept(new ItemStack(NativesBlocks.KOWHAI_LEAVES));
                    output.accept(new ItemStack(NativesBlocks.KOWHAI_SAPLING));
                })
                .build());
        tabs.register(modBus);

        DeferredRegister<Feature<?>> features = DeferredRegister.create(Registries.FEATURE, MOD_ID);
        features.register("fallen_log", () -> new FallenLogFeature(NoneFeatureConfiguration.CODEC));
        features.register(modBus);

        modBus.addListener(this::addAttributes);
        modBus.addListener(this::registerRenderers);
        modBus.addListener(this::clientSetup);
    }

    private void addAttributes(EntityAttributeCreationEvent event) {
        event.put(KiwiEntity.TYPE,
                KiwiEntity.createMobAttributes()
                        .add(Attributes.MAX_HEALTH, 4.0D)
                        .add(Attributes.MOVEMENT_SPEED, 0.2D)
                        .add(Attributes.FOLLOW_RANGE, 16.0D)
                        .build());
        event.put(KatipoEntity.TYPE, KatipoEntity.createAttributes().build());
        event.put(HectorsDolphinEntity.TYPE,
                HectorsDolphinEntity.createAttributes().build());
        event.put(RuruEntity.TYPE,
                RuruEntity.createMobAttributes()
                        .add(Attributes.MAX_HEALTH, 6.0D)
                        .add(Attributes.MOVEMENT_SPEED, 0.4D)
                        .add(Attributes.FOLLOW_RANGE, 16.0D)
                        .add(Attributes.FLYING_SPEED, 0.4D)
                        .build());

        var allTypes = NativesEntities.all();
        var allSpecies = NativesEntities.SPECIES;
        for (int i = 0; i < allSpecies.length && i < allTypes.size(); i++) {
            String id = allSpecies[i].shortId();
            if (id.equals("kiwi") || id.equals("katipo") || id.equals("hectors_dolphin") || id.equals("ruru")) continue;
            @SuppressWarnings("unchecked")
            EntityType<? extends LivingEntity> livingType =
                    (EntityType<? extends LivingEntity>) allTypes.get(i);
            double[] stats = SpeciesEntities.statsFor(allSpecies[i]);
            var builder = NativesEntity.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, stats[0])
                    .add(Attributes.MOVEMENT_SPEED, stats[1])
                    .add(Attributes.FOLLOW_RANGE, id.equals("haasts_eagle") ? 64.0D : 16.0D)
                    .add(Attributes.FLYING_SPEED, 0.4D);
            if (id.equals("haasts_eagle")) {
                builder.add(Attributes.ATTACK_DAMAGE, 6.0D);
            }
            event.put(livingType, builder.build());
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(KiwiEntity.TYPE, KiwiRenderer::new);
        event.registerEntityRenderer(KatipoEntity.TYPE, KatipoRenderer::new);
        event.registerEntityRenderer(HectorsDolphinEntity.TYPE, HectorsDolphinRenderer::new);
        event.registerEntityRenderer(RuruEntity.TYPE, RuruRenderer::new);

        var allTypes = NativesEntities.all();
        var allSpecies = NativesEntities.SPECIES;
        for (int i = 0; i < allSpecies.length && i < allTypes.size(); i++) {
            String id = allSpecies[i].shortId();
            if (id.equals("kiwi") || id.equals("katipo") || id.equals("hectors_dolphin") || id.equals("ruru")) continue;
            EntityType<? extends NativesEntity> type =
                    (EntityType<? extends NativesEntity>) allTypes.get(i);
            if (id.equals("albatross")) {
                event.registerEntityRenderer(type, AlbatrossRenderer::new);
            } else if (id.equals("basket_fungus")) {
                event.registerEntityRenderer(type, BasketFungusRenderer::new);
            } else if (id.equals("bat")) {
                event.registerEntityRenderer(type, BatRenderer::new);
            } else if (id.equals("eel")) {
                event.registerEntityRenderer(type, EelRenderer::new);
            } else if (id.equals("falcon")) {
                event.registerEntityRenderer(type, FalconRenderer::new);
            } else if (id.equals("fantail")) {
                event.registerEntityRenderer(type, FantailRenderer::new);
            } else if (id.equals("frog")) {
                event.registerEntityRenderer(type, FrogRenderer::new);
            } else if (id.equals("gecko")) {
                event.registerEntityRenderer(type, GeckoRenderer::new);
            } else if (id.equals("haasts_eagle")) {
                event.registerEntityRenderer(type, HaastsEagleRenderer::new);
            } else if (id.equals("harakeke")) {
                event.registerEntityRenderer(type, HarakekeRenderer::new);
            } else if (id.equals("harvestman")) {
                event.registerEntityRenderer(type, HarvestmanRenderer::new);
            } else if (id.equals("hoiho")) {
                event.registerEntityRenderer(type, HoihoRenderer::new);
            } else if (id.equals("huhu")) {
                event.registerEntityRenderer(type, HuhuRenderer::new);
            } else if (id.equals("huia")) {
                event.registerEntityRenderer(type, HuiaRenderer::new);
            } else if (id.equals("humpback_whale")) {
                event.registerEntityRenderer(type, HumpbackWhaleRenderer::new);
            } else if (id.equals("hura")) {
                event.registerEntityRenderer(type, HuraRenderer::new);
            } else if (id.equals("kakapo")) {
                event.registerEntityRenderer(type, KakapoRenderer::new);
            } else if (id.equals("kea")) {
                event.registerEntityRenderer(type, KeaRenderer::new);
            } else if (id.equals("kereru")) {
                event.registerEntityRenderer(type, KereruRenderer::new);
            } else if (id.equals("kina")) {
                event.registerEntityRenderer(type, KinaRenderer::new);
            } else if (id.equals("king_shag")) {
                event.registerEntityRenderer(type, KingShagRenderer::new);
            } else if (id.equals("kokako")) {
                event.registerEntityRenderer(type, KokakoRenderer::new);
            } else if (id.equals("kokopu")) {
                event.registerEntityRenderer(type, KokopuRenderer::new);
            } else if (id.equals("korora")) {
                event.registerEntityRenderer(type, KororaRenderer::new);
            } else if (id.equals("kotare")) {
                event.registerEntityRenderer(type, KotareRenderer::new);
            } else if (id.equals("kotuku")) {
                event.registerEntityRenderer(type, KotukuRenderer::new);
            } else if (id.equals("koura")) {
                event.registerEntityRenderer(type, KouraRenderer::new);
            } else if (id.equals("kunekune")) {
                event.registerEntityRenderer(type, KunekuneRenderer::new);
            } else if (id.equals("moa")) {
                event.registerEntityRenderer(type, MoaRenderer::new);
            } else if (id.equals("monarch")) {
                event.registerEntityRenderer(type, MonarchRenderer::new);
            } else if (id.equals("papaka")) {
                event.registerEntityRenderer(type, PapakaRenderer::new);
            } else if (id.equals("pateke")) {
                event.registerEntityRenderer(type, PatekeRenderer::new);
            } else if (id.equals("pohutukawa")) {
                event.registerEntityRenderer(type, PohutukawaRenderer::new);
            } else if (id.equals("ponga")) {
                event.registerEntityRenderer(type, PongaRenderer::new);
            } else if (id.equals("pukeko")) {
                event.registerEntityRenderer(type, PukekoRenderer::new);
            } else if (id.equals("puriri")) {
                event.registerEntityRenderer(type, PuririRenderer::new);
            } else if (id.equals("red_admiral")) {
                event.registerEntityRenderer(type, RedAdmiralRenderer::new);
            } else if (id.equals("saddleback")) {
                event.registerEntityRenderer(type, SaddlebackRenderer::new);
            } else if (id.equals("sea_lion")) {
                event.registerEntityRenderer(type, SeaLionRenderer::new);
            } else if (id.equals("skink")) {
                event.registerEntityRenderer(type, SkinkRenderer::new);
            } else if (id.equals("snail")) {
                event.registerEntityRenderer(type, SnailRenderer::new);
            } else if (id.equals("takahe")) {
                event.registerEntityRenderer(type, TakaheRenderer::new);
            } else if (id.equals("tamure")) {
                event.registerEntityRenderer(type, TamureRenderer::new);
            } else if (id.equals("tawaki")) {
                event.registerEntityRenderer(type, TawakiRenderer::new);
            } else if (id.equals("tuatara")) {
                event.registerEntityRenderer(type, TuataraRenderer::new);
            } else if (id.equals("tui")) {
                event.registerEntityRenderer(type, TuiRenderer::new);
            } else if (id.equals("wasp")) {
                event.registerEntityRenderer(type, WaspRenderer::new);
            } else if (id.equals("weevil")) {
                event.registerEntityRenderer(type, WeevilRenderer::new);
            } else if (id.equals("weka")) {
                event.registerEntityRenderer(type, WekaRenderer::new);
            } else if (id.equals("weta")) {
                event.registerEntityRenderer(type, WetaRenderer::new);
            } else if (id.equals("whio")) {
                event.registerEntityRenderer(type, WhioRenderer::new);
            }
        }
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(NativesBlocks.KOWHAI_LEAVES, RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(NativesBlocks.KOWHAI_SAPLING, RenderType.cutout());
        });
    }
}
