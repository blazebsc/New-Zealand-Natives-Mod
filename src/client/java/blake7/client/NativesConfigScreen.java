package blake7.client;

import blake7.newzealandnativesmod.registry.NativesConfig;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class NativesConfigScreen extends Screen {
    private final Screen parent;
    private final NativesConfig cfg = NativesConfig.INSTANCE;

    public NativesConfigScreen(Screen parent) {
        super(Component.literal("New Zealand Natives Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int w = 200;
        int x = this.width / 2 - w / 2;
        int y = 40;
        int h = 20;
        int gap = 24;

        this.addRenderableWidget(CycleButton.onOffBuilder(cfg.spawnLand)
                .create(x, y, w, h, Component.literal("Spawn Land"),
                        (btn, val) -> cfg.spawnLand = val));
        y += gap;
        this.addRenderableWidget(CycleButton.onOffBuilder(cfg.spawnWater)
                .create(x, y, w, h, Component.literal("Spawn Water"),
                        (btn, val) -> cfg.spawnWater = val));
        y += gap;
        this.addRenderableWidget(CycleButton.onOffBuilder(cfg.spawnMonsters)
                .create(x, y, w, h, Component.literal("Spawn Monsters"),
                        (btn, val) -> cfg.spawnMonsters = val));
        y += gap;
        this.addRenderableWidget(CycleButton.onOffBuilder(cfg.spawnPlants)
                .create(x, y, w, h, Component.literal("Spawn Plants"),
                        (btn, val) -> cfg.spawnPlants = val));
        y += gap;
        this.addRenderableWidget(CycleButton.onOffBuilder(cfg.eagleHostile)
                .create(x, y, w, h, Component.literal("Haast's Eagle Hostile"),
                        (btn, val) -> cfg.eagleHostile = val));
        y += gap;
        this.addRenderableWidget(CycleButton.onOffBuilder(cfg.poison)
                .create(x, y, w, h, Component.literal("Katipo Poison"),
                        (btn, val) -> cfg.poison = val));
        y += gap;
        this.addRenderableWidget(CycleButton.onOffBuilder(cfg.fallenLogs)
                .create(x, y, w, h, Component.literal("Fallen Logs"),
                        (btn, val) -> cfg.fallenLogs = val));
        y += gap;
        this.addRenderableWidget(CycleButton.onOffBuilder(cfg.kowhaiTrees)
                .create(x, y, w, h, Component.literal("Kowhai Trees"),
                        (btn, val) -> cfg.kowhaiTrees = val));
        y += gap;
        this.addRenderableWidget(new AbstractSliderButton(x, y, w, h,
                Component.literal("Spawn Rate: " + cfg.spawnRate), (cfg.spawnRate - 0.25) / 2.75) {
            @Override
            protected void updateMessage() {
                this.setMessage(Component.literal("Spawn Rate: " + NativesConfigScreen.this.cfg.spawnRate));
            }

            @Override
            protected void applyValue() {
                NativesConfigScreen.this.cfg.spawnRate = 0.25 + Math.round(this.value * 11) * 0.25;
            }
        });
        y += gap + 4;
        this.addRenderableWidget(Button.builder(Component.literal("Done"),
                btn -> this.onClose()).bounds(x, y, w, h).build());
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
        graphics.centeredText(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) this.minecraft.setScreenAndShow(this.parent);
    }

    @Override
    public void removed() {
        super.removed();
        NativesConfig.save();
    }
}
