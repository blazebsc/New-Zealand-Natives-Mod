package blake7.newzealandnativesmod.entity;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

// Picks a nearby patch of open air and steers straight at it.
// Fire-and-forget: avoids FlyGoal stalling when a target or navigation state pins it.
public class WanderFlyGoal extends Goal {
    private final PathfinderMob mob;
    private int cooldown;

    public WanderFlyGoal(PathfinderMob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE));
        this.cooldown = mob.getRandom().nextInt(60);
    }

    @Override
    public boolean canUse() {
        if (mob.getTarget() != null) return false;
        if (mob.isInWater()) return false;
        if (--cooldown > 0) return false;
        return true;
    }

    @Override
    public void start() {
        cooldown = 40 + mob.getRandom().nextInt(80);
        for (int i = 0; i < 8; i++) {
            double x = mob.getX() + (mob.getRandom().nextDouble() - 0.5) * 24.0;
            double y = mob.getY() + (mob.getRandom().nextDouble() - 0.5) * 10.0;
            double z = mob.getZ() + (mob.getRandom().nextDouble() - 0.5) * 24.0;
            if (y < mob.level().getMinY() + 2) continue;
            if (!mob.level().isEmptyBlock(BlockPos.containing(x, y, z))) continue;
            mob.getMoveControl().setWantedPosition(x, y, z, 1.0);
            break;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }
}
