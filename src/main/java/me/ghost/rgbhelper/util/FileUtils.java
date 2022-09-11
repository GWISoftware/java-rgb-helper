package me.ghost.rgbhelper.util;

import me.ghost.rgbhelper.RGBHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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

    public static boolean download(String url, File output) {
        try {
            InputStream in = new URL(url).openStream();
            Files.copy(in, output.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
