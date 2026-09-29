package blake7.newzealandnativesmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.sounds.AmbientLeavesBlockSoundPlayer;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class KowhaiLeaves extends LeavesBlock {
    public KowhaiLeaves(BlockBehaviour.Properties props) {
        super(AmbientLeavesBlockSoundPlayer.noAmbientSound(), props);
    }

    @Override
    public void animateTick(net.minecraft.world.level.block.state.BlockState state, Level level, BlockPos pos, RandomSource random) {
    }
}
