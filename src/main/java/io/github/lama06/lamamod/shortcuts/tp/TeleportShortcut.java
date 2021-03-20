package io.github.lama06.lamamod.shortcuts.tp;

import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;
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
        Util.sendMsgToChat(String.format("/tp %s", msg.getText()));
    }

    @Override
    protected String getName() {
        return "TpShortcut";
    }

    @Override
    protected ShortcutOptions getShortcutOptions(Options options) {
        return options.tpShortcut;
    }

    @Override
    protected void setShortcutOptions(ShortcutOptions shortcutOptions) {
        Options options = Options.getOptions();
        options.tpShortcut = shortcutOptions;
        Options.setOptions(options);
    }
}
