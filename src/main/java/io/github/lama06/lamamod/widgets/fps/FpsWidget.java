package io.github.lama06.lamamod.widgets.fps;

import io.github.lama06.lamamod.mixins.MinecraftClientAccessor;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.widgets.AbstractTextWidget;

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
    protected String getName() {
        return "FpsWidget";
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
