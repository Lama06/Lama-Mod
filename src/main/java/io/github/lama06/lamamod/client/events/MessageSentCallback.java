package io.github.lama06.lamamod.client.events;

import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.common.events.Event;
import io.github.lama06.lamamod.common.events.EventResult;

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
