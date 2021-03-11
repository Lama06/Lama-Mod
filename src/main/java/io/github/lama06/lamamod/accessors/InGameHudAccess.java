package io.github.lama06.lamamod.accessors;

import io.github.lama06.lamamod.hud.*;

public interface InGameHudAccess {
    CoordinatesWidget getCoordinatesWidget();
    FpsWidget getFpsWidget();
    VersionWidget getVersionWidget();
    TimeWidget getTimeWidget();
    KeystrokesWidget getKeystrokesWidget();
    OnlinePlayersWidget getOnlinePlayersWidget();
}
