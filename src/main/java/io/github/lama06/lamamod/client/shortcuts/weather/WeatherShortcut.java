package io.github.lama06.lamamod.client.shortcuts.weather;

import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.client.shortcuts.ShortcutOptions;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.ClientUtil;

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
        if (isClearShortcut(msg)) {
            ClientUtil.sendMsgToChat("/weather clear");
        } else if (isRainShortcut(msg)) {
            ClientUtil.sendMsgToChat("/weather rain");
        }
    }

    @Override
    protected String getName() {
        return "WeatherShortcut";
    }

    @Override
    protected ShortcutOptions getShortcutOptions(ClientOptions options) {
        return options.weatherShortcut;
    }

    @Override
    protected void setShortcutOptions(ShortcutOptions shortcutOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.weatherShortcut = shortcutOptions;
        ClientOptions.setOptions(options);
    }
}
