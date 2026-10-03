package blake7.newzealandnativesmod.entity;

import blake7.newzealandnativesmod.NativesId;
import blake7.newzealandnativesmod.registry.NativesSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
//? if >=26.1 {
/*import net.minecraft.world.entity.animal.dolphin.Dolphin;
*///?} else {
import net.minecraft.entity.passive.DolphinEntity;
//?}
//? if >1.21.1 {
/*import net.minecraft.registry.RegistryKey;

import net.minecraft.registry.RegistryKeys;
*///?}
//? if >=26.1 {
/*import net.minecraft.core.registries.Registries;
*///?}
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

// Bedrock uses vanilla dolphin geometry/animations, so this extends Dolphin
// (swim AI, breaching, treasure-seeking come free) with a custom texture + sounds.
public class HectorsDolphinEntity extends DolphinEntity {
    public static final EntityType<HectorsDolphinEntity> TYPE = EntityType.Builder.create(HectorsDolphinEntity::new, SpawnGroup.WATER_CREATURE)
            //? if <=1.20.4 {
                /*.setDimensions(0.9f, 0.6f)
            *///?}
            //? if >1.20.4 && <26.1 {
                /*.dimensions(0.9f, 0.6f)
            *///?}
            //? if >=26.1 {
                /*.sized(0.9f, 0.6f)
            *///?}
//? if <=1.21.1 {
            .build(NativesId.of("hectors_dolphin").toString());
//?} else {
            /*.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, NativesId.of("hectors_dolphin")));
*///?}

    public HectorsDolphinEntity(EntityType<? extends DolphinEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return NativesSounds.get("hectors_dolphin", "say");
    }
}
