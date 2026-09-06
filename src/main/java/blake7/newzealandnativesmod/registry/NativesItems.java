package blake7.newzealandnativesmod.registry;

import blake7.newzealandnativesmod.entity.KiwiEntity;
import blake7.newzealandnativesmod.entity.KatipoEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public final class NativesItems {
    private NativesItems() {}

    private static final List<Item> REGISTERED = new ArrayList<>();
    private static final Map<String, Item> SPAWN_EGGS = new LinkedHashMap<>();

    public static final Item HUHU_GRUB = register("huhu_grub", props -> new Item(props.food(
            new FoodProperties.Builder()
                    .nutrition(3).saturationModifier(0.6f).build())));

    public static final Item KIWI_SPAWN_EGG = register("kiwi_spawn_egg",
            props -> new SpawnEggItem(props.spawnEgg(KiwiEntity.TYPE)));
    public static final Item KATIPO_SPAWN_EGG = register("katipo_spawn_egg",
            props -> new SpawnEggItem(props.spawnEgg(KatipoEntity.TYPE)));

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
            EntityType<? extends Mob> type = (EntityType<? extends Mob>) all.get(i);
            Item egg = register(id + "_spawn_egg",
                    props -> new SpawnEggItem(props.spawnEgg(type)));
            SPAWN_EGGS.put(id, egg);
        }
    }

    public static Map<String, Item> spawnEggs() { return Collections.unmodifiableMap(SPAWN_EGGS); }
    public static List<Item> all() { return Collections.unmodifiableList(REGISTERED); }
    public static int size() { return REGISTERED.size(); }

    private static Item register(String id, Function<Item.Properties, Item> factory) {
        Identifier identifier = Identifier.fromNamespaceAndPath("newzealandnatives", id);
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, identifier);
        Item item = factory.apply(new Item.Properties().setId(key));
        Item registered = Registry.register(BuiltInRegistries.ITEM, identifier, item);
        REGISTERED.add(registered);
        return registered;
    }
}
