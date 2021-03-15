package io.github.lama06.lamamod.events;


import net.minecraft.client.util.math.MatrixStack;

public interface HudRenderCallback {
    Event<HudRenderCallback> EVENT = new Event<>((listeners) -> (matrices) -> {
        for(HudRenderCallback listener : listeners) {
            listener.onHudRender(matrices);
        }
    });

    void onHudRender(MatrixStack matrices);
}
