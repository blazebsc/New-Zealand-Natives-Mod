package blake7.newzealandnativesmod.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.LeavesBlock;
//? if >1.21.1 {
/*import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
*///?}
//? if >=26.3 {
/*import net.minecraft.world.level.block.sounds.AmbientLeavesBlockSoundPlayer;
*///?}

public class KowhaiLeaves extends LeavesBlock {
//? if <=1.21.1 {
    public KowhaiLeaves(BlockBehaviour.Properties settings) {
        super(settings);
    }
//?}
//? if >1.21.1 && <26.1 {
    /*public static final MapCodec<KowhaiLeaves> CODEC = simpleCodec(props -> new KowhaiLeaves(0.0f, props));

    public KowhaiLeaves(float leafParticleChance, BlockBehaviour.Properties settings) {
        super(leafParticleChance, settings);
    }
*///?}
//? if >=26.1 && <26.3 {
    /*public static final MapCodec<KowhaiLeaves> CODEC = simpleCodec(KowhaiLeaves::new);

    public KowhaiLeaves(BlockBehaviour.Properties props) {
        super(0.0f, props);
    }
*///?}
//? if >=26.3 {
    /*public KowhaiLeaves(BlockBehaviour.Properties props) {
        super(AmbientLeavesBlockSoundPlayer.noAmbientSound(), props);
    }
*///?}
//? if >1.21.1 && <26.3 {
    /*@Override
    public MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }

    @Override
    public void spawnFallingLeavesParticle(Level world, BlockPos pos, RandomSource random) {
    }
*///?}
//? if >=26.3 {
    /*@Override
    public void animateTick(net.minecraft.world.level.block.state.BlockState state, Level level, BlockPos pos, RandomSource random) {
    }
*///?}
}
