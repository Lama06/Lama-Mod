package io.github.lama06.lamamod.hud;

public class TimeWidgetOptions extends WidgetOptions {
    public boolean seconds;
    public boolean date;

    public TimeWidgetOptions(boolean shown, int x, int y, boolean date, boolean seconds) {
        super(shown, x, y);
        this.date = date;
        this.seconds = seconds;
    }
}
