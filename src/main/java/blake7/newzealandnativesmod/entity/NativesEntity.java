package blake7.newzealandnativesmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import blake7.newzealandnativesmod.registry.NativesItems;
import blake7.newzealandnativesmod.registry.NativesSounds;

import java.util.Map;
import java.util.Set;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
//? if >1.21.1 {
/*import software.bernie.geckolib.animatable.manager.AnimatableManager;
*///?}
import software.bernie.geckolib.animation.*;
//? if >1.21.1 {
/*import software.bernie.geckolib.animation.object.PlayState;
import software.bernie.geckolib.animation.state.AnimationTest;
*///?}
import software.bernie.geckolib.util.GeckoLibUtil;

public class NativesEntity extends Animal implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private MovementType moveType;

    public enum MovementType { LAND, FLY, WATER, AMPHIBIOUS }

    // Bedrock fall_damage:0 / damage_sensor fall:false species.
    private static final Set<String> FALL_IMMUNE = Set.of(
            "albatross", "bat", "falcon", "fantail", "huhu", "huia", "kakapo", "kea",
            "kereru", "king_shag", "kiwi", "kokako", "kotare", "kotuku", "monarch",
            "pateke", "pukeko", "puriri", "red_admiral", "ruru", "saddleback",
            "takahe", "tui", "wasp", "weka", "whio");

    public NativesEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
        this.moveType = NativesAnimRegistry.getMovementType(NativesAnimRegistry.getId(entityType));
        if (moveType == MovementType.FLY) {
            this.moveControl = new FlyingMoveControl(this, 20, true);
        }
    }

    @Override
