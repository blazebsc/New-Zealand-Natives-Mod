package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class KouraRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
    public KouraRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new KouraModel());
    }
}

class KouraModel extends com.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "geo/koura");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "textures/entity/koura/koura.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "koura.animation");
    }
}
