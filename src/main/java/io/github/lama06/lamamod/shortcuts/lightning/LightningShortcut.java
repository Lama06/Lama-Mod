package io.github.lama06.lamamod.shortcuts.lightning;

import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

public class LightningShortcut extends AbstractShortcut<ShortcutOptions> {
    @Override
    protected boolean isChatShortcut(ChatMessage msg) {
        return msg.getText().startsWith("blitz");
    }

    @Override
    protected void executeChatShortcut(ChatMessage msg) {
        String playername;
        if (msg.getArgs().length < 1) {
            playername = Util.getPlayerName();
            Util.sendMsgToPlayer("<Zeus> Ich bin sauer. Du hast mir nicht gesagt, wo der Blitz einschlagen soll!");
        } else {
            playername = msg.getArgs()[0];
            Util.sendMsgToPlayer("<Zeus> Ich lasse jetzt einen Blitz bei " + playername + " einschlagen.");
        }

        String command = String.format("/execute as %s at %s run summon minecraft:lightning_bolt ~ ~ ~", playername, playername);
        Util.sendMsgToChat(command);
    }

    @Override
    protected String getName() {
        return "BlitzShortcut";
    }

    @Override
    protected ShortcutOptions getShortcutOptions(Options options) {
        return options.lightningShortcut;
    }

    @Override
    protected void setShortcutOptions(ShortcutOptions shortcutOptions) {
        Options options = Options.getOptions();
        options.lightningShortcut = shortcutOptions;
        Options.setOptions(options);
    }
}
