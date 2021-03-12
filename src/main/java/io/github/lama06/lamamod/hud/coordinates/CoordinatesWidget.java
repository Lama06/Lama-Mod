package io.github.lama06.lamamod.hud.coordinates;

import io.github.lama06.lamamod.hud.AbstractTextWidget;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class CoordinatesWidget extends AbstractTextWidget<CoordinatesWidgetOptions> {
    @Override
    protected String getText(CoordinatesWidgetOptions options) {
        PlayerEntity player = client.player;

        String coordinates = (int) Math.floor(player.getX()) + " " + (int) Math.floor(player.getY()) + " " + (int) Math.floor(player.getZ());

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
    protected String getPrefix() {
        return "Koordinaten: ";
    }

    @Override
    public void onChatMessage(String[] args) {
        super.onChatMessage(args);

        CoordinatesWidgetOptions options = getWidgetOptions(Options.getOptions());

        if(args.length == 1) {
            if(args[0].equals("nether")) {
               if(options.netherCoordinates) {
                   options.netherCoordinates = false;
                   Util.sendMsgToPlayer("Die Nether Koordinaten werden nun ausgeblendet");
               } else {
                   options.netherCoordinates = true;
                   Util.sendMsgToPlayer("Die Nether Koordinaten werden nun angezeigt");
               }
            }
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
