package blake7.newzealandnativesmod.entity;

import blake7.newzealandnativesmod.NativesId;
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
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
//? if >1.21.1 {
/*import net.minecraft.registry.RegistryKey;

import net.minecraft.registry.RegistryKeys;
*///?}
//? if >=26.1 {
/*import net.minecraft.core.registries.Registries;
*///?}
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
//? if <=1.20.4 {
/*import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
*///?} else {
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
//?}
//? if <=1.20.4 {
/*import software.bernie.geckolib.core.animation.AnimatableManager;
*///?}
//? if >1.20.4 && <=1.21.1 {
import software.bernie.geckolib.animation.AnimatableManager;
//?}
//? if >1.21.1 {
/*import software.bernie.geckolib.animatable.manager.AnimatableManager;
*///?}
//? if <=1.20.4 {
/*import software.bernie.geckolib.core.animation.AnimationController;
*///?} else {
import software.bernie.geckolib.animation.AnimationController;
//?}
//? if <=1.20.4 {
/*import software.bernie.geckolib.core.object.PlayState;
*///?}
//? if >1.20.4 && <=1.21.1 {
import software.bernie.geckolib.animation.PlayState;
//?}
//? if >1.21.1 {
/*import software.bernie.geckolib.animation.object.PlayState;
*///?}
//? if <=1.20.4 {
/*import software.bernie.geckolib.core.animation.RawAnimation;
*///?} else {
import software.bernie.geckolib.animation.RawAnimation;
//?}
import software.bernie.geckolib.util.GeckoLibUtil;

// Bedrock parrot_wild/parrot_tame groups: tamed with seeds (1/3), sits, follows owner.
public class RuruEntity extends TameableEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final EntityType<RuruEntity> TYPE = EntityType.Builder.create(RuruEntity::new, SpawnGroup.CREATURE)
            //? if <=1.20.4 {
                /*.setDimensions(0.6f, 0.7f)
            *///?}
            //? if >1.20.4 && <26.1 {
                /*.dimensions(0.6f, 0.7f)
            *///?}
            //? if >=26.1 {
                /*.sized(0.6f, 0.7f)
            *///?}
//? if <=1.21.1 {
            .build(NativesId.of("ruru").toString());
//?} else {
            /*.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, NativesId.of("ruru")));
*///?}

    public RuruEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

//? if <=1.20.6 {
    /*// Yarn 1.20.x ships Tameable#method_48926() as ACC_SYNTHETIC, so javac
    // ignores it when checking overrides and then rejects the class as not
    // implementing the abstract interface method. Declaring it here satisfies
    // both. Same signature as the interface; delegates to the level accessor.
    @Override
    public net.minecraft.world.EntityView method_48926() {
        return this.getEntityWorld();
    }
*///?}



    private static boolean isSeed(ItemStack stack) {
        return stack.isOf(Items.WHEAT_SEEDS) || stack.isOf(Items.BEETROOT_SEEDS)
                || stack.isOf(Items.MELON_SEEDS) || stack.isOf(Items.PUMPKIN_SEEDS);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new SitGoal(this));
//? if <1.21 {
        /*this.goalSelector.add(2, new FollowOwnerGoal(this, 1.0, 5.0f, 1.0f, true));
*///?}
//? if >=1.21 {
        this.goalSelector.add(2, new FollowOwnerGoal(this, 1.0, 5.0f, 1.0f));
//?}
//? if <=1.20.4 {
        /*this.goalSelector.add(3, new TemptGoal(this, 1.1, Ingredient.ofItems(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS), false));
*///?}
//? if >1.20.4 {
        this.goalSelector.add(3, new TemptGoal(this, 1.1, RuruEntity::isSeed, false));
//?}
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
        nav.setCanSwim(false);
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
//? if <1.20.2 {
                /*this.setSitting(!this.isSitting());
*///?}
//? if >1.20.4 {
                this.setSitting(!this.isSitting());
//?}
//? if <=1.21.1 {
                return ActionResult.success(this.getEntityWorld().isClient);
//?} else {
                /*return ActionResult.SUCCESS;
*///?}
            }
        } else if (isSeed(stack)) {
//? if <=1.20.4 {
            /*if (!player.getAbilities().creativeMode) stack.decrement(1);
*///?}
//? if >1.20.4 {
            stack.decrementUnlessCreative(1, player);
//?}
            if (this.getRandom().nextInt(3) == 0) {
//? if >=26.1 {
                /*this.setOwner(player);
*///?}
//? if <26.1 {
                this.setOwner(player);
//?}
//? if <1.20.2 {
                /*this.setTamed(true);
                this.setSitting(true);
*///?}
//? if >1.20.4 {
                this.setTamed(true, true);
                this.setSitting(true);
//?}
//? if >=26.1 {
                /*this.getEntityWorld().broadcastEntityEvent(this, (byte) 7);
*///?}
//? if <26.1 {
                this.getEntityWorld().sendEntityStatus(this, (byte) 7);
//?}
            } else {
//? if >=26.1 {
                /*this.getEntityWorld().broadcastEntityEvent(this, (byte) 6);
*///?}
//? if <26.1 {
                this.getEntityWorld().sendEntityStatus(this, (byte) 6);
//?}
            }
//? if <=1.21.1 {
            return ActionResult.success(this.getEntityWorld().isClient);
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
//? if >=26.1 {
    /*public float getAgeScale() {
        return this.isBaby() ? 0.6f : super.getAgeScale();
    }
*///?}
//? if <26.1 {
    public float getScaleFactor() {
        return this.isBaby() ? 0.6f : super.getScaleFactor();
    }
//?}

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return isSeed(stack);
    }

    @Override
    public RuruEntity createChild(ServerWorld world, PassiveEntity entity) {
        return new RuruEntity(TYPE, world);
    }
}
