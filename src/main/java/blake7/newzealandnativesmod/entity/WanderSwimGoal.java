package blake7.newzealandnativesmod.entity;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;

// Picks a nearby water block and steers at it. Companion to WanderFlyGoal.
public class WanderSwimGoal extends Goal {
    private final PathAwareEntity mob;
    private int cooldown;

    public WanderSwimGoal(PathAwareEntity mob) {
        this.mob = mob;
        this.setControls(EnumSet.of(Control.MOVE));
        this.cooldown = mob.getRandom().nextInt(60);
    }

    @Override
    public boolean canStart() {
        if (mob.getTarget() != null) return false;
        if (!mob.isTouchingWater()) return false;
        if (--cooldown > 0) return false;
        return true;
    }

    @Override
    public void start() {
        cooldown = 40 + mob.getRandom().nextInt(80);
        for (int i = 0; i < 8; i++) {
            double x = mob.getX() + (mob.getRandom().nextDouble() - 0.5) * 16.0;
            double y = mob.getY() + (mob.getRandom().nextDouble() - 0.5) * 6.0;
            double z = mob.getZ() + (mob.getRandom().nextDouble() - 0.5) * 16.0;
            BlockPos p = BlockPos.ofFloored(x, y, z);
//? if <=1.21.1 {
            if (!mob.getWorld().getFluidState(p).isIn(FluidTags.WATER)) continue;
//?} else {
            /*if (!mob.getEntityWorld().getFluidState(p).isIn(FluidTags.WATER)) continue;
*///?}
            mob.getMoveControl().moveTo(x, y, z, 1.0);
            break;
        }
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }
}
