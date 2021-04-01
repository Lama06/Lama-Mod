package io.github.lama06.lamamod.server.commands;

import io.github.lama06.lamamod.common.LamaMod;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.server.ServerMod;
import io.github.lama06.lamamod.server.util.ServerUtil;
import net.minecraft.server.network.ServerPlayerEntity;

public class VersionCommand extends AbstractCommand {
    @Override
    protected boolean isCommand(ServerPlayerEntity player, ChatMessage msg) {
        return msg.getText().equals("lamaserver");
    }

    @Override
    protected void executeCommand(ServerPlayerEntity player, ChatMessage msg) {
        String currentVersion = LamaMod.currentModVersion.toVersionTag();
        ServerUtil.sendMsg(player, String.format(ServerMod.PREFIX + "Auf diesem Server ist die Lama Mod %s installiert", currentVersion));
    }
}
