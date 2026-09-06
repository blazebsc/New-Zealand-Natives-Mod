package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.state.EntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class FrogRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
    public FrogRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new FrogModel());
    }
}

class FrogModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.of("newzealandnatives", "geo/frog");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.of("newzealandnatives", "textures/entity/frog/archeys_frog.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.of("newzealandnatives", "frog.animation");
    }
}
