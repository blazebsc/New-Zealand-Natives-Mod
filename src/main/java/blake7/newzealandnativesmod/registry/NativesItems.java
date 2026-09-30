package blake7.newzealandnativesmod.registry;

import blake7.newzealandnativesmod.entity.KiwiEntity;
import blake7.newzealandnativesmod.entity.KatipoEntity;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
//? if >1.21.1 {
/*import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
*///?}
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
//? if >1.21.1 {
/*import java.util.function.Function;
*///?}

public final class NativesItems {
    private NativesItems() {}

    private static final List<Item> REGISTERED = new ArrayList<>();
    private static final Map<String, Item> SPAWN_EGGS = new LinkedHashMap<>();

//? if <=1.21.1 {
    public static final Item HUHU_GRUB = register("huhu_grub", new Item(new Item.Settings().food(
//?} else {
    /*public static final Item HUHU_GRUB = register("huhu_grub", props -> new Item(props.food(
*///?}
            new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(0.6f).build())));

    public static final Item KIWI_SPAWN_EGG = register("kiwi_spawn_egg",
//? if <=1.21.1 {
            new SpawnEggItem(KiwiEntity.TYPE, 0x5C4033, 0x3B2814, new Item.Settings()));
//?} else {
            /*props -> new SpawnEggItem(props.spawnEgg(KiwiEntity.TYPE)));
*///?}
    public static final Item KATIPO_SPAWN_EGG = register("katipo_spawn_egg",
//? if <=1.21.1 {
            new SpawnEggItem(KatipoEntity.TYPE, 0x1A1A1A, 0xCC0000, new Item.Settings()));
//?} else {
            /*props -> new SpawnEggItem(props.spawnEgg(KatipoEntity.TYPE)));
*///?}

    static {
        SPAWN_EGGS.put("kiwi", KIWI_SPAWN_EGG);
        SPAWN_EGGS.put("katipo", KATIPO_SPAWN_EGG);
    }

    @SuppressWarnings("unchecked")
    public static void register() {
        List<EntityType<?>> all = NativesEntities.all();
        NativesEntities.Entry[] species = NativesEntities.SPECIES;
        for (int i = 0; i < species.length && i < all.size(); i++) {
            NativesEntities.Entry e = species[i];
            String id = e.shortId();
            if (SPAWN_EGGS.containsKey(id)) continue;
//? if <=1.21.1 {
            int[] colors = EGG_COLORS.getOrDefault(id, new int[]{0x808080, 0x404040});
//?}
            EntityType<? extends MobEntity> type = (EntityType<? extends MobEntity>) all.get(i);
            Item egg = register(id + "_spawn_egg",
//? if <=1.21.1 {
                    new SpawnEggItem(type, colors[0], colors[1], new Item.Settings()));
//?} else {
                    /*props -> new SpawnEggItem(props.spawnEgg(type)));
*///?}
            SPAWN_EGGS.put(id, egg);
        }
    }

    public static Map<String, Item> spawnEggs() { return Collections.unmodifiableMap(SPAWN_EGGS); }
    public static List<Item> all() { return Collections.unmodifiableList(REGISTERED); }
    public static int size() { return REGISTERED.size(); }

//? if <=1.21.1 {
    private static Item register(String id, Item item) {
        Item registered = Registry.register(Registries.ITEM, Identifier.of("newzealandnatives", id), item);
//?} else {
    /*private static Item register(String id, Function<Item.Settings, Item> factory) {
        Identifier identifier = Identifier.of("newzealandnatives", id);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, identifier);
        Item item = factory.apply(new Item.Settings().registryKey(key));
        Item registered = Registry.register(Registries.ITEM, identifier, item);
*///?}
        REGISTERED.add(registered);
        return registered;
    }
//? if <=1.21.1 {

