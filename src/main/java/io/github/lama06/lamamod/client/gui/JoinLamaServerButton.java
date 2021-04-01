package io.github.lama06.lamamod.client.gui;

import io.github.lama06.lamamod.client.ClientMod;
import io.github.lama06.lamamod.client.util.ClientUtil;
import io.github.lama06.lamamod.common.version.GithubRelease;
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
            if (GithubRelease.isNewerModVersionAvailable()) {
                MinecraftClient.getInstance().openScreen(new NewVersionAvailableScreen(() -> {
                    ClientUtil.connectToServer(parent, ClientMod.lamaServer);
                }));
            } else {
                ClientUtil.connectToServer(parent, ClientMod.lamaServer);
            }
        });
    }

    public JoinLamaServerButton(Screen parent) {
        this(parent, 20, 20, 200, 20);
    }
}
