package blake7.newzealandnativesmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import blake7.newzealandnativesmod.registry.NativesSounds;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class KiwiEntity extends AnimalEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final EntityType<KiwiEntity> TYPE = EntityType.Builder.create(KiwiEntity::new, SpawnGroup.CREATURE)
            .dimensions(0.6f, 0.7f)
            .build(Identifier.of("newzealandnatives", "kiwi").toString());

    public KiwiEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AvoidSunlightGoal(this));
        // ponytail: daytime nap has no vanilla equivalent; shade-seeking covers flee_sun/restrict_sun.
        this.goalSelector.add(2, new TemptGoal(this, 1.1, stack ->
                stack.isOf(net.minecraft.item.Items.WHEAT_SEEDS)
                        || stack.isOf(net.minecraft.item.Items.BEETROOT_SEEDS)
                        || stack.isOf(net.minecraft.item.Items.MELON_SEEDS)
                        || stack.isOf(net.minecraft.item.Items.PUMPKIN_SEEDS), false));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(7, new FollowParentGoal(this, 1.2));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "walk", 0, state -> {
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
    public float getScaleFactor() {
        return this.isBaby() ? 0.33f : super.getScaleFactor();
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier,
            net.minecraft.entity.damage.DamageSource damageSource) {
        return false;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(blake7.newzealandnativesmod.registry.NativesItems.HUHU_GRUB);
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return new KiwiEntity((EntityType<? extends AnimalEntity>) this.getType(), world);
    }
}
