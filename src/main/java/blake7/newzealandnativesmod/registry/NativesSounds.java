package blake7.newzealandnativesmod.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public final class NativesSounds {
    private NativesSounds() {}

    private static final Map<String, SoundEvent> EVENTS = new HashMap<>();

    public static void register() {
        register("albatross.say");
        register("bat.say");
        register("falcon.say");
        register("fantail.say");
        register("haasts_eagle.say");
        register("hoiho.say");
        register("huia.say");
        register("humpback_whale.say");
        register("kakapo.say");
        register("kea.say");
        register("kereru.say");
        register("kina.say");
        register("king_shag.say");
        register("kiwi.say");
        register("kokako.say");
        register("korora.say");
        register("kotare.say");
        register("kotuku.say");
        register("kunekune.say");
        register("moa.say");
        register("pateke.say");
        register("pukeko.say");
        register("ruru.say");
        register("saddleback.say");
        register("sea_lion.say");
        register("sea_lion.hurt");
        register("takahe.say");
        register("takahe.hurt");
        register("tawaki.say");
        register("tuatara.say");
        register("tui.say");
        register("wasp.say");
        register("weka.say");
        register("whio.say");
    }

    //? if fabric {
    private static void register(String path) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("newzealandnatives", path);
        EVENTS.put(path, Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id)));
    }
    //?}
    //? if neoforge {
    /*private static void register(String path) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("newzealandnatives", path);
        EVENTS.put(path, SoundEvent.createVariableRangeEvent(id));
    }
    *///?}

    public static SoundEvent get(String species, String kind) {
        return EVENTS.get(species + "." + kind);
    }

    public static java.util.Set<String> paths() {
        return java.util.Collections.unmodifiableSet(EVENTS.keySet());
    }

    public static SoundEvent byPath(String path) {
        return EVENTS.get(path);
    }
}
