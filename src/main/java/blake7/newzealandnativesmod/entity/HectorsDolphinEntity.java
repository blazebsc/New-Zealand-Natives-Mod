package blake7.newzealandnativesmod.entity;

import blake7.newzealandnativesmod.registry.NativesSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

// Bedrock uses vanilla dolphin geometry/animations, so this extends DolphinEntity
// (swim AI, breaching, treasure-seeking come free) with a custom texture + sounds.
public class HectorsDolphinEntity extends DolphinEntity {
    public static final EntityType<HectorsDolphinEntity> TYPE = EntityType.Builder.create(HectorsDolphinEntity::new, SpawnGroup.WATER_CREATURE)
            .dimensions(0.9f, 0.6f)
            .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of("newzealandnatives", "hectors_dolphin")));

    public HectorsDolphinEntity(EntityType<? extends DolphinEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return NativesSounds.get("hectors_dolphin", "say");
    }
}
