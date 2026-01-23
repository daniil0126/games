package com.donii.GameCenter.casino.Utils;

public class Text {
    public static final String RESET = "\u001B[0m";

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static String red(String text){
        return Text.RED + text + Text.RESET;
    }
    public static String green(String text){
        return Text.GREEN + text + Text.RESET;
    }
    public static String yellow(String text){
        return Text.YELLOW + text + Text.RESET;
    }
    public static String blue(String text){
        return Text.BLUE + text + Text.RESET;
    }
    public static String purple(String text){
        return Text.PURPLE + text +  Text.RESET;
    }
    public static String cyan(String text){
        return Text.CYAN + text +  Text.RESET;
    }
    public static String white(String text){
        return Text.WHITE + text +  Text.RESET;
    }

}