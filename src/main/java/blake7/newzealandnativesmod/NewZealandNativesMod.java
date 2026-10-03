package blake7.newzealandnativesmod;

import blake7.newzealandnativesmod.entity.KiwiEntity;
import blake7.newzealandnativesmod.entity.KatipoEntity;
import blake7.newzealandnativesmod.entity.HectorsDolphinEntity;
import blake7.newzealandnativesmod.entity.RuruEntity;
import blake7.newzealandnativesmod.entity.NativesEntity;
import blake7.newzealandnativesmod.registry.NativesEntities;
import blake7.newzealandnativesmod.registry.NativesConfig;
import blake7.newzealandnativesmod.registry.SpeciesEntities;
import blake7.newzealandnativesmod.registry.NativesItemGroups;
import blake7.newzealandnativesmod.registry.NativesBlocks;
import blake7.newzealandnativesmod.worldgen.FallenLogFeature;
import blake7.newzealandnativesmod.registry.NativesItems;
import blake7.newzealandnativesmod.registry.NativesSounds;
import blake7.newzealandnativesmod.registry.NativesSpawns;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewZealandNativesMod implements ModInitializer {
    public static final String MOD_ID = "newzealandnatives";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        NativesConfig.load();
        NativesEntities.register();
        NativesItems.register();
        NativesBlocks.register();
        FallenLogFeature.register();
        NativesItemGroups.register();
        NativesSounds.register();
        NativesSpawns.register();

        FabricDefaultAttributeRegistry.register(KiwiEntity.TYPE,
                KiwiEntity.createMobAttributes()
//? if <=1.21.1 {
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D)
                        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D)
//?} else {
                        /*.add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D)
                        .add(EntityAttributes.TEMPT_RANGE, 10.0D)
                        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D)
*///?}
                        .build());

        FabricDefaultAttributeRegistry.register(KatipoEntity.TYPE, KatipoEntity.createDolphinAttributes());
        FabricDefaultAttributeRegistry.register(HectorsDolphinEntity.TYPE,
                HectorsDolphinEntity.createDolphinAttributes().build());
        FabricDefaultAttributeRegistry.register(RuruEntity.TYPE,
                RuruEntity.createMobAttributes()
//? if <=1.21.1 {
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4D)
                        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D)
                        .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4D)
//?} else {
                        /*.add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4D)
                        .add(EntityAttributes.TEMPT_RANGE, 10.0D)
                        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D)
                        .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4D)
*///?}
                        .build());

        var allTypes = NativesEntities.all();
        var allSpecies = NativesEntities.SPECIES;
        for (int i = 0; i < allSpecies.length; i++) {
            String id = allSpecies[i].shortId();
            if (id.equals("kiwi") || id.equals("katipo") || id.equals("hectors_dolphin") || id.equals("ruru")) continue;
            if (i < allTypes.size()) {
                EntityType<?> type = allTypes.get(i);
                @SuppressWarnings("unchecked")
                EntityType<? extends net.minecraft.entity.LivingEntity> livingType =
                        (EntityType<? extends net.minecraft.entity.LivingEntity>) type;
                double[] stats = SpeciesEntities.statsFor(allSpecies[i]);
                var builder = NativesEntity.createMobAttributes()
//? if <=1.21.1 {
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, stats[0])
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, stats[1])
                        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, id.equals("haasts_eagle") ? 64.0D : 16.0D)
                        .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4D);
//?} else {
                        /*.add(EntityAttributes.GENERIC_MAX_HEALTH, stats[0])
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, stats[1])
                        .add(EntityAttributes.TEMPT_RANGE, 10.0D)
                        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, id.equals("haasts_eagle") ? 64.0D : 16.0D)
                        .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4D);
*///?}
                if (id.equals("haasts_eagle")) {
//? if <=1.21.1 {
                    builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D);
//?} else {
                    /*builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D);
*///?}
                }
                FabricDefaultAttributeRegistry.register(livingType, builder.build());
            }
        }

        LOGGER.info("New Zealand Natives Mod initialized — {} entities, {} items registered.",
                NativesEntities.size(), NativesItems.size());
    }
}
