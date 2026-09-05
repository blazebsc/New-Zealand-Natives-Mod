package blake7.newzealandnativesmod.client.renderer;

import blake7.newzealandnativesmod.entity.RuruEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RuruRenderer extends GeoEntityRenderer<RuruEntity> {
    public RuruRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new RuruModel());
    }
}

class RuruModel extends software.bernie.geckolib.model.GeoModel<RuruEntity> {
    @Override
    public Identifier getModelResource(RuruEntity object) {
        return Identifier.of("newzealandnatives", "geo/ruru.geo.json");
    }

    @Override
    public Identifier getTextureResource(RuruEntity object) {
        return Identifier.of("newzealandnatives", "textures/entity/ruru/ruru.png");
    }

    @Override
    public Identifier getAnimationResource(RuruEntity animatable) {
        return Identifier.of("newzealandnatives", "animations/ruru.animation.json");
    }
}
