package io.github.lama06.lamamod.shortcuts.time;

import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

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
            Util.sendMsgToChat("/time set day");
        } else if (isNightShortcut(msg)) {
            Util.sendMsgToChat("/time set night");
        }
    }

    @Override
    protected String getName() {
        return "TimeShortcut";
    }

    @Override
    protected ShortcutOptions getShortcutOptions(Options options) {
        return options.timeShortcut;
    }

    @Override
    protected void setShortcutOptions(ShortcutOptions shortcutOptions) {
        Options options = Options.getOptions();
        options.timeShortcut = shortcutOptions;
        Options.setOptions(options);
    }
}
