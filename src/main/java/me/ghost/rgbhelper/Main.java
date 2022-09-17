package me.ghost.rgbhelper;

import me.ghost.rgbhelper.iterf.MacroBuilder;
import me.ghost.rgbhelper.iterf.RGBInterface;
import me.ghost.rgbhelper.iterf.commands.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    private static final ExecutorService thread = Executors.newSingleThreadExecutor();


    public static void main(String[] args) {
        thread.execute(() -> {
            RGBHelper.log("Waiting for interface...");
            //RGBHelper.log("Helper files OK! Starting interface");
            RGBInterface rgbInterface = new RGBInterface();
            while (!rgbInterface.isOk()) {
                try {
                    Thread.sleep(500);
                } catch (Exception ignored) {}
            }
            RGBHelper.log("Starting tests... (keyboard)");

            MacroBuilder tk = new MacroBuilder();
            tk.add(KeyboardCommands.solid("white"));
            tk.add(MacroCommands.delay(500));
            tk.add(KeyboardCommands.topbar("green"));
            tk.add(MacroCommands.delay(500));
            tk.add(KeyboardCommands.flash("green", 2, "bottom"));
            tk.add(MacroCommands.delay(500));
            tk.add(KeyboardCommands.blink("green", 2));
            tk.add(MacroCommands.delay(500));
            tk.add(KeyboardCommands.scroll("white", "green", 2, "left_to_right"));
            rgbInterface.sendMacro(tk.get());

        });
    }
}
