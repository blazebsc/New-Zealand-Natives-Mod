package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.state.EntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class AlbatrossRenderer extends GeoEntityRenderer<NativesEntity, EntityRenderState> {
    public AlbatrossRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new AlbatrossModel());
    }
}

class AlbatrossModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.of("newzealandnatives", "geo/albatross");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.of("newzealandnatives", "textures/entity/albatross/albatross.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.of("newzealandnatives", "albatross.animation");
    }
}
