package blake7.newzealandnativesmod.client;

import blake7.newzealandnativesmod.registry.NativesConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class NativesConfigScreen extends Screen {
    private final Screen parent;
    private final NativesConfig cfg = NativesConfig.INSTANCE;

    public NativesConfigScreen(Screen parent) {
        super(Text.literal("New Zealand Natives Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int w = 200;
        int x = this.width / 2 - w / 2;
        int y = 40;
        int h = 20;
        int gap = 24;

        this.addDrawableChild(CyclingButtonWidget.onOffBuilder(cfg.spawnLand)
                .build(x, y, w, h, Text.literal("Spawn Land"),
                        (btn, val) -> cfg.spawnLand = val));
        y += gap;
        this.addDrawableChild(CyclingButtonWidget.onOffBuilder(cfg.spawnWater)
                .build(x, y, w, h, Text.literal("Spawn Water"),
                        (btn, val) -> cfg.spawnWater = val));
        y += gap;
        this.addDrawableChild(CyclingButtonWidget.onOffBuilder(cfg.spawnMonsters)
                .build(x, y, w, h, Text.literal("Spawn Monsters"),
                        (btn, val) -> cfg.spawnMonsters = val));
        y += gap;
        this.addDrawableChild(CyclingButtonWidget.onOffBuilder(cfg.spawnPlants)
                .build(x, y, w, h, Text.literal("Spawn Plants"),
                        (btn, val) -> cfg.spawnPlants = val));
        y += gap;
        this.addDrawableChild(CyclingButtonWidget.onOffBuilder(cfg.eagleHostile)
                .build(x, y, w, h, Text.literal("Haast's Eagle Hostile"),
                        (btn, val) -> cfg.eagleHostile = val));
        y += gap;
        this.addDrawableChild(CyclingButtonWidget.onOffBuilder(cfg.poison)
                .build(x, y, w, h, Text.literal("Katipo Poison"),
                        (btn, val) -> cfg.poison = val));
        y += gap;
        this.addDrawableChild(CyclingButtonWidget.onOffBuilder(cfg.fallenLogs)
                .build(x, y, w, h, Text.literal("Fallen Logs"),
                        (btn, val) -> cfg.fallenLogs = val));
        y += gap;
        this.addDrawableChild(new SliderWidget(x, y, w, h,
                Text.literal("Spawn Rate: " + cfg.spawnRate), (cfg.spawnRate - 0.25) / 2.75) {
            @Override
            protected void updateMessage() {
                this.setMessage(Text.literal("Spawn Rate: " + NativesConfigScreen.this.cfg.spawnRate));
            }

            @Override
            protected void applyValue() {
                NativesConfigScreen.this.cfg.spawnRate = 0.25 + Math.round(this.value * 11) * 0.25;
            }
        });
        y += gap + 4;
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Done"), btn -> this.close())
                .dimensions(x, y, w, h).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
    }

    @Override
    public void close() {
        if (this.client != null) this.client.setScreen(this.parent);
    }

    @Override
    public void removed() {
        super.removed();
        NativesConfig.save();
    }
}
