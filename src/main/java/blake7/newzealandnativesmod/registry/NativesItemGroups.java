package blake7.newzealandnativesmod.registry;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class NativesItemGroups {
    private NativesItemGroups() {}

    public static final CreativeModeTab NATIVES = FabricCreativeModeTab.builder()
            .title(Component.translatable("itemGroup.newzealandnatives.natives"))
            .icon(() -> new ItemStack(NativesItems.KIWI_SPAWN_EGG))
            .displayItems((ctx, entries) -> {
                for (var egg : NativesItems.spawnEggs().values()) {
                    entries.accept(egg);
                }
                entries.accept(NativesItems.HUHU_GRUB);
                entries.accept(NativesBlocks.ROTTEN_LOG);
                entries.accept(NativesBlocks.KOWHAI_LOG);
                entries.accept(NativesBlocks.KOWHAI_LEAVES);
                entries.accept(NativesBlocks.KOWHAI_SAPLING);
            })
            .build();

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath("newzealandnatives", "natives"), NATIVES);
    }
}
