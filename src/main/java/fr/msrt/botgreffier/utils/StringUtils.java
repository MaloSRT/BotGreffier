package fr.msrt.botgreffier.utils;

import java.text.Normalizer;
import java.util.stream.Stream;

public class StringUtils {

    public static boolean isLink(String s) {
        if (s.length() < 10) return false;
        return (s.substring(0, 7).equalsIgnoreCase("http://") || s.substring(0, 8).equalsIgnoreCase("https://"))
                && s.contains(".");
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

}