package fr.msrt.botgreffier.utils;

import fr.msrt.botgreffier.Constants;
import org.apache.commons.lang3.StringUtils;

public class CmdUtils {

    /**
     * Affiche la commande dans la console.
     *
     * @param message Message contenant la commande
     */
    public static void sysoutCmd(String message) {

        if (message.length() <= 50) {
            System.out.println("Command: " + message);
        } else {
            System.out.println("Command: " + message.substring(0, 50) + " [...]");
        }

    }

    /**
     * Retourne l'avertissement d'une syntaxe incorrecte et donne la syntaxe à utiliser.
     *
     * @param message le message contenant la commande incorrecte
     * @param args syntaxe des arguments
     * @return L'avertissement d'une syntaxe incorrecte et la notation de la syntaxe à utiliser
     */
    public static String warnSyntax(String message, String args) {
        return Constants.EMOTE_ERR + " **Syntaxe incorrecte**\nLa syntaxe pour cette commande est : `" + getPrefixUsed(message) + getCmdName(message) + " " + args + "`";
    }

    /**
     * Donne le nom de  la commande utilisée.
     * Cela correspond à la commande sans préfixe et sans argument.
     *
     * @param message Message contenant la commande
     * @return Le nom de la commande
     */
    public static String getCmdName(String message) {
        return StringUtils.strip(message.substring(getPrefixLength(message))).split(" ")[0];
    }

    /**
     * Donne le préfixe utilisé pour invoquer la commande.
     *
     * @param message Message contenant la commande
     * @return Le préfixe utilisé
     */
    public static String getPrefixUsed(String message) {
        if (message.length() >= Constants.PREFIX_LENGTH && message.startsWith(Constants.PREFIX)) {
            return Constants.PREFIX;
        } else if (message.length() >= Constants.ALT_PREFIX_LENGTH && message.toLowerCase().startsWith(Constants.ALT_PREFIX)) {
            return Constants.ALT_PREFIX;
        } else {
            return null;
        }
    }

    /**
     * Donne la longueur du préfixe utilisé.
     *
     * @param message Message contenant la commande
     * @return La longueur du préfixe
     */
    public static int getPrefixLength(String message) {
        if (message.length() >= Constants.PREFIX_LENGTH && message.substring(0, Constants.PREFIX_LENGTH).equalsIgnoreCase(Constants.PREFIX)) {
            return Constants.PREFIX_LENGTH;
        } else if (message.length() >= Constants.ALT_PREFIX_LENGTH && message.substring(0, Constants.ALT_PREFIX_LENGTH).equalsIgnoreCase(Constants.ALT_PREFIX)) {
            return Constants.ALT_PREFIX_LENGTH;
        } else {
            return 0;
        }
    }

    /**
     * Permet de détecter si le message contient un ping <b>@here</b> ou <b>@everyone</b>.
     *
     * @param message Message
     * @return {@code true} si le message contient un ping
     */
    public static boolean antiPing(String message) {
        if (message.toLowerCase().contains("@everyone") || message.toLowerCase().contains("@here")) {
            System.out.println("AntiPing");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Permet de savoir si un message est une commande.
     *
     * @param message Message
     * @return {@code true} si le message est une commande
     */
    public static boolean isCommand(String message) {
        return message.length() > Constants.PREFIX_LENGTH && message.startsWith(Constants.PREFIX)
                || message.length() > Constants.ALT_PREFIX_LENGTH && message.toLowerCase().startsWith(Constants.ALT_PREFIX);
    }

    /**
     * Permet de savoir s'il s'agit d'un message d'annulation.
     *
     * @param message Message
     * @return {@code true} s'il s'agit d'un message d'annulation.
     */
    public static boolean isCancelled(String message) {
        return message.equalsIgnoreCase("stop")
                || message.equalsIgnoreCase("exit")
                || message.equalsIgnoreCase("cancel")
                || message.equalsIgnoreCase("annuler");
    }

}