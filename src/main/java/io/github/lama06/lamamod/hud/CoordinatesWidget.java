package io.github.lama06.lamamod.hud;

import io.github.lama06.lamamod.options.Options;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class CoordinatesWidget extends AbstractTextWidget<CoordinatesWidgetOptions> {
    @Override
    protected String getText(CoordinatesWidgetOptions options) {
        PlayerEntity player = client.player;

        String coordinates = "Koordinaten: " + (int) Math.floor(player.getX()) + " " + (int) Math.floor(player.getY()) + " " + (int) Math.floor(player.getZ());

        if(options.netherCoordinates) {
            if(player.world.getRegistryKey().equals(World.OVERWORLD)) {
                return coordinates + " (im Nether " + (int) Math.floor(player.getX() / 8) + " " + (int) Math.floor(player.getZ() / 8) + ")";
            } else if(player.world.getRegistryKey().equals(World.NETHER)) {
                return coordinates + " (in der Oberwelt " + (int) Math.floor(player.getX() * 8) + " " + (int) Math.floor(player.getZ() * 8) + ")";
            } else {
                return coordinates;
            }
        } else {
            return coordinates;
        }
    }

    @Override
    protected CoordinatesWidgetOptions getWidgetOptions(Options options) {
        return options.coordinatesWidget;
    }

    @Override
    protected void setWidgetOptions(CoordinatesWidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.coordinatesWidget = widgetOptions;
        Options.setOptions(options);
    }
}
