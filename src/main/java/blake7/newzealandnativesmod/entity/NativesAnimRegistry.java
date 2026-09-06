package blake7.newzealandnativesmod.entity;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.entity.EntityType;

public final class NativesAnimRegistry {
    private NativesAnimRegistry() {}

    public record AnimSet(String walk, String still, String fly, String swim) {}

    private static final Map<String, AnimSet> ANIMATIONS = new HashMap<>();
    private static final Map<String, String> EXTRA = new HashMap<>();
    private static final Map<String, NativesEntity.MovementType> MOVEMENT = new HashMap<>();
    private static final Map<EntityType<?>, String> TYPE_TO_ID = new HashMap<>();

    // Bedrock animate rules: fly iff airborne, swim iff in water, walk iff moving on ground,
    // still-pose iff listed, otherwise the model holds its bind pose. Null = no clip for that state.
    static {
        anim("albatross", null, "animation.albatross.bob", "animation.albatross.fly", null);
        anim("bat", "animation.bat.walk", "animation.bat.rest", "animation.bat.fly", null);
        anim("eel", null, "animation.eel.swim", null, "animation.eel.swim");
        anim("falcon", "animation.falcon.walk", null, "animation.falcon.fly", null);
        anim("fantail", "animation.fantail.walk", null, "animation.fantail.fly", null);
        anim("frog", "animation.frog.hop", "animation.frog.hop", null, null);
        anim("gecko", "animation.skink.walk", null, null, null);
        anim("haasts_eagle", "animation.falcon.walk", null, "animation.falcon.fly", null);
        anim("harvestman", "animation.harvestman.walk", null, null, null);
        anim("hoiho", "animation.hoiho.walk", null, null, "animation.hoiho.swim");
        anim("huhu", "animation.huhu_beetle.walk", null, "animation.huhu_beetle.fly", null);
        anim("huia", "animation.huia.walk", null, "animation.huia.fly", null);
        anim("humpback_whale", null, "animation.humpback_whale.swim", null, "animation.humpback_whale.swim");
        anim("hura", "animation.hura.walk", null, null, null);
        anim("kakapo", "animation.kakapo.walk", null, null, null);
        anim("kea", "animation.kea.walk", null, "animation.kea.fly", null);
        anim("kereru", "animation.kereru.walk", null, "animation.kereru.fly", null);
        anim("kina", null, "animation.korora.swim", null, "animation.korora.swim");
        anim("king_shag", "animation.king_shag.walk", null, "animation.king_shag.fly", null);
        anim("kokako", "animation.huia.walk", null, "animation.huia.fly", null);
        anim("kokopu", null, "animation.kokopu.flop", null, "animation.kokopu.swim");
        anim("korora", "animation.korora.walk", null, null, "animation.korora.swim");
        anim("kotare", "animation.kotare.walk", null, "animation.kotare.fly", null);
        anim("kotuku", "animation.kotuku.walk", null, "animation.kotuku.fly", null);
        anim("koura", "animation.koura.walk", "animation.koura.nip", null, null);
        anim("kunekune", "animation.kunekune.walk", null, null, null);
        anim("moa", "animation.moa.walk", null, null, null);
        anim("monarch", "animation.monarch.flying", "animation.monarch.flying", "animation.monarch.flying", null);
        anim("papaka", null, "animation.papaka.crab", null, "animation.papaka.swim");
        anim("pateke", "animation.pateke.walk", null, "animation.pateke.fly", "animation.pateke.swim");
        anim("pukeko", "animation.pukeko.walk", null, "animation.pukeko.fly", null);
        anim("puriri", "animation.puriri.tree", "animation.puriri.tree", "animation.puriri.tree", null);
        anim("red_admiral", "animation.monarch.flying", "animation.monarch.flying", "animation.monarch.flying", null);
        anim("ruru", "animation.ruru.walk", null, "animation.ruru.fly", null);
        anim("saddleback", "animation.huia.walk", null, "animation.huia.fly", null);
        anim("basket_fungus", null, "animation.flora.idle", null, null);
        anim("harakeke", null, "animation.flora.idle", null, null);
        anim("kowhai", null, "animation.flora.idle", null, null);
        anim("pohutukawa", null, "animation.flora.idle", null, null);
        anim("ponga", null, "animation.flora.idle", null, null);
        anim("sea_lion", "animation.sea_lion.walk", null, null, "animation.sea_lion.swim");
        anim("skink", "animation.skink.walk", null, null, null);
        anim("snail", "animation.hura.walk", null, null, null);
        anim("takahe", "animation.takahe.walk", null, null, null);
        anim("tamure", null, "animation.tamure.swim", null, "animation.tamure.swim");
        anim("tawaki", "animation.tawaki.walk", null, null, "animation.tawaki.swim");
        anim("tuatara", "animation.tuatara.walk", null, null, null);
        anim("tui", "animation.tui.walk", null, "animation.tui.fly", null);
        anim("wasp", "animation.wasp.walk", null, "animation.wasp.fly", null);
        anim("weevil", "animation.weevil.walk", null, null, null);
        anim("weka", "animation.takahe.walk", null, null, null);
        anim("weta", "animation.weta.walk", null, null, null);
        anim("whio", "animation.whio.walk", null, "animation.whio.fly", "animation.whio.paddle");
        anim("kiwi", "animation.kiwi.walk", "animation.kiwi.idle", null, null);

        EXTRA.put("fantail", "animation.fantail.tail");
        EXTRA.put("bat", "animation.bat.look_at_target");
        EXTRA.put("skink", "animation.skink.look_at_target");
        EXTRA.put("gecko", "animation.skink.look_at_target");
        EXTRA.put("tuatara", "animation.tuatara.look_at_target");
        EXTRA.put("weta", "animation.weta.look_at_target");
        EXTRA.put("hoiho", "animation.hoiho.look_at_target");
        EXTRA.put("tawaki", "animation.tawaki.look_at_target");
        EXTRA.put("sea_lion", "animation.sea_lion.look_at_target");
        EXTRA.put("kotare", "animation.kotare.look_at_target");

        MOVEMENT.put("eel", NativesEntity.MovementType.WATER);
        MOVEMENT.put("hectors_dolphin", NativesEntity.MovementType.WATER);
        MOVEMENT.put("humpback_whale", NativesEntity.MovementType.WATER);
        MOVEMENT.put("kina", NativesEntity.MovementType.WATER);
        MOVEMENT.put("kokopu", NativesEntity.MovementType.WATER);
        MOVEMENT.put("koura", NativesEntity.MovementType.WATER);
        MOVEMENT.put("tamure", NativesEntity.MovementType.WATER);

        MOVEMENT.put("albatross", NativesEntity.MovementType.FLY);
        MOVEMENT.put("falcon", NativesEntity.MovementType.FLY);
        MOVEMENT.put("fantail", NativesEntity.MovementType.FLY);
        MOVEMENT.put("haasts_eagle", NativesEntity.MovementType.FLY);
        MOVEMENT.put("huia", NativesEntity.MovementType.FLY);
        MOVEMENT.put("kea", NativesEntity.MovementType.FLY);
        MOVEMENT.put("kereru", NativesEntity.MovementType.FLY);
        MOVEMENT.put("king_shag", NativesEntity.MovementType.FLY);
        MOVEMENT.put("kokako", NativesEntity.MovementType.FLY);
        MOVEMENT.put("kotare", NativesEntity.MovementType.FLY);
        MOVEMENT.put("kotuku", NativesEntity.MovementType.FLY);
        MOVEMENT.put("monarch", NativesEntity.MovementType.FLY);
        MOVEMENT.put("puriri", NativesEntity.MovementType.FLY);
        MOVEMENT.put("red_admiral", NativesEntity.MovementType.FLY);
        MOVEMENT.put("ruru", NativesEntity.MovementType.FLY);
        MOVEMENT.put("saddleback", NativesEntity.MovementType.FLY);
        MOVEMENT.put("tui", NativesEntity.MovementType.FLY);
        MOVEMENT.put("wasp", NativesEntity.MovementType.FLY);
        MOVEMENT.put("bat", NativesEntity.MovementType.FLY);

        MOVEMENT.put("korora", NativesEntity.MovementType.AMPHIBIOUS);
        MOVEMENT.put("sea_lion", NativesEntity.MovementType.AMPHIBIOUS);
        MOVEMENT.put("hoiho", NativesEntity.MovementType.AMPHIBIOUS);
        MOVEMENT.put("papaka", NativesEntity.MovementType.AMPHIBIOUS);
        MOVEMENT.put("tawaki", NativesEntity.MovementType.AMPHIBIOUS);
        MOVEMENT.put("whio", NativesEntity.MovementType.AMPHIBIOUS);

        MOVEMENT.put("pateke", NativesEntity.MovementType.AMPHIBIOUS);
        MOVEMENT.put("pukeko", NativesEntity.MovementType.AMPHIBIOUS);

        MOVEMENT.put("huhu", NativesEntity.MovementType.FLY);
    }

    public static void registerType(EntityType<?> type, String id) {
        TYPE_TO_ID.put(type, id);
    }

    public static String getId(EntityType<?> type) {
        return TYPE_TO_ID.getOrDefault(type, "kiwi");
    }

    private static void anim(String id, String walk, String still, String fly, String swim) {
        ANIMATIONS.put(id, new AnimSet(walk, still, fly, swim));
    }

    public static AnimSet getAnims(String id) {
        AnimSet set = ANIMATIONS.get(id);
        return set != null ? set : new AnimSet("animation.kiwi.walk", "animation.kiwi.idle", null, null);
    }

    public static String getExtra(String id) {
        return EXTRA.get(id);
    }

    public static NativesEntity.MovementType getMovementType(String id) {
        return MOVEMENT.getOrDefault(id, NativesEntity.MovementType.LAND);
    }
}
