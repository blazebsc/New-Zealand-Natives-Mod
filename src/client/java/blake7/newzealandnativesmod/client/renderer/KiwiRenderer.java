package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.KiwiEntity;
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
public class KiwiRenderer extends GeoEntityRenderer<KiwiEntity> {
//?} else {
/*public class KiwiRenderer extends GeoEntityRenderer<KiwiEntity, EntityRenderState> {
*///?}
    public KiwiRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new KiwiModel());
    }
}

class KiwiModel extends software.bernie.geckolib.model.GeoModel<KiwiEntity> {
    @Override
//? if <=1.21.1 {
    public Identifier getModelResource(KiwiEntity object) {
        return Identifier.of("newzealandnatives", "geo/kiwi.geo.json");
//?} else {
    /*public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.of("newzealandnatives", "geo/kiwi");
*///?}
    }

    @Override
//? if <=1.21.1 {
    public Identifier getTextureResource(KiwiEntity object) {
//?} else {
    /*public Identifier getTextureResource(GeoRenderState renderState) {
*///?}
        return Identifier.of("newzealandnatives", "textures/entity/kiwi/kiwi.png");
    }

    @Override
    public Identifier getAnimationResource(KiwiEntity animatable) {
//? if <=1.21.1 {
        return Identifier.of("newzealandnatives", "animations/kiwi.animation.json");
//?} else {
        /*return Identifier.of("newzealandnatives", "kiwi");
*///?}
    }
}
