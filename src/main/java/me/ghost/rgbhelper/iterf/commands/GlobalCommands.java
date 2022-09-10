package me.ghost.rgbhelper.iterf.commands;

public class GlobalCommands {

    public static String setEverything(String color) {
        return "global solid " + color;
    }

    public static String setDefault(String color) {
        return "global set default_color " + color;
    }

}
