package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PongaRenderer extends GeoEntityRenderer<NativesEntity> {
    public PongaRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new PongaModel());
    }
}

class PongaModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "geo/ponga.geo.json");
    }

    @Override
    public Identifier getTextureResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "textures/entity/flora/ponga.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.of("newzealandnatives", "animations/flora.animation.json");
    }
}
