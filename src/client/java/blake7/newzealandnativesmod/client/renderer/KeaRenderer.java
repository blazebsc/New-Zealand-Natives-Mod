package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KeaRenderer extends GeoEntityRenderer<NativesEntity> {
    public KeaRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new KeaModel());
    }
}

class KeaModel extends software.bernie.geckolib.model.GeoModel<NativesEntity> {
    @Override
    public Identifier getModelResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "geo/kea.geo.json");
    }

    @Override
    public Identifier getTextureResource(NativesEntity object) {
        return Identifier.of("newzealandnatives", "textures/entity/kea/kea.png");
    }

    @Override
    public Identifier getAnimationResource(NativesEntity animatable) {
        return Identifier.of("newzealandnatives", "animations/kea.animation.json");
    }
}
