package blake7.newzealandnativesmod;

import blake7.newzealandnativesmod.entity.KiwiEntity;
import blake7.newzealandnativesmod.entity.KatipoEntity;
import blake7.newzealandnativesmod.entity.HectorsDolphinEntity;
import blake7.newzealandnativesmod.entity.RuruEntity;
import blake7.newzealandnativesmod.entity.NativesEntity;
import blake7.newzealandnativesmod.registry.NativesEntities;
import blake7.newzealandnativesmod.registry.NativesConfig;
import blake7.newzealandnativesmod.registry.SpeciesEntities;
import blake7.newzealandnativesmod.registry.NativesItemGroups;
import blake7.newzealandnativesmod.registry.NativesBlocks;
import blake7.newzealandnativesmod.worldgen.FallenLogFeature;
import blake7.newzealandnativesmod.registry.NativesItems;
import blake7.newzealandnativesmod.registry.NativesSounds;
import blake7.newzealandnativesmod.registry.NativesSpawns;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewZealandNativesMod implements ModInitializer {
    public static final String MOD_ID = "newzealandnatives";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        NativesConfig.load();
        NativesEntities.register();
        NativesItems.register();
        NativesBlocks.register();
        FallenLogFeature.register();
        NativesItemGroups.register();
        NativesSounds.register();
        NativesSpawns.register();

        FabricDefaultAttributeRegistry.register(KiwiEntity.TYPE,
                KiwiEntity.createMobAttributes()
//? if <=1.21.1 {
                        .add(Attributes.MAX_HEALTH, 4.0D)
                        .add(Attributes.MOVEMENT_SPEED, 0.2D)
                        .add(Attributes.FOLLOW_RANGE, 16.0D)
//?} else {
                        /*.add(Attributes.MAX_HEALTH, 4.0D)
                        .add(Attributes.MOVEMENT_SPEED, 0.2D)
                        .add(Attributes.TEMPT_RANGE, 10.0D)
                        .add(Attributes.FOLLOW_RANGE, 16.0D)
*///?}
                        .build());

        FabricDefaultAttributeRegistry.register(KatipoEntity.TYPE, KatipoEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(HectorsDolphinEntity.TYPE,
                HectorsDolphinEntity.createAttributes().build());
        FabricDefaultAttributeRegistry.register(RuruEntity.TYPE,
                RuruEntity.createMobAttributes()
//? if <=1.21.1 {
                        .add(Attributes.MAX_HEALTH, 6.0D)
                        .add(Attributes.MOVEMENT_SPEED, 0.4D)
                        .add(Attributes.FOLLOW_RANGE, 16.0D)
                        .add(Attributes.FLYING_SPEED, 0.4D)
//?} else {
                        /*.add(Attributes.MAX_HEALTH, 6.0D)
                        .add(Attributes.MOVEMENT_SPEED, 0.4D)
                        .add(Attributes.TEMPT_RANGE, 10.0D)
                        .add(Attributes.FOLLOW_RANGE, 16.0D)
                        .add(Attributes.FLYING_SPEED, 0.4D)
*///?}
                        .build());

        var allTypes = NativesEntities.all();
        var allSpecies = NativesEntities.SPECIES;
        for (int i = 0; i < allSpecies.length; i++) {
            String id = allSpecies[i].shortId();
            if (id.equals("kiwi") || id.equals("katipo") || id.equals("hectors_dolphin") || id.equals("ruru")) continue;
            if (i < allTypes.size()) {
                EntityType<?> type = allTypes.get(i);
                @SuppressWarnings("unchecked")
                EntityType<? extends net.minecraft.world.entity.LivingEntity> livingType =
                        (EntityType<? extends net.minecraft.world.entity.LivingEntity>) type;
                double[] stats = SpeciesEntities.statsFor(allSpecies[i]);
                var builder = NativesEntity.createMobAttributes()
//? if <=1.21.1 {
                        .add(Attributes.MAX_HEALTH, stats[0])
                        .add(Attributes.MOVEMENT_SPEED, stats[1])
                        .add(Attributes.FOLLOW_RANGE, id.equals("haasts_eagle") ? 64.0D : 16.0D)
                        .add(Attributes.FLYING_SPEED, 0.4D);
//?} else {
                        /*.add(Attributes.MAX_HEALTH, stats[0])
                        .add(Attributes.MOVEMENT_SPEED, stats[1])
                        .add(Attributes.TEMPT_RANGE, 10.0D)
                        .add(Attributes.FOLLOW_RANGE, id.equals("haasts_eagle") ? 64.0D : 16.0D)
                        .add(Attributes.FLYING_SPEED, 0.4D);
*///?}
                if (id.equals("haasts_eagle")) {
//? if <=1.21.1 {
                    builder.add(Attributes.ATTACK_DAMAGE, 6.0D);
//?} else {
                    /*builder.add(Attributes.ATTACK_DAMAGE, 6.0D);
*///?}
                }
                FabricDefaultAttributeRegistry.register(livingType, builder.build());
            }
        }

        LOGGER.info("New Zealand Natives Mod initialized — {} entities, {} items registered.",
                NativesEntities.size(), NativesItems.size());
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static ResourceLocation bedrockId(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
