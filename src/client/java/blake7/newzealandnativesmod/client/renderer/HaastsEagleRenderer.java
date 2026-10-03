package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.NativesId;
import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
//? if >1.21.1 {
/*import net.minecraft.client.render.entity.state.EntityRenderState;
*///?}
import software.bernie.geckolib.renderer.GeoEntityRenderer;
//? if >1.21.1 {
/*import software.bernie.geckolib.renderer.base.GeoRenderState;
*///?}

//? if <=1.21.1 {
public class HaastsEagleRenderer extends GeoEntityRenderer<NativesEntity> {
//?} else {
/*public class HaastsEagleRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
*///?}
    public HaastsEagleRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new HaastsEagleModel());
    }
}

class HaastsEagleModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
//? if <=1.21.1 {
    public Identifier getModelResource(NativesEntity object) {
        return NativesId.of("geo/haasts_eagle.geo.json");
//?} else {
    /*public Identifier getModelResource(GeoRenderState renderState) {
        return NativesId.of("geo/haasts_eagle");
*///?}
    }

    @Override
//? if <=1.21.1 {
    public Identifier getTextureResource(NativesEntity object) {
//?} else {
    /*public Identifier getTextureResource(GeoRenderState renderState) {
*///?}
        return NativesId.of("textures/entity/haasts_eagle/haasts_eagle.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
//? if <=1.21.1 {
        return NativesId.of("animations/falcon.animation.json");
//?} else {
        /*return NativesId.of("falcon");
*///?}
    }
}
