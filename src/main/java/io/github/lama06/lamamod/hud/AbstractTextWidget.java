package io.github.lama06.lamamod.hud;

import net.minecraft.client.util.math.MatrixStack;

/**
 * Sollte von allen Widgets erweitert werden, die nur Text darstellen
 * @param <T>
 */
public abstract class AbstractTextWidget<T extends WidgetOptions> extends AbstractWidget<T> {
    /**
     * Muss implementiert werden und den Text zurückgeben, den das Widget darstellen soll
     */
    protected abstract String getText(T options);

    @Override
    public void renderWidget(T options, MatrixStack matrices) {
        renderText(matrices, getText(options), options.x, options.y);
    }
}
