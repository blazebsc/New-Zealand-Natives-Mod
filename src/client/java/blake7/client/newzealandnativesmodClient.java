package blake7.client;

import blake7.newzealandnativesmod.client.renderer.*;
import blake7.newzealandnativesmod.entity.KiwiEntity;
import blake7.newzealandnativesmod.entity.KatipoEntity;
import blake7.newzealandnativesmod.entity.HectorsDolphinEntity;
import blake7.newzealandnativesmod.entity.RuruEntity;
import blake7.newzealandnativesmod.entity.NativesEntity;
import blake7.newzealandnativesmod.registry.NativesBlocks;
import blake7.newzealandnativesmod.registry.NativesEntities;
import net.fabricmc.api.ClientModInitializer;
//? if <=1.21.1 {
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
//?}
//? if >1.21.1 && <26.1 {
/*import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
*///?}
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
//? if <=1.21.1 {
import net.minecraft.client.renderer.RenderType;
//?}
//? if >1.21.1 && <26.1 {
/*import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
*///?}
import net.minecraft.world.entity.EntityType;

public class newzealandnativesmodClient implements ClientModInitializer {

    @SuppressWarnings("unchecked")
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(KiwiEntity.TYPE, KiwiRenderer::new);
        EntityRendererRegistry.register(KatipoEntity.TYPE, KatipoRenderer::new);
        EntityRendererRegistry.register(HectorsDolphinEntity.TYPE, HectorsDolphinRenderer::new);
        EntityRendererRegistry.register(RuruEntity.TYPE, RuruRenderer::new);

