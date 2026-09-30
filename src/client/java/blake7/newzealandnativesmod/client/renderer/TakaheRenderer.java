package blake7.newzealandnativesmod.client.renderer;

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
public class TakaheRenderer extends GeoEntityRenderer<NativesEntity> {
//?} else {
/*public class TakaheRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
*///?}
    public TakaheRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new TakaheModel());
    }
}

class TakaheModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
//? if <=1.21.1 {
    public Identifier getModelResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "geo/takahe.geo.json");
//?} else {
    /*public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.of("newzealandnatives", "geo/takahe");
*///?}
    }

    @Override
//? if <=1.21.1 {
    public Identifier getTextureResource(NativesEntity object) {
//?} else {
    /*public Identifier getTextureResource(GeoRenderState renderState) {
*///?}
        return Identifier.of("newzealandnatives", "textures/entity/takahe/takahe.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
//? if <=1.21.1 {
        return Identifier.of("newzealandnatives", "animations/takahe.animation.json");
//?} else {
        /*return Identifier.of("newzealandnatives", "takahe");
*///?}
    }
}
