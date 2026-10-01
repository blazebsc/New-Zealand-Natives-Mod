package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.NativesId;
import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
//? if >1.21.1 {
/*import net.minecraft.client.render.entity.state.EntityRenderState;
*///?}
import software.bernie.geckolib.renderer.GeoEntityRenderer;
//? if >1.21.1 {
/*import software.bernie.geckolib.renderer.base.GeoRenderState;
*///?}

//? if <=1.21.1 {
public class KokopuRenderer extends GeoEntityRenderer<NativesEntity> {
//?} else {
/*public class KokopuRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
*///?}
    public KokopuRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new KokopuModel());
    }
}

class KokopuModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
//? if <=1.21.1 {
    public ResourceLocation getModelResource(NativesEntity object) {
        return NativesId.of("geo/kokopu.geo.json");
//?} else {
    /*public ResourceLocation getModelResource(GeoRenderState renderState) {
        return NativesId.of("geo/kokopu");
*///?}
    }

    @Override
//? if <=1.21.1 {
    public ResourceLocation getTextureResource(NativesEntity object) {
//?} else {
    /*public ResourceLocation getTextureResource(GeoRenderState renderState) {
*///?}
        return NativesId.of("textures/entity/kokopu/kokopu.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NativesEntity animatable) {
//? if <=1.21.1 {
        return NativesId.of("animations/kokopu.animation.json");
//?} else {
        /*return NativesId.of("kokopu");
*///?}
    }
}
