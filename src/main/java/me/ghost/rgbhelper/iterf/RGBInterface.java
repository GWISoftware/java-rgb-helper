package me.ghost.rgbhelper.iterf;

import me.ghost.rgbhelper.RGBHelper;
import me.ghost.rgbhelper.util.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RGBInterface {


    private File HOME_DIR = new File(FileUtils.getWorkDir(), "java-rgb");
    private final ExecutorService THREAD = Executors.newCachedThreadPool();

    private Process process = null;
    private boolean ok = false;
    private boolean created = false;


    public RGBInterface() {
        this.create();
    }

    public RGBInterface(File dir) {
        this.HOME_DIR = new File(dir, "java-rgb");
        this.create();
    }


    private boolean prepare() {
        if (!this.HOME_DIR.exists()) this.HOME_DIR.mkdirs();
        return this.checkColore() && this.checkHelper();
    }


    private void create() {

        if (!this.created && !this.prepare()) {
            RGBHelper.log("Couldn't start interface (check logs)");
            return;
        }

        if (this.created) {
            RGBHelper.log("RBGHelper crashed, attempting to restart.");
        } else {
            this.created = true;
        }

        File rgbHelper = new File(this.HOME_DIR, "rgbhelper.exe");
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
            e.printStackTrace();
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



    public boolean checkColore() {
        File colore = new File(this.HOME_DIR, "Colore.dll");
        if (colore.exists()) return true;
        return FileUtils.copyResource("Colore.dll", colore);
    }

    public boolean checkHelper() {
        File rgbhelper = new File(this.HOME_DIR, "rgbhelper.exe");
        if (rgbhelper.exists()) return true;
        return FileUtils.copyResource("rgbhelper.exe", rgbhelper);
    }

}
