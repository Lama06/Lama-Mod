package io.github.lama06.lamamod.client.widgets;

import io.github.lama06.lamamod.common.events.EventResult;
import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.ClientUtil;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Locale;

/**
 * Sollte von allen Widgets erweitert werden, die nur Text darstellen
 */
public abstract class AbstractTextWidget<T extends TextWidgetOptions> extends ColoredWidget<T> {
    /**
     * Muss implementiert werden und den Text zurückgeben, den das Widget darstellen soll
     */
    protected abstract String getText(T options);

    /**
     * Muss implementiert werden und den Prefix des Widgets zurückgeben (zB "Version: ")
     */
    protected abstract String getPrefix();

    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        if (msg.getText().startsWith(getName().toLowerCase(Locale.ROOT))) {
            T options = getWidgetOptions(ClientOptions.getOptions());
            String[] args = msg.getArgs();

            if (args.length == 1) {
                if (args[0].equals("prefix")) {
                    if (options.prefix) {
                        options.prefix = false;
                        ClientUtil.sendMsgToPlayer("Der Prefix wird nun ausgeblendet");
                    } else {
                        options.prefix = true;
                        ClientUtil.sendMsgToPlayer("Der Prefix wird nun angezeigt");
                    }
                    setWidgetOptions(options);
                } else if (args[0].equals("tochat")) {
                    ClientUtil.sendMsgToChat(getPrefix() + getText(options));
                }
            }
        }

        return super.onChatMessage(msg);
    }

    @Override
    public void renderWidget(T options, MatrixStack matrices) {
        renderText(matrices, (options.prefix ? getPrefix() : "") + getText(options), options.color.toInt(), options.x, options.y);
    }
}
