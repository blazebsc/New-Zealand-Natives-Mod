package blake7.newzealandnativesmod.registry;

import blake7.newzealandnativesmod.NativesId;
//? if <26.1 {
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
//?}
//? if >=26.1 {
/*import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
*///?}
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public final class NativesItemGroups {
    private NativesItemGroups() {}

    public static final CreativeModeTab NATIVES =
//? if <26.1 {
            FabricItemGroup.builder()
//?} else {
            /*FabricCreativeModeTab.builder()
*///?}
            .title(Component.translatable("itemGroup.newzealandnatives.natives"))
            .icon(() -> new ItemStack(NativesItems.KIWI_SPAWN_EGG))
            .displayItems((params, output) -> {
                for (var egg : NativesItems.spawnEggs().values()) {
                    output.accept(egg);
                }
                output.accept(NativesItems.HUHU_GRUB);
                output.accept(NativesBlocks.ROTTEN_LOG);
                output.accept(NativesBlocks.KOWHAI_LOG);
                output.accept(NativesBlocks.KOWHAI_LEAVES);
                output.accept(NativesBlocks.KOWHAI_SAPLING);
            })
            .build();

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                NativesId.of("natives"), NATIVES);
    }
}
