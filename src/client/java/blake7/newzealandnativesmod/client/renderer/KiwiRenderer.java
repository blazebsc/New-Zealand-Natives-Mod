package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.NativesId;
import blake7.newzealandnativesmod.entity.KiwiEntity;
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
public class KiwiRenderer extends GeoEntityRenderer<KiwiEntity> {
//?} else {
/*public class KiwiRenderer extends GeoEntityRenderer<KiwiEntity, EntityRenderState> {
*///?}
    public KiwiRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new KiwiModel());
    }
}

class KiwiModel extends software.bernie.geckolib.model.GeoModel<KiwiEntity> {
    @Override
//? if <=1.21.1 {
    public ResourceLocation getModelResource(KiwiEntity object) {
        return NativesId.of("geo/kiwi.geo.json");
//?} else {
    /*public ResourceLocation getModelResource(GeoRenderState renderState) {
        return NativesId.of("geo/kiwi");
*///?}
    }

    @Override
//? if <=1.21.1 {
    public ResourceLocation getTextureResource(KiwiEntity object) {
//?} else {
    /*public ResourceLocation getTextureResource(GeoRenderState renderState) {
*///?}
        return NativesId.of("textures/entity/kiwi/kiwi.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KiwiEntity animatable) {
//? if <=1.21.1 {
        return NativesId.of("animations/kiwi.animation.json");
//?} else {
        /*return NativesId.of("kiwi");
*///?}
    }
}
