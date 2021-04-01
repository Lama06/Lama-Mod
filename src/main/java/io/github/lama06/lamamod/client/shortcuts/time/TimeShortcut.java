package io.github.lama06.lamamod.client.shortcuts.time;

import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.client.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.ClientUtil;

public class TimeShortcut extends AbstractShortcut<ShortcutOptions> {
    private boolean isDayShortcut(ChatMessage msg) {
        String text = msg.getText();
        return text.equals("d") || text.equals("tsd") || text.equals("day");
    }

    private boolean isNightShortcut(ChatMessage msg) {
        String text = msg.getText();
        return text.equals("n") || text.equals("tsn") || text.equals("night");
    }

    @Override
    protected boolean isChatShortcut(ChatMessage msg) {
        return isDayShortcut(msg) || isNightShortcut(msg);
    }

    @Override
    protected void executeChatShortcut(ChatMessage msg) {
        if (isDayShortcut(msg)) {
            ClientUtil.sendMsgToChat("/time set day");
        } else if (isNightShortcut(msg)) {
            ClientUtil.sendMsgToChat("/time set night");
        }
    }

    @Override
    protected String getName() {
        return "TimeShortcut";
    }

    @Override
    protected ShortcutOptions getShortcutOptions(ClientOptions options) {
        return options.timeShortcut;
    }

    @Override
    protected void setShortcutOptions(ShortcutOptions shortcutOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.timeShortcut = shortcutOptions;
        ClientOptions.setOptions(options);
    }
}
