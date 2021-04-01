package io.github.lama06.lamamod.client.widgets.fps;

import io.github.lama06.lamamod.client.mixins.MinecraftClientAccessor;
import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.widgets.AbstractTextWidget;

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
    protected FpsWidgetOptions getWidgetOptions(ClientOptions options) {
        return options.fpsWidget;
    }

    @Override
    protected void setWidgetOptions(FpsWidgetOptions widgetOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.fpsWidget = widgetOptions;
        ClientOptions.setOptions(options);
    }
}
