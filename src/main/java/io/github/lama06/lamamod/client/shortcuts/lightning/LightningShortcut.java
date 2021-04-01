package io.github.lama06.lamamod.client.shortcuts.lightning;

import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.client.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.ClientUtil;

public class LightningShortcut extends AbstractShortcut<ShortcutOptions> {
    @Override
    protected boolean isChatShortcut(ChatMessage msg) {
        return msg.getText().startsWith("blitz");
    }

    @Override
    protected void executeChatShortcut(ChatMessage msg) {
        String playername;
        if (msg.getArgs().length == 0 || msg.getArgs().length > 1) {
            playername = ClientUtil.getPlayerName();
            ClientUtil.sendMsgToPlayer("<Zeus> Ich bin sauer. Du hast mir nicht gesagt, wo der Blitz einschlagen soll!");
        } else {
            playername = msg.getArgs()[0];
            ClientUtil.sendMsgToPlayer("<Zeus> Ich lasse jetzt einen Blitz bei " + playername + " einschlagen.");
        }

        String command = String.format("/execute as %s at %s run summon minecraft:lightning_bolt ~ ~ ~", playername, playername);
        ClientUtil.sendMsgToChat(command);
    }

    @Override
    protected String getName() {
        return "BlitzShortcut";
    }

    @Override
    protected ShortcutOptions getShortcutOptions(ClientOptions options) {
        return options.lightningShortcut;
    }

    @Override
    protected void setShortcutOptions(ShortcutOptions shortcutOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.lightningShortcut = shortcutOptions;
        ClientOptions.setOptions(options);
    }
}
