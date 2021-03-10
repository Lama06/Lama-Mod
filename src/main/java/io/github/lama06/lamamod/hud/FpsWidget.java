package io.github.lama06.lamamod.hud;

import io.github.lama06.lamamod.mixins.MinecraftClientAccessor;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.util.Util;

public class FpsWidget extends AbstractTextWidget<WidgetOptions> {
    @Override
    protected String getText(WidgetOptions options) {
        return "Fps: " + MinecraftClientAccessor.getCurrentFps();
    }

    @Override
    protected WidgetOptions getWidgetOptions(Options options) {
        return options.fpsWidget;
    }

    @Override
    protected void setWidgetOptions(WidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.fpsWidget = widgetOptions;
        Options.setOptions(options);

        Util.sendMsgToPlayer("Setzt Widget Options");
    }
}
