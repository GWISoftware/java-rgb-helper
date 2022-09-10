package me.ghost.rgbhelper;

import me.ghost.rgbhelper.iterf.MacroBuilder;
import me.ghost.rgbhelper.iterf.RGBInterface;
import me.ghost.rgbhelper.iterf.commands.*;

import java.util.logging.Logger;

public class RGBHelper {

    public static final Logger LOG = Logger.getLogger("java-rgb");
    public static final RGBInterface RGB_INTERFACE = new RGBInterface();




    public static void macroExample() {
        MacroBuilder testMacro = new MacroBuilder();
        // solid coloring
        testMacro.addCommand(GlobalCommands.setEverything("red"));
        testMacro.addCommand(KeyboardCommands.solid("green"));
        testMacro.addCommand(MouseCommands.solid("blue"));
        testMacro.addCommand(MousepadCommands.solid("blue"));
        testMacro.addCommand(HeadsetCommands.solid("green"));
        // adding delay before next command(s)
        testMacro.addCommand(MacroCommands.delay(5000));
        // keyboard effects
        testMacro.addCommand(KeyboardCommands.blink("red", 5));
        testMacro.addCommand(KeyboardCommands.flash("red", 5, "left")); // left right top bottom
        // top_to_bottom bottom_to_top left_to_right right_to_left
        testMacro.addCommand(KeyboardCommands.scroll("blue", "white", 2, "top_to_bottom"));
        testMacro.addCommand(KeyboardCommands.topbar("orange"));

        testMacro.run();
    }

    public static void log(String info) {
        LOG.info(info);
    }


}
