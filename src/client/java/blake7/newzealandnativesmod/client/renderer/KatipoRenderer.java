package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.KatipoEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class KatipoRenderer extends GeoEntityRenderer<KatipoEntity, EntityRenderState> {
    public KatipoRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new KatipoModel());
    }
}

class KatipoModel extends com.geckolib.model.GeoModel<KatipoEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "geo/katipo");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "textures/entity/katipo/katipo.png");
    }

    @Override
    public Identifier getAnimationResource(KatipoEntity animatable) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "katipo.animation");
    }
}
