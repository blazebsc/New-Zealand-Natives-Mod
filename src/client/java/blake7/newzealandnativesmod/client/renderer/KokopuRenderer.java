package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KokopuRenderer extends GeoEntityRenderer<NativesEntity> {
    public KokopuRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new KokopuModel());
    }
}

class KokopuModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "geo/kokopu.geo.json");
    }

    @Override
    public Identifier getTextureResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "textures/entity/kokopu/kokopu.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.of("newzealandnatives", "animations/kokopu.animation.json");
    }
}
