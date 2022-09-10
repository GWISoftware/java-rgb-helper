package me.ghost.rgbhelper.iterf.commands;

public class KeyboardCommands {



    public static String solid(String color) {
        return "keyboard solid " + color;
    }

    public static String blink(String color, int times) {
        return blink(color, times, -1);
    }

    public static String blink(String color, int times, int delay) {
        if (delay == -1) return "keyboard blink " + color + " " + times;
        return "keyboard blink " + color + " " + times + " delay " + delay;
    }

    public static String scroll(String color1, String color2, int times, String direction) {
        return scroll(color1, color2, times, direction, -1);
    }

    public static String scroll(String color1, String color2, int times, String direction, int delay) {
        if (delay == -1) return "keyboard scroll " + color1 + " " + color2 + " " + times + " " + direction;
        return "keyboard scroll " + color1 + " " + color2 + " " + times + " " + direction + " delay " + delay;
    }

    public static String flash(String color, int times, String side) {
        return flash(color, times, side, -1);
    }

    public static String flash(String color, int times, String side, int delay) {
        if (delay == -1) return "keyboard scroll " + color + " " + times + " " + side;
        return "keyboard scroll " + color + " " + times + " " + side + " delay " + delay;
    }

    public static String topbar(String color) {
        return "keyboard topbar " + color;
    }

}
