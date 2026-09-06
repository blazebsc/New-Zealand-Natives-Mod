package blake7.newzealandnativesmod.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class KowhaiLeaves extends LeavesBlock {
    public static final MapCodec<KowhaiLeaves> CODEC = simpleCodec(KowhaiLeaves::new);

    public KowhaiLeaves(BlockBehaviour.Properties props) {
        super(0.0f, props);
    }

    @Override
    public MapCodec<KowhaiLeaves> codec() {
        return CODEC;
    }

    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {
    }
}
