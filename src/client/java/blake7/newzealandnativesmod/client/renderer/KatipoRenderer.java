package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.NativesId;
import blake7.newzealandnativesmod.entity.KatipoEntity;
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
public class KatipoRenderer extends GeoEntityRenderer<KatipoEntity> {
//?} else {
/*public class KatipoRenderer extends GeoEntityRenderer<KatipoEntity, EntityRenderState> {
*///?}
    public KatipoRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new KatipoModel());
    }
}

class KatipoModel extends software.bernie.geckolib.model.GeoModel<KatipoEntity> {
    @Override
//? if <=1.21.1 {
    public ResourceLocation getModelResource(KatipoEntity object) {
        return NativesId.of("geo/katipo.geo.json");
//?} else {
    /*public ResourceLocation getModelResource(GeoRenderState renderState) {
        return NativesId.of("geo/katipo");
*///?}
    }

    @Override
//? if <=1.21.1 {
    public ResourceLocation getTextureResource(KatipoEntity object) {
//?} else {
    /*public ResourceLocation getTextureResource(GeoRenderState renderState) {
*///?}
        return NativesId.of("textures/entity/katipo/katipo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KatipoEntity animatable) {
//? if <=1.21.1 {
        return NativesId.of("animations/katipo.animation.json");
//?} else {
        /*return NativesId.of("katipo");
*///?}
    }
}
