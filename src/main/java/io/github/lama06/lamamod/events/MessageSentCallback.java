package io.github.lama06.lamamod.events;

import io.github.lama06.lamamod.util.ChatMessage;

public interface MessageSentCallback {
    Event<MessageSentCallback> BEFORE_SENT = new Event<>((listeners) -> (msg) -> {
        for (MessageSentCallback listener : listeners) {
            EventResult result = listener.onChatMessage(msg);

            if (result == EventResult.CANCEL) {
                return result;
            }
        }
        return EventResult.PASS;
    });

    EventResult onChatMessage(ChatMessage msg);
}
