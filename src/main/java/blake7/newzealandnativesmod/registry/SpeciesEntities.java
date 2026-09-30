package blake7.newzealandnativesmod.registry;

import net.minecraft.entity.EntityType;
import blake7.newzealandnativesmod.entity.NativesEntity;

public final class SpeciesEntities {
    private SpeciesEntities() {}

    static EntityType.EntityFactory factoryFor(NativesEntities.Entry e) {
        // ponytail: was a 120-line if/else returning this same lambda in every live branch.
        // Special species (kiwi, katipo, eagle, ruru, dolphin) bypass this via NativesEntities.
        return (type, world) -> new NativesEntity(type, world);
    }

    // Bedrock minecraft:health / minecraft:movement values; missing stats default to 6 HP / 0.1.
    // ponytail: one table, not 50 entity classes; split out a class when a species needs real AI.
    public static double[] statsFor(NativesEntities.Entry e) {
        return switch (e.shortId()) {
            case "albatross" -> new double[]{10, 0.1};
            case "bat" -> new double[]{6, 0.4};
            case "eel" -> new double[]{6, 0.12};
            case "falcon", "fantail", "huia" -> new double[]{6, 0.1};
            case "frog" -> new double[]{10, 0.1};
            case "haasts_eagle" -> new double[]{20, 0.3};
            case "harvestman" -> new double[]{10, 0.05};
            case "hectors_dolphin" -> new double[]{10, 0.1};
            case "hoiho" -> new double[]{30, 0.1};
            case "humpback_whale" -> new double[]{10, 0.08};
            case "hura" -> new double[]{10, 0.1};
            case "kakapo", "kea" -> new double[]{6, 0.25};
            case "kereru", "kokako", "kotare", "kotuku", "ruru", "saddleback", "tui", "pukeko" -> new double[]{6, 0.4};
            case "kina" -> new double[]{10, 0.01};
            case "king_shag" -> new double[]{10, 0.3};
            case "kiwi" -> new double[]{4, 0.25};
            case "katipo" -> new double[]{4, 0.1};
            case "kokopu", "korora", "koura", "kunekune", "papaka", "pateke" -> new double[]{10, 0.1};
            case "moa" -> new double[]{10, 0.25};
            case "monarch", "puriri", "red_admiral" -> new double[]{4, 0.05};
            case "sea_lion", "tawaki" -> new double[]{30, 0.1};
            case "snail" -> new double[]{2, 0.05};
            case "takahe", "weka", "whio" -> new double[]{4, 0.25};
            case "tamure" -> new double[]{10, 0.1};
            case "wasp", "weevil" -> new double[]{10, 0.05};
            default -> new double[]{6, 0.1};
        };
    }

