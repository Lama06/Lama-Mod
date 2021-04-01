package io.github.lama06.lamamod.server.vote;

import io.github.lama06.lamamod.common.events.EventResult;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.server.events.MessageSentCallback;
import io.github.lama06.lamamod.server.events.ServerTickCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import static io.github.lama06.lamamod.server.util.ServerUtil.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Eine Abstimmung, bei der mit Ja oder Nein abgestimmt werden kann
 */
public class Vote implements MessageSentCallback, ServerTickCallback {
    /** Gibt an ob auf dem Server aktuell eine Abstimmung läuft */
    public static boolean voteRunning = false;
    public static final String PREFIX = "[Abstimmung] ";

    private final MinecraftServer server;
    /** Die Anzahl der Ticks, die bis zum Ende der Abstimmung noch verbleiben */
    private int ticksLeft = 600;
    /** Alle Spieler, die für Ja abgestimmt haben */
    private final Set<ServerPlayerEntity> votesYes = new HashSet<>();
    /** Alle Spieler, die für Nein abgestimmt haben */
    private final Set<ServerPlayerEntity> votesNo = new HashSet<>();
    /** Ein Callback, das aufgerufen wird, wenn die Abstimmung beendet ist */
    private final VoteEndedCallback onEndedCallback;

    /**
     * @param server der Minecraft Server
     * @param creator der Name des Erstellers der Abstimmung
     * @param question die Frage der Abstimmung
     * @param onEndedCallback ein Callback, das aufgerufen wird, wenn die Abstimmung beendet ist
     */
    public Vote(MinecraftServer server, String creator, String question, VoteEndedCallback onEndedCallback) {
        this.server = server;
        this.onEndedCallback = onEndedCallback;

        voteRunning = true;

        brodcastMsg(server, PREFIX + "Neue Abstimmung:");
        brodcastMsg(server, String.format("<%s> %s", creator, question));
        brodcastMsg(server, PREFIX + "Zum Abstimmen Ja oder Nein in den Chat schreiben");
    }

    private int getYesVotes() {
        return votesYes.size();
    }

    private int getNoVotes() {
        return votesNo.size();
    }

    private boolean hasVoted(ServerPlayerEntity player) {
        return votesYes.contains(player) || votesNo.contains(player);
    }

    private void onEnded() {
        voteRunning = false;
        onEndedCallback.onVoteEnded(votesYes, votesNo);

        int yes = getYesVotes();
        int no = getNoVotes();

        if(yes > no) {
            brodcastMsg(server, String.format(PREFIX + "Das Ergebis der Abstimmung ist JA mit %s zu %s Stimmen", yes, no));
        } else if(no > yes) {
            brodcastMsg(server, String.format(PREFIX + "Das Ergebnis der Abstimmung ist NEIN mit %s zu %s Stimmen", no, yes));
        } else {
            brodcastMsg(server, String.format(PREFIX + "Das Erbebnis der Abstimmung ist UNENTSCHIEDEN mit %s zu %s", yes, yes));
        }
    }

    @Override
    public EventResult onChatMessage(ServerPlayerEntity player, ChatMessage msg) {
        String text = msg.getText();
        String playerName = player.getName().asString();

        if(text.equals("ja")) {
            if(hasVoted(player)) {
                if(votesNo.contains(player)) {
                    brodcastMsg(server, String.format(PREFIX + "%s hat seine Meinung zu JA geändert", playerName));
                    votesNo.remove(player);
                    votesYes.add(player);
                } else {
                    sendMsg(player, PREFIX + "Du hast schon für JA abgestimmt");
                }
            } else {
                brodcastMsg(server, String.format(PREFIX + "%s hat für JA abgestimmt", playerName));
                votesYes.add(player);
            }

            return EventResult.CANCEL;
        } else if(text.equals("nein") || text.equals("ne")) {
            if(hasVoted(player)) {
                if(votesYes.contains(player)) {
                    brodcastMsg(server, String.format(PREFIX + "%s hat seine Meinung zu NEIN geändert", playerName));
                    votesYes.remove(player);
                    votesNo.add(player);
                } else {
                    sendMsg(player, PREFIX + "Du hast schon für NEIN abgestimmt");
                }
            } else {
                brodcastMsg(server, String.format(PREFIX + "%s hat für NEIN abgestimmt", playerName));
                votesNo.add(player);
            }

            return EventResult.CANCEL;
        }

        return EventResult.PASS;
    }

    @Override
    public void onServerTick() {
        if(ticksLeft > 0) {
            ticksLeft--;

            if (ticksLeft == 200) {
                brodcastMsg(server, PREFIX + "Die Abstimmung endet in 10 Sekunden");
            } else if (ticksLeft == 0) {
                onEnded();
            }
        }
    }

}
