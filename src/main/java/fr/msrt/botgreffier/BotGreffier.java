package fr.msrt.botgreffier;

import fr.msrt.botgreffier.info.Info;
import fr.msrt.botgreffier.jda.JDAManager;

public class BotGreffier {

    public static void main(String[] args) {

        System.out.println("BotGreffier v" + Info.VERSION);

        if (JDAManager.getShardManager() == null) {
            System.err.println("Greffier non connecté");
        } else {
            System.out.println("Greffier connecté !");
        }

    }

}