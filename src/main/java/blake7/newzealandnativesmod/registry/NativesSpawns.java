package blake7.newzealandnativesmod.registry;

import blake7.newzealandnativesmod.NativesId;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
//? if <=1.20.4 {
/*import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
*///?}
//? if >1.20.4 {
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
//?}
import net.minecraft.registry.tag.BiomeTags;
// Yarn's spawn-location naming changed twice (standalone SpawnLocation plus
// SpawnLocationTypes constants in 1.20.6+, nested SpawnRestriction.Location in
// 1.20.x) and the stitcher shields a string rule's target from every other rule,
// so all three eras are hand-gated here rather than rewritten.
//? if >=26.1 {
/*import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacementTypes;
*///?}
//? if <26.1 {
import net.minecraft.entity.SpawnRestriction;
//?}
//? if <26.1 && >1.20.4 {
import net.minecraft.entity.SpawnLocation;
import net.minecraft.entity.SpawnLocationTypes;
//?}
import net.minecraft.registry.RegistryKey;
//? if >=26.1 {
/*import net.minecraft.core.registries.Registries;
*///?}
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.Heightmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public final class NativesSpawns {
    private NativesSpawns() {}

    private static final Set<String> WATER = Set.of(
            "eel", "hectors_dolphin", "humpback_whale", "kina",
            "kokopu", "koura", "papaka", "tamure");
    private static final Set<String> MONSTERS = Set.of("katipo");
    private static final Set<String> PLANTS = Set.of(
            "basket_fungus", "harakeke", "pohutukawa", "ponga");

    public static void register() {
        Map<String, EntityType<?>> byId = new HashMap<>();
        var species = NativesEntities.SPECIES;
        var types = NativesEntities.all();
        for (int i = 0; i < species.length && i < types.size(); i++) {
            byId.put(species[i].shortId(), types.get(i));
        }

        // Generalist land fauna: bedrock biome "animal" ~= anywhere overworld.
        String[] general = {"falcon", "fantail", "frog", "huia", "hura", "kokako",
                "kotare", "kunekune", "pateke", "pukeko", "saddleback", "wasp", "weevil", "whio"};
        for (String id : general) add(byId, id, NativesSpawns::overworld, 10, 2, 4);

        add(byId, "kiwi", NativesSpawns::overworld, 20, 2, 4);
        add(byId, "moa", NativesSpawns::overworld, 20, 2, 3);
        add(byId, "haasts_eagle", NativesSpawns::overworld, 8, 1, 1);
        // ponytail: no Bedrock rule for katipo; spider-like: dark overworld, pairs.
        add(byId, "katipo", NativesSpawns::overworld, 10, 1, 2);
        add(byId, "snail", NativesSpawns::overworld, 8, 1, 1);
        add(byId, "weta", NativesSpawns::overworld, 8, 1, 1);
        add(byId, "huhu", NativesSpawns::forest, 8, 1, 1);
        add(byId, "harvestman", NativesSpawns::forest, 10, 2, 4);

        // Forest birds / flora.
        String[] forest = {"kakapo", "kereru", "puriri", "ruru", "takahe", "tui", "weka",
                "pohutukawa", "ponga", "harakeke", "kea", "basket_fungus",
                "gecko", "skink", "tuatara"};
        for (String id : forest) add(byId, id, NativesSpawns::forest, 20, 2, 3);
        add(byId, "bat", NativesSpawns::forest, 20, 2, 3);
        add(byId, "kotuku", NativesSpawns::wetland, 20, 2, 3);
        add(byId, "monarch", NativesSpawns::plains, 10, 1, 1);
        add(byId, "red_admiral", NativesSpawns::plains, 10, 1, 1);

        // Shore / ocean.
        add(byId, "albatross", NativesSpawns::ocean, 10, 2, 4);
        add(byId, "king_shag", NativesSpawns::shore, 10, 2, 4);
        add(byId, "hoiho", NativesSpawns::shore, 10, 2, 4);
        add(byId, "korora", NativesSpawns::shore, 10, 2, 4);
        add(byId, "tawaki", NativesSpawns::shore, 10, 2, 4);
        add(byId, "sea_lion", NativesSpawns::ocean, 8, 1, 1);

        // Water.
        add(byId, "humpback_whale", NativesSpawns::ocean, 10, 2, 4);
        add(byId, "kina", NativesSpawns::ocean, 10, 2, 4);
        add(byId, "papaka", NativesSpawns::ocean, 10, 2, 4);
        add(byId, "tamure", NativesSpawns::ocean, 10, 2, 4);
        add(byId, "hectors_dolphin", NativesSpawns::ocean, 7, 3, 5);
        add(byId, "eel", NativesSpawns::wetland, 10, 2, 4);
        add(byId, "kokopu", NativesSpawns::wetland, 10, 2, 4);
        add(byId, "koura", NativesSpawns::river, 10, 2, 4);
        // Bedrock brightness filters as light gates (most 7-15, bats 0-7, rest ungated).
        String[] day = {"falcon", "fantail", "frog", "huia", "hura", "kokako", "kotare",
                "kunekune", "pateke", "pukeko", "saddleback", "wasp", "weevil", "whio",
                "kiwi", "moa", "haasts_eagle", "harvestman", "kakapo", "kereru", "puriri",
                "ruru", "takahe", "tui", "weka", "pohutukawa", "ponga", "harakeke",
                "kea", "basket_fungus", "kotuku", "monarch", "red_admiral", "albatross",
                "king_shag", "hoiho", "korora", "tawaki", "eel", "kokopu", "kina", "papaka",
                "tamure", "humpback_whale"};
        for (String id : day) {
            gate(byId, id, true);
        }
        gate(byId, "bat", false);
        gate(byId, "katipo", false);
        // ponytail: grass-only blocks and herd "born" events not enforced.

        // Fallen logs on the forest floor (Bedrock has no worldgen for these; mushroom-patch pattern).
        if (NativesConfig.INSTANCE.fallenLogs) {
            BiomeModifications.addFeature(
                    NativesSpawns::forest,
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                            NativesId.of("rotten_log_patch")));
        }
        // Kowhai trees dot open country, never closed canopy (Bedrock lists plains/savanna first).
        if (NativesConfig.INSTANCE.kowhaiTrees) {
            BiomeModifications.addFeature(
                    NativesSpawns::openCountry,
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                            NativesId.of("kowhai_trees")));
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void gate(Map<String, EntityType<?>> byId, String id, boolean day) {
        EntityType type = byId.get(id);
        if (type == null) return;
        boolean water = type.getSpawnGroup() == SpawnGroup.WATER_AMBIENT
                || type.getSpawnGroup() == SpawnGroup.WATER_CREATURE;
        //? if >=26.1 {
/*SpawnPlacementType loc = water ? SpawnPlacementTypes.IN_WATER : SpawnPlacementTypes.ON_GROUND;
*///?}
//? if <=1.20.4 {
/*SpawnRestriction.Location loc = water ? SpawnRestriction.Location.IN_WATER : SpawnRestriction.Location.ON_GROUND;
*///?}
//? if <26.1 && >1.20.4 {
SpawnLocation loc = water ? SpawnLocationTypes.IN_WATER : SpawnLocationTypes.ON_GROUND;
//?}
        // ponytail: no light gate underwater — sunlight rarely reaches depth, vanilla fish have none.
        if (water && day) {
//? if >=26.1 {
/*SpawnPlacements.register(type, loc, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                    (t, world, reason, pos, random) -> true);
*///?}
//? if <26.1 {
SpawnRestriction.register(type, loc, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                    (t, world, reason, pos, random) -> true);
//?}
            return;
        }
//? if >=26.1 {
/*SpawnPlacements.register(type, loc, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                (t, world, reason, pos, random) -> day
                        ? world.getLightLevel(pos) >= 7
                        : world.getLightLevel(pos) <= 7);
*///?}
//? if <26.1 {
SpawnRestriction.register(type, loc, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                (t, world, reason, pos, random) -> day
                        ? world.getLightLevel(pos) >= 7
                        : world.getLightLevel(pos) <= 7);
//?}
    }

    private static boolean overworld(BiomeSelectionContext ctx) {
        return ctx.hasTag(BiomeTags.IS_OVERWORLD);
    }

    private static boolean forest(BiomeSelectionContext ctx) {
        return ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.IS_JUNGLE);
    }

    private static boolean openCountry(BiomeSelectionContext ctx) {
        return ctx.hasTag(ConventionalBiomeTags.IS_PLAINS)
                || ctx.hasTag(ConventionalBiomeTags.IS_SAVANNA)
                || ctx.hasTag(ConventionalBiomeTags.IS_FLOWER_FOREST);
    }

    private static boolean ocean(BiomeSelectionContext ctx) {
        return ctx.hasTag(BiomeTags.IS_OCEAN) || ctx.hasTag(BiomeTags.IS_DEEP_OCEAN);
    }

    private static boolean river(BiomeSelectionContext ctx) {
        return ctx.hasTag(BiomeTags.IS_RIVER);
    }

    private static boolean wetland(BiomeSelectionContext ctx) {
        return ctx.hasTag(BiomeTags.IS_RIVER) || ctx.hasTag(ConventionalBiomeTags.IS_SWAMP) || ctx.hasTag(BiomeTags.IS_OCEAN);
    }

    private static boolean shore(BiomeSelectionContext ctx) {
        return ctx.hasTag(BiomeTags.IS_BEACH) || ctx.hasTag(BiomeTags.IS_OCEAN) || ctx.hasTag(BiomeTags.IS_RIVER);
    }

    private static boolean plains(BiomeSelectionContext ctx) {
        return ctx.hasTag(ConventionalBiomeTags.IS_PLAINS) || ctx.hasTag(BiomeTags.IS_FOREST);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void add(Map<String, EntityType<?>> byId, String id,
            Predicate<BiomeSelectionContext> selector, int weight, int min, int max) {
        if (WATER.contains(id)) {
            if (!NativesConfig.INSTANCE.spawnWater) return;
        } else if (MONSTERS.contains(id)) {
            if (!NativesConfig.INSTANCE.spawnHostileEntitys) return;
        } else if (PLANTS.contains(id)) {
            if (!NativesConfig.INSTANCE.spawnPlants) return;
        } else {
            if (!NativesConfig.INSTANCE.spawnLand) return;
        }
        EntityType<?> raw = byId.get(id);
        if (raw == null) return;
        SpawnGroup group = null;
        for (var e : NativesEntities.SPECIES) {
            if (e.shortId().equals(id)) {
                group = e.spawnGroup();
                break;
            }
        }
        if (group == null) return;
        int scaled = Math.max(1, (int) Math.round(weight * NativesConfig.INSTANCE.spawnRate));
        BiomeModifications.addSpawn(selector, group, (EntityType) raw, scaled, min, max);
    }
}
