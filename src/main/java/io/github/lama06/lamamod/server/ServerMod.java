package io.github.lama06.lamamod.server;

import io.github.lama06.lamamod.server.commands.VersionCommand;
import io.github.lama06.lamamod.server.commands.VoteCommand;
import io.github.lama06.lamamod.server.events.MessageSentCallback;
import io.github.lama06.lamamod.server.events.ServerTickCallback;
import net.fabricmc.api.DedicatedServerModInitializer;

public class ServerMod implements DedicatedServerModInitializer {
    public static final String PREFIX = "[LamaMod] ";

    public VoteCommand voteCommand = new VoteCommand();
    public VersionCommand versionCommand = new VersionCommand();

    private void registerListeners() {
        MessageSentCallback.EVENT.register(voteCommand);
        ServerTickCallback.EVENT.register(voteCommand);

        MessageSentCallback.EVENT.register(versionCommand);
    }

    @Override
    public void onInitializeServer() {
        registerListeners();
    }
}
