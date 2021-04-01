package io.github.lama06.lamamod.client.shortcuts.crash;

import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.client.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.ClientUtil;

public class CrashGameShortcut extends AbstractShortcut<ShortcutOptions> {
    @Override
    protected boolean isChatShortcut(ChatMessage msg) {
        String text = msg.getText();
        return text.equals("wehner") || text.equals("wehnergmbh") || text.equals("ich mag lamas nicht") || text.equals("tschüss");
    }

    @Override
    protected void executeChatShortcut(ChatMessage msg) {
        ClientUtil.crashGame("kaputt");
    }

    @Override
    protected String getName() {
        return "CrashShortcut";
    }

    @Override
    protected ShortcutOptions getShortcutOptions(ClientOptions options) {
        return options.crashGameShortcut;
    }

    @Override
    protected void setShortcutOptions(ShortcutOptions shortcutOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.crashGameShortcut = shortcutOptions;
        ClientOptions.setOptions(options);
    }
}
