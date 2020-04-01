package fr.msrt.botgreffier.utils;

import fr.msrt.botgreffier.Constants;
import org.apache.commons.lang3.StringUtils;

public class CmdUtils {

    public static void sysoutCmd(String message) {

        if (message.length() <= 50) {
            System.out.println("Command : " + message);
        } else {
            System.out.println("Command : " + message.substring(0, 50) + " [...]");
        }

    }

    public static String warnSyntax(String syntax) {
        return ":x: **Syntaxe incorrecte**\nLa syntaxe pour cette commande est : `" + syntax + "`";
    }

    public static String getCmdName(String message) {
        return StringUtils.strip(message.substring(Constants.PREFIX.length())).split(" ")[0];
    }

    public static boolean antiPing(String message) {
        if (message.toLowerCase().contains("@everyone") || message.toLowerCase().contains("@here")) {
            System.out.println("AntiPing");
            return false;
        } else {
            return true;
        }
    }

}