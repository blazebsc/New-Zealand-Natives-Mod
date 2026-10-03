package blake7.newzealandnativesmod;

//? if <1.21 {
/*import net.minecraft.util.Identifier;
*///?} else {
import net.minecraft.util.Identifier;
//?}

// Central id factory: 1.20.x has no fromNamespaceAndPath (its public
// (String,String) ctor went private on every 1.21+ ResourceLocation), so the
// construction is version-gated here once instead of at 300+ call sites.
public final class NativesId {
    private NativesId() {}

    public static final String NAMESPACE = "newzealandnatives";

//? if <1.21 {
    /*public static Identifier of(String path) {
        return new Identifier(NAMESPACE, path);
    }
*///?} else {
    public static Identifier of(String path) {
        return Identifier.of(NAMESPACE, path);
    }
//?}
}
