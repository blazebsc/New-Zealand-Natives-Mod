package blake7.newzealandnativesmod.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class KowhaiLeaves extends LeavesBlock {
    public static final MapCodec<KowhaiLeaves> CODEC = MapCodec.unit(new KowhaiLeaves(0.0f,
            AbstractBlock.Settings.create().registryKey(
                    RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("newzealandnatives", "kowhai_leaves")))));

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
