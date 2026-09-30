package blake7.newzealandnativesmod.entity;

import blake7.newzealandnativesmod.registry.NativesSounds;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
//? if >1.21.1 {
/*import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
*///?}
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
//? if <=1.21.1 {
import software.bernie.geckolib.animation.AnimatableManager;
//?} else {
/*import software.bernie.geckolib.animatable.manager.AnimatableManager;
*///?}
import software.bernie.geckolib.animation.AnimationController;
//? if <=1.21.1 {
import software.bernie.geckolib.animation.PlayState;
//?} else {
/*import software.bernie.geckolib.animation.object.PlayState;
*///?}
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

// Bedrock parrot_wild/parrot_tame groups: tamed with seeds (1/3), sits, follows owner.
public class RuruEntity extends TamableAnimal implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final EntityType<RuruEntity> TYPE = EntityType.Builder.of(RuruEntity::new, MobCategory.CREATURE)
            .sized(0.6f, 0.7f)
//? if <=1.21.1 {
            .build(ResourceLocation.fromNamespaceAndPath("newzealandnatives", "ruru").toString());
//?} else {
            /*.build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("newzealandnatives", "ruru")));
*///?}

    public RuruEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    private static boolean isSeed(ItemStack stack) {
        return stack.is(Items.WHEAT_SEEDS) || stack.is(Items.BEETROOT_SEEDS)
                || stack.is(Items.MELON_SEEDS) || stack.is(Items.PUMPKIN_SEEDS);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0, 5.0f, 1.0f));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1, RuruEntity::isSeed, false));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(5, new WanderFlyGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new FollowParentGoal(this, 1.2));
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        FlyingPathNavigation nav = new FlyingPathNavigation(this, world);
//? if <=1.21.1 {
        nav.setCanPassDoors(false);
//?}
        nav.setCanFloat(false);
//? if <=1.21.1 {
        nav.setCanOpenDoors(true);
//?}
        return nav;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (this.isTame()) {
            if (this.isOwnedBy(player) && !isSeed(stack)) {
                this.setInSittingPose(!this.isInSittingPose());
//? if <=1.21.1 {
                return InteractionResult.sidedSuccess(this.level().isClientSide);
//?} else {
                /*return InteractionResult.SUCCESS;
*///?}
            }
        } else if (isSeed(stack)) {
            if (!player.getAbilities().instabuild) stack.shrink(1);
            if (this.getRandom().nextInt(3) == 0) {
                this.tame(player);
                this.setTame(true, true);
                this.setInSittingPose(true);
                if (this.level() instanceof ServerLevel serverLevel) serverLevel.broadcastEntityEvent(this, (byte) 7);
            } else {
                if (this.level() instanceof ServerLevel serverLevel) serverLevel.broadcastEntityEvent(this, (byte) 6);
            }
//? if <=1.21.1 {
            return InteractionResult.sidedSuccess(this.level().isClientSide);
//?} else {
            /*return InteractionResult.SUCCESS;
*///?}
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
//? if <=1.21.1 {
        controllers.add(new AnimationController<>(this, "main", 0, state -> {
//?} else {
        /*controllers.add(new AnimationController<>("main", 0, state -> {
*///?}
            if (!this.onGround()) {
                state.setAnimation(RawAnimation.begin().thenLoop("animation.ruru.fly"));
                return PlayState.CONTINUE;
            }
            if (state.isMoving()) {
                state.setAnimation(RawAnimation.begin().thenLoop("animation.ruru.walk"));
                return PlayState.CONTINUE;
            }
            return PlayState.STOP;
        }));
    }

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
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return NativesSounds.get("ruru", "say");
    }

    @Override
    public float getAgeScale() {
        return this.isBaby() ? 0.6f : super.getAgeScale();
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return isSeed(stack);
    }

    @Override
    public RuruEntity getBreedOffspring(ServerLevel world, AgeableMob entity) {
        return new RuruEntity(TYPE, world);
    }
}
