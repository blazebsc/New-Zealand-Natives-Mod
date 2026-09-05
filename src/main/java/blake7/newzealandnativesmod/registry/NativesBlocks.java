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
import net.minecraft.util.Identifier;

public final class NativesBlocks {
    private NativesBlocks() {}

    public static final Block ROTTEN_LOG = register("rotten_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.BROWN)));

    public static void register() {
    }

    private static Block register(String id, Block block) {
        Block registered = Registry.register(Registries.BLOCK, Identifier.of("newzealandnatives", id), block);
        Registry.register(Registries.ITEM, Identifier.of("newzealandnatives", id),
                new BlockItem(registered, new Item.Settings()));
        return registered;
    }
}
