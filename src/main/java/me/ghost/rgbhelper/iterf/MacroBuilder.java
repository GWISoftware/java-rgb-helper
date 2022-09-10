package me.ghost.rgbhelper.iterf;

import me.ghost.rgbhelper.RGBHelper;

import java.util.ArrayList;

public class MacroBuilder {

    private final ArrayList<String> macro = new ArrayList<>();


    public MacroBuilder() {

    }

    public void addCommand(String command) {
        this.macro.add(command);
    }

    public void run() {
        RGBHelper.RGB_INTERFACE.sendMacro(this.macro);
    }

}
