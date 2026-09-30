package blake7.newzealandnativesmod.client.renderer;

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
public class HuiaRenderer extends GeoEntityRenderer<NativesEntity> {
//?} else {
/*public class HuiaRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
*///?}
    public HuiaRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new HuiaModel());
    }
}

class HuiaModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
//? if <=1.21.1 {
    public ResourceLocation getModelResource(NativesEntity object) {
        return ResourceLocation.fromNamespaceAndPath("newzealandnatives", "geo/huia.geo.json");
//?} else {
    /*public ResourceLocation getModelResource(GeoRenderState renderState) {
        return ResourceLocation.fromNamespaceAndPath("newzealandnatives", "geo/huia");
*///?}
    }

    @Override
//? if <=1.21.1 {
    public ResourceLocation getTextureResource(NativesEntity object) {
//?} else {
    /*public ResourceLocation getTextureResource(GeoRenderState renderState) {
*///?}
        return ResourceLocation.fromNamespaceAndPath("newzealandnatives", "textures/entity/huia/huia.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NativesEntity animatable) {
//? if <=1.21.1 {
        return ResourceLocation.fromNamespaceAndPath("newzealandnatives", "animations/huia.animation.json");
//?} else {
        /*return ResourceLocation.fromNamespaceAndPath("newzealandnatives", "huia");
*///?}
    }
}
