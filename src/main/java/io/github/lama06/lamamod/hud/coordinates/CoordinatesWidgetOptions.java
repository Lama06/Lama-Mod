package io.github.lama06.lamamod.hud;

public class CoordinatesWidgetOptions extends TextWidgetOptions {
    public boolean netherCoordinates = true;

    public CoordinatesWidgetOptions(boolean shown, int x, int y, boolean prefix, boolean netherCoordinates) {
        super(shown, x, y, prefix);
        this.netherCoordinates = netherCoordinates;
    }
}
