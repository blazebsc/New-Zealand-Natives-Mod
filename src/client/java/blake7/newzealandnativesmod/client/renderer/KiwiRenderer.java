package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.KiwiEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KiwiRenderer extends GeoEntityRenderer<KiwiEntity> {
    public KiwiRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new KiwiModel());
    }
}

class KiwiModel extends software.bernie.geckolib.model.GeoModel<KiwiEntity> {
    @Override
    public Identifier getModelResource(KiwiEntity object) {
        return Identifier.of("newzealandnatives", "geo/kiwi.geo.json");
    }

    @Override
    public Identifier getTextureResource(KiwiEntity object) {
        return Identifier.of("newzealandnatives", "textures/entity/kiwi/kiwi.png");
    }

    @Override
    public Identifier getAnimationResource(KiwiEntity animatable) {
        return Identifier.of("newzealandnatives", "animations/kiwi.animation.json");
    }
}