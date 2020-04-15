package fr.msrt.botgreffier.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.util.stream.Stream;

public class StringUtils {

    public static boolean isURL(String s) {
        try {
            new URL(s);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public static boolean isAlphabetLetter(String s) {
        String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        return (s.length() == 1) && Stream.of(alphabet).anyMatch(s::equalsIgnoreCase);
    }

    public static boolean isInteger(String s) {
        return s.matches("-?\\d+");
    }

    public static String onlyAlphabetLetters(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static String formatURLArg(String s) {
        return s.replace("%", "%25")
                .replace("/", "%2f")
                .replace("#", "%23")
                .replace("?", "%3F")
                .replace("&", "%26")
                .replace("=", "%3D")
                .replace("$", "%24")
                .replace("+", "%2B")
                .replace(",", "%2C")
                .replace(";", "%3B")
                .replace("@", "%40")
                .replace(":", "%3A")
                .replace(" ", "+");
    }

}