    // Mojang mappings keep EntityDimensions fields private, so this returns
    // {width, height} instead — callers index [0]/[1].
    static float[] sizeFor(NativesEntities.Entry e) {
        String id = e.shortId();
        if (id.equals("whio")) {
            return new float[]{1.1f, 0.6f};
        } else if (id.equals("weta")) {
            return new float[]{1.1f, 0.5f};
        } else if (id.equals("weka")) {
            return new float[]{1.5f, 1.0f};
        } else if (id.equals("weevil")) {
            return new float[]{1.0f, 0.3f};
        } else if (id.equals("wasp")) {
            return new float[]{2.2f, 0.7f};
        } else if (id.equals("tui")) {
            return new float[]{0.9f, 0.9f};
        } else if (id.equals("tuatara")) {
            return new float[]{2.4f, 1.5f};
        } else if (id.equals("tawaki")) {
            return new float[]{0.4f, 0.6f};
        } else if (id.equals("tamure")) {
            return new float[]{3.3f, 1.3f};
        } else if (id.equals("takahe")) {
            return new float[]{1.1f, 0.8f};
        } else if (id.equals("snail")) {
            return new float[]{1.0f, 0.5f};
        } else if (id.equals("skink")) {
            return new float[]{2.2f, 0.3f};
        } else if (id.equals("sea_lion")) {
            return new float[]{1.8f, 0.4f};
        } else if (id.equals("saddleback")) {
            return new float[]{1.1f, 0.5f};
        } else if (id.equals("ruru")) {
            return new float[]{0.4f, 0.8f};
        } else if (id.equals("red_admiral")) {
            return new float[]{1.5f, 0.4f};
        } else if (id.equals("puriri")) {
            return new float[]{1.5f, 0.4f};
        } else if (id.equals("pukeko")) {
            return new float[]{1.1f, 1.0f};
        } else if (id.equals("ponga")) {
            return new float[]{1.2f, 1.2f};
        } else if (id.equals("pohutukawa")) {
            return new float[]{2.4f, 2.0f};
        } else if (id.equals("pateke")) {
            return new float[]{0.9f, 0.7f};
        } else if (id.equals("papaka")) {
            return new float[]{1.5f, 0.3f};
        } else if (id.equals("monarch")) {
            return new float[]{1.5f, 0.6f};
        } else if (id.equals("moa")) {
            return new float[]{1.4f, 1.0f};
        } else if (id.equals("kunekune")) {
            return new float[]{1.6f, 0.8f};
        } else if (id.equals("koura")) {
            return new float[]{1.2f, 0.3f};
        } else if (id.equals("kotuku")) {
            return new float[]{1.4f, 1.7f};
        } else if (id.equals("kotare")) {
            return new float[]{0.9f, 1.0f};
        } else if (id.equals("korora")) {
            return new float[]{0.4f, 0.3f};
        } else if (id.equals("kokopu")) {
            return new float[]{1.4f, 0.3f};
        } else if (id.equals("kokako")) {
            return new float[]{1.0f, 0.5f};
        } else if (id.equals("kiwi")) {
            return new float[]{1.2f, 0.7f};
        } else if (id.equals("king_shag")) {
            return new float[]{1.0f, 1.0f};
        } else if (id.equals("kina")) {
            return new float[]{0.8f, 1.2f};
        } else if (id.equals("kereru")) {
            return new float[]{1.1f, 0.4f};
        } else if (id.equals("kea")) {
            return new float[]{3.4f, 0.9f};
        } else if (id.equals("katipo")) {
            return new float[]{2.8f, 0.5f};
        } else if (id.equals("kakapo")) {
            return new float[]{0.4f, 0.6f};
        } else if (id.equals("hura")) {
            return new float[]{3.0f, 0.4f};
        } else if (id.equals("humpback_whale")) {
            return new float[]{3.5f, 0.7f};
        } else if (id.equals("huia")) {
            return new float[]{1.3f, 0.5f};
        } else if (id.equals("huhu")) {
            return new float[]{0.9f, 0.3f};
        } else if (id.equals("hoiho")) {
            return new float[]{0.9f, 1.4f};
        } else if (id.equals("hectors_dolphin")) {
            return new float[]{0.9f, 0.6f};
        } else if (id.equals("harvestman")) {
            return new float[]{0.9f, 0.6f};
        } else if (id.equals("harakeke")) {
            return new float[]{0.4f, 1.3f};
        } else if (id.equals("haasts_eagle")) {
            return new float[]{3.1f, 1.2f};
        } else if (id.equals("gecko")) {
            return new float[]{2.2f, 0.3f};
        } else if (id.equals("frog")) {
            return new float[]{0.8f, 0.3f};
        } else if (id.equals("fantail")) {
            return new float[]{0.8f, 0.4f};
        } else if (id.equals("falcon")) {
            return new float[]{1.7f, 0.7f};
        } else if (id.equals("eel")) {
            return new float[]{2.7f, 0.3f};
        } else if (id.equals("bat")) {
            return new float[]{2.5f, 0.3f};
        } else if (id.equals("basket_fungus")) {
            return new float[]{1.8f, 1.3f};
        } else if (id.equals("albatross")) {
            return new float[]{3.2f, 0.5f};
        }
        // ponytail: every species matched above; unreachable fallback kept as plain default.
        return new float[]{0.6f, 0.7f};
    }
}
