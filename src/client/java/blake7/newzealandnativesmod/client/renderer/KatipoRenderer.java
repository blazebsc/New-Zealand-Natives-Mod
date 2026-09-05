package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.KatipoEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KatipoRenderer extends GeoEntityRenderer<KatipoEntity> {
    public KatipoRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new KatipoModel());
    }
}

class KatipoModel extends software.bernie.geckolib.model.GeoModel<KatipoEntity> {
    @Override
    public Identifier getModelResource(KatipoEntity object) {
        return Identifier.of("newzealandnatives", "geo/katipo.geo.json");
    }

    @Override
    public Identifier getTextureResource(KatipoEntity object) {
        return Identifier.of("newzealandnatives", "textures/entity/katipo/katipo.png");
    }

    @Override
    public Identifier getAnimationResource(KatipoEntity animatable) {
        return Identifier.of("newzealandnatives", "animations/katipo.animation.json");
    }
}