//? if <=1.21.1 {
    public boolean causeFallDamage(float fallDistance, float damageMultiplier,
//?} else {
    /*public boolean causeFallDamage(double fallDistance, float damageMultiplier,
*///?}
            net.minecraft.world.damagesource.DamageSource damageSource) {
        if (FALL_IMMUNE.contains(NativesAnimRegistry.getId(this.getType()))) {
            return false;
        }
        return super.causeFallDamage(fallDistance, damageMultiplier, damageSource);
    }

    private MovementType resolveMoveType() {
        String id = NativesAnimRegistry.getId(this.getType());
        return NativesAnimRegistry.getMovementType(id);
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        MovementType mt = moveType != null ? moveType : resolveMoveType();
        if (mt == MovementType.FLY) {
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
        return super.createNavigation(world);
    }

//? if >1.21.1 && <26.3 {
    /*// Late-Mojang hook replacing the Yarn-era canMoveVoluntarily override; final again in 26.3.
    @Override
    public boolean canSimulateMovement() {
        MovementType mt = moveType != null ? moveType : resolveMoveType();
        return mt == MovementType.FLY || super.canSimulateMovement();
    }
*///?}

    // Bedrock tempt/breed items per species (bare ids resolved: fish->cod, dye:0->ink, S4->4 seeds, F7->3 flowers).
    private static final Set<Item> SEEDS = Set.of(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS);
    private static final Set<Item> FLOWERS = Set.of(Items.POPPY, Items.DANDELION, Items.WITHER_ROSE);

    private static final Map<String, Set<Item>> TEMPT = Map.ofEntries(
            Map.entry("albatross", Set.of(Items.COD)),
            Map.entry("falcon", Set.of(Items.RABBIT)),
            Map.entry("frog", Set.of(Items.LILY_PAD)),
            Map.entry("harvestman", Set.of(NativesItems.HUHU_GRUB)),
            Map.entry("hoiho", Set.of(Items.INK_SAC)),
            Map.entry("huhu", Set.of(Items.OAK_SAPLING)),
            Map.entry("humpback_whale", Set.of(Items.SALMON)),
            Map.entry("hura", Set.of(Items.BROWN_MUSHROOM)),
            Map.entry("kea", SEEDS),
            Map.entry("kina", Set.of(Items.KELP)),
            Map.entry("king_shag", Set.of(Items.COD)),
            Map.entry("kiwi", SEEDS),
            Map.entry("korora", Set.of(Items.WHEAT)),
            Map.entry("koura", Set.of(Items.VINE)),
            Map.entry("kunekune", Set.of(Items.WHEAT)),
            Map.entry("moa", Set.of(Items.WHEAT)),
            Map.entry("monarch", FLOWERS),
            Map.entry("papaka", Set.of(Items.KELP)),
            Map.entry("pateke", Set.of(Items.LILY_PAD)),
            Map.entry("puriri", FLOWERS),
            Map.entry("red_admiral", FLOWERS),
            Map.entry("sea_lion", Set.of(Items.COD)),
            Map.entry("takahe", SEEDS),
            Map.entry("tamure", Set.of(Items.SPIDER_EYE)),
            Map.entry("tawaki", Set.of(Items.INK_SAC)),
            Map.entry("wasp", Set.of(Items.SPIDER_EYE)),
            Map.entry("weevil", Set.of(Items.BROWN_MUSHROOM)),
            Map.entry("weka", SEEDS),
            Map.entry("whio", Set.of(Items.SPIDER_EYE))
    );

    private static final Map<String, Set<Item>> BREED = Map.ofEntries(
            Map.entry("bat", Set.of(Items.APPLE, Items.POPPY)),
            Map.entry("falcon", Set.of(Items.RABBIT)),
            Map.entry("humpback_whale", Set.of(Items.SALMON)),
            Map.entry("kakapo", SEEDS),
            Map.entry("kea", SEEDS),
            Map.entry("kereru", SEEDS),
            Map.entry("kina", Set.of(Items.KELP)),
            Map.entry("kiwi", Set.of(NativesItems.HUHU_GRUB)),
            Map.entry("korora", Set.of(Items.COD, Items.SALMON)),
            Map.entry("kotuku", SEEDS),
            Map.entry("koura", Set.of(Items.VINE)),
            Map.entry("kunekune", Set.of(Items.WHEAT)),
            Map.entry("moa", Set.of(Items.WHEAT)),
            Map.entry("papaka", Set.of(Items.VINE)),
            Map.entry("pukeko", SEEDS),
            Map.entry("ruru", SEEDS),
            Map.entry("takahe", SEEDS),
            Map.entry("tamure", Set.of(Items.SPIDER_EYE)),
            Map.entry("tui", SEEDS),
            Map.entry("wasp", Set.of(Items.SPIDER_EYE)),
            Map.entry("weka", SEEDS),
            Map.entry("whio", Set.of(Items.SPIDER_EYE))
    );

    // Upstream issue #11: plant entities drift with water. They stay planted.
    static final Set<String> FLORA = Set.of("basket_fungus", "harakeke", "pohutukawa", "ponga");

    @Override
    public boolean isPushable() {
        return !FLORA.contains(NativesAnimRegistry.getId(this.getType())) && super.isPushable();
    }

    @Override
    public boolean isPushedByFluid() {
        return !FLORA.contains(NativesAnimRegistry.getId(this.getType())) && super.isPushedByFluid();
    }

    @Override
    protected void registerGoals() {
        MovementType mt = resolveMoveType();
        String id = NativesAnimRegistry.getId(this.getType());
        boolean flora = FLORA.contains(id);
        if (!flora) {
            this.goalSelector.addGoal(0, new FloatGoal(this));
        }
        if (id.equals("eel")) {
            this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 10.0f, 1.5, 2.0));
        } else if (id.equals("kokopu")) {
            this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 6.0f, 1.5, 2.0));
        } else if (id.equals("moa")) {
            this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 5.0f, 1.3, 1.8));
        }
        Set<Item> tempt = TEMPT.getOrDefault(id, Set.of());
        if (!tempt.isEmpty()) {
            this.goalSelector.addGoal(1, new TemptGoal(this, 1.1, stack -> tempt.contains(stack.getItem()), false));
        }
        if (BREED.containsKey(id)) {
            this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        }
        if (flora) {
            // no movement goals: plants stay where planted
        } else if (mt == MovementType.FLY) {
            this.goalSelector.addGoal(3, new WanderFlyGoal(this));
        } else if (mt == MovementType.WATER) {
            this.goalSelector.addGoal(3, new WanderSwimGoal(this));
        } else if (mt == MovementType.AMPHIBIOUS) {
            this.goalSelector.addGoal(3, new WanderSwimGoal(this));
            this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0));
        } else {
            this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0));
        }
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.2));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
//? if <=1.21.1 {
        controllers.add(new AnimationController<>(this, "main", 0, state -> {
//?} else {
        /*controllers.add(new AnimationController<>("main", 0, state -> {
*///?}
            NativesAnimRegistry.AnimSet set =
                    NativesAnimRegistry.getAnims(NativesAnimRegistry.getId(this.getType()));
            if (this.isInWater() && set.swim() != null) {
                return loop(state, set.swim());
            }
            if (!this.onGround() && !this.isInWater() && set.fly() != null) {
                return loop(state, set.fly());
            }
            if (state.isMoving() && set.walk() != null) {
                return loop(state, set.walk());
            }
            if (set.still() != null) {
                return loop(state, set.still());
            }
            return PlayState.STOP;
        }));
        String extra = NativesAnimRegistry.getExtra(NativesAnimRegistry.getId(this.getType()));
        if (extra != null) {
//? if <=1.21.1 {
            controllers.add(new AnimationController<>(this, "extra", 0, state -> loop(state, extra)));
//?} else {
            /*controllers.add(new AnimationController<>("extra", 0, state -> loop(state, extra)));
*///?}
        }
    }

