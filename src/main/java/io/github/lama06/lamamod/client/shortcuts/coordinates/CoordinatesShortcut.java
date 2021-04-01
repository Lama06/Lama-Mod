package io.github.lama06.lamamod.client.shortcuts.coordinates;

import io.github.lama06.lamamod.common.events.EventResult;
import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.ClientUtil;
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
            ClientUtil.sendMsgToPlayer("Deine Koordinaten werden in den Chat gesendet und in die Zwischenablage kopiert");
            ClientUtil.sendMsgToChat(coordinates);
            client.keyboard.setClipboard(coordinates);
        } else if (options.copyToClipboard) {
            ClientUtil.sendMsgToPlayer("Deine Koordinaten werden in die Zwischenablage kopiert");
            client.keyboard.setClipboard(coordinates);
        } else if (options.writeToChat) {
            ClientUtil.sendMsgToPlayer("Deine Koordinaten werden in den Chat geschrieben");
            ClientUtil.sendMsgToChat(coordinates);
        } else {
            ClientUtil.sendMsgToPlayer("Du hast deaktiviert, dass die Koordinaten in den Chat gesendet und dass die Koordinaten kopiert werden");
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
                        ClientUtil.sendMsgToPlayer("Die Koordinaten werden jetzt nicht mehr in den Chat geschrieben");
                    } else {
                        options.writeToChat = true;
                        ClientUtil.sendMsgToPlayer("Die Koordinaten werden jetzt wieder in den Chat gesendet");
                    }
                    setShortcutOptions(options);
                } else if (args[0].equals("copy")) {
                    if (options.copyToClipboard) {
                        options.copyToClipboard = false;
                        ClientUtil.sendMsgToPlayer("Die Koordinaten werden jetzt nicht mehr in die Zwischenablage kopiert");
                    } else {
                        options.copyToClipboard = true;
                        ClientUtil.sendMsgToPlayer("Die Koordinaten werden jetzt wieder in die Zwischenablage kopiert");
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
    protected CoordinatesShortcutOptions getShortcutOptions(ClientOptions options) {
        return options.coordinatesShortcut;
    }

    @Override
    protected void setShortcutOptions(CoordinatesShortcutOptions shortcutOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.coordinatesShortcut = shortcutOptions;
        ClientOptions.setOptions(options);
    }
}
