package blake7.newzealandnativesmod.registry;

import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public final class NativesBlocks {
    private NativesBlocks() {}

    public static final Block ROTTEN_LOG = register("rotten_log", key ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.COLOR_BROWN).setId(key)));

    public static void register() {
    }

    private static Block register(String id, Function<ResourceKey<Block>, Block> factory) {
        Identifier identifier = Identifier.fromNamespaceAndPath("newzealandnatives", id);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, identifier);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, identifier);
        Block block = factory.apply(blockKey);
        Block registered = Registry.register(BuiltInRegistries.BLOCK, identifier, block);
        Registry.register(BuiltInRegistries.ITEM, identifier,
                new BlockItem(registered, new Item.Properties().setId(itemKey)));
        return registered;
    }
}
