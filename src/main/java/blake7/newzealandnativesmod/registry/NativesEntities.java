package blake7.newzealandnativesmod.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import blake7.newzealandnativesmod.entity.KiwiEntity;
import blake7.newzealandnativesmod.entity.KatipoEntity;
import blake7.newzealandnativesmod.entity.HectorsDolphinEntity;
import blake7.newzealandnativesmod.entity.HaastsEagleEntity;
import blake7.newzealandnativesmod.entity.RuruEntity;
import blake7.newzealandnativesmod.entity.NativesAnimRegistry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class NativesEntities {
    private NativesEntities() {}

    public record Entry(String shortId, String displayName, SpawnGroup spawnGroup) {}

    private static final List<EntityType<?>> REGISTERED = new ArrayList<>();
    public static List<EntityType<?>> all() { return Collections.unmodifiableList(REGISTERED); }
    public static int size() { return REGISTERED.size(); }

    public static void register() {
        for (Entry e : SPECIES) {
            EntityType<?> type;
            if (e.shortId().equals("kiwi")) {
                type = Registry.register(
                        Registries.ENTITY_TYPE,
                        Identifier.of("newzealandnatives", e.shortId()),
                        KiwiEntity.TYPE
                );
            } else if (e.shortId().equals("katipo")) {
                type = Registry.register(
                        Registries.ENTITY_TYPE,
                        Identifier.of("newzealandnatives", e.shortId()),
                        KatipoEntity.TYPE
                );
            } else if (e.shortId().equals("haasts_eagle")) {
                type = Registry.register(
                        Registries.ENTITY_TYPE,
                        Identifier.of("newzealandnatives", e.shortId()),
                        HaastsEagleEntity.TYPE
                );
            } else if (e.shortId().equals("ruru")) {
                type = Registry.register(
                        Registries.ENTITY_TYPE,
                        Identifier.of("newzealandnatives", e.shortId()),
                        RuruEntity.TYPE
                );
            } else if (e.shortId().equals("hectors_dolphin")) {
                type = Registry.register(
                        Registries.ENTITY_TYPE,
                        Identifier.of("newzealandnatives", e.shortId()),
                        HectorsDolphinEntity.TYPE
                );
            } else {
                type = Registry.register(
                        Registries.ENTITY_TYPE,
                        Identifier.of("newzealandnatives", e.shortId()),
                        EntityType.Builder.create(SpeciesEntities.factoryFor(e), e.spawnGroup())
                                .dimensions(SpeciesEntities.sizeFor(e).width(), SpeciesEntities.sizeFor(e).height())
                                .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of("newzealandnatives", e.shortId())))
                );
            }
            REGISTERED.add(type);
            NativesAnimRegistry.registerType(type, e.shortId());
        }
    }

    public static final Entry[] SPECIES = new Entry[] {
            new Entry("albatross",       "Royal Albatross",         SpawnGroup.CREATURE),
            new Entry("basket_fungus",   "Basket Fungus",           SpawnGroup.CREATURE),
            new Entry("bat",             "Short-tailed Bat",        SpawnGroup.CREATURE),
            new Entry("eel",             "Long-finned Eel",         SpawnGroup.WATER_AMBIENT),
            new Entry("falcon",          "Falcon",                  SpawnGroup.CREATURE),
            new Entry("fantail",         "Fantail",                 SpawnGroup.CREATURE),
            new Entry("frog",            "Frog",                    SpawnGroup.CREATURE),
            new Entry("gecko",           "Gecko",                   SpawnGroup.CREATURE),
            new Entry("haasts_eagle",    "Haast's Eagle",           SpawnGroup.CREATURE),
            new Entry("harakeke",        "Harakeke",                SpawnGroup.CREATURE),
            new Entry("harvestman",      "Harvestman",              SpawnGroup.CREATURE),
            new Entry("hectors_dolphin", "Hector's Dolphin",        SpawnGroup.WATER_CREATURE),
            new Entry("hoiho",           "Hoiho",                   SpawnGroup.CREATURE),
            new Entry("huhu",            "Huhu",                    SpawnGroup.CREATURE),
            new Entry("huia",            "Huia",                    SpawnGroup.CREATURE),
            new Entry("humpback_whale",  "Humpback Whale",          SpawnGroup.WATER_CREATURE),
            new Entry("hura",            "Hura",                    SpawnGroup.CREATURE),
            new Entry("kakapo",          "Kakapo",                  SpawnGroup.CREATURE),
            new Entry("katipo",          "Katipo",                  SpawnGroup.MONSTER),
            new Entry("kea",             "Kea",                     SpawnGroup.CREATURE),
            new Entry("kereru",          "Kererū",                  SpawnGroup.CREATURE),
            new Entry("kina",            "Kina",                    SpawnGroup.WATER_CREATURE),
            new Entry("king_shag",       "King Shag",               SpawnGroup.CREATURE),
            new Entry("kiwi",            "Brown Kiwi",              SpawnGroup.CREATURE),
            new Entry("kokako",          "Kōkako",                  SpawnGroup.CREATURE),
            new Entry("kokopu",          "Giant Kōkopu",            SpawnGroup.WATER_AMBIENT),
            new Entry("korora",          "Kororā",                  SpawnGroup.CREATURE),
            new Entry("kotare",          "Kōtare",                  SpawnGroup.CREATURE),
            new Entry("kotuku",          "Kotuku",                  SpawnGroup.CREATURE),
            new Entry("koura",           "Kēkēwai",                SpawnGroup.WATER_CREATURE),
            new Entry("kunekune",        "Kunekune Pig",            SpawnGroup.CREATURE),
            new Entry("moa",             "South Island Moa",        SpawnGroup.CREATURE),
            new Entry("monarch",         "Monarch",                 SpawnGroup.CREATURE),
            new Entry("papaka",          "Pāpaka",                  SpawnGroup.WATER_CREATURE),
            new Entry("pateke",          "Pāteke",                  SpawnGroup.CREATURE),
            new Entry("pohutukawa",      "Pohutukawa",              SpawnGroup.CREATURE),
            new Entry("ponga",           "Ponga",                   SpawnGroup.CREATURE),
            new Entry("pukeko",          "Pukeko",                  SpawnGroup.CREATURE),
            new Entry("puriri",          "Puriri Moth",             SpawnGroup.CREATURE),
            new Entry("red_admiral",     "Red Admiral",             SpawnGroup.CREATURE),
            new Entry("ruru",            "Ruru",                    SpawnGroup.CREATURE),
            new Entry("saddleback",      "Saddleback",              SpawnGroup.CREATURE),
            new Entry("sea_lion",        "Sea Lion",                SpawnGroup.WATER_CREATURE),
            new Entry("skink",           "Skink",                   SpawnGroup.CREATURE),
            new Entry("snail",           "Powelliphanta",           SpawnGroup.CREATURE),
            new Entry("takahe",          "Takahe",                  SpawnGroup.CREATURE),
            new Entry("tamure",          "Tāmure",                  SpawnGroup.WATER_AMBIENT),
            new Entry("tawaki",          "Tawaki",                  SpawnGroup.CREATURE),
            new Entry("tuatara",         "Tuatara",                 SpawnGroup.CREATURE),
            new Entry("tui",             "Tūī",                     SpawnGroup.CREATURE),
            new Entry("wasp",            "Wasp",                    SpawnGroup.CREATURE),
            new Entry("weevil",          "Giraffe Weevil",          SpawnGroup.CREATURE),
            new Entry("weka",            "Weka",                    SpawnGroup.CREATURE),
            new Entry("weta",            "Giant Wētā",              SpawnGroup.CREATURE),
            new Entry("whio",            "Whio",                    SpawnGroup.CREATURE)
    };
}
