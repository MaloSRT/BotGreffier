package fr.msrt.botgreffier.utils;

public class CmdUtils {

    public static String warnSyntax(String syntax) {
        return ":x: **Syntaxe incorrecte**\nLa syntaxe pour cette commande est : `" + syntax + "`";
    }

    public static String warnPing() {
        return ":x: **Cette commande a été ignorée pour éviter un ping**";
    }

    public static void sysoutCmd(String msgContent) {

        if (msgContent.length() <= 50) {
            System.out.println("Command : " + msgContent);
        } else {
            System.out.println("Command : " + msgContent.substring(0, 50) + " [...]");
        }

    }
}
