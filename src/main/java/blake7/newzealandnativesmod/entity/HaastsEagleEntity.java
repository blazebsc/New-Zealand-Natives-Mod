package blake7.newzealandnativesmod.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

// Bedrock attacks players within 64 blocks with swoop + circle-anchor.
// Vanilla has no swoop goal; polar-bear pattern (melee + FlyGoal) approximates it.
public class HaastsEagleEntity extends NativesEntity {
    public static final EntityType<HaastsEagleEntity> TYPE = EntityType.Builder.of(HaastsEagleEntity::new, MobCategory.CREATURE)
            .sized(0.7f, 0.8f)
            .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("newzealandnatives", "haasts_eagle")));

    public HaastsEagleEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false, true));
    }
}
