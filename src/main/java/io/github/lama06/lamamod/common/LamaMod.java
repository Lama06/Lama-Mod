package io.github.lama06.lamamod.common;

import io.github.lama06.lamamod.common.version.GithubRelease;
import io.github.lama06.lamamod.common.version.ModVersion;
import net.fabricmc.api.ModInitializer;

public class LamaMod implements ModInitializer {
    public static final ModVersion currentModVersion = new ModVersion(1, 6, 4);

    @Override
    public void onInitialize() {
        System.out.println("Die Lama Mod wurde geleaden");

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
