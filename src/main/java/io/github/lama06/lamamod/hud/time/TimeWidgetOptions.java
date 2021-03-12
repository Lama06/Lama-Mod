package io.github.lama06.lamamod.hud.time;

import io.github.lama06.lamamod.hud.TextWidgetOptions;

public class TimeWidgetOptions extends TextWidgetOptions {
    public boolean seconds = true;
    public boolean date = true;

    public TimeWidgetOptions() {
        y = 80;
    }
}
