package blake7.newzealandnativesmod.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public final class NativesBlocks {
    private NativesBlocks() {}

    public static final Block ROTTEN_LOG = register("rotten_log", key ->
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.BROWN).registryKey(key)));

    public static void register() {
    }

    private static Block register(String id, Function<RegistryKey<Block>, Block> factory) {
        Identifier identifier = Identifier.of("newzealandnatives", id);
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, identifier);
        Block block = factory.apply(blockKey);
        Block registered = Registry.register(Registries.BLOCK, identifier, block);
        Registry.register(Registries.ITEM, identifier,
                new BlockItem(registered, new Item.Settings().registryKey(itemKey)));
        return registered;
    }
}
