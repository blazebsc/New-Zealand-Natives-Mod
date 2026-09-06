package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class KowhaiRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
    public KowhaiRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new KowhaiModel());
    }
}

class KowhaiModel extends com.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "geo/kowhai");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "textures/entity/flora/kowhai.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "flora.animation");
    }
}
