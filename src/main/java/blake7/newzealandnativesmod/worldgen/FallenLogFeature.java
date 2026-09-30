package blake7.newzealandnativesmod.worldgen;

import blake7.newzealandnativesmod.registry.NativesBlocks;
//? if <26.3 {
import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
//?}
//? if >=26.3 {
/*import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.chunk.ChunkGenerator;
*///?}
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;

// A 3-5 block horizontal trunk on the forest floor, like a fallen tree.
//? if <26.3 {
public class FallenLogFeature extends Feature<NoneFeatureConfiguration> {
    public FallenLogFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }
//?}
//? if >=26.3 {
    /*public class FallenLogFeature implements Feature {
    public static final MapCodec<FallenLogFeature> CODEC = MapCodec.unit(FallenLogFeature::new);

    @Override
    public MapCodec<FallenLogFeature> codec() {
        return CODEC;
    }
*///?}

//? if fabric && <26.3 {
    public static void register() {
        Registry.register(BuiltInRegistries.FEATURE, ResourceLocation.fromNamespaceAndPath("newzealandnatives", "fallen_log"),
                new FallenLogFeature(NoneFeatureConfiguration.CODEC));
    }
//?}
//? if fabric && >=26.3 {
    /*public static void register() {
        Registry.register(BuiltInRegistries.FEATURE_TYPE, ResourceLocation.fromNamespaceAndPath("newzealandnatives", "fallen_log"),
                CODEC);
    }
*///?}

//? if <26.3 {
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
//?}
//? if >=26.3 {
    /*@Override
    public boolean place(WorldGenLevel world, ChunkGenerator generator, RandomSource random, BlockPos origin) {
*///?}
        // ponytail: dense forest rarely fits a 3-4 log runway on the first try; poke around.
        for (int attempt = 0; attempt < 6; attempt++) {
            BlockPos candidate = origin.offset(random.nextInt(13) - 6, 0, random.nextInt(13) - 6);
            if (tryPlace(world, random, candidate)) return true;
        }
        return false;
    }

    private boolean tryPlace(WorldGenLevel world, RandomSource random, BlockPos origin) {
        Direction dir = random.nextBoolean() ? Direction.EAST : Direction.SOUTH;
        int length = 3 + random.nextInt(2); // 3-4 blocks
        BlockPos baseGround = findGround(world, origin);
        if (baseGround == null) return false;

        BlockPos[] spots = new BlockPos[length];
        for (int i = 0; i < length; i++) {
            BlockPos p = new BlockPos(origin.getX() + dir.getStepX() * i, 0, origin.getZ() + dir.getStepZ() * i);
            BlockPos ground = findGround(world, p);
            if (ground == null || Math.abs(ground.getY() - baseGround.getY()) > 1) return false;
            BlockPos above = ground.above();
            if (!world.getFluidState(above).isEmpty()) return false;
            BlockState over = world.getBlockState(above);
            if (!over.isAir() && !over.canBeReplaced() && !over.is(BlockTags.LEAVES) && !over.is(BlockTags.CLIMBABLE))
                return false;
            spots[i] = above;
        }
        Direction.Axis flat = dir.getAxis() == Direction.Axis.X ? Direction.Axis.X : Direction.Axis.Z;
        int stump = random.nextBoolean() ? 0 : spots.length - 1;
        for (int i = 0; i < spots.length; i++) {
            world.setBlock(spots[i], NativesBlocks.ROTTEN_LOG.defaultBlockState()
                    .setValue(RotatedPillarBlock.AXIS, i == stump ? Direction.Axis.Y : flat), 3);
        }
        return true;
    }

    // Ground is the first dirt under the heightmap top, boring through canopy, vines and cover.
    private BlockPos findGround(WorldGenLevel world, BlockPos column) {
        BlockPos cursor = world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, column);
        for (int d = 0; d < 30; d++) {
            if (!world.getFluidState(cursor).isEmpty()) return null;
            BlockState state = world.getBlockState(cursor);
            if (state.is(BlockTags.DIRT) || state.is(Blocks.GRASS_BLOCK)) return cursor;
            if (state.isAir() || state.canBeReplaced() || state.is(BlockTags.LEAVES) || state.is(BlockTags.LOGS)
                    || state.is(BlockTags.CLIMBABLE)) {
                cursor = cursor.below();
                continue;
            }
            return null;
        }
        return null;
    }
}
