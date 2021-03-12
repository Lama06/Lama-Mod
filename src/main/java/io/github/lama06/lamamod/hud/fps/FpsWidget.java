package io.github.lama06.lamamod.hud.fps;

import io.github.lama06.lamamod.hud.AbstractTextWidget;
import io.github.lama06.lamamod.mixins.MinecraftClientAccessor;
import io.github.lama06.lamamod.options.Options;

public class FpsWidget extends AbstractTextWidget<FpsWidgetOptions> {
    @Override
    protected String getText(FpsWidgetOptions options) {
        return Integer.toString(MinecraftClientAccessor.getCurrentFps());
    }

    @Override
    protected String getPrefix() {
        return "Fps: ";
    }

    @Override
    protected FpsWidgetOptions getWidgetOptions(Options options) {
        return options.fpsWidget;
    }

    @Override
    protected void setWidgetOptions(FpsWidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.fpsWidget = widgetOptions;
        Options.setOptions(options);
    }
}
