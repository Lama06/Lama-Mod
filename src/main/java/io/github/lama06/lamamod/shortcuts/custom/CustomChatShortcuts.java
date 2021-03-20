package io.github.lama06.lamamod.shortcuts.custom;

import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.shortcuts.AbstractShortcut;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

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
                Util.sendMsgToChat(shortcut.message);
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
                Util.sendMsgToPlayer(msg.getPlainText());

                waitingForMessage = WaitingForMessageStatus.MESSAGE;
                Util.sendMsgToPlayer("Gibt bitte jetzt die Nachricht ein: ");
            } else if (waitingForMessage == WaitingForMessageStatus.MESSAGE) {
                newShortcut.message = msg.getPlainText();
                Util.sendMsgToPlayer(msg.getPlainText());

                waitingForMessage = WaitingForMessageStatus.NO;
                options.shortcuts.add(newShortcut);
                setShortcutOptions(options);
                Util.sendMsgToPlayer("Shortcut hinzugefügt!");
            }
            return EventResult.CANCEL;
        } else if (msg.getText().startsWith(getName().toLowerCase(Locale.ROOT))) {
            if (args.length >= 1) {
                if (args[0].equals("list")) {
                    int amountOfShortcuts = options.shortcuts.size();
                    Util.sendMsgToPlayer(String.format("Es gibt %s Shortcuts: ", amountOfShortcuts));
                    for (ChatShortcut shortcut : options.shortcuts) {
                        Util.sendMsgToPlayer(String.format("%s -> %s", shortcut.trigger, shortcut.message));
                    }
                } else if (args[0].equals("add")) {
                    newShortcut = new ChatShortcut();
                    waitingForMessage = WaitingForMessageStatus.TRIGGER;
                    Util.sendMsgToPlayer("Gib bitte jetzt den Trigger ein: ");
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
    protected CustomShortcutOptions getShortcutOptions(Options options) {
        return options.customShortcuts;
    }

    @Override
    protected void setShortcutOptions(CustomShortcutOptions shortcutOptions) {
        Options options = Options.getOptions();
        options.customShortcuts = shortcutOptions;
        Options.setOptions(options);
    }
}
