package io.github.lama06.lamamod.mixins;

import io.github.lama06.lamamod.hud.CoordinatesWidget;
import io.github.lama06.lamamod.hud.FpsWidget;
import io.github.lama06.lamamod.hud.TimeWidget;
import io.github.lama06.lamamod.hud.VersionWidget;
import io.github.lama06.lamamod.accessors.InGameHudAccess;
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

    @Inject(at = @At("HEAD"), method = "render")
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        coordinatesHud.render(matrices);
        fpsWidget.render(matrices);
        versionWidget.render(matrices);
        timeWidget.render(matrices);
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
}
