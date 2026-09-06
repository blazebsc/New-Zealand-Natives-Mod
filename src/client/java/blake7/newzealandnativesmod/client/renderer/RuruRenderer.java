package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.RuruEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class RuruRenderer extends GeoEntityRenderer<RuruEntity, EntityRenderState> {
    public RuruRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new RuruModel());
    }
}

class RuruModel extends com.geckolib.model.GeoModel<RuruEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "geo/ruru");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "textures/entity/ruru/ruru");
    }

    @Override
    public Identifier getAnimationResource(RuruEntity animatable) {
        return Identifier.fromNamespaceAndPath("newzealandnatives", "ruru.animation");
    }
}
