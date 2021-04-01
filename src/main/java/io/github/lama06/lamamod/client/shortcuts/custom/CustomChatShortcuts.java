package io.github.lama06.lamamod.client.shortcuts.custom;

import io.github.lama06.lamamod.common.events.EventResult;
import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.ClientUtil;

import java.util.Locale;

enum WaitingForMessageStatus {
    NO,
    TRIGGER,
    MESSAGE,
}

public class CustomChatShortcuts extends AbstractShortcut<CustomShortcutOptions> {
    private ChatShortcut newShortcut = new ChatShortcut();
    private WaitingForMessageStatus waitingForMessage = WaitingForMessageStatus.NO;

    @Override
    protected boolean isChatShortcut(ChatMessage msg) {
        for (ChatShortcut shortcut : getShortcutOptions().shortcuts) {
            if (msg.getText().equals(shortcut.trigger.toLowerCase(Locale.ROOT).trim())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void executeChatShortcut(ChatMessage msg) {
        for (ChatShortcut shortcut : getShortcutOptions().shortcuts) {
            if (msg.getText().equals(shortcut.trigger.toLowerCase(Locale.ROOT).trim())) {
                ClientUtil.sendMsgToChat(shortcut.message);
            }
        }
    }

    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        CustomShortcutOptions options = getShortcutOptions();
        String[] args = msg.getArgs();

        if (waitingForMessage != WaitingForMessageStatus.NO) {
            if (waitingForMessage == WaitingForMessageStatus.TRIGGER) {
                newShortcut.trigger = msg.getPlainText();
                ClientUtil.sendMsgToPlayer(msg.getPlainText());

                waitingForMessage = WaitingForMessageStatus.MESSAGE;
                ClientUtil.sendMsgToPlayer("Gibt bitte jetzt die Nachricht ein: ");
            } else if (waitingForMessage == WaitingForMessageStatus.MESSAGE) {
                newShortcut.message = msg.getPlainText();
                ClientUtil.sendMsgToPlayer(msg.getPlainText());

                waitingForMessage = WaitingForMessageStatus.NO;
                options.shortcuts.add(newShortcut);
                setShortcutOptions(options);
                ClientUtil.sendMsgToPlayer("Shortcut hinzugefügt!");
            }
            return EventResult.CANCEL;
        } else if (msg.getText().startsWith(getName().toLowerCase(Locale.ROOT))) {
            if (args.length >= 1) {
                if (args[0].equals("list")) {
                    int amountOfShortcuts = options.shortcuts.size();
                    ClientUtil.sendMsgToPlayer(String.format("Es gibt %s Shortcuts: ", amountOfShortcuts));
                    for (ChatShortcut shortcut : options.shortcuts) {
                        ClientUtil.sendMsgToPlayer(String.format("%s -> %s", shortcut.trigger, shortcut.message));
                    }
                } else if (args[0].equals("add")) {
                    newShortcut = new ChatShortcut();
                    waitingForMessage = WaitingForMessageStatus.TRIGGER;
                    ClientUtil.sendMsgToPlayer("Gib bitte jetzt den Trigger ein: ");
                }
            }
        }

        return super.onChatMessage(msg);
    }

    @Override
    protected String getName() {
        return "CustomShortcuts";
    }

    @Override
    protected CustomShortcutOptions getShortcutOptions(ClientOptions options) {
        return options.customShortcuts;
    }

    @Override
    protected void setShortcutOptions(CustomShortcutOptions shortcutOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.customShortcuts = shortcutOptions;
        ClientOptions.setOptions(options);
    }
}
