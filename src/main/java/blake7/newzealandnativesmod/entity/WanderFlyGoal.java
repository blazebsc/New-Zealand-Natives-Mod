package blake7.newzealandnativesmod.entity;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;

// Picks a nearby patch of open air and steers straight at it.
// Fire-and-forget: avoids FlyGoal stalling when a target or navigation state pins it.
public class WanderFlyGoal extends Goal {
    private final PathAwareEntity mob;
    private int cooldown;

    public WanderFlyGoal(PathAwareEntity mob) {
        this.mob = mob;
        this.setControls(EnumSet.of(Control.MOVE));
        this.cooldown = mob.getRandom().nextInt(60);
    }

    @Override
    public boolean canStart() {
        if (mob.getTarget() != null) return false;
        if (mob.isTouchingWater()) return false;
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
            // yarn spells the World's bottom accessor getBottomY() in every
            // version; only mojang renamed it (getMinY in 1.21.2+).
            if (y < mob.getEntityWorld().getBottomY() + 2) continue;
            if (!mob.getEntityWorld().isAir(BlockPos.ofFloored(x, y, z))) continue;
            mob.getMoveControl().moveTo(x, y, z, 1.0);
            break;
        }
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }
}