    private static final Map<String, int[]> EGG_COLORS = Map.ofEntries(
            Map.entry("albatross",       new int[]{0xF0F0F0, 0xD0D0D0}),
            Map.entry("basket_fungus",   new int[]{0xC8865A, 0x7A4A28}),
            Map.entry("bat",             new int[]{0x6B4226, 0x3B2218}),
            Map.entry("eel",             new int[]{0x2C3E50, 0x1A2530}),
            Map.entry("falcon",          new int[]{0x8B6B3A, 0x5A4225}),
            Map.entry("fantail",         new int[]{0xA0A0A0, 0x707070}),
            Map.entry("frog",            new int[]{0x6B8E23, 0x4A6018}),
            Map.entry("gecko",           new int[]{0x88AA44, 0x556622}),
            Map.entry("haasts_eagle",    new int[]{0x6B4226, 0xC0A060}),
            Map.entry("harakeke",        new int[]{0x4A7A2A, 0x2A4A18}),
            Map.entry("harvestman",      new int[]{0x8B7355, 0x5A4A35}),
            Map.entry("hectors_dolphin", new int[]{0x7A95A8, 0x4A6580}),
            Map.entry("hoiho",           new int[]{0x2A2A2A, 0xF5D800}),
            Map.entry("huhu",            new int[]{0x6B4226, 0xA0826B}),
            Map.entry("huia",            new int[]{0x1A1A1A, 0xF5D800}),
            Map.entry("humpback_whale",  new int[]{0x4A5A6A, 0x2A3540}),
            Map.entry("hura",            new int[]{0x8B3A2A, 0x4A1A10}),
            Map.entry("kakapo",         new int[]{0x88AA44, 0x445522}),
            Map.entry("kea",             new int[]{0x88AA44, 0xCC5500}),
            Map.entry("kereru",         new int[]{0x2A8A4A, 0xFFFFFF}),
            Map.entry("kina",            new int[]{0x3A2A1A, 0x8B6914}),
            Map.entry("king_shag",       new int[]{0x1A1A2A, 0x5A5A8A}),
            Map.entry("kokako",         new int[]{0x5A6A8A, 0x2A3550}),
            Map.entry("kokopu",         new int[]{0x8B6B3A, 0xD4A550}),
            Map.entry("korora",         new int[]{0x2A2A2A, 0xF0F0F0}),
            Map.entry("kotare",         new int[]{0x4A8AC8, 0xF5D800}),
            Map.entry("kotuku",          new int[]{0xF0F0F0, 0xD0C8A0}),
            Map.entry("koura",           new int[]{0x8B3A2A, 0x4A1A10}),
            Map.entry("kunekune",       new int[]{0xC8865A, 0x8B6B3A}),
            Map.entry("moa",             new int[]{0x8B6B3A, 0x5A4225}),
            Map.entry("monarch",         new int[]{0xFF8C00, 0xFFFFFF}),
            Map.entry("papaka",          new int[]{0x8B3A2A, 0x3A1A10}),
            Map.entry("pateke",         new int[]{0x8B6B3A, 0x3A6A4A}),
            Map.entry("pohutukawa",     new int[]{0xCC0000, 0x4A7A2A}),
            Map.entry("ponga",          new int[]{0x4A7A2A, 0x88AA44}),
            Map.entry("pukeko",         new int[]{0x2A2A8A, 0x000000}),
            Map.entry("puriri",         new int[]{0x4A7A2A, 0x2A4A18}),
            Map.entry("red_admiral",    new int[]{0xCC0000, 0x1A1A1A}),
            Map.entry("ruru",            new int[]{0x5A4A35, 0x3A2A1A}),
            Map.entry("saddleback",     new int[]{0x1A1A1A, 0xCC5500}),
            Map.entry("sea_lion",       new int[]{0x8B7355, 0x5A4A35}),
            Map.entry("skink",           new int[]{0x4A6A2A, 0xC8865A}),
            Map.entry("snail",           new int[]{0x6B4226, 0x3B2218}),
            Map.entry("takahe",         new int[]{0x2A2A8A, 0xCC0000}),
            Map.entry("tamure",         new int[]{0x88AA44, 0x4A7A2A}),
            Map.entry("tawaki",         new int[]{0x1A1A1A, 0xF5D800}),
            Map.entry("tuatara",        new int[]{0x4A6A2A, 0x88AA44}),
            Map.entry("tui",             new int[]{0x1A1A1A, 0xF0F0F0}),
            Map.entry("wasp",            new int[]{0xF5D800, 0x1A1A1A}),
            Map.entry("weevil",         new int[]{0x1A1A1A, 0xC8865A}),
            Map.entry("weka",            new int[]{0x8B6B3A, 0x5A4A35}),
            Map.entry("weta",            new int[]{0x5A6A2A, 0x3A4A18}),
            Map.entry("whio",            new int[]{0x5A8AC8, 0xFFFFFF})
    );
//?}
}
