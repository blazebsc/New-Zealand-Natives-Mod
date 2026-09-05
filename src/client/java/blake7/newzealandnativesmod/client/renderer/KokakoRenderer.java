package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KokakoRenderer extends GeoEntityRenderer<NativesEntity> {
    public KokakoRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new KokakoModel());
    }
}

class KokakoModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "geo/kokako.geo.json");
    }

    @Override
    public Identifier getTextureResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "textures/entity/kokako/kokako.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.of("newzealandnatives", "animations/huia.animation.json");
    }
}
