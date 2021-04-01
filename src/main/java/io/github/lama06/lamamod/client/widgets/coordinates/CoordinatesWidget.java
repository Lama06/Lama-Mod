package io.github.lama06.lamamod.client.widgets.coordinates;

import io.github.lama06.lamamod.common.events.EventResult;
import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.ClientUtil;
import io.github.lama06.lamamod.client.widgets.AbstractTextWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.Locale;

public class CoordinatesWidget extends AbstractTextWidget<CoordinateWidgetOptions> {
    @Override
    protected String getText(CoordinateWidgetOptions options) {
        PlayerEntity player = client.player;

        String coordinates = Math.round(player.getX()) + " " + Math.round(player.getY()) + " " + Math.round(player.getZ());

        if (options.netherCoordinates) {
            if (player.world.getRegistryKey().equals(World.OVERWORLD)) {
                return coordinates + " (im Nether " + Math.round(player.getX() / 8) + " " + Math.round(player.getZ() / 8) + ")";
            } else if (player.world.getRegistryKey().equals(World.NETHER)) {
                return coordinates + " (in der Oberwelt " + Math.round(player.getX() * 8) + " " + Math.round(player.getZ() * 8) + ")";
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
        if (msg.getText().startsWith(getName().toLowerCase(Locale.ROOT))) {
            CoordinateWidgetOptions options = getWidgetOptions(ClientOptions.getOptions());
            String[] args = msg.getArgs();

            if (args.length == 1) {
                if (args[0].equals("nether")) {
                    if (options.netherCoordinates) {
                        options.netherCoordinates = false;
                        ClientUtil.sendMsgToPlayer("Die Nether Koordinaten werden nun ausgeblendet");
                    } else {
                        options.netherCoordinates = true;
                        ClientUtil.sendMsgToPlayer("Die Nether Koordinaten werden nun angezeigt");
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
    protected CoordinateWidgetOptions getWidgetOptions(ClientOptions options) {
        return options.coordinatesWidget;
    }

    @Override
    protected void setWidgetOptions(CoordinateWidgetOptions widgetOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.coordinatesWidget = widgetOptions;
        ClientOptions.setOptions(options);
    }
}
