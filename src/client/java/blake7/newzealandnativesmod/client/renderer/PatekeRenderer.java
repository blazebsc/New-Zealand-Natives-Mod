package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class PatekeRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
    public PatekeRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new PatekeModel());
    }
}

class PatekeModel extends com.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "geo/pateke");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "textures/entity/pateke/pateke.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "pateke.animation");
    }
}
