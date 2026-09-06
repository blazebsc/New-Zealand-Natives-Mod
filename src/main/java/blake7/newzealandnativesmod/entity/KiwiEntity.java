package blake7.newzealandnativesmod.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import blake7.newzealandnativesmod.registry.NativesSounds;
import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.*;
import com.geckolib.animation.object.PlayState;
import com.geckolib.util.GeckoLibUtil;

public class KiwiEntity extends Animal implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final EntityType<KiwiEntity> TYPE = EntityType.Builder.of(KiwiEntity::new, MobCategory.CREATURE)
            .sized(0.6f, 0.7f)
            .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("newzealandnatives", "kiwi")));

    public KiwiEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RestrictSunGoal(this));
        // ponytail: daytime nap has no vanilla equivalent; shade-seeking covers flee_sun/restrict_sun.
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.1, stack ->
                stack.is(net.minecraft.world.item.Items.WHEAT_SEEDS)
                        || stack.is(net.minecraft.world.item.Items.BEETROOT_SEEDS)
                        || stack.is(net.minecraft.world.item.Items.MELON_SEEDS)
                        || stack.is(net.minecraft.world.item.Items.PUMPKIN_SEEDS), false));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new FollowParentGoal(this, 1.2));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("walk", 0, state -> {
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
    public float getAgeScale() {
        return this.isBaby() ? 0.33f : super.getAgeScale();
    }

    @Override
    public boolean causeFallDamage(double fallDistance, float damageMultiplier,
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
