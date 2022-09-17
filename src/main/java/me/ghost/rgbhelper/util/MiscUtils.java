package me.ghost.rgbhelper.util;

public class MiscUtils {

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {}
    }
}
