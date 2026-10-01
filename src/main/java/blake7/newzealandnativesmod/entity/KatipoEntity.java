package blake7.newzealandnativesmod.entity;

import blake7.newzealandnativesmod.NativesId;
import blake7.newzealandnativesmod.registry.NativesConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
//? if >1.21.1 {
/*import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
*///?}
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
//? if <=1.20.4 {
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
//?} else {
/*import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
*///?}
//? if >1.21.1 {
/*import software.bernie.geckolib.animatable.manager.AnimatableManager;
*///?}
//? if <=1.20.4 {
import software.bernie.geckolib.core.animation.*;
//?} else {
/*import software.bernie.geckolib.animation.*;
*///?}
//? if <=1.20.4 {
import software.bernie.geckolib.core.object.PlayState;
//?}
//? if >1.21.1 {
/*import software.bernie.geckolib.animation.object.PlayState;
*///?}
import software.bernie.geckolib.util.GeckoLibUtil;

public class KatipoEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final EntityType<KatipoEntity> TYPE = EntityType.Builder.of(KatipoEntity::new, MobCategory.MONSTER)
            .sized(0.75f, 0.5f)
//? if <=1.21.1 {
            .build(NativesId.of("katipo").toString());
//?} else {
            /*.build(ResourceKey.create(Registries.ENTITY_TYPE, NativesId.of("katipo")));
*///?}

    public KatipoEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
//? if <=1.21.1 {
                .add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(Attributes.FOLLOW_RANGE, 16.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0);
//?} else {
                /*.add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(Attributes.FOLLOW_RANGE, 16.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0);
*///?}
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
//? if <=1.21.1 {
    public boolean doHurtTarget(net.minecraft.world.entity.Entity target) {
        boolean hit = super.doHurtTarget(target);
//?} else {
    /*public boolean doHurtTarget(net.minecraft.server.level.ServerLevel world, net.minecraft.world.entity.Entity target) {
        boolean hit = super.doHurtTarget(world, target);
*///?}
        if (hit && NativesConfig.INSTANCE.poison && target instanceof net.minecraft.world.entity.LivingEntity living) {
            living.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                    net.minecraft.world.effect.MobEffects.POISON, 200, 0));
        }
        return hit;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
//? if <=1.21.1 {
        controllers.add(new AnimationController<>(this, "walk", state -> {
//?} else {
        /*controllers.add(new AnimationController<>("walk", state -> {
*///?}
            if (state.isMoving()) {
                state.setAnimation(RawAnimation.begin().thenLoop("katipo.walk"));
                return PlayState.CONTINUE;
            }
            state.setAnimation(RawAnimation.begin().thenLoop("katipo.idle"));
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
