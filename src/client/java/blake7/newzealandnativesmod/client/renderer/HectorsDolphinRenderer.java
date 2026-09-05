package blake7.newzealandnativesmod.client.renderer;

import net.minecraft.client.render.entity.DolphinEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.util.Identifier;

public class HectorsDolphinRenderer extends DolphinEntityRenderer {
    private static final Identifier TEXTURE = Identifier.of("newzealandnatives", "textures/entity/hectors_dolphin/hectors_dolphin.png");

    public HectorsDolphinRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(DolphinEntity entity) {
        return TEXTURE;
    }
}
