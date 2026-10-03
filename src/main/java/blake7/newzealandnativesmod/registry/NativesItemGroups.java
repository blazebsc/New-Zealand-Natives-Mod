package blake7.newzealandnativesmod.registry;

import blake7.newzealandnativesmod.NativesId;
//? if <26.1 {
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
//?}
//? if >=26.1 {
/*import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
*///?}
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
//? if >=26.1 {
/*import net.minecraft.core.registries.BuiltInRegistries;
*///?}
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class NativesItemGroups {
    private NativesItemGroups() {}

    public static final ItemGroup NATIVES =
//? if <26.1 {
            FabricItemGroup.builder()
//?} else {
            /*FabricCreativeModeTab.builder()
*///?}
            .displayName(Text.translatable("itemGroup.newzealandnatives.natives"))
            .icon(() -> new ItemStack(NativesItems.KIWI_SPAWN_EGG))
            .entries((params, output) -> {
                for (var egg : NativesItems.spawnEggs().values()) {
                    output.add(egg);
                }
                output.add(NativesItems.HUHU_GRUB);
                output.add(NativesBlocks.ROTTEN_LOG);
                output.add(NativesBlocks.KOWHAI_LOG);
                output.add(NativesBlocks.KOWHAI_LEAVES);
                output.add(NativesBlocks.KOWHAI_SAPLING);
            })
            .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP,
                NativesId.of("natives"), NATIVES);
    }
}
