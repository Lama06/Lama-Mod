package io.github.lama06.lamamod.server.util;

import net.minecraft.network.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Util;

public class ServerUtil {
    public static void brodcastMsg(MinecraftServer server, String text) {
        server.getPlayerManager().broadcastChatMessage(new LiteralText(text), MessageType.SYSTEM, Util.NIL_UUID);
    }

    public static void sendMsg(ServerPlayerEntity player, String msg) {
        player.sendSystemMessage(new LiteralText(msg), Util.NIL_UUID);
    }
}
