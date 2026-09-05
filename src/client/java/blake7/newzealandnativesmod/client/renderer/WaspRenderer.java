package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WaspRenderer extends GeoEntityRenderer<NativesEntity> {
    public WaspRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new WaspModel());
    }
}

class WaspModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "geo/wasp.geo.json");
    }

    @Override
    public Identifier getTextureResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "textures/entity/wasp/wasp.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.of("newzealandnatives", "animations/wasp.animation.json");
    }
}
