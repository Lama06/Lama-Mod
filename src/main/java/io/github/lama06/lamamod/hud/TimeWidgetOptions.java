package io.github.lama06.lamamod.hud;

public class TimeWidgetOptions extends TextWidgetOptions {
    public boolean seconds;
    public boolean date;

    public TimeWidgetOptions(boolean shown, int x, int y, boolean prefix, boolean date, boolean seconds) {
        super(shown, x, y, prefix);
        this.date = date;
        this.seconds = seconds;
    }
}
