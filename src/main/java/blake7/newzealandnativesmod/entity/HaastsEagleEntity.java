package blake7.newzealandnativesmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

// Bedrock attacks players within 64 blocks with swoop + circle-anchor.
// Vanilla has no swoop goal; polar-bear pattern (melee + FlyGoal) approximates it.
public class HaastsEagleEntity extends NativesEntity {
    public static final EntityType<HaastsEagleEntity> TYPE = EntityType.Builder.create(HaastsEagleEntity::new, SpawnGroup.CREATURE)
            .dimensions(0.7f, 0.8f)
            .build(Identifier.of("newzealandnatives", "haasts_eagle").toString());

    public HaastsEagleEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, false, true));
    }
}
