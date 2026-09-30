package blake7.newzealandnativesmod.entity;

import blake7.newzealandnativesmod.registry.NativesSounds;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Dolphin;
//? if >1.21.1 {
/*import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
*///?}
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

// Bedrock uses vanilla dolphin geometry/animations, so this extends Dolphin
// (swim AI, breaching, treasure-seeking come free) with a custom texture + sounds.
public class HectorsDolphinEntity extends Dolphin {
    public static final EntityType<HectorsDolphinEntity> TYPE = EntityType.Builder.of(HectorsDolphinEntity::new, MobCategory.WATER_CREATURE)
            .sized(0.9f, 0.6f)
//? if <=1.21.1 {
            .build(ResourceLocation.fromNamespaceAndPath("newzealandnatives", "hectors_dolphin").toString());
//?} else {
            /*.build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("newzealandnatives", "hectors_dolphin")));
*///?}

    public HectorsDolphinEntity(EntityType<? extends Dolphin> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return NativesSounds.get("hectors_dolphin", "say");
    }
}
