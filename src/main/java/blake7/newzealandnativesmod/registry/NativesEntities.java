package blake7.newzealandnativesmod.registry;

import blake7.newzealandnativesmod.entity.KiwiEntity;
import blake7.newzealandnativesmod.entity.KatipoEntity;
import blake7.newzealandnativesmod.entity.HectorsDolphinEntity;
import blake7.newzealandnativesmod.entity.HaastsEagleEntity;
import blake7.newzealandnativesmod.entity.RuruEntity;
import blake7.newzealandnativesmod.entity.NativesAnimRegistry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class NativesEntities {
    private NativesEntities() {}

    public record Entry(String shortId, String displayName, MobCategory spawnGroup) {}

    private static final List<EntityType<?>> REGISTERED = new ArrayList<>();
    public static List<EntityType<?>> all() { return Collections.unmodifiableList(REGISTERED); }
    public static int size() { return REGISTERED.size(); }

    public static void register() {
        for (Entry e : SPECIES) {
            EntityType<?> type;
            if (e.shortId().equals("kiwi")) {
                type = Registry.register(
                        BuiltInRegistries.ENTITY_TYPE,
                        Identifier.fromNamespaceAndPath("newzealandnatives", e.shortId()),
                        KiwiEntity.TYPE
                );
            } else if (e.shortId().equals("katipo")) {
                type = Registry.register(
                        BuiltInRegistries.ENTITY_TYPE,
                        Identifier.fromNamespaceAndPath("newzealandnatives", e.shortId()),
                        KatipoEntity.TYPE
                );
            } else if (e.shortId().equals("haasts_eagle")) {
                type = Registry.register(
                        BuiltInRegistries.ENTITY_TYPE,
                        Identifier.fromNamespaceAndPath("newzealandnatives", e.shortId()),
                        HaastsEagleEntity.TYPE
                );
            } else if (e.shortId().equals("ruru")) {
                type = Registry.register(
                        BuiltInRegistries.ENTITY_TYPE,
                        Identifier.fromNamespaceAndPath("newzealandnatives", e.shortId()),
                        RuruEntity.TYPE
                );
            } else if (e.shortId().equals("hectors_dolphin")) {
                type = Registry.register(
                        BuiltInRegistries.ENTITY_TYPE,
                        Identifier.fromNamespaceAndPath("newzealandnatives", e.shortId()),
                        HectorsDolphinEntity.TYPE
                );
            } else {
                type = Registry.register(
                        BuiltInRegistries.ENTITY_TYPE,
                        Identifier.fromNamespaceAndPath("newzealandnatives", e.shortId()),
                        EntityType.Builder.of(SpeciesEntities.factoryFor(e), e.spawnGroup())
                                .sized(SpeciesEntities.sizeFor(e).width(), SpeciesEntities.sizeFor(e).height())
                                .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("newzealandnatives", e.shortId())))
                );
            }
            REGISTERED.add(type);
            NativesAnimRegistry.registerType(type, e.shortId());
        }
    }

    public static final Entry[] SPECIES = new Entry[] {
            new Entry("albatross",       "Royal Albatross",         MobCategory.CREATURE),
            new Entry("basket_fungus",   "Basket Fungus",           MobCategory.CREATURE),
            new Entry("bat",             "Short-tailed Bat",        MobCategory.CREATURE),
            new Entry("eel",             "Long-finned Eel",         MobCategory.WATER_AMBIENT),
            new Entry("falcon",          "Falcon",                  MobCategory.CREATURE),
            new Entry("fantail",         "Fantail",                 MobCategory.CREATURE),
            new Entry("frog",            "Frog",                    MobCategory.CREATURE),
            new Entry("gecko",           "Gecko",                   MobCategory.CREATURE),
            new Entry("haasts_eagle",    "Haast's Eagle",           MobCategory.CREATURE),
            new Entry("harakeke",        "Harakeke",                MobCategory.CREATURE),
            new Entry("harvestman",      "Harvestman",              MobCategory.CREATURE),
            new Entry("hectors_dolphin", "Hector's Dolphin",        MobCategory.WATER_CREATURE),
            new Entry("hoiho",           "Hoiho",                   MobCategory.CREATURE),
            new Entry("huhu",            "Huhu",                    MobCategory.CREATURE),
            new Entry("huia",            "Huia",                    MobCategory.CREATURE),
            new Entry("humpback_whale",  "Humpback Whale",          MobCategory.WATER_CREATURE),
            new Entry("hura",            "Hura",                    MobCategory.CREATURE),
            new Entry("kakapo",          "Kakapo",                  MobCategory.CREATURE),
            new Entry("katipo",          "Katipo",                  MobCategory.MONSTER),
            new Entry("kea",             "Kea",                     MobCategory.CREATURE),
            new Entry("kereru",          "Kererū",                  MobCategory.CREATURE),
            new Entry("kina",            "Kina",                    MobCategory.WATER_CREATURE),
            new Entry("king_shag",       "King Shag",               MobCategory.CREATURE),
            new Entry("kiwi",            "Brown Kiwi",              MobCategory.CREATURE),
            new Entry("kokako",          "Kōkako",                  MobCategory.CREATURE),
            new Entry("kokopu",          "Giant Kōkopu",            MobCategory.WATER_AMBIENT),
            new Entry("korora",          "Kororā",                  MobCategory.CREATURE),
            new Entry("kotare",          "Kōtare",                  MobCategory.CREATURE),
            new Entry("kotuku",          "Kotuku",                  MobCategory.CREATURE),
            new Entry("koura",           "Kēkēwai",                MobCategory.WATER_CREATURE),
            new Entry("kunekune",        "Kunekune Pig",            MobCategory.CREATURE),
            new Entry("moa",             "South Island Moa",        MobCategory.CREATURE),
            new Entry("monarch",         "Monarch",                 MobCategory.CREATURE),
            new Entry("papaka",          "Pāpaka",                  MobCategory.WATER_CREATURE),
            new Entry("pateke",          "Pāteke",                  MobCategory.CREATURE),
            new Entry("pohutukawa",      "Pohutukawa",              MobCategory.CREATURE),
            new Entry("ponga",           "Ponga",                   MobCategory.CREATURE),
            new Entry("pukeko",          "Pukeko",                  MobCategory.CREATURE),
            new Entry("puriri",          "Puriri Moth",             MobCategory.CREATURE),
            new Entry("red_admiral",     "Red Admiral",             MobCategory.CREATURE),
            new Entry("ruru",            "Ruru",                    MobCategory.CREATURE),
            new Entry("saddleback",      "Saddleback",              MobCategory.CREATURE),
            new Entry("sea_lion",        "Sea Lion",                MobCategory.WATER_CREATURE),
            new Entry("skink",           "Skink",                   MobCategory.CREATURE),
            new Entry("snail",           "Powelliphanta",           MobCategory.CREATURE),
            new Entry("takahe",          "Takahe",                  MobCategory.CREATURE),
            new Entry("tamure",          "Tāmure",                  MobCategory.WATER_AMBIENT),
            new Entry("tawaki",          "Tawaki",                  MobCategory.CREATURE),
            new Entry("tuatara",         "Tuatara",                 MobCategory.CREATURE),
            new Entry("tui",             "Tūī",                     MobCategory.CREATURE),
            new Entry("wasp",            "Wasp",                    MobCategory.CREATURE),
            new Entry("weevil",          "Giraffe Weevil",          MobCategory.CREATURE),
            new Entry("weka",            "Weka",                    MobCategory.CREATURE),
            new Entry("weta",            "Giant Wētā",              MobCategory.CREATURE),
            new Entry("whio",            "Whio",                    MobCategory.CREATURE)
    };
}
