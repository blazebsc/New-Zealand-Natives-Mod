package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.KiwiEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.state.EntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class KiwiRenderer extends GeoEntityRenderer<KiwiEntity, EntityRenderState> {
    public KiwiRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new KiwiModel());
    }
}

class KiwiModel extends software.bernie.geckolib.model.GeoModel<KiwiEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.of("newzealandnatives", "geo/kiwi");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.of("newzealandnatives", "textures/entity/kiwi/kiwi.png");
    }

    @Override
    public Identifier getAnimationResource(KiwiEntity animatable) {
        return Identifier.of("newzealandnatives", "kiwi");
    }
}