package blake7.newzealandnativesmod;

//? if <1.21 {
import net.minecraft.resources.ResourceLocation;
//?} else {
/*import net.minecraft.resources.ResourceLocation;
*///?}

// Central id factory: 1.20.x has no fromNamespaceAndPath (its public
// (String,String) ctor went private on every 1.21+ ResourceLocation), so the
// construction is version-gated here once instead of at 300+ call sites.
public final class NativesId {
    private NativesId() {}

    public static final String NAMESPACE = "newzealandnatives";

//? if <1.21 {
    public static ResourceLocation of(String path) {
        return new ResourceLocation(NAMESPACE, path);
    }
//?} else {
    /*public static ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(NAMESPACE, path);
    }
*///?}
}
