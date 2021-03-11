package io.github.lama06.lamamod.hud;

public class TextWidgetOptions extends WidgetOptions {
    public boolean prefix;

    public TextWidgetOptions(boolean shown, int x, int y, boolean prefix) {
        super(shown, x, y);
        this.prefix = prefix;
    }
}
