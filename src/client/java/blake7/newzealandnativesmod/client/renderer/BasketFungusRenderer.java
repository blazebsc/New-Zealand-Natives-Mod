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
public class BasketFungusRenderer extends GeoEntityRenderer<NativesEntity> {
//?} else {
/*public class BasketFungusRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
*///?}
    public BasketFungusRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BasketFungusModel());
    }
}

class BasketFungusModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
//? if <=1.21.1 {
    public Identifier getModelResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "geo/basket_fungus.geo.json");
//?} else {
    /*public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.of("newzealandnatives", "geo/basket_fungus");
*///?}
    }

    @Override
//? if <=1.21.1 {
    public Identifier getTextureResource(NativesEntity object) {
//?} else {
    /*public Identifier getTextureResource(GeoRenderState renderState) {
*///?}
        return Identifier.of("newzealandnatives", "textures/entity/flora/basket_fungus.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
//? if <=1.21.1 {
        return Identifier.of("newzealandnatives", "animations/flora.animation.json");
//?} else {
        /*return Identifier.of("newzealandnatives", "flora");
*///?}
    }
}
