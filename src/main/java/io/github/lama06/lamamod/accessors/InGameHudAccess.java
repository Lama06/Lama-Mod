package io.github.lama06.lamamod.accessors;

import io.github.lama06.lamamod.hud.CoordinatesWidget;
import io.github.lama06.lamamod.hud.FpsWidget;
import io.github.lama06.lamamod.hud.TimeWidget;
import io.github.lama06.lamamod.hud.VersionWidget;

public interface InGameHudAccess {
    CoordinatesWidget getCoordinatesWidget();
    FpsWidget getFpsWidget();
    VersionWidget getVersionWidget();
    TimeWidget getTimeWidget();
}
