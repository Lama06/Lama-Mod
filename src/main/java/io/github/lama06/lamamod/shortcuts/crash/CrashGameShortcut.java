package io.github.lama06.lamamod.shortcuts.crash;

import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

public class CrashGameShortcut extends AbstractShortcut<ShortcutOptions> {
    @Override
    protected boolean isChatShortcut(ChatMessage msg) {
        String text = msg.getText();
        return text.equals("wehner") || text.equals("wehnergmbh") || text.equals("ich mag lamas nicht") || text.equals("tschüss");
    }

    @Override
    protected void executeChatShortcut(ChatMessage msg) {
        Util.crashGame("kaputt");
    }

    @Override
    protected String getName() {
        return "CrashShortcut";
    }

    @Override
    protected ShortcutOptions getShortcutOptions(Options options) {
        return options.crashGameShortcut;
    }

    @Override
    protected void setShortcutOptions(ShortcutOptions shortcutOptions) {
        Options options = Options.getOptions();
        options.crashGameShortcut = shortcutOptions;
        Options.setOptions(options);
    }
}
