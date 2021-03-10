package io.github.lama06.lamamod.options;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.lama06.lamamod.LamaMod;
import io.github.lama06.lamamod.hud.CoordinatesWidgetOptions;
import io.github.lama06.lamamod.hud.TimeWidgetOptions;
import io.github.lama06.lamamod.hud.WidgetOptions;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.IOException;

public class Options {
    public ChatShortcutOptions[] customChatShortcuts = { new ChatShortcutOptions("Hallo", "Hallo Welt") };

    public CoordinatesWidgetOptions coordinatesWidget = new CoordinatesWidgetOptions(true, 20, 20, true);
    public WidgetOptions fpsWidget = new WidgetOptions(true, 20, 40);
    public WidgetOptions versionWidget = new WidgetOptions(true, 20, 60);
    public TimeWidgetOptions timeWidget = new TimeWidgetOptions(true, 20, 80, true, true);
    public WidgetOptions keystrokesWidget = new WidgetOptions(true, 20, 100);

    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static final String optionsFileName = client.runDirectory.getPath() + "/lama-mod-options.json";

    private static Options cachedOptions = null;

    /**
     * Gibt die Optionen der Mod zurück
     */
    public static Options getOptions() {
        try {
            if(cachedOptions != null) {
                return cachedOptions;
            }

            File file = new File(optionsFileName);
            if(!file.exists()) {
                file.createNewFile();
                Util.writeToFile(file, "{}");
            }

            String optionsFileContent = Util.readFromFile(file);
            Gson gson = new Gson();
            Options options = gson.fromJson(optionsFileContent, Options.class);

            cachedOptions = options;
            return options;
        } catch (IOException e) {
            LamaMod.handleException(e);
            return null;
        }
    }

    /**
     * Setzt die Optionen der Mod
     */
    public static void setOptions(Options options) {
        try {
            cachedOptions = options;

            File file = new File(optionsFileName);
            if(!file.exists()) {
                file.createNewFile();
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String optionsJson = gson.toJson(options, Options.class);
            Util.writeToFile(file, optionsJson);
        } catch (IOException e) {
            LamaMod.handleException(e);
        }
    }
}
