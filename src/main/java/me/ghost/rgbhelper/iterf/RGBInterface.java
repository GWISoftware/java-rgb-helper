package me.ghost.rgbhelper.iterf;

import me.ghost.rgbhelper.RGBHelper;
import me.ghost.rgbhelper.util.FileUtils;
import me.ghost.rgbhelper.util.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RGBInterface {

    //private final File HOME_DIR = Utils.HOME_DIR;
    private final ExecutorService THREAD = Executors.newCachedThreadPool();

    private Process process = null;
    private boolean ok = false;
    private boolean created = false;


    public RGBInterface() {
        this.create();
    }


    private void create() {
        if (this.created) {
            RGBHelper.log("RBGHelper crashed, attempting to restart.");
        } else {
            this.created = true;
        }

        File rgbHelper = new File(Utils.HOME_DIR, "rgbhelper.exe");
        File rgbDll = new File(Utils.HOME_DIR, "Colore.dll");

        if (!rgbHelper.exists()) {
            RGBHelper.log("Cannot start interface, rgbhelper.exe missing from current path.");
            this.ok = false;
            return;
        }

        if (!rgbDll.exists()) {
            RGBHelper.log("Cannot start interface, Colore.dll missing from current path.");
            this.ok = false;
            return;
        }

        ProcessBuilder builder = new ProcessBuilder(rgbHelper.getAbsolutePath());
        try {
            RGBHelper.log("Waiting for rgbhelper.exe");
            this.process = builder.start();
            this.THREAD.execute(() -> {
                try {
                    this.process.waitFor();
                } catch (Exception ignored) {}
            });
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) if (line.contains("Command?")) break;
            reader.close();
            RGBHelper.log("Connected to rgbhelper!");
            this.ok = true;
        } catch (IOException e) {
            RGBHelper.log("Cannot start interface, error while starting rgbhelper.exe");
            this.ok = false;
        }
    }

    public boolean isOk() {
        return this.ok;
    }

    public void sendMacro(ArrayList<String> commands) {
        StringBuilder command = new StringBuilder("macro ");
        for (String cmd : commands) command.append(cmd).append(",");
        command.deleteCharAt(command.length() - 1);
        command.append("\n");
        RGBHelper.log("sendMacro | " + command);
        try {
            if (!this.process.isAlive()) {
                RGBHelper.log("Interface unlinked (rgbhelper crashed?)");
                this.ok = false;
                return;
            }
            this.process.outputWriter().write(command.toString());
            this.process.outputWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCmd(String cmd) {
        cmd += "\n";
        try {
            if (!this.process.isAlive()) {
                RGBHelper.log("Interface unlinked (rgbhelper crashed?)");
                this.ok = false;
                return;
            }
            this.process.outputWriter().write(cmd);
            this.process.outputWriter().flush();
        } catch (IOException e) {
            RGBHelper.log("Error sending command " + cmd);
            e.printStackTrace();
        }
    }


}
