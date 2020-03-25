package fr.msrt.botgreffier.features;

import fr.msrt.botgreffier.utils.Utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class PenduMot {

    private char[] motATrouver;
    private char[] motATrouverDisp;
    private int[] lettreTrouve;

    public PenduMot() {

        String[] tabMots = new String[22703];

        try {
            InputStream flux = new FileInputStream("/root/botgreffier/res/mots.txt");
            // InputStream flux = new FileInputStream("/home/malo/IdeaProjects/BotGreffier/src/main/resources/mots.txt");
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String ligne;
            int i = 0;
            while ((ligne = buff.readLine()) != null) {
                tabMots[i] = ligne;
                i++;
            }
            buff.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int nbAlea = new Random().nextInt(tabMots.length);
        motATrouver = Utils.onlyAlphabetLetters(tabMots[nbAlea].toLowerCase()).toCharArray();
        motATrouverDisp = tabMots[nbAlea].toCharArray();
        lettreTrouve = new int[motATrouver.length];

        for(int i = 0; i < motATrouver.length; i++) {
            if (Utils.isAlphabetLetter(String.valueOf(motATrouver[i]))) {
                lettreTrouve[i] = 0;
            } else {
                lettreTrouve[i] = 1;
            }
        }

        // debug
        /* for (char c : MotATrouver) {
            System.out.print(c + "\t");
        }
        System.out.print("\n");
        for (int i = 0; i < MotATrouver.length; i++) {
            System.out.print(MotATrouverDisp[i]+"\t");
        }
        System.out.print("\n");
        for (int value : LettreTrouve) {
            System.out.print(value + "\t");
        }
        System.out.print("\n"); */

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