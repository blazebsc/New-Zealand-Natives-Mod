package blake7.newzealandnativesmod.worldgen;

import blake7.newzealandnativesmod.registry.NativesBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

// A 3-5 block horizontal trunk on the forest floor, like a fallen tree.
public class FallenLogFeature extends Feature<NoneFeatureConfiguration> {
    public FallenLogFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public static void register() {
        Registry.register(BuiltInRegistries.FEATURE, Identifier.fromNamespaceAndPath("newzealandnatives", "fallen_log"),
                new FallenLogFeature(NoneFeatureConfiguration.CODEC));
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        Direction dir = random.nextBoolean() ? Direction.EAST : Direction.SOUTH;
        int length = 3 + random.nextInt(2); // 3-4 blocks
        int baseY = world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, origin).getY();

        BlockPos[] spots = new BlockPos[length];
        for (int i = 0; i < length; i++) {
            BlockPos p = new BlockPos(origin.getX() + dir.getStepX() * i, 0, origin.getZ() + dir.getStepZ() * i);
            BlockPos top = world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, p);
            if (Math.abs(top.getY() - baseY) > 1) return false;
            if (!world.getBlockState(top).isAir()) return false;
            if (!world.getFluidState(top).isEmpty()) return false;
            if (!world.getBlockState(top.below()).is(BlockTags.DIRT)) return false;
            spots[i] = top;
        }
        Direction.Axis flat = dir.getAxis() == Direction.Axis.X ? Direction.Axis.X : Direction.Axis.Z;
        int stump = random.nextBoolean() ? 0 : spots.length - 1;
        for (int i = 0; i < spots.length; i++) {
            world.setBlock(spots[i], NativesBlocks.ROTTEN_LOG.defaultBlockState()
                    .setValue(RotatedPillarBlock.AXIS, i == stump ? Direction.Axis.Y : flat), 3);
        }
        return true;
    }
}
