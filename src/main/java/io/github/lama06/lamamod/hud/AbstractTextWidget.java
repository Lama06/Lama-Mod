package io.github.lama06.lamamod.hud;

import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.client.util.math.MatrixStack;

/**
 * Sollte von allen Widgets erweitert werden, die nur Text darstellen
 */
public abstract class AbstractTextWidget<T extends TextWidgetOptions> extends AbstractWidget<T> {
    /**
     * Muss implementiert werden und den Text zurückgeben, den das Widget darstellen soll
     */
    protected abstract String getText(T options);

    /**
     * Muss implementiert werden und den Prefix des Widgets zurückgeben (zB "Version: ")
     */
    protected abstract String getPrefix();

    @Override
    public void onChatMessage(String[] args) {
        super.onChatMessage(args);

        T options = getWidgetOptions(Options.getOptions());

        if(args.length == 1) {
            if(args[0].equals("prefix")) {
                if(options.prefix) {
                    options.prefix = false;
                    Util.sendMsgToPlayer("Der Prefix wird nun ausgeblendet");
                } else {
                    options.prefix = true;
                    Util.sendMsgToPlayer("Der Prefix wird nun angezeigt");
                }
                setWidgetOptions(options);
            }
        }
    }

    @Override
    public void renderWidget(T options, MatrixStack matrices) {
        renderText(matrices, (options.prefix ? getPrefix() : "") + getText(options), options.x, options.y);
    }
}
