package io.github.lama06.lamamod.client.widgets.keystrokes;

import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.util.ClientUtil;
import io.github.lama06.lamamod.client.widgets.AbstractTextWidget;
import net.minecraft.client.util.InputUtil;

public class KeystrokesWidget extends AbstractTextWidget<KeystrokesWidgetOptions> {
    @Override
    protected String getText(KeystrokesWidgetOptions options) {
        long handle = ClientUtil.getHandle();
        return (InputUtil.isKeyPressed(handle, 32) ? "SPACE" : "     ") + " " +
                (InputUtil.isKeyPressed(handle, 87) ? "W" : "") + " " +
                (InputUtil.isKeyPressed(handle, 65) ? "A" : "") + " " +
                (InputUtil.isKeyPressed(handle, 83) ? "S" : "") + " " +
                (InputUtil.isKeyPressed(handle, 68) ? "D" : "");
    }

    @Override
    protected String getPrefix() {
        return "Keystrokes: ";
    }

    @Override
    protected String getName() {
        return "KeystrokesWidget";
    }

    @Override
    protected KeystrokesWidgetOptions getWidgetOptions(ClientOptions options) {
        return options.keystrokesWidget;
    }

    @Override
    protected void setWidgetOptions(KeystrokesWidgetOptions widgetOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.keystrokesWidget = widgetOptions;
        ClientOptions.setOptions(options);
    }
}
