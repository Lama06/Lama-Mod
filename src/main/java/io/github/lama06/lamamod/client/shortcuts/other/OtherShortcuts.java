package io.github.lama06.lamamod.client.shortcuts.other;

import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.client.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.ClientUtil;

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
        ClientUtil.sendMsgToChat(shortcuts.get(msg.getText()));
    }

    @Override
    protected String getName() {
        return "OtherShortcuts";
    }

    @Override
    protected ShortcutOptions getShortcutOptions(ClientOptions options) {
        return options.otherShortcuts;
    }

    @Override
    protected void setShortcutOptions(ShortcutOptions shortcutOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.otherShortcuts = shortcutOptions;
        ClientOptions.setOptions(options);
    }
}
