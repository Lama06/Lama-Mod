package io.github.lama06.lamamod.gui;

import io.github.lama06.lamamod.LamaMod;
import io.github.lama06.lamamod.util.Util;
import io.github.lama06.lamamod.version.GithubRelease;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;

/**
 * Ein Knopf, der einen auf den Lama Server bringt
 */
public class JoinLamaServerButton extends ButtonWidget {
    public JoinLamaServerButton(Screen parent, int x, int y, int width, int height) {
        super(x, y, width, height, new LiteralText("Lama Server"), (button) -> {
            if(GithubRelease.isNewerModVersionAvailable()) {
                MinecraftClient.getInstance().openScreen(new NewVersionAvailableScreen(() -> {
                    Util.connectToServer(parent, LamaMod.lamaServer);
                }));
            } else {
                Util.connectToServer(parent, LamaMod.lamaServer);
            }
        });
    }

    public JoinLamaServerButton(Screen parent) {
        this(parent, 20, 20, 200, 20);
    }
}
