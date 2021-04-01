package io.github.lama06.lamamod.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Repräsentiert eine Nachricht im Chat
 */
public class ChatMessage {
    /**
     * Text der Nachricht
     */
    private final String text;

    /**
     * Erstellt eine neue Nachricht
     *
     * @param text Text der Nachricht
     */
    public ChatMessage(String text) {
        this.text = text.trim();
    }

    /**
     * Gibt den Text der Nachricht wieder. Alle Großbuchstaben werden zu Kleinbuchstaben.
     * Wenn die Nachricht, mit unveränderter Groß- und Kleinschreibung benötigt wird, sollte getPlainMsg
     * verwendet werden.
     *
     * @return den Text der nachricht in Kleinbuchstaben.
     */
    public String getText() {
        return getPlainText().toLowerCase(Locale.ROOT);
    }

    /**
     * Gibt den Text der Nachricht zurück.
     *
     * @return den Text der Nachricht
     */
    public String getPlainText() {
        return text;
    }

    /**
     * Gibt die Argumente der Nachricht zurück. Großbuchstaben werden zu Kleinbuchstaben.
     * Beispiel: "Hallo das ist ein Test" -> ["das", "ist", "ein", "test"]
     *
     * @return
     */
    public String[] getArgs() {
        List<String> args = new ArrayList<>();

        for (String arg : getPlainArgs()) {
            args.add(arg.toLowerCase(Locale.ROOT));
        }

        return args.toArray(new String[]{});
    }

    /**
     * Gibt die Argumente der Nachricht zurück
     *
     * @return due Argumente der Nachricht
     */
    public String[] getPlainArgs() {
        String[] args = text.split(" ");
        return Arrays.copyOfRange(args, 1, args.length);
    }

    /**
     * Gibt das erste Argument zurück. Großbuchstaben werden zu Kleinbuchstaben.
     * Beispiel: "Hallo das ist ein Test" -> "hallo"
     *
     * @return
     */
    public String getFirstArg() {
        return getFirstArgPlain().toLowerCase(Locale.ROOT);
    }

    /**
     * Gibt das erste Argument zurück.
     *
     * @return das erste Argument
     */
    public String getFirstArgPlain() {
        return text.split(" ")[0];
    }

    public String getArgsAsString() {
        StringBuilder string = new StringBuilder();

        for(String arg : getArgs()) {
            string.append(arg).append(" ");
        }

        return string.toString().trim();
    }

    public String getPlainArgsAsString() {
        StringBuilder string = new StringBuilder();

        for(String arg : getPlainArgs()) {
            string.append(arg).append(" ");
        }

        return string.toString().trim();
    }
}
