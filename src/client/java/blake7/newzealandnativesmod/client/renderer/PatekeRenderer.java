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
public class PatekeRenderer extends GeoEntityRenderer<NativesEntity> {
//?} else {
/*public class PatekeRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
*///?}
    public PatekeRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new PatekeModel());
    }
}

class PatekeModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
//? if <=1.21.1 {
    public ResourceLocation getModelResource(NativesEntity object) {
        return NativesId.of("geo/pateke.geo.json");
//?} else {
    /*public ResourceLocation getModelResource(GeoRenderState renderState) {
        return NativesId.of("geo/pateke");
*///?}
    }

    @Override
//? if <=1.21.1 {
    public ResourceLocation getTextureResource(NativesEntity object) {
//?} else {
    /*public ResourceLocation getTextureResource(GeoRenderState renderState) {
*///?}
        return NativesId.of("textures/entity/pateke/pateke.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NativesEntity animatable) {
//? if <=1.21.1 {
        return NativesId.of("animations/pateke.animation.json");
//?} else {
        /*return NativesId.of("pateke");
*///?}
    }
}
