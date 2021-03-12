package io.github.lama06.lamamod.hud.players;

import io.github.lama06.lamamod.hud.AbstractTextWidget;
import io.github.lama06.lamamod.hud.players.OnlinePlayersWidgetOptions;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.client.network.PlayerListEntry;

import java.util.Collection;

public class OnlinePlayersWidget extends AbstractTextWidget<OnlinePlayersWidgetOptions> {
    @Override
    protected String getText(OnlinePlayersWidgetOptions options) {
        StringBuilder string = new StringBuilder();

        Collection<PlayerListEntry> onlinePlayers = client.getNetworkHandler().getPlayerList();
        int playersOnServer = onlinePlayers.size();

        string.append("(").append(playersOnServer).append(") ");

        int players = 0;
        for(PlayerListEntry player : onlinePlayers) {
            if(players < options.maxPlayers || options.maxPlayers <= 0) {
                String playerName = player.getProfile().getName();

                string.append(playerName).append(" ");
                players++;
            } else {
                int hiddenPlayers = playersOnServer - options.maxPlayers;
                string.append(" (+ ").append(hiddenPlayers).append(hiddenPlayers > 1 ? " weitere" : " weiterer").append(")");
                break;
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

        try {
            if(args.length == 2) {
                if(args[0].equals("maxplayers")) {
                    int amount = Integer.parseInt(args[1]);
                    options.maxPlayers = amount;
                    Util.sendMsgToPlayer("Maximale Anzahl der Spieler, die angezeigt werden, wurde auf " + (amount <= 0 ? "unendlich" : amount) + " gesetzt");
                    setWidgetOptions(options);
                }
            }
        } catch(NumberFormatException e) {
            Util.sendMsgToPlayer("Bitte gib eine richtige Zahl ein");
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
