package blake7.newzealandnativesmod.entity;

import blake7.newzealandnativesmod.NativesId;
import blake7.newzealandnativesmod.registry.NativesConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
//? if >1.21.1 {
/*import net.minecraft.registry.RegistryKey;

import net.minecraft.registry.RegistryKeys;
*///?}
//? if >=26.1 {
/*import net.minecraft.core.registries.Registries;
*///?}
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

// Bedrock attacks players within 64 blocks with swoop + circle-anchor.
// Vanilla has no swoop goal; polar-bear pattern (melee + FlyGoal) approximates it.
public class HaastsEagleEntity extends NativesEntity {
    public static final EntityType<HaastsEagleEntity> TYPE = EntityType.Builder.create(HaastsEagleEntity::new, SpawnGroup.CREATURE)
            //? if <=1.20.4 {
                /*.setDimensions(0.7f, 0.8f)
            *///?}
            //? if >1.20.4 && <26.1 {
                /*.dimensions(0.7f, 0.8f)
            *///?}
            //? if >=26.1 {
                /*.sized(0.7f, 0.8f)
            *///?}
//? if <=1.21.1 {
            .build(NativesId.of("haasts_eagle").toString());
//?} else {
            /*.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, NativesId.of("haasts_eagle")));
*///?}

    public HaastsEagleEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        if (NativesConfig.INSTANCE.eagleHostile) {
            this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, true));
            this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, false, true));
        }
    }
}
