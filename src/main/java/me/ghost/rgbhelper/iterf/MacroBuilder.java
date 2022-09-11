package me.ghost.rgbhelper.iterf;

import java.util.ArrayList;

public class MacroBuilder {

    private final ArrayList<String> macro = new ArrayList<>();


    public MacroBuilder() {

    }

    public void add(String command) {
        this.macro.add(command);
    }

    public ArrayList<String> get() {
        return this.macro;
    }

}
