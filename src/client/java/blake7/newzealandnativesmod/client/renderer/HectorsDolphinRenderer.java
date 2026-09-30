package blake7.newzealandnativesmod.client.renderer;

import net.minecraft.client.renderer.entity.DolphinRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
//? if <=1.21.1 {
import net.minecraft.world.entity.animal.Dolphin;
//?} else {
/*import net.minecraft.client.render.entity.state.DolphinEntityRenderState;
*///?}
import net.minecraft.resources.ResourceLocation;

public class HectorsDolphinRenderer extends DolphinRenderer {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("newzealandnatives", "textures/entity/hectors_dolphin/hectors_dolphin.png");

    public HectorsDolphinRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
//? if <=1.21.1 {
    public ResourceLocation getTextureLocation(Dolphin entity) {
//?} else {
    /*public ResourceLocation getTextureLocation(DolphinEntityRenderState state) {
*///?}
        return TEXTURE;
    }
}
