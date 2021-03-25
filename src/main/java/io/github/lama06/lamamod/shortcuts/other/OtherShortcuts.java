package io.github.lama06.lamamod.shortcuts.other;

import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

import java.util.HashMap;
import java.util.Map;

public class OtherShortcuts extends AbstractShortcut<ShortcutOptions> {
    private static final Map<String, String> shortcuts = new HashMap<>();

    static {
        shortcuts.put("weber", "WeberGMBH");
        shortcuts.put("l", "lol");
        shortcuts.put("x", "xD");
        shortcuts.put("h", "hallo");
        shortcuts.put(")", ":)");
        shortcuts.put(":", ":D");
    }

    @Override
    protected boolean isChatShortcut(ChatMessage msg) {
        return shortcuts.containsKey(msg.getText());
    }

    @Override
    protected void executeChatShortcut(ChatMessage msg) {
        Util.sendMsgToChat(shortcuts.get(msg.getText()));
    }

    @Override
    protected String getName() {
        return "OtherShortcuts";
    }

    @Override
    protected ShortcutOptions getShortcutOptions(Options options) {
        return options.otherShortcuts;
    }

    @Override
    protected void setShortcutOptions(ShortcutOptions shortcutOptions) {
        Options options = Options.getOptions();
        options.otherShortcuts = shortcutOptions;
        Options.setOptions(options);
    }
}
