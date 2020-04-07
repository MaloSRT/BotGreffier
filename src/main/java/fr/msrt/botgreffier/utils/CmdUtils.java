package fr.msrt.botgreffier.utils;

import fr.msrt.botgreffier.Constants;
import org.apache.commons.lang3.StringUtils;

public class CmdUtils {

    public static void sysoutCmd(String message) {

        if (message.length() <= 50) {
            System.out.println("Command: " + message);
        } else {
            System.out.println("Command: " + message.substring(0, 50) + " [...]");
        }

    }

    public static String warnSyntax(String message, String args) {
        return ":x: **Syntaxe incorrecte**\nLa syntaxe pour cette commande est : `" + getPrefixUsed(message) + getCmdName(message) + " " + args + "`";
    }

    public static String getCmdName(String message) {
        return StringUtils.strip(message.substring(Constants.PREFIX.length())).split(" ")[0];
    }

    public static String getPrefixUsed(String message) {
        if (message.length() >= Constants.PREFIX_LENGTH && message.substring(0, Constants.PREFIX_LENGTH).equalsIgnoreCase(Constants.PREFIX)) {
            return Constants.PREFIX;
        } else if (message.length() >= Constants.ALT_PREFIX_LENGTH && message.substring(0, Constants.ALT_PREFIX_LENGTH).equalsIgnoreCase(Constants.ALT_PREFIX)) {
            return Constants.ALT_PREFIX;
        } else {
            return null;
        }
    }

    public static boolean antiPing(String message) {
        if (message.toLowerCase().contains("@everyone") || message.toLowerCase().contains("@here")) {
            System.out.println("AntiPing");
            return false;
        } else {
            return true;
        }
    }

    public static boolean isCommand(String message) {
        return message.length() > Constants.PREFIX.length() && message.substring(0, Constants.PREFIX.length()).equals(Constants.PREFIX);
    }

    public static boolean isCancelled(String message) {
        return message.equalsIgnoreCase("stop")
                || message.equalsIgnoreCase("exit")
                || message.equalsIgnoreCase("cancel")
                || message.equalsIgnoreCase("annuler");
    }

}