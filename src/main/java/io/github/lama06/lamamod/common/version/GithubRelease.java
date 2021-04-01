package io.github.lama06.lamamod.common.version;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.github.lama06.lamamod.common.LamaMod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Stellt ein Release der Mod auf Github da
 */
public class GithubRelease {
    @SerializedName("tag_name")
    public String versionTag = "v0.0.0";

    @SerializedName("html_url")
    public String htmlUrl = "https://www.example.com/";

    public ModVersion getVersion() {
        return new ModVersion(versionTag);
    }

    private static GithubRelease[] cachedReleases = null;

    /**
     * Gibt eine Liste aller GithubReleases zurück, die über die GithubAPI abgerufen worden sind
     * Gibt null bei einem Error zurück
     */
    public static GithubRelease[] fetchReleases() {
        try {
            if (cachedReleases != null) {
                return cachedReleases;
            }

            URL url = new URL("https://api.github.com/repos/Lama06/Lama-Mod/releases");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder responseBuilder = new StringBuilder();
            while (true) {
                String nextLine = in.readLine();

                if (nextLine == null) {
                    break;
                } else {
                    responseBuilder.append(nextLine);
                }
            }
            String response = responseBuilder.toString();

            Gson gson = new Gson();
            GithubRelease[] releases = gson.fromJson(response, GithubRelease[].class);

            cachedReleases = releases;
            return releases;
        } catch (Exception e) {
            LamaMod.handleException(e);
            return new GithubRelease[]{new GithubRelease()};
        }
    }

    /**
     * Gibt das neuste GithubRelease zurück, das über die GithubAPI abgerufen worden ist
     */
    public static GithubRelease fetchNewestRelease() {
        GithubRelease[] releases = fetchReleases();
        if (releases.length == 0) {
            return new GithubRelease();
        } else {
            return releases[0];
        }
    }

    /**
     * Gibt zurück, ob eine neuere Version der Mod verfügabr ist
     */
    public static boolean isNewerModVersionAvailable() {
        ModVersion newestVersion = GithubRelease.fetchNewestRelease().getVersion();
        return LamaMod.currentModVersion.isOlderThan(newestVersion);
    }
}
