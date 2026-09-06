package blake7.newzealandnativesmod.registry;

import blake7.newzealandnativesmod.block.KowhaiLeaves;
import blake7.newzealandnativesmod.block.KowhaiSapling;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Optional;

public final class NativesBlocks {
    private NativesBlocks() {}

    public static final Block ROTTEN_LOG = register("rotten_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.BROWN)));

    public static final RegistryKey<ConfiguredFeature<?, ?>> KOWHAI_TREE = RegistryKey.of(
            RegistryKeys.CONFIGURED_FEATURE, Identifier.of("newzealandnatives", "kowhai"));
    public static final Block KOWHAI_LOG = register("kowhai_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.OAK_TAN)));
    public static final Block KOWHAI_LEAVES = register("kowhai_leaves",
            new KowhaiLeaves(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block KOWHAI_SAPLING = register("kowhai_sapling",
            new KowhaiSapling(new SaplingGenerator("kowhai", Optional.empty(), Optional.of(KOWHAI_TREE), Optional.empty()),
                    AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    public static void register() {
    }

    private static Block register(String id, Block block) {
        Block registered = Registry.register(Registries.BLOCK, Identifier.of("newzealandnatives", id), block);
        Registry.register(Registries.ITEM, Identifier.of("newzealandnatives", id),
                new BlockItem(registered, new Item.Settings()));
        return registered;
    }
}
