package blake7.newzealandnativesmod.entity;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

// Picks a nearby water block and steers at it. Companion to WanderFlyGoal.
public class WanderSwimGoal extends Goal {
    private final PathfinderMob mob;
    private int cooldown;

    public WanderSwimGoal(PathfinderMob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE));
        this.cooldown = mob.getRandom().nextInt(60);
    }

    @Override
    public boolean canUse() {
        if (mob.getTarget() != null) return false;
        if (!mob.isInWater()) return false;
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
            BlockPos p = BlockPos.containing(x, y, z);
            if (!mob.level().getFluidState(p).is(FluidTags.WATER)) continue;
            mob.getMoveControl().setWantedPosition(x, y, z, 1.0);
            break;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }
}
