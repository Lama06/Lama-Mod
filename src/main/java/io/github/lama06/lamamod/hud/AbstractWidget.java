package io.github.lama06.lamamod.hud;

import io.github.lama06.lamamod.options.Options;
import static io.github.lama06.lamamod.util.Util.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

/**
 * Sollte von jedem Widget erweitert werden
 * @param <T> die Einstellungen des Widgets.
 *           Dafür kann WidgetOptions verwendet werden, wenn das Widget keine weiteren Einstellungen benötigt.
 */
public abstract class AbstractWidget<T extends WidgetOptions> extends DrawableHelper {
    protected final MinecraftClient client = MinecraftClient.getInstance();
    protected final TextRenderer textRenderer = client.textRenderer;
    protected final int textColor = 14737632;

    /**
     * Muss vom Widget implementiert werden. Wird aufgerufen, wenn das Widget gerendert werden soll.
     */
    public abstract void renderWidget(T options, MatrixStack matrices);

    /**
     * Muss vom Widget implementiert werden. Wird aufgerufen, um die Optionen des Widgets zu erhalten.
     * @param options die Optionen der Mod
     */
    protected abstract T getWidgetOptions(Options options);

    /**
     * Muss vim Widget implementiert werden. Wird aufgerufen, umd die Optionen des Widgets zu ändern.
     * @param widgetOptions die neuen Einstellungen des Widgets
     */
    protected abstract void setWidgetOptions(T widgetOptions);

    /**
     * Rendert das Widget
     */
    public void render(MatrixStack matrices) {
        T options = getWidgetOptions(Options.getOptions());
        if(options.shown) {
            renderWidget(options, matrices);
        }
    }

    /**
     * Sollte aufgerufen werden, wenn eine Chat Nachricht gesendet wird
     */
    public void onChatMessage(String[] args) {
        T options = getWidgetOptions(Options.getOptions());

        try {
            if(args.length == 0) {
                if(options.shown) {
                    sendMsgToPlayer("Das Widget wird jetzt versteckt");
                    options.shown = false;
                } else {
                    sendMsgToPlayer("Das Widget wird jetzt angezeigt");
                    options.shown = true;
                }
                setWidgetOptions(options);
            } else if(args.length == 1) {
                if(args[0].equals("x")) {
                    sendMsgToPlayer("X ist " + options.x);
                } else if(args[0].equals("y")) {
                    sendMsgToPlayer("Y ist " + options.y);
                }
            } else if(args.length == 2) {
                if(args[0].equals("x")) {
                    options.x = Integer.parseInt(args[1]);
                    sendMsgToPlayer("Die X Position des Widgets wird jetzt auf " + options.x + " gesetzt");
                } else if(args[0].equals("y")) {
                    options.y = Integer.parseInt(args[1]);
                    sendMsgToPlayer("Die Y Position des Widgets wird jetzt auf " + options.y + " gesetzt");
                }
                setWidgetOptions(options);
            }
        } catch(NumberFormatException e) {
            sendMsgToPlayer("Bitte gebe eine richtige Zahl an");
        }
    }

    /**
     * Eine Funktion, die benutzt werden kann um Text darzustellen
     */
    protected void renderText(MatrixStack matrices, String text, float x, float y) {
        textRenderer.draw(matrices, new LiteralText(text), x, y, textColor);
    }
}
