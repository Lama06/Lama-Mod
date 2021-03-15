package io.github.lama06.lamamod.shortcuts;

import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.events.MessageSentCallback;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.client.MinecraftClient;

import java.util.Locale;

/**
 * Ein Shortcut ist ein spezielles Wort, der, wenn er vom Spieler in den Chat geschrieben wird, dazu führt, dass
 * zum Beispiel ein Command ausgeführt wird.
 * Muss von jedem Shortcut erweitert werden.
 * @param <T> die Optionen des Shortcuts. Wenn es keine weiteren Optionen gibt, kann ShortcutOptions verwendet werden
 */
public abstract class AbstractShortcut<T extends ShortcutOptions> implements MessageSentCallback {
    protected final MinecraftClient client = MinecraftClient.getInstance();

    /**
     * Muss von jedem Shortcut implementiert werden und zurückgeben, ob es sich bei einer vom Spieler in den Chat
     * geschriebenen Nachricht um eine Nachricht handlet, die von diesem Chat Shortcut betroffen ist.
     * @param msg die Nachricht, die der Spieler in den Chat geschrieben hat
     */
    protected abstract boolean isChatShortcut(ChatMessage msg);

    /**
     * Muss von jedem Shortcut implementiert werden. Die Methode wird dann aufgerufen, wenn der Chat Shortcut vom Spieler
     * aufgerufen worden ist.
     * @param msg die Nachricht, die der Spieler in den Chat geschrieben hat
     */
    protected abstract void executeChatShortcut(ChatMessage msg);

    /**
     * Muss von jedem Shortcut implementiert werden. Sollte den Namen zurückgeben, unter dem durch den Chat die Einstellungen
     * des Shortcuts angepasst werden können. Groß- und Kleinschreibung spielt keine Rolle.
     */
    protected abstract String getName();

    /**
     * Zur implementierung des Interfaces MessageSentCallback benötigt. Die Methode wird aufgerufen, wenn der Spieler
     * eine Nachricht in den Chat sendet und dient dazu, bei einer Chat Nachricht
     * zu überprüfen, ob es sich um den Chat Shortcut handelt und zu überpfüfen ob der Spieler durch das Senden der Nachricht
     * die Einstellungen des Shortcuts, die aus der Klasse ShortcutOptions stammen, ändern will.
     * Wenn ein Shortcut, weil er zum Beispiel eine Einstellung hat, deren Wert über den Chat verändert werden soll, das
     * Verhalten der Methode erweitern will, kann er die überschreiben, muss dann aber immer am Ende der Methode
     * return super.onChatMessage(msg) aufrufen.
     * @param msg die Nachricht, die der
     */
    public EventResult onChatMessage(ChatMessage msg) {
        T options = getShortcutOptions();

        if(msg.getText().startsWith(getName().toLowerCase(Locale.ROOT))) {
            String[] args = msg.getArgs();

            if(args.length == 0) {
                if(options.enabled) {
                    options.enabled = false;
                    Util.sendMsgToPlayer("Der Shortcut ist jetzt deaktiviert");
                } else {
                    options.enabled = true;
                    Util.sendMsgToPlayer("Der Shortcut ist jeztz aktiviert");
                }
                setShortcutOptions(options);
            }

            return EventResult.CANCEL;
        } else if(options.enabled && isChatShortcut(msg)) {
            executeChatShortcut(msg);
            return EventResult.CANCEL;
        }

        return EventResult.PASS;
    }

    /**
     * Muss von jedem Shortcut implementiert werden. Sollte die aktuellen Einstellungen des Shortcuts zurückgeben.
     * @param options die Optionen der Mod
     * @return die Einstellungen des Shortcuts
     */
    protected abstract T getShortcutOptions(Options options);

    protected T getShortcutOptions() {
        return getShortcutOptions(Options.getOptions());
    }

    /**
     * Muss von jedem Shortcut implementiert werden. Sollte die Einstellungen des Shortcuts festlegen.
     * @param shortcutOptions die neuen Einstellugen
     */
    protected abstract void setShortcutOptions(T shortcutOptions);
}
