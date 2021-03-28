package io.github.lama06.lamamod.options;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.lama06.lamamod.LamaMod;
import io.github.lama06.lamamod.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.shortcuts.coordinates.CoordinatesShortcutOptions;
import io.github.lama06.lamamod.shortcuts.custom.CustomShortcutOptions;
import io.github.lama06.lamamod.util.Util;
import io.github.lama06.lamamod.widgets.biome.BiomeWidgetOptions;
import io.github.lama06.lamamod.widgets.coordinates.CoordinateWidgetOptions;
import io.github.lama06.lamamod.widgets.fps.FpsWidgetOptions;
import io.github.lama06.lamamod.widgets.keystrokes.KeystrokesWidgetOptions;
import io.github.lama06.lamamod.widgets.lightlevel.LightLevelWidgetOptions;
import io.github.lama06.lamamod.widgets.players.OnlinePlayersWidgetOptions;
import io.github.lama06.lamamod.widgets.targetedblock.TargetedBlockWidgetOptions;
import io.github.lama06.lamamod.widgets.time.TimeWidgetOptions;
import io.github.lama06.lamamod.widgets.version.VersionWidgetOptions;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.IOException;

public class Options {
    public ShortcutOptions timeShortcut = new ShortcutOptions();
    public CoordinatesShortcutOptions coordinatesShortcut = new CoordinatesShortcutOptions();
    public ShortcutOptions crashGameShortcut = new ShortcutOptions();
    public ShortcutOptions lightningShortcut = new ShortcutOptions();
    public CustomShortcutOptions customShortcuts = new CustomShortcutOptions();
    public ShortcutOptions tpShortcut = new ShortcutOptions();
    public ShortcutOptions otherShortcuts = new ShortcutOptions();
    public ShortcutOptions weatherShortcut = new ShortcutOptions();

    public CoordinateWidgetOptions coordinatesWidget = new CoordinateWidgetOptions();
    public FpsWidgetOptions fpsWidget = new FpsWidgetOptions();
    public VersionWidgetOptions versionWidget = new VersionWidgetOptions();
    public TimeWidgetOptions timeWidget = new TimeWidgetOptions();
    public KeystrokesWidgetOptions keystrokesWidget = new KeystrokesWidgetOptions();
    public OnlinePlayersWidgetOptions onlinePlayersWidget = new OnlinePlayersWidgetOptions();
    public LightLevelWidgetOptions lightLevelWidget = new LightLevelWidgetOptions();
    public BiomeWidgetOptions biomeWidget = new BiomeWidgetOptions();
    public TargetedBlockWidgetOptions targetedBlockWidget = new TargetedBlockWidgetOptions();

    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static final String optionsFileName = client.runDirectory.getPath() + "/lama-mod-options.json";

    private static Options cachedOptions = null;

    /**
     * Gibt die Optionen der Mod zurück
     */
    public static Options getOptions() {
        try {
            if (cachedOptions != null) {
                return cachedOptions;
            }

            File file = new File(optionsFileName);
            if (!file.exists()) {
                file.createNewFile();
                Util.writeToFile(file, "{}");
            }

            String optionsFileContent = Util.readFromFile(file);
            Gson gson = getGson();
            Options options = gson.fromJson(optionsFileContent, Options.class);

            cachedOptions = options;
            return options;
        } catch (IOException e) {
            LamaMod.handleException(e);
            return new Options();
        }
    }

    /**
     * Setzt die Optionen der Mod
     */
    public static void setOptions(Options options) {
        try {
            cachedOptions = options;

            File file = new File(optionsFileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            Gson gson = getGson();
            String optionsJson = gson.toJson(options, Options.class);
            Util.writeToFile(file, optionsJson);
        } catch (IOException e) {
            LamaMod.handleException(e);
        }
    }

    /**
     * Gibt ein GSON Objekt zurück, dass zum Serialisieren und Deserialisieren
     * der Einstellungen verwendet werden sollte
     */
    private static Gson getGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
}
