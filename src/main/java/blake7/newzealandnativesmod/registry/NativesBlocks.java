package blake7.newzealandnativesmod.registry;

import blake7.newzealandnativesmod.block.KowhaiLeaves;
import blake7.newzealandnativesmod.block.KowhaiSapling;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
//? if <26.3 {
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//?}
//? if >=26.3 {
/*import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.levelgen.feature.Feature;
*///?}

import java.util.Optional;
//? if >1.21.1 {
/*import java.util.function.Function;
*///?}

public final class NativesBlocks {
    private NativesBlocks() {}

//? if <=1.21.1 {
    public static final Block ROTTEN_LOG = register("rotten_log",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.COLOR_BROWN)));
//?} else {
    /*public static final Block ROTTEN_LOG = register("rotten_log", key ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.COLOR_BROWN).setId(key)));
*///?}

//? if <26.3 {
    public static final ResourceKey<ConfiguredFeature<?, ?>> KOWHAI_TREE = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath("newzealandnatives", "kowhai"));
//?}
//? if >=26.3 {
    /*public static final ResourceKey<Feature> KOWHAI_TREE = ResourceKey.create(
            Registries.FEATURE,
            ResourceLocation.fromNamespaceAndPath("newzealandnatives", "kowhai"));
*///?}
//? if <=1.21.1 {
    public static final Block KOWHAI_LOG = register("kowhai_log",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.WOOD)));
    public static final Block KOWHAI_LEAVES = register("kowhai_leaves",
            new KowhaiLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final Block KOWHAI_SAPLING = register("kowhai_sapling",
            new KowhaiSapling(new TreeGrower("kowhai", Optional.empty(), Optional.of(KOWHAI_TREE), Optional.empty()),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
//?}
//? if >1.21.1 && <26.1 {
    /*public static final Block KOWHAI_LOG = register("kowhai_log", key ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.WOOD).setId(key)));
    public static final Block KOWHAI_LEAVES = register("kowhai_leaves", key ->
            new KowhaiLeaves(0.0f, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(key)));
    public static final Block KOWHAI_SAPLING = register("kowhai_sapling", key ->
            new KowhaiSapling(new TreeGrower("kowhai", Optional.empty(), Optional.of(KOWHAI_TREE), Optional.empty()),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(key)));
*///?}
//? if >=26.1 && <26.3 {
    /*public static final Block KOWHAI_LOG = register("kowhai_log", key ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.WOOD).setId(key)));
    public static final Block KOWHAI_LEAVES = register("kowhai_leaves", key ->
            new KowhaiLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(key)));
    public static final Block KOWHAI_SAPLING = register("kowhai_sapling", key ->
            new KowhaiSapling(new TreeGrower("kowhai", Optional.empty(), Optional.of(KOWHAI_TREE), Optional.empty()),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(key)));
*///?}
//? if >=26.3 {
    /*public static final Block KOWHAI_LOG = register("kowhai_log", key ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.WOOD).setId(key)));
    public static final Block KOWHAI_LEAVES = register("kowhai_leaves", key ->
            new KowhaiLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(key)));
    public static final Block KOWHAI_SAPLING = register("kowhai_sapling", key ->
            new KowhaiSapling(new TreeGrower("kowhai", WeightedList.of(KOWHAI_TREE), WeightedList.of(), WeightedList.of(), KOWHAI_TREE),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(key)));
*///?}

    public static void register() {
    }

//? if fabric && <=1.21.1 {
    private static Block register(String id, Block block) {
        Block registered = Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath("newzealandnatives", id), block);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath("newzealandnatives", id),
                new BlockItem(registered, new Item.Properties()));
        return registered;
    }
//?}
//? if fabric && >1.21.1 {
    /*private static Block register(String id, Function<ResourceKey<Block>, Block> factory) {
        ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath("newzealandnatives", id);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, identifier);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, identifier);
        Block block = factory.apply(blockKey);
        Block registered = Registry.register(BuiltInRegistries.BLOCK, identifier, block);
        Registry.register(BuiltInRegistries.ITEM, identifier,
                new BlockItem(registered, new Item.Properties().setId(itemKey)));
        return registered;
    }
*///?}
//? if neoforge {
    /*private static Block register(String id, Block block) {
        return block;
    }
*///?}
}
