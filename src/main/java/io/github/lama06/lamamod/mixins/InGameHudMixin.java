package io.github.lama06.lamamod.mixins;

import io.github.lama06.lamamod.accessors.InGameHudAccess;
import io.github.lama06.lamamod.hud.coordinates.CoordinatesWidget;
import io.github.lama06.lamamod.hud.fps.FpsWidget;
import io.github.lama06.lamamod.hud.keystrokes.KeystrokesWidget;
import io.github.lama06.lamamod.hud.players.OnlinePlayersWidget;
import io.github.lama06.lamamod.hud.time.TimeWidget;
import io.github.lama06.lamamod.hud.version.VersionWidget;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin implements InGameHudAccess {
    public CoordinatesWidget coordinatesHud = new CoordinatesWidget();
    public FpsWidget fpsWidget = new FpsWidget();
    public VersionWidget versionWidget = new VersionWidget();
    public TimeWidget timeWidget = new TimeWidget();
    public KeystrokesWidget keystrokesWidget = new KeystrokesWidget();
    public OnlinePlayersWidget onlinePlayersWidget = new OnlinePlayersWidget();

    @Inject(at = @At("HEAD"), method = "render")
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        coordinatesHud.render(matrices);
        fpsWidget.render(matrices);
        versionWidget.render(matrices);
        timeWidget.render(matrices);
        keystrokesWidget.render(matrices);
        onlinePlayersWidget.render(matrices);
    }

    @Override
    public CoordinatesWidget getCoordinatesWidget() {
        return coordinatesHud;
    }

    @Override
    public FpsWidget getFpsWidget() {
        return fpsWidget;
    }

    @Override
    public VersionWidget getVersionWidget() {
        return versionWidget;
    }

    @Override
    public TimeWidget getTimeWidget() {
        return timeWidget;
    }

    @Override
    public KeystrokesWidget getKeystrokesWidget() {
        return keystrokesWidget;
    }

    @Override
    public OnlinePlayersWidget getOnlinePlayersWidget() {
        return onlinePlayersWidget;
    }
}
