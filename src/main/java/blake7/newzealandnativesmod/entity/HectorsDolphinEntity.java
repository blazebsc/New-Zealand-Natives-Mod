package blake7.newzealandnativesmod.entity;

import blake7.newzealandnativesmod.registry.NativesSounds;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.dolphin.Dolphin;
import net.minecraft.world.level.Level;

// Bedrock uses vanilla dolphin geometry/animations, so this extends DolphinEntity
// (swim AI, breaching, treasure-seeking come free) with a custom texture + sounds.
public class HectorsDolphinEntity extends Dolphin {
    public static final EntityType<HectorsDolphinEntity> TYPE = EntityType.Builder.of(HectorsDolphinEntity::new, MobCategory.WATER_CREATURE)
            .sized(0.9f, 0.6f)
            .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("newzealandnatives", "hectors_dolphin")));

    public HectorsDolphinEntity(EntityType<? extends Dolphin> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return NativesSounds.get("hectors_dolphin", "say");
    }
}
