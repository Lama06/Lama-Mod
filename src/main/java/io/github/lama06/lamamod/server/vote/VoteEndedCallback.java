package io.github.lama06.lamamod.server.vote;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Set;

public interface VoteEndedCallback {
    void onVoteEnded(Set<ServerPlayerEntity> votesYes, Set<ServerPlayerEntity> votesNo);
}