//? if <=1.21.1 {
    private static PlayState loop(software.bernie.geckolib.animation.AnimationState<?> state, String anim) {
//?} else {
    /*private static PlayState loop(AnimationTest state, String anim) {
*///?}
        state.setAnimation(RawAnimation.begin().thenLoop(anim));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return NativesSounds.get(NativesAnimRegistry.getId(this.getType()), "say");
    }

    @Override
    protected SoundEvent getHurtSound(net.minecraft.world.damagesource.DamageSource source) {
        SoundEvent hurt = NativesSounds.get(NativesAnimRegistry.getId(this.getType()), "hurt");
        return hurt != null ? hurt : super.getHurtSound(source);
    }

    @Override
    protected SoundEvent getDeathSound() {
        SoundEvent hurt = NativesSounds.get(NativesAnimRegistry.getId(this.getType()), "hurt");
        return hurt != null ? hurt : super.getDeathSound();
    }

    // Bedrock is_baby scales; vanilla babies render 0.5x without this.
    private static final Map<String, Float> BABY_SCALE = Map.ofEntries(
            Map.entry("bat", 0.3f),
            Map.entry("falcon", 0.4f),
            Map.entry("hoiho", 0.4f),
            Map.entry("huhu", 0.33f),
            Map.entry("humpback_whale", 1.0f),
            Map.entry("kakapo", 0.75f),
            Map.entry("kea", 0.4f),
            Map.entry("kereru", 0.4f),
            Map.entry("kina", 0.2f),
            Map.entry("kiwi", 0.33f),
            Map.entry("kotuku", 0.5f),
            Map.entry("koura", 0.3f),
            Map.entry("kunekune", 0.6f),
            Map.entry("moa", 1.5f),
            Map.entry("monarch", 0.33f),
            Map.entry("papaka", 0.3f),
            Map.entry("pukeko", 0.4f),
            Map.entry("sea_lion", 0.4f),
            Map.entry("takahe", 0.5f),
            Map.entry("tamure", 0.5f),
            Map.entry("tawaki", 1.0f),
            Map.entry("tui", 0.4f),
            Map.entry("weka", 0.33f),
            Map.entry("whio", 0.6f)
    );

    @Override
    public float getAgeScale() {
        if (this.isBaby()) {
            Float s = BABY_SCALE.get(NativesAnimRegistry.getId(this.getType()));
            if (s != null) return s;
        }
        return super.getAgeScale();
    }

    @Override
    public boolean isFood(ItemStack stack) {
        Set<Item> breed = BREED.get(NativesAnimRegistry.getId(this.getType()));
        return breed != null && breed.contains(stack.getItem());
    }

    @Override
    @SuppressWarnings("unchecked")
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        return new NativesEntity((EntityType<? extends Animal>) this.getType(), world);
    }
}
