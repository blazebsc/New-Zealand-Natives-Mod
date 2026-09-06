package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class WekaRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
    public WekaRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new WekaModel());
    }
}

class WekaModel extends com.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "geo/weka");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "textures/entity/weka/weka.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "takahe.animation");
    }
}
