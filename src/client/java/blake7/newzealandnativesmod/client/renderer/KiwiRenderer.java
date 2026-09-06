package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.KiwiEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class KiwiRenderer extends GeoEntityRenderer<KiwiEntity, EntityRenderState> {
    public KiwiRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new KiwiModel());
    }
}

class KiwiModel extends com.geckolib.model.GeoModel<KiwiEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "geo/kiwi");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "textures/entity/kiwi/kiwi.png");
    }

    @Override
    public Identifier getAnimationResource(KiwiEntity animatable) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "kiwi.animation");
    }
}