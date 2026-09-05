package blake7.newzealandnativesmod.worldgen;

import blake7.newzealandnativesmod.registry.NativesBlocks;
import com.mojang.serialization.Codec;
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

    public static void register() {
        Registry.register(Registries.FEATURE, Identifier.of("newzealandnatives", "fallen_log"),
                new FallenLogFeature(DefaultFeatureConfig.CODEC));
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        Direction dir = random.nextBoolean() ? Direction.EAST : Direction.SOUTH;
        int length = 3 + random.nextInt(2); // 3-4 blocks
        int baseY = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, origin).getY();

        BlockPos[] spots = new BlockPos[length];
        for (int i = 0; i < length; i++) {
            BlockPos p = new BlockPos(origin.getX() + dir.getOffsetX() * i, 0, origin.getZ() + dir.getOffsetZ() * i);
            BlockPos top = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, p);
            if (Math.abs(top.getY() - baseY) > 1) return false;
            if (!world.getBlockState(top).isAir()) return false;
            if (!world.getFluidState(top).isEmpty()) return false;
            if (!world.getBlockState(top.down()).isIn(BlockTags.DIRT)) return false;
            spots[i] = top;
        }
        Direction.Axis flat = dir.getAxis() == Direction.Axis.X ? Direction.Axis.X : Direction.Axis.Z;
        int stump = random.nextBoolean() ? 0 : spots.length - 1;
        for (int i = 0; i < spots.length; i++) {
            world.setBlockState(spots[i], NativesBlocks.ROTTEN_LOG.getDefaultState()
                    .with(PillarBlock.AXIS, i == stump ? Direction.Axis.Y : flat), 3);
        }
        return true;
    }
}
