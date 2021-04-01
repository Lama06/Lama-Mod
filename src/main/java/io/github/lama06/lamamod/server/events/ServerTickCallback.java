package io.github.lama06.lamamod.server.events;

import io.github.lama06.lamamod.common.events.Event;

public interface ServerTickCallback {
    Event<ServerTickCallback> EVENT = new Event<>((listeners) -> () -> {
        for(ServerTickCallback listener : listeners) {
            listener.onServerTick();
        }
    });

    void onServerTick();
}
