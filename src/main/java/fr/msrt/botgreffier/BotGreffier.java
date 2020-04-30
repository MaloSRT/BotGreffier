package fr.msrt.botgreffier;

import fr.msrt.botgreffier.config.Config;
import fr.msrt.botgreffier.info.Info;
import fr.msrt.botgreffier.jda.JDAManager;

public class BotGreffier {

    /**
     * BotGreffier
     *
     * Site : https://msrt.ls2k17.fr
     * GitHub : https://github.com/MaloSRT/BotGreffier
     *
     * @author Malo
     * LS2k17 Corp.
     *
     */
    public static void main(String[] args) {

        System.out.println("BotGreffier v" + Info.VERSION);

        new Config("config.json");

        if (JDAManager.getShardManager() == null) {
            System.err.println("Greffier non connecté");
        } else {
            System.out.println("Greffier connecté !");
        }

    }

}