package io.github.lama06.lamamod.widgets.coordinates;

import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.widgets.AbstractTextWidget;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.Locale;

public class CoordinatesWidget extends AbstractTextWidget<CoordinateWidgetOptions> {
    @Override
    protected String getText(CoordinateWidgetOptions options) {
        PlayerEntity player = client.player;

        String coordinates = Math.floor(player.getX()) + " " + Math.floor(player.getY()) + " " + Math.floor(player.getZ());

        if(options.netherCoordinates) {
            if(player.world.getRegistryKey().equals(World.OVERWORLD)) {
                return coordinates + " (im Nether " + Math.floor(player.getX() / 8) + " " + Math.floor(player.getZ() / 8) + ")";
            } else if(player.world.getRegistryKey().equals(World.NETHER)) {
                return coordinates + " (in der Oberwelt " + Math.floor(player.getX() * 8) + " " + Math.floor(player.getZ() * 8) + ")";
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
    public EventResult onChatMessage(ChatMessage msg) {
        if(msg.getText().startsWith(getName().toLowerCase(Locale.ROOT))) {
            CoordinateWidgetOptions options = getWidgetOptions(Options.getOptions());
            String[] args = msg.getArgs();

            if(args.length == 1) {
                if(args[0].equals("nether")) {
                    if(options.netherCoordinates) {
                        options.netherCoordinates = false;
                        Util.sendMsgToPlayer("Die Nether Koordinaten werden nun ausgeblendet");
                    } else {
                        options.netherCoordinates = true;
                        Util.sendMsgToPlayer("Die Nether Koordinaten werden nun angezeigt");
                    }
                    setWidgetOptions(options);
                }
            }
        }

        return super.onChatMessage(msg);
    }

    @Override
    protected String getName() {
        return "CoordsWidget";
    }

    @Override
    protected CoordinateWidgetOptions getWidgetOptions(Options options) {
        return options.coordinatesWidget;
    }

    @Override
    protected void setWidgetOptions(CoordinateWidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.coordinatesWidget = widgetOptions;
        Options.setOptions(options);
    }
}
