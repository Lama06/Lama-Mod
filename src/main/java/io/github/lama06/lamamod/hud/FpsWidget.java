package io.github.lama06.lamamod.hud;

import io.github.lama06.lamamod.mixins.MinecraftClientAccessor;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.util.Util;

public class FpsWidget extends AbstractTextWidget<TextWidgetOptions> {
    @Override
    protected String getText(TextWidgetOptions options) {
        return Integer.toString(MinecraftClientAccessor.getCurrentFps());
    }

    @Override
    protected String getPrefix() {
        return "Fps: ";
    }

    @Override
    protected TextWidgetOptions getWidgetOptions(Options options) {
        return options.fpsWidget;
    }

    @Override
    protected void setWidgetOptions(TextWidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.fpsWidget = widgetOptions;
        Options.setOptions(options);
    }
}
