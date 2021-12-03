package fr.msrt.botgreffier.features;

import fr.msrt.botgreffier.utils.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Random;

public class PenduMot {

    private final char[] motATrouver;
    private final char[] motATrouverDisp;
    private final int[] lettreTrouve;

    public PenduMot() {

        String[] tabMots = new String[22703];

        try {
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("mots.txt");
            InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(stream));
            BufferedReader buff = new BufferedReader(reader);
            String ligne;
            int i = 0;
            while ((ligne = buff.readLine()) != null) {
                tabMots[i] = ligne;
                i++;
            }
            buff.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int nbAlea = new Random().nextInt(tabMots.length);
        motATrouver = StringUtils.onlyAlphabetLetters(tabMots[nbAlea].toLowerCase()).toCharArray();
        motATrouverDisp = tabMots[nbAlea].toCharArray();
        lettreTrouve = new int[motATrouver.length];

        for(int i = 0; i < motATrouver.length; i++) {
            if (StringUtils.isAlphabetLetter(String.valueOf(motATrouver[i]))) {
                lettreTrouve[i] = 0;
            } else {
                lettreTrouve[i] = 1;
            }
        }

    }

    public boolean testLettre(char caract) {

        boolean reussite = false;
        for (int i = 0; i < motATrouver.length; i++) {
            if(caract == motATrouver[i]) {
                lettreTrouve[i] = 1;
                reussite = true;
            }
        }
        return reussite;

    }

    public String getLettreMot() {

        StringBuilder mot = new StringBuilder();
        for(int i = 0; i < motATrouver.length; i++) {
            if (lettreTrouve[i] == 1) {
                mot.append(motATrouverDisp[i]).append(" ");
            } else {
                mot.append("_ ");
            }
        }
        return mot.toString().toUpperCase();

    }

    public boolean getVictoire() {

        int nbLettreTrouve = 0;
        boolean victoire = false;

        for (int value : lettreTrouve) {
            if (value == 1) nbLettreTrouve++;
        }

        if (nbLettreTrouve == motATrouver.length) victoire = true;

        return victoire;

    }

    public String getMot() {

        StringBuilder mot = new StringBuilder();
        for(int i = 0; i < motATrouver.length; i++) {
            mot.append(motATrouverDisp[i]);
        }
        return mot.toString();

    }

}