package io.github.lama06.lamamod.util;

import io.github.lama06.lamamod.LamaMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.LiteralText;
import net.minecraft.util.crash.CrashReport;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Util {
    public static final UUID Hepux06UUID = UUID.fromString("08f0ac27-c134-4324-8b16-6a53c8361c6d");
    public static final UUID Lama06UUID = UUID.fromString("7370723c-1f89-4e7c-a9fe-30ba8b4f0ae3");

    private static final MinecraftClient client = MinecraftClient.getInstance();

    /**
     * Verbindet den Client mit einem Server
     */
    public static void connectToServer(Screen parent, ServerInfo server) {
        client.openScreen(new ConnectScreen(parent, client, server));
    }

    /**
     * Schreibt etwas in eine Datei
     * @param file die Datei
     * @param content der Inhalt, der in die Datei geschrieben werden soll
     */
    public static void writeToFile(File file, String content) throws IOException {
        System.out.println("Schreibe Daten in Datei: " + file.getName());

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        writer.close();
    }

    /**
     * Liest den Inhalt einer Datei
     * @param file die Datei
     * @return den Inhalt der Datei
     */
    public static String readFromFile(File file) throws FileNotFoundException {
        System.out.println("Lese Daten aus Datei: " + file.getName());

        Scanner scanner = new Scanner(file);
        StringBuilder content = new StringBuilder();
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            content.append(line);
        }
        scanner.close();
        return content.toString();
    }

    /**
     * Sendet eine Nachricht in den Chat
     * @param msg die Nachricht
     */
    public static void sendMsgToChat(String msg) {
        client.player.sendChatMessage(msg);
    }

    public static void addToChatHistory(String msg) {
        client.inGameHud.getChatHud().addToMessageHistory(msg);
    }

    /**
     * Sendet eine Nachricht an den Spieler, die nur für ihn angezeigt wird
     * @param msg die Nachricht
     */
    public static void sendMsgToPlayer(String msg) {
        client.player.sendSystemMessage(new LiteralText(msg), net.minecraft.util.Util.NIL_UUID);
    }

    /**
     * Crasht das Spielt
     * @param reason der Grund, der im Launcher angezeigt wird
     */
    public static void crashGame(String reason) {
        client.setCrashReport(new CrashReport(reason, new Exception()));
    }

    /**
     * Gibt den Namen des Spielers zurück
     */
    public static String getPlayerName() {
        return client.player.getName().asString();
    }

    /**
     * Öffnet eine URL
     */
    public static void openURL(String url) {
        try {
            net.minecraft.util.Util.getOperatingSystem().open(new URL(url));
        } catch (MalformedURLException e) {
            LamaMod.handleException(e);
        }
    }
}
