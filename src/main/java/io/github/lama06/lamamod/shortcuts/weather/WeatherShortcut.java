package io.github.lama06.lamamod.shortcuts.weather;

import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

public class WeatherShortcut extends AbstractShortcut<ShortcutOptions> {
    private boolean isClearShortcut(ChatMessage msg) {
        return msg.getText().startsWith("wc");
    }

    private boolean isRainShortcut(ChatMessage msg) {
        return msg.getText().startsWith("wr");
    }

    @Override
    protected boolean isChatShortcut(ChatMessage msg) {
        return isClearShortcut(msg) || isRainShortcut(msg);
    }

    @Override
    protected void executeChatShortcut(ChatMessage msg) {
        if(isClearShortcut(msg)) {
            Util.sendMsgToChat("/weather clear");
        } else if(isRainShortcut(msg)) {
            Util.sendMsgToChat("/weather rain");
        }
    }

    @Override
    protected String getName() {
        return "WeatherShortcut";
    }

    @Override
    protected ShortcutOptions getShortcutOptions(Options options) {
        return options.weatherShortcut;
    }

    @Override
    protected void setShortcutOptions(ShortcutOptions shortcutOptions) {
        Options options = Options.getOptions();
        options.weatherShortcut = shortcutOptions;
        Options.setOptions(options);
    }
}
