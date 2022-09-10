package me.ghost.rgbhelper.util;

import me.ghost.rgbhelper.RGBHelper;

import java.io.File;
import java.security.CodeSource;

public class FileUtils {

    public static File getThis() {
        CodeSource codeSource = RGBHelper.class.getProtectionDomain().getCodeSource();
        try {
            return new File(codeSource.getLocation().toURI().getPath());
        } catch (Exception e) {
            return null;
        }
    }

    public static File getWorkDir() {
        CodeSource codeSource = RGBHelper.class.getProtectionDomain().getCodeSource();
        try {
            return new File(codeSource.getLocation().toURI().getPath()).getParentFile();
        } catch (Exception e) {
            return null;
        }
    }

}
