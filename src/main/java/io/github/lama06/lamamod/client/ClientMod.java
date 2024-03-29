package io.github.lama06.lamamod.client;

import io.github.lama06.lamamod.client.events.HudRenderCallback;
import io.github.lama06.lamamod.client.events.MessageSentCallback;
import io.github.lama06.lamamod.client.shortcuts.coordinates.CoordinatesShortcut;
import io.github.lama06.lamamod.client.shortcuts.crash.CrashGameShortcut;
import io.github.lama06.lamamod.client.shortcuts.custom.CustomChatShortcuts;
import io.github.lama06.lamamod.client.shortcuts.lightning.LightningShortcut;
import io.github.lama06.lamamod.client.shortcuts.other.OtherShortcuts;
import io.github.lama06.lamamod.client.shortcuts.time.TimeShortcut;
import io.github.lama06.lamamod.client.shortcuts.tp.TeleportShortcut;
import io.github.lama06.lamamod.client.shortcuts.weather.WeatherShortcut;
import io.github.lama06.lamamod.client.widgets.biome.BiomeWidget;
import io.github.lama06.lamamod.client.widgets.coordinates.CoordinatesWidget;
import io.github.lama06.lamamod.client.widgets.fps.FpsWidget;
import io.github.lama06.lamamod.client.widgets.keystrokes.KeystrokesWidget;
import io.github.lama06.lamamod.client.widgets.lightlevel.LightLevelWidget;
import io.github.lama06.lamamod.client.widgets.players.OnlinePlayersWidget;
import io.github.lama06.lamamod.client.widgets.targetedblock.TargetedBlockWidget;
import io.github.lama06.lamamod.client.widgets.time.TimeWidget;
import io.github.lama06.lamamod.client.widgets.version.VersionWidget;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.network.ServerInfo;

public class ClientMod implements ClientModInitializer {
    public static final ServerInfo lamaServer = new ServerInfo("Lama Server", "lamaserver.ddns.net", false);

    public CoordinatesShortcut coordinatesShortcut = new CoordinatesShortcut();
    public CrashGameShortcut crashGameShortcut = new CrashGameShortcut();
    public TimeShortcut timeShortcut = new TimeShortcut();
    public LightningShortcut lightningShortcut = new LightningShortcut();
    public CustomChatShortcuts customShortcuts = new CustomChatShortcuts();
    public TeleportShortcut teleportShortcut = new TeleportShortcut();
    public OtherShortcuts otherShortcuts = new OtherShortcuts();
    public WeatherShortcut weatherShortcut = new WeatherShortcut();

    public CoordinatesWidget coordinatesWidget = new CoordinatesWidget();
    public FpsWidget fpsWidget = new FpsWidget();
    public VersionWidget versionWidget = new VersionWidget();
    public TimeWidget timeWidget = new TimeWidget();
    public KeystrokesWidget keystrokesWidget = new KeystrokesWidget();
    public OnlinePlayersWidget onlinePlayersWidget = new OnlinePlayersWidget();
    public LightLevelWidget lightLevelWidget = new LightLevelWidget();
    public BiomeWidget biomeWidget = new BiomeWidget();
    public TargetedBlockWidget targetedBlockWidget = new TargetedBlockWidget();

    private void registerListeners() {
        // Shortcuts

        MessageSentCallback.BEFORE_SENT.register(coordinatesShortcut);
        MessageSentCallback.BEFORE_SENT.register(crashGameShortcut);
        MessageSentCallback.BEFORE_SENT.register(timeShortcut);
        MessageSentCallback.BEFORE_SENT.register(lightningShortcut);
        MessageSentCallback.BEFORE_SENT.register(customShortcuts);
        MessageSentCallback.BEFORE_SENT.register(teleportShortcut);
        MessageSentCallback.BEFORE_SENT.register(otherShortcuts);
        MessageSentCallback.BEFORE_SENT.register(weatherShortcut);

        // Widgets

        HudRenderCallback.EVENT.register(coordinatesWidget);
        HudRenderCallback.EVENT.register(fpsWidget);
        HudRenderCallback.EVENT.register(versionWidget);
        HudRenderCallback.EVENT.register(timeWidget);
        HudRenderCallback.EVENT.register(keystrokesWidget);
        HudRenderCallback.EVENT.register(onlinePlayersWidget);
        HudRenderCallback.EVENT.register(lightLevelWidget);
        HudRenderCallback.EVENT.register(biomeWidget);
        HudRenderCallback.EVENT.register(targetedBlockWidget);

        MessageSentCallback.BEFORE_SENT.register(coordinatesWidget);
        MessageSentCallback.BEFORE_SENT.register(fpsWidget);
        MessageSentCallback.BEFORE_SENT.register(versionWidget);
        MessageSentCallback.BEFORE_SENT.register(timeWidget);
        MessageSentCallback.BEFORE_SENT.register(keystrokesWidget);
        MessageSentCallback.BEFORE_SENT.register(onlinePlayersWidget);
        MessageSentCallback.BEFORE_SENT.register(lightLevelWidget);
        MessageSentCallback.BEFORE_SENT.register(biomeWidget);
        MessageSentCallback.BEFORE_SENT.register(targetedBlockWidget);
    }

    @Override
    public void onInitializeClient() {
        registerListeners();
    }
}
