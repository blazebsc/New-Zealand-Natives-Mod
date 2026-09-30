package blake7.newzealandnativesmod.entity;

import blake7.newzealandnativesmod.registry.NativesSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.FlyGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
//? if >1.21.1 {
/*import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
*///?}
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
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
public class RuruEntity extends TameableEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final EntityType<RuruEntity> TYPE = EntityType.Builder.create(RuruEntity::new, SpawnGroup.CREATURE)
            .dimensions(0.6f, 0.7f)
//? if <=1.21.1 {
            .build(Identifier.of("newzealandnatives", "ruru").toString());
//?} else {
            /*.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of("newzealandnatives", "ruru")));
*///?}

    public RuruEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

    private static boolean isSeed(ItemStack stack) {
        return stack.isOf(Items.WHEAT_SEEDS) || stack.isOf(Items.BEETROOT_SEEDS)
                || stack.isOf(Items.MELON_SEEDS) || stack.isOf(Items.PUMPKIN_SEEDS);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new SitGoal(this));
        this.goalSelector.add(2, new FollowOwnerGoal(this, 1.0, 5.0f, 1.0f));
        this.goalSelector.add(3, new TemptGoal(this, 1.1, RuruEntity::isSeed, false));
        this.goalSelector.add(4, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(5, new WanderFlyGoal(this));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(9, new FollowParentGoal(this, 1.2));
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation nav = new BirdNavigation(this, world);
//? if <=1.21.1 {
        nav.setCanPathThroughDoors(false);
//?}
//? if fabric {
        nav.setCanSwim(false);
//?}
//? if <=1.21.1 {
        nav.setCanEnterOpenDoors(true);
//?}
        return nav;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (this.isTamed()) {
            if (this.isOwner(player) && !isSeed(stack)) {
                this.setSitting(!this.isSitting());
//? if <=1.21.1 {
                return ActionResult.success(this.getWorld().isClient);
//?} else {
                /*return ActionResult.SUCCESS;
*///?}
            }
        } else if (isSeed(stack)) {
            stack.decrementUnlessCreative(1, player);
            if (this.getRandom().nextInt(3) == 0) {
                this.setOwner(player);
                this.setTamed(true, true);
                this.setSitting(true);
//? if <=1.21.1 {
                this.getWorld().sendEntityStatus(this, (byte) 7);
//?} else {
                /*this.getEntityWorld().sendEntityStatus(this, (byte) 7);
*///?}
            } else {
//? if <=1.21.1 {
                this.getWorld().sendEntityStatus(this, (byte) 6);
//?} else {
                /*this.getEntityWorld().sendEntityStatus(this, (byte) 6);
*///?}
            }
//? if <=1.21.1 {
            return ActionResult.success(this.getWorld().isClient);
//?} else {
            /*return ActionResult.SUCCESS;
*///?}
        }
        return super.interactMob(player, hand);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
//? if <=1.21.1 {
        controllers.add(new AnimationController<>(this, "main", 0, state -> {
//?} else {
        /*controllers.add(new AnimationController<>("main", 0, state -> {
*///?}
            if (!this.isOnGround()) {
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
    public boolean handleFallDamage(float fallDistance, float damageMultiplier,
//?} else {
    /*public boolean handleFallDamage(double fallDistance, float damageMultiplier,
*///?}
            net.minecraft.entity.damage.DamageSource damageSource) {
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
    public float getScaleFactor() {
        return this.isBaby() ? 0.6f : super.getScaleFactor();
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return isSeed(stack);
    }

    @Override
    public RuruEntity createChild(ServerWorld world, PassiveEntity entity) {
        return new RuruEntity(TYPE, world);
    }
}
