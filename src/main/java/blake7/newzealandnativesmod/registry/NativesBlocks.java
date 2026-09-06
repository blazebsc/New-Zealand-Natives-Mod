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
import java.util.function.Function;

public final class NativesBlocks {
    private NativesBlocks() {}

    public static final Block ROTTEN_LOG = register("rotten_log", key ->
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.BROWN).registryKey(key)));

    public static final RegistryKey<ConfiguredFeature<?, ?>> KOWHAI_TREE = RegistryKey.of(
            RegistryKeys.CONFIGURED_FEATURE, Identifier.of("newzealandnatives", "kowhai"));
    public static final Block KOWHAI_LOG = register("kowhai_log", key ->
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.OAK_TAN).registryKey(key)));
    public static final Block KOWHAI_LEAVES = register("kowhai_leaves", key ->
            new KowhaiLeaves(0.0f, AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).registryKey(key)));
    public static final Block KOWHAI_SAPLING = register("kowhai_sapling", key ->
            new KowhaiSapling(new SaplingGenerator("kowhai", Optional.empty(), Optional.of(KOWHAI_TREE), Optional.empty()),
                    AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).registryKey(key)));

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
