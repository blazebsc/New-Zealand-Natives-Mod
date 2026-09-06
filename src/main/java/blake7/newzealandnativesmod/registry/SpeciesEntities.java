package blake7.newzealandnativesmod.registry;

import blake7.newzealandnativesmod.entity.NativesEntity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;

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

    static EntityDimensions sizeFor(NativesEntities.Entry e) {
        String id = e.shortId();
        if (id.equals("whio")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("weta")) {
            return EntityDimensions.fixed(0.4f, 0.3f);
        } else if (id.equals("weka")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("weevil")) {
            return EntityDimensions.fixed(0.4f, 0.3f);
        } else if (id.equals("wasp")) {
            return EntityDimensions.fixed(0.4f, 0.3f);
        } else if (id.equals("tui")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("tuatara")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("tawaki")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("tamure")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("takahe")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("snail")) {
            return EntityDimensions.fixed(0.4f, 0.3f);
        } else if (id.equals("skink")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("sea_lion")) {
            return EntityDimensions.fixed(1.0f, 1.2f);
        } else if (id.equals("saddleback")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("ruru")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("red_admiral")) {
            return EntityDimensions.fixed(0.4f, 0.3f);
        } else if (id.equals("puriri")) {
            return EntityDimensions.fixed(0.4f, 0.3f);
        } else if (id.equals("pukeko")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("ponga")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("pohutukawa")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("pateke")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("papaka")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("monarch")) {
            return EntityDimensions.fixed(0.4f, 0.3f);
        } else if (id.equals("moa")) {
            return EntityDimensions.fixed(1.0f, 1.2f);
        } else if (id.equals("kunekune")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("koura")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("kotuku")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("kotare")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("korora")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("kokopu")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("kokako")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("kiwi")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("king_shag")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("kina")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("kereru")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("kea")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("katipo")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("kakapo")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("hura")) {
            return EntityDimensions.fixed(0.4f, 0.3f);
        } else if (id.equals("humpback_whale")) {
            return EntityDimensions.fixed(1.0f, 1.2f);
        } else if (id.equals("huia")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("huhu")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("hoiho")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("hectors_dolphin")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("harvestman")) {
            return EntityDimensions.fixed(0.4f, 0.3f);
        } else if (id.equals("harakeke")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("haasts_eagle")) {
            return EntityDimensions.fixed(0.7f, 0.8f);
        } else if (id.equals("gecko")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("frog")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("fantail")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("falcon")) {
            return EntityDimensions.fixed(0.7f, 0.8f);
        } else if (id.equals("eel")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("bat")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("basket_fungus")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        } else if (id.equals("albatross")) {
            return EntityDimensions.fixed(0.6f, 0.7f);
        }
        // ponytail: every species matched above; unreachable fallback kept as plain default.
        return EntityDimensions.fixed(0.6f, 0.7f);
    }
}
