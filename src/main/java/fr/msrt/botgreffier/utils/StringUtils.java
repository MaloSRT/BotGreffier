package fr.msrt.botgreffier.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.stream.Stream;

public class StringUtils {

    /**
     * Vérifie si un {@link String} est une URL.
     *
     * @param s {@link String}
     * @return {@code true} si le {@code String} est une URL
     */
    public static boolean isURL(String s) {
        try {
            new URL(s);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    /**
     * Vérifie si le {@link String} est une lettre de l'alphabet :
     * ne contient qu'un seul caractère qui doit être une lettre sans accent, cédille, etc.
     *
     * @param s {@link String}
     * @return {@code true} si le {@link String} est une lettre de l'alphabet
     */
    public static boolean isAlphabetLetter(String s) {
        String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        return (s.length() == 1) && Stream.of(alphabet).anyMatch(s::equalsIgnoreCase);
    }

    /**
     * Vérifie si le {@link String} est un entier relatif.
     *
     * @param s {@link String}
     * @return {@code true} si le {@link String} est un entier
     */
    public static boolean isInteger(String s) {
        return s.matches("-?\\d+");
    }

    /**
     * Remplace toutes les lettres accentuées par des lettres de l'alphabet.
     * Ne change pas les autres caractères.
     *
     * Par exemple :    "Böñĵṏùr Gréffîêr, çấ vá ?"
     * retourne :       "Bonjour Greffier, ca va ?"
     *
     * @param s {@code String}
     * @return {@code String} avec les lettres accentuées remplacées par des lettres simples
     */
    public static String onlyAlphabetLetters(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    /**
     * Formate le {@link String} pour qu'il puisse être inséré en tant qu'argument dans une URL.
     *
     * @param s {@link String}
     * @return le {@link String} formaté
     */
    public static String formatURLArg(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}