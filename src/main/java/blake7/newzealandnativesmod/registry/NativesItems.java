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
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class NativesItems {
    private NativesItems() {}

    private static final List<Item> REGISTERED = new ArrayList<>();
    private static final Map<String, Item> SPAWN_EGGS = new LinkedHashMap<>();

    public static final Item HUHU_GRUB = register("huhu_grub", new Item(new Item.Settings().food(
            new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(0.6f).build())));

    public static final Item KIWI_SPAWN_EGG = register("kiwi_spawn_egg",
            new SpawnEggItem(new Item.Settings().spawnEgg(KiwiEntity.TYPE)));
    public static final Item KATIPO_SPAWN_EGG = register("katipo_spawn_egg",
            new SpawnEggItem(new Item.Settings().spawnEgg(KatipoEntity.TYPE)));

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
            EntityType<? extends MobEntity> type = (EntityType<? extends MobEntity>) all.get(i);
            Item egg = register(id + "_spawn_egg",
                    new SpawnEggItem(new Item.Settings().spawnEgg(type)));
            SPAWN_EGGS.put(id, egg);
        }
    }

    public static Map<String, Item> spawnEggs() { return Collections.unmodifiableMap(SPAWN_EGGS); }
    public static List<Item> all() { return Collections.unmodifiableList(REGISTERED); }
    public static int size() { return REGISTERED.size(); }

    private static Item register(String id, Item item) {
        Item registered = Registry.register(Registries.ITEM, Identifier.of("newzealandnatives", id), item);
        REGISTERED.add(registered);
        return registered;
    }
}
