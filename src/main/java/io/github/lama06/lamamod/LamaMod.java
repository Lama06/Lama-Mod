package io.github.lama06.lamamod;

import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.version.GithubRelease;
import io.github.lama06.lamamod.version.ModVersion;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;

public class LamaMod implements ClientModInitializer {
    public static final ModVersion currentModVersion = new ModVersion(1, 0, 0);
    public static final ServerInfo lamaServer = new ServerInfo("Lama Server", "lamaserver.ddns.net", false);
    public static final MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {
        System.out.println("Die Lama Mod wurde geleaden");

        Options.setOptions(Options.getOptions());

        if(GithubRelease.isNewerModVersionAvailable()) {
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
