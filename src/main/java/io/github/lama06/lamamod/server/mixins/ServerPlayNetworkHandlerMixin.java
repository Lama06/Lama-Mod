package io.github.lama06.lamamod.server.mixins;

import io.github.lama06.lamamod.common.events.EventResult;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.server.events.MessageSentCallback;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onGameMessage", at = @At("HEAD"), cancellable = true)
    public void onGameMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        if(!packet.getChatMessage().startsWith("/")) {
            EventResult result = MessageSentCallback.EVENT.invoker().onChatMessage(player, new ChatMessage(packet.getChatMessage()));

            if(result == EventResult.CANCEL) {
                ci.cancel();
            }
        }
    }
}
