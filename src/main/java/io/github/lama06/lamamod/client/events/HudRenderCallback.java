package io.github.lama06.lamamod.client.events;


import io.github.lama06.lamamod.common.events.Event;
import net.minecraft.client.util.math.MatrixStack;

public interface HudRenderCallback {
    Event<HudRenderCallback> EVENT = new Event<>((listeners) -> (matrices) -> {
        for (HudRenderCallback listener : listeners) {
            listener.onHudRender(matrices);
        }
    });

    void onHudRender(MatrixStack matrices);
}