        var allTypes = NativesEntities.all();
        var allSpecies = NativesEntities.SPECIES;
        for (int i = 0; i < allSpecies.length; i++) {
            String id = allSpecies[i].shortId();
            if (id.equals("kiwi") || id.equals("katipo") || id.equals("hectors_dolphin") || id.equals("ruru")) continue;
            if (i >= allTypes.size()) continue;
            EntityType<?> rawType = allTypes.get(i);
            EntityType<? extends NativesEntity> type =
                    (EntityType<? extends NativesEntity>) rawType;
            if (id.equals("albatross")) {
                EntityRendererRegistry.register(type, AlbatrossRenderer::new);
            } else if (id.equals("basket_fungus")) {
                EntityRendererRegistry.register(type, BasketFungusRenderer::new);
            } else if (id.equals("bat")) {
                EntityRendererRegistry.register(type, BatRenderer::new);
            } else if (id.equals("eel")) {
                EntityRendererRegistry.register(type, EelRenderer::new);
            } else if (id.equals("falcon")) {
                EntityRendererRegistry.register(type, FalconRenderer::new);
            } else if (id.equals("fantail")) {
                EntityRendererRegistry.register(type, FantailRenderer::new);
            } else if (id.equals("frog")) {
                EntityRendererRegistry.register(type, FrogRenderer::new);
            } else if (id.equals("gecko")) {
                EntityRendererRegistry.register(type, GeckoRenderer::new);
            } else if (id.equals("haasts_eagle")) {
                EntityRendererRegistry.register(type, HaastsEagleRenderer::new);
            } else if (id.equals("harakeke")) {
                EntityRendererRegistry.register(type, HarakekeRenderer::new);
            } else if (id.equals("harvestman")) {
                EntityRendererRegistry.register(type, HarvestmanRenderer::new);
            } else if (id.equals("hoiho")) {
                EntityRendererRegistry.register(type, HoihoRenderer::new);
            } else if (id.equals("huhu")) {
                EntityRendererRegistry.register(type, HuhuRenderer::new);
            } else if (id.equals("huia")) {
                EntityRendererRegistry.register(type, HuiaRenderer::new);
            } else if (id.equals("humpback_whale")) {
                EntityRendererRegistry.register(type, HumpbackWhaleRenderer::new);
            } else if (id.equals("hura")) {
                EntityRendererRegistry.register(type, HuraRenderer::new);
            } else if (id.equals("kakapo")) {
                EntityRendererRegistry.register(type, KakapoRenderer::new);
            } else if (id.equals("kea")) {
                EntityRendererRegistry.register(type, KeaRenderer::new);
            } else if (id.equals("kereru")) {
                EntityRendererRegistry.register(type, KereruRenderer::new);
            } else if (id.equals("kina")) {
                EntityRendererRegistry.register(type, KinaRenderer::new);
            } else if (id.equals("king_shag")) {
                EntityRendererRegistry.register(type, KingShagRenderer::new);
            } else if (id.equals("kokako")) {
                EntityRendererRegistry.register(type, KokakoRenderer::new);
            } else if (id.equals("kokopu")) {
                EntityRendererRegistry.register(type, KokopuRenderer::new);
            } else if (id.equals("korora")) {
                EntityRendererRegistry.register(type, KororaRenderer::new);
            } else if (id.equals("kotare")) {
                EntityRendererRegistry.register(type, KotareRenderer::new);
            } else if (id.equals("kotuku")) {
                EntityRendererRegistry.register(type, KotukuRenderer::new);
            } else if (id.equals("koura")) {
                EntityRendererRegistry.register(type, KouraRenderer::new);
            } else if (id.equals("kunekune")) {
                EntityRendererRegistry.register(type, KunekuneRenderer::new);
            } else if (id.equals("moa")) {
                EntityRendererRegistry.register(type, MoaRenderer::new);
            } else if (id.equals("monarch")) {
                EntityRendererRegistry.register(type, MonarchRenderer::new);
            } else if (id.equals("papaka")) {
                EntityRendererRegistry.register(type, PapakaRenderer::new);
            } else if (id.equals("pateke")) {
                EntityRendererRegistry.register(type, PatekeRenderer::new);
            } else if (id.equals("pohutukawa")) {
                EntityRendererRegistry.register(type, PohutukawaRenderer::new);
            } else if (id.equals("ponga")) {
                EntityRendererRegistry.register(type, PongaRenderer::new);
            } else if (id.equals("pukeko")) {
                EntityRendererRegistry.register(type, PukekoRenderer::new);
            } else if (id.equals("puriri")) {
                EntityRendererRegistry.register(type, PuririRenderer::new);
            } else if (id.equals("red_admiral")) {
                EntityRendererRegistry.register(type, RedAdmiralRenderer::new);
            } else if (id.equals("saddleback")) {
                EntityRendererRegistry.register(type, SaddlebackRenderer::new);
            } else if (id.equals("sea_lion")) {
                EntityRendererRegistry.register(type, SeaLionRenderer::new);
            } else if (id.equals("skink")) {
                EntityRendererRegistry.register(type, SkinkRenderer::new);
            } else if (id.equals("snail")) {
                EntityRendererRegistry.register(type, SnailRenderer::new);
            } else if (id.equals("takahe")) {
                EntityRendererRegistry.register(type, TakaheRenderer::new);
            } else if (id.equals("tamure")) {
                EntityRendererRegistry.register(type, TamureRenderer::new);
            } else if (id.equals("tawaki")) {
                EntityRendererRegistry.register(type, TawakiRenderer::new);
            } else if (id.equals("tuatara")) {
                EntityRendererRegistry.register(type, TuataraRenderer::new);
            } else if (id.equals("tui")) {
                EntityRendererRegistry.register(type, TuiRenderer::new);
            } else if (id.equals("wasp")) {
                EntityRendererRegistry.register(type, WaspRenderer::new);
            } else if (id.equals("weevil")) {
                EntityRendererRegistry.register(type, WeevilRenderer::new);
            } else if (id.equals("weka")) {
                EntityRendererRegistry.register(type, WekaRenderer::new);
            } else if (id.equals("weta")) {
                EntityRendererRegistry.register(type, WetaRenderer::new);
            } else if (id.equals("whio")) {
                EntityRendererRegistry.register(type, WhioRenderer::new);
            }
        }

//? if <=1.21.1 {
        BlockRenderLayerMap.INSTANCE.putBlock(NativesBlocks.KOWHAI_LEAVES, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(NativesBlocks.KOWHAI_SAPLING, RenderType.cutout());
//?}
//? if >1.21.1 && <26.1 {
        /*BlockRenderLayerMap.putBlock(NativesBlocks.KOWHAI_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(NativesBlocks.KOWHAI_SAPLING, ChunkSectionLayer.CUTOUT);
*///?}
    }
}
