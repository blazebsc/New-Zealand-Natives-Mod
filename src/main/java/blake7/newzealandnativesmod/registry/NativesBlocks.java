package blake7.newzealandnativesmod.registry;

import blake7.newzealandnativesmod.NativesId;
import blake7.newzealandnativesmod.block.KowhaiLeaves;
import blake7.newzealandnativesmod.block.KowhaiSapling;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
//? if <1.20.2 {
/*import net.minecraft.block.sapling.SaplingGenerator;
*///?}
//? if >=1.20.2 {
import net.minecraft.block.SaplingGenerator;
//?}
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
//? if >=26.1 {
/*import net.minecraft.core.registries.BuiltInRegistries;
*///?}
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
//? if <26.3 {
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
//?}
//? if >=26.3 {
/*import net.minecraft.util.random.WeightedList;
import net.minecraft.world.gen.feature.Feature;
*///?}

import java.util.Optional;
//? if >1.21.1 {
/*import java.util.function.Function;
*///?}

public final class NativesBlocks {
    private NativesBlocks() {}

//? if <=1.21.1 {
    public static final Block ROTTEN_LOG = register("rotten_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.BROWN)));
//?} else {
    /*public static final Block ROTTEN_LOG = register("rotten_log", key ->
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.BROWN).setId(key)));
*///?}

//? if <26.3 {
    public static final RegistryKey<ConfiguredFeature<?, ?>> KOWHAI_TREE = RegistryKey.of(
            RegistryKeys.CONFIGURED_FEATURE, NativesId.of("kowhai"));
//?}
//? if >=26.3 {
    /*public static final RegistryKey<Feature> KOWHAI_TREE = RegistryKey.of(
            RegistryKeys.FEATURE,
            NativesId.of("kowhai"));
*///?}
//? if <1.20.2 {
    /*public static final Block KOWHAI_LOG = register("kowhai_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.OAK_TAN)));
    public static final Block KOWHAI_LEAVES = register("kowhai_leaves",
            new KowhaiLeaves(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block KOWHAI_SAPLING = register("kowhai_sapling",
            new KowhaiSapling(new SaplingGenerator() {
                @Override
                protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
                    return KOWHAI_TREE;
                }
            }, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
*///?}
//? if >=1.20.2 && <=1.21.1 {
    public static final Block KOWHAI_LOG = register("kowhai_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.OAK_TAN)));
    public static final Block KOWHAI_LEAVES = register("kowhai_leaves",
            new KowhaiLeaves(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block KOWHAI_SAPLING = register("kowhai_sapling",
            new KowhaiSapling(new SaplingGenerator("kowhai", Optional.empty(), Optional.of(KOWHAI_TREE), Optional.empty()),
                    AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
//?}
//? if >1.21.1 && <26.1 {
    /*public static final Block KOWHAI_LOG = register("kowhai_log", key ->
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.OAK_TAN).setId(key)));
    public static final Block KOWHAI_LEAVES = register("kowhai_leaves", key ->
            new KowhaiLeaves(0.0f, AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).setId(key)));
    public static final Block KOWHAI_SAPLING = register("kowhai_sapling", key ->
            new KowhaiSapling(new SaplingGenerator("kowhai", Optional.empty(), Optional.of(KOWHAI_TREE), Optional.empty()),
                    AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).setId(key)));
*///?}
//? if >=26.1 && <26.3 {
    /*public static final Block KOWHAI_LOG = register("kowhai_log", key ->
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.OAK_TAN).setId(key)));
    public static final Block KOWHAI_LEAVES = register("kowhai_leaves", key ->
            new KowhaiLeaves(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).setId(key)));
    public static final Block KOWHAI_SAPLING = register("kowhai_sapling", key ->
            new KowhaiSapling(new SaplingGenerator("kowhai", Optional.empty(), Optional.of(KOWHAI_TREE), Optional.empty()),
                    AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).setId(key)));
*///?}
//? if >=26.3 {
    /*public static final Block KOWHAI_LOG = register("kowhai_log", key ->
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.OAK_TAN).setId(key)));
    public static final Block KOWHAI_LEAVES = register("kowhai_leaves", key ->
            new KowhaiLeaves(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).setId(key)));
    public static final Block KOWHAI_SAPLING = register("kowhai_sapling", key ->
            new KowhaiSapling(new SaplingGenerator("kowhai", WeightedList.of(KOWHAI_TREE), WeightedList.of(), WeightedList.of(), KOWHAI_TREE),
                    AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).setId(key)));
*///?}

    public static void register() {
    }

//? if fabric && <=1.21.1 {
    private static Block register(String id, Block block) {
        Block registered = Registry.register(Registries.BLOCK, NativesId.of(id), block);
        Registry.register(Registries.ITEM, NativesId.of(id),
                new BlockItem(registered, new Item.Settings()));
        return registered;
    }
//?}
//? if fabric && >1.21.1 {
    /*private static Block register(String id, Function<RegistryKey<Block>, Block> factory) {
        Identifier identifier = NativesId.of(id);
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, identifier);
        Block block = factory.apply(blockKey);
        Block registered = Registry.register(Registries.BLOCK, identifier, block);
        Registry.register(Registries.ITEM, identifier,
                new BlockItem(registered, new Item.Settings().setId(itemKey)));
        return registered;
    }
*///?}
}
