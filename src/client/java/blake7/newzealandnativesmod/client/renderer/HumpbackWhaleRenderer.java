package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HumpbackWhaleRenderer extends GeoEntityRenderer<NativesEntity> {
    public HumpbackWhaleRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new HumpbackWhaleModel());
    }
}

class HumpbackWhaleModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "geo/humpback_whale.geo.json");
    }

    @Override
    public Identifier getTextureResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "textures/entity/humpback_whale/humpback_whale.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.of("newzealandnatives", "animations/humpback_whale.animation.json");
    }
}
