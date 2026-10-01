package blake7.newzealandnativesmod.entity;

import blake7.newzealandnativesmod.NativesId;
import blake7.newzealandnativesmod.registry.NativesConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
//? if >1.21.1 {
/*import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
*///?}
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

// Bedrock attacks players within 64 blocks with swoop + circle-anchor.
// Vanilla has no swoop goal; polar-bear pattern (melee + FlyGoal) approximates it.
public class HaastsEagleEntity extends NativesEntity {
    public static final EntityType<HaastsEagleEntity> TYPE = EntityType.Builder.of(HaastsEagleEntity::new, MobCategory.CREATURE)
            .sized(0.7f, 0.8f)
//? if <=1.21.1 {
            .build(NativesId.of("haasts_eagle").toString());
//?} else {
            /*.build(ResourceKey.create(Registries.ENTITY_TYPE, NativesId.of("haasts_eagle")));
*///?}

    public HaastsEagleEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        if (NativesConfig.INSTANCE.eagleHostile) {
            this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true));
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false, true));
        }
    }
}
