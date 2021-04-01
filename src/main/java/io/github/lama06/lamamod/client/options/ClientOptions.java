package io.github.lama06.lamamod.client.options;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.lama06.lamamod.common.LamaMod;
import io.github.lama06.lamamod.client.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.client.shortcuts.coordinates.CoordinatesShortcutOptions;
import io.github.lama06.lamamod.client.shortcuts.custom.CustomShortcutOptions;
import io.github.lama06.lamamod.client.util.ClientUtil;
import io.github.lama06.lamamod.client.widgets.biome.BiomeWidgetOptions;
import io.github.lama06.lamamod.client.widgets.coordinates.CoordinateWidgetOptions;
import io.github.lama06.lamamod.client.widgets.fps.FpsWidgetOptions;
import io.github.lama06.lamamod.client.widgets.keystrokes.KeystrokesWidgetOptions;
import io.github.lama06.lamamod.client.widgets.lightlevel.LightLevelWidgetOptions;
import io.github.lama06.lamamod.client.widgets.players.OnlinePlayersWidgetOptions;
import io.github.lama06.lamamod.client.widgets.targetedblock.TargetedBlockWidgetOptions;
import io.github.lama06.lamamod.client.widgets.time.TimeWidgetOptions;
import io.github.lama06.lamamod.client.widgets.version.VersionWidgetOptions;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.IOException;

public class ClientOptions {
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

    private static ClientOptions cachedOptions = null;

    /**
     * Gibt die Optionen der Mod zurück
     */
    public static ClientOptions getOptions() {
        try {
            if (cachedOptions != null) {
                return cachedOptions;
            }

            File file = new File(optionsFileName);
            if (!file.exists()) {
                file.createNewFile();
                ClientUtil.writeToFile(file, "{}");
            }

            String optionsFileContent = ClientUtil.readFromFile(file);
            Gson gson = getGson();
            ClientOptions options = gson.fromJson(optionsFileContent, ClientOptions.class);

            cachedOptions = options;
            return options;
        } catch (IOException e) {
            LamaMod.handleException(e);
            return new ClientOptions();
        }
    }

    /**
     * Setzt die Optionen der Mod
     */
    public static void setOptions(ClientOptions options) {
        try {
            cachedOptions = options;

            File file = new File(optionsFileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            Gson gson = getGson();
            String optionsJson = gson.toJson(options, ClientOptions.class);
            ClientUtil.writeToFile(file, optionsJson);
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
