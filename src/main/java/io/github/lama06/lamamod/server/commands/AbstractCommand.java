package io.github.lama06.lamamod.server.commands;

import io.github.lama06.lamamod.common.events.EventResult;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.server.events.MessageSentCallback;
import net.minecraft.server.network.ServerPlayerEntity;

public abstract class AbstractCommand implements MessageSentCallback {
    protected abstract boolean isCommand(ServerPlayerEntity player, ChatMessage msg);

    protected abstract void executeCommand(ServerPlayerEntity player, ChatMessage msg);

    @Override
    public EventResult onChatMessage(ServerPlayerEntity player, ChatMessage msg) {
        if(isCommand(player, msg)) {
            executeCommand(player, msg);
            return EventResult.CANCEL;
        }

        return EventResult.PASS;
    }
}
