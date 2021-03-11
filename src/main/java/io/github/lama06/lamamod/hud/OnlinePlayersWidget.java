package io.github.lama06.lamamod.hud;

import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.client.network.AbstractClientPlayerEntity;

public class OnlinePlayersWidget extends AbstractTextWidget<OnlinePlayersWidgetOptions> {
    @Override
    protected String getText(OnlinePlayersWidgetOptions options) {
        StringBuilder string = new StringBuilder();
        int players = 0;

        for(AbstractClientPlayerEntity player : client.world.getPlayers()) {
            if(players < options.maxPlayers || options.maxPlayers <= 0) {
                string.append(player.getName().asString()).append(" ");
                players++;
            }
        }

        return string.toString();
    }

    @Override
    protected String getPrefix() {
        return "Spieler: ";
    }

    @Override
    public void onChatMessage(String[] args) {
        super.onChatMessage(args);

        OnlinePlayersWidgetOptions options = getWidgetOptions(Options.getOptions());

        if(args.length == 2) {
            if(args[0].equals("maxplayers")) {
                int amount = Integer.parseInt(args[1]);
                options.maxPlayers = amount;
                Util.sendMsgToPlayer("Maximale Anzahl der Spieler, die angezeigt werden, wurde auf " + (amount <= 0 ? "unendlich" : amount) + " gesetzt");
                setWidgetOptions(options);
            }
        }
    }

    @Override
    protected OnlinePlayersWidgetOptions getWidgetOptions(Options options) {
        return options.onlinePlayersWidget;
    }

    @Override
    protected void setWidgetOptions(OnlinePlayersWidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.onlinePlayersWidget = widgetOptions;
        Options.setOptions(options);
    }
}
