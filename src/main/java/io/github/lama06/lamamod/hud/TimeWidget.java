package io.github.lama06.lamamod.hud;

import io.github.lama06.lamamod.options.Options;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeWidget extends AbstractTextWidget<TimeWidgetOptions> {
    @Override
    protected String getText(TimeWidgetOptions options) {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern((options.date ? "dd.MM ": "") + "HH:mm" + (options.seconds ? ":ss" : ""));
        LocalDateTime now = LocalDateTime.now();
        return "Zeit: " + formater.format(now);
    }

    @Override
    protected TimeWidgetOptions getWidgetOptions(Options options) {
        return options.timeWidget;
    }

    @Override
    protected void setWidgetOptions(TimeWidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.timeWidget = widgetOptions;
        Options.setOptions(options);
    }
}
