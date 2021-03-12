package io.github.lama06.lamamod.hud;

public class OnlinePlayersWidgetOptions extends TextWidgetOptions {
    public int maxPlayers;

    public OnlinePlayersWidgetOptions(boolean shown, int x, int y, boolean prefix, int maxPlayers) {
        super(shown, x, y, prefix);
        this.maxPlayers = maxPlayers;
    }
}
