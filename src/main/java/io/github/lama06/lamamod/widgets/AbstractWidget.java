package io.github.lama06.lamamod.widgets;

import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.events.HudRenderCallback;
import io.github.lama06.lamamod.events.MessageSentCallback;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.util.ChatMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.Locale;

import static io.github.lama06.lamamod.util.Util.sendMsgToPlayer;

/**
 * Sollte von jedem Widget erweitert werden
 *
 * @param <T> die Einstellungen des Widgets.
 *            Dafür kann WidgetOptions verwendet werden, wenn das Widget keine weiteren Einstellungen benötigt.
 */
public abstract class AbstractWidget<T extends WidgetOptions> extends DrawableHelper implements HudRenderCallback, MessageSentCallback {
    protected final MinecraftClient client = MinecraftClient.getInstance();

    /**
     * Muss vom Widget implementiert werden. Wird aufgerufen, wenn das Widget gerendert werden soll.
     */
    public abstract void renderWidget(T options, MatrixStack matrices);

    protected abstract String getName();

    /**
     * Muss vom Widget implementiert werden. Wird aufgerufen, um die Optionen des Widgets zu erhalten.
     *
     * @param options die Optionen der Mod
     */
    protected abstract T getWidgetOptions(Options options);

    protected T getWidgetOptions() {
        return getWidgetOptions(Options.getOptions());
    }

    /**
     * Muss vom Widget implementiert werden. Wird aufgerufen, umd die Optionen des Widgets zu ändern.
     *
     * @param widgetOptions die neuen Einstellungen des Widgets
     */
    protected abstract void setWidgetOptions(T widgetOptions);

    /**
     * Rendert das Widget
     */
    public void onHudRender(MatrixStack matrices) {
        T options = getWidgetOptions(Options.getOptions());
        if (options.shown) {
            renderWidget(options, matrices);
        }
    }

    public EventResult onChatMessage(ChatMessage msg) {
        if (msg.getText().startsWith(getName().toLowerCase(Locale.ROOT))) {
            T options = getWidgetOptions(Options.getOptions());
            String[] args = msg.getArgs();

            try {
                if (args.length == 0) {
                    if (options.shown) {
                        sendMsgToPlayer("Das Widget wird jetzt versteckt");
                        options.shown = false;
                    } else {
                        sendMsgToPlayer("Das Widget wird jetzt angezeigt");
                        options.shown = true;
                    }
                    setWidgetOptions(options);
                } else if (args.length == 1) {
                    if (args[0].equals("x")) {
                        sendMsgToPlayer("X ist " + options.x);
                    } else if (args[0].equals("y")) {
                        sendMsgToPlayer("Y ist " + options.y);
                    }
                } else if (args.length == 2) {
                    if (args[0].equals("x")) {
                        options.x = Integer.parseInt(args[1]);
                        sendMsgToPlayer("Die X Position des Widgets wird jetzt auf " + options.x + " gesetzt");
                    } else if (args[0].equals("y")) {
                        options.y = Integer.parseInt(args[1]);
                        sendMsgToPlayer("Die Y Position des Widgets wird jetzt auf " + options.y + " gesetzt");
                    }
                    setWidgetOptions(options);
                }

            } catch (NumberFormatException e) {
                sendMsgToPlayer("Bitte gebe eine richtige Zahl an");
            }

            return EventResult.CANCEL;
        } else {
            return EventResult.PASS;
        }
    }

    /**
     * Eine Funktion, die benutzt werden kann um Text darzustellen
     */
    protected void renderText(MatrixStack matrices, String text, int textColor, float x, float y) {
        MinecraftClient.getInstance().textRenderer.draw(matrices, new LiteralText(text), x, y, textColor);
    }
}
