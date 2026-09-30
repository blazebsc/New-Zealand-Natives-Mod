package blake7.newzealandnativesmod.worldgen;

import blake7.newzealandnativesmod.registry.NativesBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

// A 3-5 block horizontal trunk on the forest floor, like a fallen tree.
public class FallenLogFeature extends Feature<DefaultFeatureConfig> {
    public FallenLogFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    //? if fabric {
    public static void register() {
        Registry.register(Registries.FEATURE, Identifier.of("newzealandnatives", "fallen_log"),
                new FallenLogFeature(DefaultFeatureConfig.CODEC));
    }
    //?}

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
//? if fabric {
        StructureWorldAccess world = context.getWorld();
//?}
//? if neoforge {
        /*ServerLevelAccessor world = (ServerLevelAccessor) context.level();
*///?}
        BlockPos origin = context.getOrigin();
//? if fabric {
        Random random = context.getRandom();
//?}
//? if neoforge {
        /*RandomSource random = context.random();
*///?}
        // ponytail: dense forest rarely fits a 3-4 log runway on the first try; poke around.
        for (int attempt = 0; attempt < 6; attempt++) {
            BlockPos candidate = origin.add(random.nextInt(13) - 6, 0, random.nextInt(13) - 6);
            if (tryPlace(world, random, candidate)) return true;
        }
        return false;
    }

    private boolean tryPlace(StructureWorldAccess world, Random random, BlockPos origin) {
        Direction dir = random.nextBoolean() ? Direction.EAST : Direction.SOUTH;
        int length = 3 + random.nextInt(2); // 3-4 blocks
        BlockPos baseGround = findGround(world, origin);
        if (baseGround == null) return false;

        BlockPos[] spots = new BlockPos[length];
        for (int i = 0; i < length; i++) {
            BlockPos p = new BlockPos(origin.getX() + dir.getOffsetX() * i, 0, origin.getZ() + dir.getOffsetZ() * i);
            BlockPos ground = findGround(world, p);
            if (ground == null || Math.abs(ground.getY() - baseGround.getY()) > 1) return false;
            BlockPos above = ground.up();
            if (!world.getFluidState(above).isEmpty()) return false;
            BlockState over = world.getBlockState(above);
            if (!over.isAir() && !over.isReplaceable() && !over.isIn(BlockTags.LEAVES) && !over.isIn(BlockTags.CLIMBABLE))
                return false;
            spots[i] = above;
        }
        Direction.Axis flat = dir.getAxis() == Direction.Axis.X ? Direction.Axis.X : Direction.Axis.Z;
        int stump = random.nextBoolean() ? 0 : spots.length - 1;
        for (int i = 0; i < spots.length; i++) {
            world.setBlockState(spots[i], NativesBlocks.ROTTEN_LOG.getDefaultState()
                    .with(PillarBlock.AXIS, i == stump ? Direction.Axis.Y : flat), 3);
        }
        return true;
    }

    // Ground is the first dirt under the heightmap top, boring through canopy, vines and cover.
    private BlockPos findGround(StructureWorldAccess world, BlockPos column) {
        BlockPos cursor = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, column);
        for (int d = 0; d < 30; d++) {
            if (!world.getFluidState(cursor).isEmpty()) return null;
            BlockState state = world.getBlockState(cursor);
            if (state.isIn(BlockTags.DIRT)) return cursor;
            if (state.isAir() || state.isReplaceable() || state.isIn(BlockTags.LEAVES) || state.isIn(BlockTags.LOGS)
                    || state.isIn(BlockTags.CLIMBABLE)) {
                cursor = cursor.down();
                continue;
            }
            return null;
        }
        return null;
    }
}
