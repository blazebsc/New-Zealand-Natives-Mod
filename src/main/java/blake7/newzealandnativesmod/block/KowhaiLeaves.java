package blake7.newzealandnativesmod.block;

import blake7.newzealandnativesmod.registry.NativesBlocks;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapLike;
import com.mojang.serialization.RecordBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class KowhaiLeaves extends LeavesBlock {
    // ponytail: no throwaway instance here — an unregistered block trips the registry freeze check.
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

    public KowhaiLeaves(float leafParticleChance, AbstractBlock.Settings settings) {
        super(leafParticleChance, settings);
    }

    @Override
    public MapCodec<? extends LeavesBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void spawnLeafParticle(World world, BlockPos pos, Random random) {
    }
}
