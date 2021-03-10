package io.github.lama06.lamamod.mixins;

import io.github.lama06.lamamod.hud.*;
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
    public KeystrokesWidget keystrokesWidget = new KeystrokesWidget();

    @Inject(at = @At("HEAD"), method = "render")
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        coordinatesHud.render(matrices);
        fpsWidget.render(matrices);
        versionWidget.render(matrices);
        timeWidget.render(matrices);
        keystrokesWidget.render(matrices);
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
}
