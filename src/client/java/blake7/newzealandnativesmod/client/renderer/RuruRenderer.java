package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.RuruEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.state.EntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class RuruRenderer extends GeoEntityRenderer<RuruEntity, EntityRenderState> {
    public RuruRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new RuruModel());
    }
}

class RuruModel extends software.bernie.geckolib.model.GeoModel<RuruEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.of("newzealandnatives", "geo/ruru");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.of("newzealandnatives", "textures/entity/ruru/ruru.png");
    }

    @Override
    public Identifier getAnimationResource(RuruEntity animatable) {
        return Identifier.of("newzealandnatives", "ruru.animation");
    }
}
