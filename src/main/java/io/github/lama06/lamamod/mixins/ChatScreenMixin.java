package io.github.lama06.lamamod.mixins;

import io.github.lama06.lamamod.LamaMod;
import io.github.lama06.lamamod.accessors.InGameHudAccess;
import io.github.lama06.lamamod.options.ChatShortcutOptions;
import io.github.lama06.lamamod.options.Options;
import static io.github.lama06.lamamod.util.Util.*;

import io.github.lama06.lamamod.version.GithubRelease;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Locale;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin extends Screen {
    @Shadow protected TextFieldWidget chatField;

    protected ChatScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "keyPressed", cancellable = true)
    public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        Options options = Options.getOptions();

        // Wenn Enter gedrückt wird
        if(keyCode == 257) {
            GithubRelease newestRelease = GithubRelease.fetchNewestRelease();
            InGameHudAccess inGameHudAccess = (InGameHudAccess) client.inGameHud;

            String plainMsg = chatField.getText().trim();
            String msg = plainMsg.toLowerCase(Locale.ROOT);
            String[] args = getArgumentsFromMsg(msg);

            boolean isCustomChatShortcut = false;
            String customChatShortcutMsg = null;
            for(ChatShortcutOptions shortcut : options.customChatShortcuts) {
                if(shortcut.trigger.trim().toLowerCase(Locale.ROOT).equals(msg)) {
                    isCustomChatShortcut = true;
                    customChatShortcutMsg = shortcut.message;
                }
            }

            if(isCustomChatShortcut) {
                sendMsgToChat(customChatShortcutMsg);
            } else if(msg.equals("tsd") || msg.equals("day") || msg.equals("d")) {
                sendMsgToChat("/time set day");
            } else if(msg.equals("tsn") || msg.equals("night") || msg.equals("n")) {
                sendMsgToChat("/time set night");
            } else if(msg.equals("weber") || msg.equals("webergmbh")) {
                sendMsgToChat("WeberGMBH");
            } else if(msg.equals("wehner") || msg.equals("wehnergmbh") || msg.equals("lamas sind doof") || msg.equals("ich mag keine lamas") || msg.equals("ich mag die sonne nicht")) {
                crashGame("das ist verboten");
            } else if(msg.equals("neuste version") || msg.equals("version") || msg.equals("update") || msg.equals("wclient") || msg.equals("w-client")) {
                if(GithubRelease.isNewerModVersionAvailable()) {
                    sendMsgToPlayer("Es ist eine neuere Version der Lama Mod verfügbar: " + newestRelease.versionTag + " (aktuelle Version: " + LamaMod.currentModVersion.toVersionTag() + ")");
                } else {
                    sendMsgToPlayer("Deine Version der Lama Mod ist die neuste: " + LamaMod.currentModVersion.toVersionTag());
                }
            } else if(msg.startsWith("blitz")) {
                String playerName;

                if(args.length == 0) {
                    playerName = getPlayerName();
                    sendMsgToPlayer("<Zeus> Ich bin sauer! Du hast mir nicht gesagt, wo der Blitz einschlagen soll!");
                } else {
                    playerName = args[0];
                    sendMsgToPlayer("<Zeus> Ich lasse einen Blitz bei " + playerName + " einschlagen!");
                }

                sendMsgToChat("/execute at " + playerName + " as " + playerName + " run summon lightning_bolt ~ ~ ~");
            } else if(msg.equals("c") || msg.equals("coordinates") || msg.equals("koordinaten")) {
                if(client.player == null) return;

                String coordinates = Math.round(client.player.getX()) + " " + Math.round(client.player.getY()) + " " + Math.round(client.player.getZ());

                sendMsgToPlayer("Die Koordinaten werden in den Chat gesendet und in die Zwischenablage kopiert");

                sendMsgToChat(coordinates);
                client.keyboard.setClipboard(coordinates);
            } else if(msg.startsWith("fpswidget")) {
                inGameHudAccess.getFpsWidget().onChatMessage(args);
            } else if(msg.startsWith("timewidget")) {
                inGameHudAccess.getTimeWidget().onChatMessage(args);
            } else if(msg.startsWith("coordswidget")) {
                inGameHudAccess.getCoordinatesWidget().onChatMessage(args);
            } else if(msg.startsWith("versionwidget")) {
                inGameHudAccess.getVersionWidget().onChatMessage(args);
            }

            else {
                if(client.player.getUuid().equals(Hepux06UUID) && !msg.startsWith("/") && !msg.endsWith("...")) {
                    sendMsgToChat(plainMsg + "...");
                } else {
                    sendMsgToChat(plainMsg);
                }
            }

            // Die gesendete Nachricht zum Chat Verlauf hinzufügen
            addToChatHistory(plainMsg);

            client.openScreen(null);
            cir.setReturnValue(true);
        }
    }
}
