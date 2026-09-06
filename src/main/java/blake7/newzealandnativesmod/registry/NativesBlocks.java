package blake7.newzealandnativesmod.registry;

import java.util.Optional;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import blake7.newzealandnativesmod.block.KowhaiLeaves;
import blake7.newzealandnativesmod.block.KowhaiSapling;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;

public final class NativesBlocks {
    private NativesBlocks() {}

    public static final Block ROTTEN_LOG = register("rotten_log", key ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.COLOR_BROWN).setId(key)));

    public static final ResourceKey<ConfiguredFeature<?, ?>> KOWHAI_TREE = ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            Identifier.fromNamespaceAndPath("newzealandnatives", "kowhai"));
    public static final Block KOWHAI_LOG = register("kowhai_log", key ->
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.WOOD).setId(key)));
    public static final Block KOWHAI_LEAVES = register("kowhai_leaves", key ->
            new KowhaiLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(key)));
    public static final Block KOWHAI_SAPLING = register("kowhai_sapling", key ->
            new KowhaiSapling(new TreeGrower("kowhai", Optional.empty(), Optional.of(KOWHAI_TREE), Optional.empty()),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(key)));

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
