package io.github.lama06.lamamod.hud;

public class CoordinatesWidgetOptions extends WidgetOptions {
    public boolean netherCoordinates;

    public CoordinatesWidgetOptions(boolean shown, int x, int y, boolean netherCoordinates) {
        super(shown, x, y);
        this.netherCoordinates = netherCoordinates;
    }
}
