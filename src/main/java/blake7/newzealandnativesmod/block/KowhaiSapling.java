package blake7.newzealandnativesmod.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SaplingBlock;
//? if <1.20.2 {
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
//?}
//? if >=1.20.2 {
/*import net.minecraft.world.level.block.grower.TreeGrower;
*///?}

public class KowhaiSapling extends SaplingBlock {
//? if <1.20.2 {
    public KowhaiSapling(AbstractTreeGrower generator, BlockBehaviour.Properties settings) {
        super(generator, settings);
    }
//?}
//? if >=1.20.2 {
    /*public KowhaiSapling(TreeGrower generator, BlockBehaviour.Properties settings) {
        super(generator, settings);
    }
*///?}
}
