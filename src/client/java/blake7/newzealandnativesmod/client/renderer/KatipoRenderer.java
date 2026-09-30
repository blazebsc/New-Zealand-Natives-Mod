package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.KatipoEntity;
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
public class KatipoRenderer extends GeoEntityRenderer<KatipoEntity> {
//?} else {
/*public class KatipoRenderer extends GeoEntityRenderer<KatipoEntity, EntityRenderState> {
*///?}
    public KatipoRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new KatipoModel());
    }
}

class KatipoModel extends software.bernie.geckolib.model.GeoModel<KatipoEntity> {
    @Override
//? if <=1.21.1 {
    public Identifier getModelResource(KatipoEntity object) {
        return Identifier.of("newzealandnatives", "geo/katipo.geo.json");
//?} else {
    /*public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.of("newzealandnatives", "geo/katipo");
*///?}
    }

    @Override
//? if <=1.21.1 {
    public Identifier getTextureResource(KatipoEntity object) {
//?} else {
    /*public Identifier getTextureResource(GeoRenderState renderState) {
*///?}
        return Identifier.of("newzealandnatives", "textures/entity/katipo/katipo.png");
    }

    @Override
    public Identifier getAnimationResource(KatipoEntity animatable) {
//? if <=1.21.1 {
        return Identifier.of("newzealandnatives", "animations/katipo.animation.json");
//?} else {
        /*return Identifier.of("newzealandnatives", "katipo");
*///?}
    }
}
