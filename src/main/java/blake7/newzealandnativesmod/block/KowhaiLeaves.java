package blake7.newzealandnativesmod.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.LeavesBlock;
//? if >1.21.1 {
/*import com.mojang.serialization.MapCodec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
*///?}
//? if >=26.3 {
/*import net.minecraft.world.level.block.sounds.AmbientLeavesBlockSoundPlayerEntity;
*///?}

public class KowhaiLeaves extends LeavesBlock {
//? if <=1.21.1 {
    public KowhaiLeaves(AbstractBlock.Settings settings) {
        super(settings);
    }
//?}
//? if >1.21.1 && <26.1 {
    /*public static final MapCodec<KowhaiLeaves> CODEC = simpleCodec(props -> new KowhaiLeaves(0.0f, props));

    public KowhaiLeaves(float leafParticleChance, AbstractBlock.Settings settings) {
        super(leafParticleChance, settings);
    }
*///?}
//? if >=26.1 && <26.3 {
    /*public static final MapCodec<KowhaiLeaves> CODEC = simpleCodec(KowhaiLeaves::new);

    public KowhaiLeaves(AbstractBlock.Settings props) {
        super(0.0f, props);
    }
*///?}
//? if >=26.3 {
    /*public KowhaiLeaves(AbstractBlock.Settings props) {
        super(AmbientLeavesBlockSoundPlayerEntity.noAmbientSound(), props);
    }
*///?}
//? if >1.21.1 && <26.3 {
    /*@Override
    public MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }

    @Override
    public void spawnFallingLeavesParticle(World world, BlockPos pos, Random random) {
    }
*///?}
//? if >=26.3 {
    /*@Override
    public void animateTick(net.minecraft.block.BlockState state, Level level, BlockPos pos, Random random) {
    }
*///?}
}
