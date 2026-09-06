package blake7.newzealandnativesmod.client.renderer;

import net.minecraft.client.renderer.entity.DolphinRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.DolphinRenderState;
import net.minecraft.resources.Identifier;

public class HectorsDolphinRenderer extends DolphinRenderer {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("newzealandnatives", "textures/entity/hectors_dolphin/hectors_dolphin");

    public HectorsDolphinRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTextureLocation(DolphinRenderState state) {
        return TEXTURE;
    }
}
