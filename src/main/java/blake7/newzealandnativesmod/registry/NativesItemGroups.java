package blake7.newzealandnativesmod.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class NativesItemGroups {
    private NativesItemGroups() {}

    public static final ItemGroup NATIVES = FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.newzealandnatives.natives"))
            .icon(() -> new ItemStack(NativesItems.KIWI_SPAWN_EGG))
            .entries((ctx, entries) -> {
                for (var egg : NativesItems.spawnEggs().values()) {
                    entries.add(egg);
                }
                entries.add(NativesItems.HUHU_GRUB);
                entries.add(NativesBlocks.ROTTEN_LOG);
            })
            .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP,
                Identifier.of("newzealandnatives", "natives"), NATIVES);
    }
}
