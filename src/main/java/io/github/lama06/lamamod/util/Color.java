package io.github.lama06.lamamod.util;

import java.util.HashMap;
import java.util.Map;

public class Color {
    public int red;
    public int green;
    public int blue;

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int toInt() {
        int rgb = red;
        rgb = (rgb << 8) + green;
        rgb = (rgb << 8) + blue;
        return rgb;
    }

    public String toString() {
        return "Rot: " + red + " Grün: " + green + " Blau: " + blue;
    }

    public static Color white = new Color(255, 255, 255);
    public static Color black = new Color(0, 0, 0);
    public static Color redColor = new Color(255, 0, 0);
    public static Color greenColor = new Color(0, 255, 0);
    public static Color blueColor = new Color(0, 0, 255);
    public static Color pink = new Color(226, 12, 237);

    public static Map<String, Color> colors = new HashMap<>();

    static {
        colors.put("white", white);
        colors.put("black", black);
        colors.put("red", redColor);
        colors.put("green", greenColor);
        colors.put("blue", blueColor);
        colors.put("pink", pink);
        colors.put("oinky", pink);
    }
}
