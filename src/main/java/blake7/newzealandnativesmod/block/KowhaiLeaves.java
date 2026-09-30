package blake7.newzealandnativesmod.block;

//? if >1.21.1 {
/*import blake7.newzealandnativesmod.registry.NativesBlocks;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapLike;
import com.mojang.serialization.RecordBuilder;
*///?}
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.LeavesBlock;
//? if >1.21.1 {
/*import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
*///?}

public class KowhaiLeaves extends LeavesBlock {
//? if <=1.21.1 {
    public KowhaiLeaves(BlockBehaviour.Properties settings) {
        super(settings);
//?} else {
    /*// ponytail: no throwaway instance here — an unregistered block trips the registry freeze check.
    public static final MapCodec<KowhaiLeaves> CODEC = new MapCodec<>() {
        @Override
        public <T> RecordBuilder<T> encode(KowhaiLeaves input, DynamicOps<T> ops, RecordBuilder<T> prefix) {
            return prefix;
        }

        @Override
        public <T> DataResult<KowhaiLeaves> decode(DynamicOps<T> ops, MapLike<T> input) {
            return DataResult.success((KowhaiLeaves) NativesBlocks.KOWHAI_LEAVES);
        }

        @Override
        public <T> java.util.stream.Stream<T> keys(DynamicOps<T> ops) {
            return java.util.stream.Stream.empty();
        }

        @Override
        public String toString() {
            return "KowhaiLeaves";
        }
    };

    public KowhaiLeaves(float leafParticleChance, BlockBehaviour.Properties settings) {
        super(leafParticleChance, settings);
    }

    @Override
    public MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }

    @Override
    public void spawnFallingLeavesParticle(Level world, BlockPos pos, RandomSource random) {
*///?}
    }
}
