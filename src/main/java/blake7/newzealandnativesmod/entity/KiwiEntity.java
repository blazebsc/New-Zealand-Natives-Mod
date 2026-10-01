package blake7.newzealandnativesmod.entity;

import blake7.newzealandnativesmod.NativesId;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
//? if >1.21.1 {
/*import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
*///?}
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import blake7.newzealandnativesmod.registry.NativesSounds;
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

public class KiwiEntity extends Animal implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final EntityType<KiwiEntity> TYPE = EntityType.Builder.of(KiwiEntity::new, MobCategory.CREATURE)
            .sized(0.6f, 0.7f)
//? if <=1.21.1 {
            .build(NativesId.of("kiwi").toString());
//?} else {
            /*.build(ResourceKey.create(Registries.ENTITY_TYPE, NativesId.of("kiwi")));
*///?}

    public KiwiEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RestrictSunGoal(this));
        // ponytail: daytime nap has no vanilla equivalent; shade-seeking covers flee_sun/restrict_sun.
//? if <=1.20.4 {
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.1, Ingredient.of(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS), false));
//?}
//? if >1.20.4 {
        /*this.goalSelector.addGoal(2, new TemptGoal(this, 1.1, stack ->
                stack.is(net.minecraft.world.item.Items.WHEAT_SEEDS)
                        || stack.is(net.minecraft.world.item.Items.BEETROOT_SEEDS)
                        || stack.is(net.minecraft.world.item.Items.MELON_SEEDS)
                        || stack.is(net.minecraft.world.item.Items.PUMPKIN_SEEDS), false));
*///?}
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new FollowParentGoal(this, 1.2));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
//? if <=1.21.1 {
        controllers.add(new AnimationController<>(this, "walk", state -> {
//?} else {
        /*controllers.add(new AnimationController<>("walk", state -> {
*///?}
            if (state.isMoving()) {
                state.setAnimation(RawAnimation.begin().thenLoop("animation.kiwi.walk"));
                return PlayState.CONTINUE;
            }
            state.setAnimation(RawAnimation.begin().thenLoop("animation.kiwi.idle"));
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return NativesSounds.get("kiwi", "say");
    }

    @Override
//? if <=1.20.4 {
    public float getScale() {
        return this.isBaby() ? 0.33f : super.getScale();
    }
//?}
//? if >1.20.4 {
    /*public float getAgeScale() {
        return this.isBaby() ? 0.33f : super.getAgeScale();
    }
*///?}

    @Override
//? if <=1.21.1 {
    public boolean causeFallDamage(float fallDistance, float damageMultiplier,
//?} else {
    /*public boolean causeFallDamage(double fallDistance, float damageMultiplier,
*///?}
            net.minecraft.world.damagesource.DamageSource damageSource) {
        return false;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(blake7.newzealandnativesmod.registry.NativesItems.HUHU_GRUB);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        return new KiwiEntity((EntityType<? extends Animal>) this.getType(), world);
    }
}
