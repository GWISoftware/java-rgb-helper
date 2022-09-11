package me.ghost.rgbhelper.util;

import me.ghost.rgbhelper.RGBHelper;

import java.io.File;

public class Utils {

    public static final File HOME_DIR = new File(FileUtils.getWorkDir(), "java-rgb");
    public static final String COLORE = "https://github.com/StonedGiraffe/rgb-helper/releases/download/0.1/Colore.dll";
    public static final String RGB_HELPER = "https://github.com/StonedGiraffe/rgb-helper/releases/download/0.1/rgbhelper.exe";


    public static boolean checkHelpers() {
        File colore = new File(HOME_DIR, "Colore.dll");
        File rgbhelper = new File(HOME_DIR, "rgbhelper.exe");

        if (!HOME_DIR.exists()) HOME_DIR.mkdirs();
        if (colore.exists() && rgbhelper.exists()) return true;

        if (!colore.exists()) {
            RGBHelper.log("Trying to download Colore.dll");
            if (!FileUtils.download(COLORE, colore)) {
                RGBHelper.log("Couldn't download Colore.dll");
                return false;
            }
        }

        if (!rgbhelper.exists()) {
            RGBHelper.log("Trying to download rgbhelper.exe");
            if (!FileUtils.download(RGB_HELPER, rgbhelper)) {
                RGBHelper.log("Couldn't download rgbhelper.exe");
                return false;
            }
        }


        return true;
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {}
    }
}
