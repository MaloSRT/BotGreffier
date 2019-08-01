package fr.msrt.botgreffier.features;

import fr.msrt.botgreffier.utils.Utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class PenduMotMystere {

    private char[] MotATrouver;
    private char[] MotATrouverDisp;
    private int[] LettreTrouve;

    public PenduMotMystere() {
        String[] tabMots = new String[22703];


        try {
            InputStream flux = new FileInputStream("/home/malo/IdeaProjects/BotGreffier/src/main/resources/mots.txt");
            // InputStream flux = getClass().getResourceAsStream("res/mots.txt");
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
        MotATrouver = Utils.onlyAlphabetLetters(tabMots[nbAlea].toLowerCase()).toCharArray();
        MotATrouverDisp = tabMots[nbAlea].toCharArray();
        LettreTrouve = new int[MotATrouver.length];

        for(int i = 0; i < MotATrouver.length; i++) {
            if (Utils.isAlphabetLetter(String.valueOf(MotATrouver[i]))) {
                LettreTrouve[i] = 0;
            } else {
                LettreTrouve[i] = 1;
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
        for (int i = 0; i < MotATrouver.length; i++) {
            if(caract == MotATrouver[i]) {
                LettreTrouve[i] = 1;
                reussite = true;
            }
        }
        return reussite;

    }

    public String getLettreMot() {

        StringBuilder mot = new StringBuilder();
        for(int i = 0; i < MotATrouver.length; i++) {
            if (LettreTrouve[i] == 1) {
                mot.append(MotATrouverDisp[i]).append(" ");
            } else {
                mot.append("_ ");
            }
        }
        return mot.toString().toUpperCase();

    }

    public boolean getVictoire() {

        int nbLettreTrouve = 0;
        boolean victoire = false;

        for (int value : LettreTrouve) {
            if (value == 1) nbLettreTrouve++;
        }

        if (nbLettreTrouve == MotATrouver.length) victoire = true;

        return victoire;

    }

    public String getMot() {

        StringBuilder mot = new StringBuilder();
        for(int i = 0; i < MotATrouver.length; i++) {
            mot.append(MotATrouverDisp[i]);
        }
        return mot.toString();

    }

}