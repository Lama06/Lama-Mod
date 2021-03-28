package io.github.lama06.lamamod.shortcuts.coordinates;

import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Locale;

/**
 * Ein Shortcut, der die Koordinaten des Spielers in den Chat schreibt und in die Zwischenablage kopiert
 */
public class CoordinatesShortcut extends AbstractShortcut<CoordinatesShortcutOptions> {
    @Override
    protected boolean isChatShortcut(ChatMessage msg) {
        String text = msg.getText();
        return text.equals("c") || text.equals("coordinates") || text.equals("koordinaten") || text.equals("wo bin ich");
    }

    @Override
    protected void executeChatShortcut(ChatMessage msg) {
        CoordinatesShortcutOptions options = getShortcutOptions();
        PlayerEntity player = client.player;
        String coordinates = Math.round(player.getX()) + " " + Math.round(player.getY()) + " " + Math.round(player.getZ());

        if (options.copyToClipboard && options.writeToChat) {
            Util.sendMsgToPlayer("Deine Koordinaten werden in den Chat gesendet und in die Zwischenablage kopiert");
            Util.sendMsgToChat(coordinates);
            client.keyboard.setClipboard(coordinates);
        } else if (options.copyToClipboard) {
            Util.sendMsgToPlayer("Deine Koordinaten werden in die Zwischenablage kopiert");
            client.keyboard.setClipboard(coordinates);
        } else if (options.writeToChat) {
            Util.sendMsgToPlayer("Deine Koordinaten werden in den Chat geschrieben");
            Util.sendMsgToChat(coordinates);
        } else {
            Util.sendMsgToPlayer("Du hast deaktiviert, dass die Koordinaten in den Chat gesendet und dass die Koordinaten kopiert werden");
        }
    }

    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        if (msg.getText().startsWith(getName().toLowerCase(Locale.ROOT))) {
            String[] args = msg.getArgs();
            CoordinatesShortcutOptions options = getShortcutOptions();

            if (msg.getArgs().length == 1) {
                if (args[0].equals("chat")) {
                    if (options.writeToChat) {
                        options.writeToChat = false;
                        Util.sendMsgToPlayer("Die Koordinaten werden jetzt nicht mehr in den Chat geschrieben");
                    } else {
                        options.writeToChat = true;
                        Util.sendMsgToPlayer("Die Koordinaten werden jetzt wieder in den Chat gesendet");
                    }
                    setShortcutOptions(options);
                } else if (args[0].equals("copy")) {
                    if (options.copyToClipboard) {
                        options.copyToClipboard = false;
                        Util.sendMsgToPlayer("Die Koordinaten werden jetzt nicht mehr in die Zwischenablage kopiert");
                    } else {
                        options.copyToClipboard = true;
                        Util.sendMsgToPlayer("Die Koordinaten werden jetzt wieder in die Zwischenablage kopiert");
                    }
                    setShortcutOptions(options);
                }
            }
        }

        return super.onChatMessage(msg);
    }

    @Override
    protected String getName() {
        return "CoordsShortcut";
    }

    @Override
    protected CoordinatesShortcutOptions getShortcutOptions(Options options) {
        return options.coordinatesShortcut;
    }

    @Override
    protected void setShortcutOptions(CoordinatesShortcutOptions shortcutOptions) {
        Options options = Options.getOptions();
        options.coordinatesShortcut = shortcutOptions;
        Options.setOptions(options);
    }
}
