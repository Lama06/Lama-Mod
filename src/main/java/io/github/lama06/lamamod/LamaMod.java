package io.github.lama06.lamamod;

import io.github.lama06.lamamod.events.HudRenderCallback;
import io.github.lama06.lamamod.events.MessageSentCallback;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.shortcuts.coordinates.CoordinatesShortcut;
import io.github.lama06.lamamod.shortcuts.crash.CrashGameShortcut;
import io.github.lama06.lamamod.shortcuts.custom.CustomChatShortcuts;
import io.github.lama06.lamamod.shortcuts.lightning.LightningShortcut;
import io.github.lama06.lamamod.shortcuts.other.OtherShortcuts;
import io.github.lama06.lamamod.shortcuts.time.TimeShortcut;
import io.github.lama06.lamamod.shortcuts.tp.TeleportShortcut;
import io.github.lama06.lamamod.version.GithubRelease;
import io.github.lama06.lamamod.version.ModVersion;
import io.github.lama06.lamamod.widgets.coordinates.CoordinatesWidget;
import io.github.lama06.lamamod.widgets.fps.FpsWidget;
import io.github.lama06.lamamod.widgets.keystrokes.KeystrokesWidget;
import io.github.lama06.lamamod.widgets.lightlevel.LightLevelWidget;
import io.github.lama06.lamamod.widgets.lightlevel.LightLevelWidgetOptions;
import io.github.lama06.lamamod.widgets.players.OnlinePlayersWidget;
import io.github.lama06.lamamod.widgets.time.TimeWidget;
import io.github.lama06.lamamod.widgets.version.VersionWidget;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.network.ServerInfo;

public class LamaMod implements ClientModInitializer {
    public static final ModVersion currentModVersion = new ModVersion(1, 4, 0);
    public static final ServerInfo lamaServer = new ServerInfo("Lama Server", "lamaserver.ddns.net", false);

    public CoordinatesShortcut coordinatesShortcut = new CoordinatesShortcut();
    public CrashGameShortcut crashGameShortcut = new CrashGameShortcut();
    public TimeShortcut timeShortcut = new TimeShortcut();
    public LightningShortcut lightningShortcut = new LightningShortcut();
    public CustomChatShortcuts customShortcuts = new CustomChatShortcuts();
    public TeleportShortcut teleportShortcut = new TeleportShortcut();
    public OtherShortcuts otherShortcuts = new OtherShortcuts();

    public CoordinatesWidget coordinatesWidget = new CoordinatesWidget();
    public FpsWidget fpsWidget = new FpsWidget();
    public VersionWidget versionWidget = new VersionWidget();
    public TimeWidget timeWidget = new TimeWidget();
    public KeystrokesWidget keystrokesWidget = new KeystrokesWidget();
    public OnlinePlayersWidget onlinePlayersWidget = new OnlinePlayersWidget();
    public LightLevelWidget lightLevelWidget = new LightLevelWidget();

    private void registerListeners() {
        // Shortcuts

        MessageSentCallback.BEFORE_SENT.register(coordinatesShortcut);
        MessageSentCallback.BEFORE_SENT.register(crashGameShortcut);
        MessageSentCallback.BEFORE_SENT.register(timeShortcut);
        MessageSentCallback.BEFORE_SENT.register(lightningShortcut);
        MessageSentCallback.BEFORE_SENT.register(customShortcuts);
        MessageSentCallback.BEFORE_SENT.register(teleportShortcut);
        MessageSentCallback.BEFORE_SENT.register(otherShortcuts);

        // Widgets

        HudRenderCallback.EVENT.register(coordinatesWidget);
        HudRenderCallback.EVENT.register(fpsWidget);
        HudRenderCallback.EVENT.register(versionWidget);
        HudRenderCallback.EVENT.register(timeWidget);
        HudRenderCallback.EVENT.register(keystrokesWidget);
        HudRenderCallback.EVENT.register(onlinePlayersWidget);
        HudRenderCallback.EVENT.register(lightLevelWidget);

        MessageSentCallback.BEFORE_SENT.register(coordinatesWidget);
        MessageSentCallback.BEFORE_SENT.register(fpsWidget);
        MessageSentCallback.BEFORE_SENT.register(versionWidget);
        MessageSentCallback.BEFORE_SENT.register(timeWidget);
        MessageSentCallback.BEFORE_SENT.register(keystrokesWidget);
        MessageSentCallback.BEFORE_SENT.register(onlinePlayersWidget);
        MessageSentCallback.BEFORE_SENT.register(lightLevelWidget);
    }

    @Override
    public void onInitializeClient() {
        System.out.println("Die Lama Mod wurde geleaden");

        registerListeners();

        Options.setOptions(Options.getOptions());

        if (GithubRelease.isNewerModVersionAvailable()) {
            GithubRelease newestRelease = GithubRelease.fetchNewestRelease();
            System.out.println("Es ist eine neuere Version der Lama Mod verfügbar: " + newestRelease.versionTag);
        } else {
            System.out.println("Deine Verison der Lama Mod ist die neuste: " + currentModVersion.toVersionTag());
        }
    }

    public static void handleException(Exception e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
}
