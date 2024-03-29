package io.github.lama06.lamamod.client.widgets.players;

import io.github.lama06.lamamod.common.events.EventResult;
import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.ClientUtil;
import io.github.lama06.lamamod.client.widgets.AbstractTextWidget;
import net.minecraft.client.network.PlayerListEntry;

import java.util.Collection;
import java.util.Locale;

public class OnlinePlayersWidget extends AbstractTextWidget<OnlinePlayersWidgetOptions> {
    @Override
    protected String getText(OnlinePlayersWidgetOptions options) {
        StringBuilder string = new StringBuilder();

        Collection<PlayerListEntry> onlinePlayers = client.getNetworkHandler().getPlayerList();
        int playersOnServer = onlinePlayers.size();

        string.append("(").append(playersOnServer).append(") ");

        int players = 0;
        for (PlayerListEntry player : onlinePlayers) {
            if (players < options.maxPlayers || options.maxPlayers <= 0) {
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
    public EventResult onChatMessage(ChatMessage msg) {
        if (msg.getText().startsWith(getName().toLowerCase(Locale.ROOT))) {
            OnlinePlayersWidgetOptions options = getWidgetOptions();
            String[] args = msg.getArgs();

            try {
                if (args.length == 2) {
                    if (args[0].equals("maxplayers")) {
                        int amount = Integer.parseInt(args[1]);
                        options.maxPlayers = amount;
                        ClientUtil.sendMsgToPlayer("Maximale Anzahl der Spieler, die angezeigt werden, wurde auf " + (amount <= 0 ? "unendlich" : amount) + " gesetzt");
                        setWidgetOptions(options);
                    }
                }
            } catch (NumberFormatException e) {
                ClientUtil.sendMsgToPlayer("Bitte gib eine richtige Zahl ein");
            }
        }

        return super.onChatMessage(msg);
    }

    @Override
    protected String getName() {
        return "PlayersWidget";
    }

    @Override
    protected OnlinePlayersWidgetOptions getWidgetOptions(ClientOptions options) {
        return options.onlinePlayersWidget;
    }

    @Override
    protected void setWidgetOptions(OnlinePlayersWidgetOptions widgetOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.onlinePlayersWidget = widgetOptions;
        ClientOptions.setOptions(options);
    }
}
