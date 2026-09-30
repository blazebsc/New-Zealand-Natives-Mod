package blake7.newzealandnativesmod.client.renderer;

import net.minecraft.client.render.entity.DolphinEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
//? if <=1.21.1 {
import net.minecraft.entity.passive.DolphinEntity;
//?} else {
/*import net.minecraft.client.render.entity.state.DolphinEntityRenderState;
*///?}
import net.minecraft.util.Identifier;

public class HectorsDolphinRenderer extends DolphinEntityRenderer {
    private static final Identifier TEXTURE = Identifier.of("newzealandnatives", "textures/entity/hectors_dolphin/hectors_dolphin.png");

    public HectorsDolphinRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
//? if <=1.21.1 {
    public Identifier getTexture(DolphinEntity entity) {
//?} else {
    /*public Identifier getTexture(DolphinEntityRenderState state) {
*///?}
        return TEXTURE;
    }
}
