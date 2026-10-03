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
public class KotareRenderer extends GeoEntityRenderer<NativesEntity> {
//?} else {
/*public class KotareRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
*///?}
    public KotareRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new KotareModel());
    }
}

class KotareModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
//? if <=1.21.1 {
    public Identifier getModelResource(NativesEntity object) {
        return NativesId.of("geo/kotare.geo.json");
//?} else {
    /*public Identifier getModelResource(GeoRenderState renderState) {
        return NativesId.of("geo/kotare");
*///?}
    }

    @Override
//? if <=1.21.1 {
    public Identifier getTextureResource(NativesEntity object) {
//?} else {
    /*public Identifier getTextureResource(GeoRenderState renderState) {
*///?}
        return NativesId.of("textures/entity/kotare/kotare.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
//? if <=1.21.1 {
        return NativesId.of("animations/kotare.animation.json");
//?} else {
        /*return NativesId.of("kotare");
*///?}
    }
}
