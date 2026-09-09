package blake7.newzealandnativesmod.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class NativesConfig {
    public boolean spawnLand = true;
    public boolean spawnWater = true;
    public boolean spawnMonsters = true;
    public boolean spawnPlants = true;
    public double spawnRate = 1.0;
    public boolean eagleHostile = true;
    public boolean poison = true;
    public boolean fallenLogs = true;
    public boolean kowhaiTrees = true;

    public static NativesConfig INSTANCE = new NativesConfig();

    private NativesConfig() {}

    public static void load() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("newzealandnatives.json");
        if (!Files.exists(path)) return;
        try (Reader reader = Files.newBufferedReader(path)) {
            NativesConfig loaded = new Gson().fromJson(reader, NativesConfig.class);
            if (loaded != null) INSTANCE = loaded;
        } catch (IOException | JsonSyntaxException ignored) {
        }
    }

    public static void save() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("newzealandnatives.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = Files.newBufferedWriter(path)) {
            gson.toJson(INSTANCE, writer);
        } catch (IOException ignored) {
        }
    }
}
