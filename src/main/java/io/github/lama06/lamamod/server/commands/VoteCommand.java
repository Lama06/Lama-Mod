package io.github.lama06.lamamod.server.commands;

import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.server.events.ServerTickCallback;
import io.github.lama06.lamamod.server.util.ServerUtil;
import io.github.lama06.lamamod.server.vote.Vote;
import net.minecraft.server.network.ServerPlayerEntity;

public class VoteCommand extends AbstractCommand implements ServerTickCallback {
    private Vote currentVote = null;

    @Override
    protected boolean isCommand(ServerPlayerEntity player, ChatMessage msg) {
        String text = msg.getText();
        return text.startsWith("abstimmung") || text.startsWith("ja") || text.startsWith("nein") || text.startsWith("ne");
    }

    @Override
    protected void executeCommand(ServerPlayerEntity player, ChatMessage msg) {
        if(msg.getText().startsWith("abstimmung")) {
            if(Vote.voteRunning) {
                ServerUtil.sendMsg(player, Vote.PREFIX + "Es läuft bereits eine Abstimmung");
            } else {
                String question = msg.getPlainArgsAsString();
                currentVote = new Vote(player.getServer(), player.getName().asString(), question, (yes, no) -> currentVote = null);
            }
        } else {
            if(currentVote != null) {
                currentVote.onChatMessage(player, msg);
            }
        }
    }

    @Override
    public void onServerTick() {
        if(currentVote != null) {
            currentVote.onServerTick();
        }
    }
}
