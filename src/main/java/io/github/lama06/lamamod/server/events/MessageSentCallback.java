package io.github.lama06.lamamod.server.events;

import io.github.lama06.lamamod.common.events.Event;
import io.github.lama06.lamamod.common.events.EventResult;
import io.github.lama06.lamamod.common.util.ChatMessage;
import net.minecraft.server.network.ServerPlayerEntity;

public interface MessageSentCallback {
    Event<MessageSentCallback> EVENT = new Event<>((listeners) -> (player, msg) -> {
        for(MessageSentCallback listener : listeners) {
            EventResult result = listener.onChatMessage(player, msg);

            if(result == EventResult.CANCEL) {
                return result;
            }
        }

        return EventResult.PASS;
    });

    EventResult onChatMessage(ServerPlayerEntity player, ChatMessage msg);
}
