package io.github.lama06.lamamod.hud;

import io.github.lama06.lamamod.options.Options;
import net.minecraft.client.util.InputUtil;

public class KeystrokesWidget extends AbstractTextWidget<WidgetOptions> {
    @Override
    protected String getText(WidgetOptions options) {
        long handle = client.getWindow().getHandle();
        return (InputUtil.isKeyPressed(handle, 32) ? "SPACE": "     ") + " " +
                (InputUtil.isKeyPressed(handle, 87) ? "W": "") + " " +
                (InputUtil.isKeyPressed(handle, 65) ? "A": "") + " " +
                (InputUtil.isKeyPressed(handle, 83) ? "S": "") + " " +
                (InputUtil.isKeyPressed(handle, 68) ? "D": "");
    }

    @Override
    protected WidgetOptions getWidgetOptions(Options options) {
        return options.keystrokesWidget;
    }

    @Override
    protected void setWidgetOptions(WidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.keystrokesWidget = widgetOptions;
        Options.setOptions(options);
    }
}
