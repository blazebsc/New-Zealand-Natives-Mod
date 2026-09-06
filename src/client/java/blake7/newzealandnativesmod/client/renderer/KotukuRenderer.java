package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class KotukuRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
    public KotukuRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new KotukuModel());
    }
}

class KotukuModel extends com.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "geo/kotuku");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "textures/entity/kotuku/kotuku.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "kotuku.animation");
    }
}
