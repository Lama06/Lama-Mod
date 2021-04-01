package io.github.lama06.lamamod.client.shortcuts.tp;

import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.client.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.ClientUtil;
import net.minecraft.client.network.PlayerListEntry;

/**
 * Teleportiert einen zu einem anderen Spieler, wenn man dessen Namen in den Chat eingibt.
 */
public class TeleportShortcut extends AbstractShortcut {
    @Override
    protected boolean isChatShortcut(ChatMessage msg) {
        for (PlayerListEntry player : client.getNetworkHandler().getPlayerList()) {
            if (msg.getText().equalsIgnoreCase(player.getProfile().getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void executeChatShortcut(ChatMessage msg) {
        ClientUtil.sendMsgToChat(String.format("/tp %s", msg.getText()));
    }

    @Override
    protected String getName() {
        return "TpShortcut";
    }

    @Override
    protected ShortcutOptions getShortcutOptions(ClientOptions options) {
        return options.tpShortcut;
    }

    @Override
    protected void setShortcutOptions(ShortcutOptions shortcutOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.tpShortcut = shortcutOptions;
        ClientOptions.setOptions(options);
    }
}
