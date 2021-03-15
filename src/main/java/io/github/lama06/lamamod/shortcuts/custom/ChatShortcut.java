package io.github.lama06.lamamod.shortcuts.custom;

public class ChatShortcut {
    public String trigger;
    public String message;

    public ChatShortcut(String trigger, String message) {
        this.message = message;
        this.trigger = trigger;
    }

    public ChatShortcut() {
        this("", "");
    }
}
