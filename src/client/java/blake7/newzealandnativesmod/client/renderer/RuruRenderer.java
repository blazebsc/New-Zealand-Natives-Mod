package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.NativesId;
import blake7.newzealandnativesmod.entity.RuruEntity;
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
public class RuruRenderer extends GeoEntityRenderer<RuruEntity> {
//?} else {
/*public class RuruRenderer extends GeoEntityRenderer<RuruEntity, EntityRenderState> {
*///?}
    public RuruRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new RuruModel());
    }
}

class RuruModel extends software.bernie.geckolib.model.GeoModel<RuruEntity> {
    @Override
//? if <=1.21.1 {
    public ResourceLocation getModelResource(RuruEntity object) {
        return NativesId.of("geo/ruru.geo.json");
//?} else {
    /*public ResourceLocation getModelResource(GeoRenderState renderState) {
        return NativesId.of("geo/ruru");
*///?}
    }

    @Override
//? if <=1.21.1 {
    public ResourceLocation getTextureResource(RuruEntity object) {
//?} else {
    /*public ResourceLocation getTextureResource(GeoRenderState renderState) {
*///?}
        return NativesId.of("textures/entity/ruru/ruru.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RuruEntity animatable) {
//? if <=1.21.1 {
        return NativesId.of("animations/ruru.animation.json");
//?} else {
        /*return NativesId.of("ruru");
*///?}
    }
}
