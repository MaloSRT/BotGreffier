package fr.msrt.botgreffier;

import fr.msrt.botgreffier.config.Config;
import fr.msrt.botgreffier.ia.IAData;
import fr.msrt.botgreffier.info.Info;
import fr.msrt.botgreffier.jda.JDAManager;

/**
 * BotGreffier
 *
 * Site : https://msrt.ls2k17.fr/botgreffier
 * GitHub : https://github.com/MaloSRT/BotGreffier
 *
 * @author Malo
 * LS2k17 Corp.
 *
 */
public class BotGreffier {

    public static void main(String[] args) {

        System.out.println("BotGreffier v" + Info.VERSION);

        new Config("config.json");
        new IAData("iadata.json");

        if (JDAManager.getShardManager() == null) {
            System.err.println("Greffier non connecté");
        } else {
            System.out.println("Greffier connecté !");
        }

    }

}