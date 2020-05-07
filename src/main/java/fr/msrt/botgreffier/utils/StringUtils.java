package fr.msrt.botgreffier.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}