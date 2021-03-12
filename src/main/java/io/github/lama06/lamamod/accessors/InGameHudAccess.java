package io.github.lama06.lamamod.accessors;

import io.github.lama06.lamamod.hud.coordinates.CoordinatesWidget;
import io.github.lama06.lamamod.hud.fps.FpsWidget;
import io.github.lama06.lamamod.hud.keystrokes.KeystrokesWidget;
import io.github.lama06.lamamod.hud.players.OnlinePlayersWidget;
import io.github.lama06.lamamod.hud.time.TimeWidget;
import io.github.lama06.lamamod.hud.version.VersionWidget;

public interface InGameHudAccess {
    CoordinatesWidget getCoordinatesWidget();
    FpsWidget getFpsWidget();
    VersionWidget getVersionWidget();
    TimeWidget getTimeWidget();
    KeystrokesWidget getKeystrokesWidget();
    OnlinePlayersWidget getOnlinePlayersWidget();
}
