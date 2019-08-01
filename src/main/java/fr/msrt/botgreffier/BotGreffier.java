package fr.msrt.botgreffier;

import fr.msrt.botgreffier.bdd.MySQL;
import fr.msrt.botgreffier.config.Config;
import fr.msrt.botgreffier.info.Info;
import fr.msrt.botgreffier.jda.JDAManager;

import java.io.IOException;

public class BotGreffier {

    public static final Config CONFIG;

    static {
        Config config = null;
        try {
            config = new Config("config.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CONFIG = config;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {

        if (CONFIG == null) {
            System.err.println("config.json n'a pas été chargé correctement");
            return;
        }

        MySQL mySql = new MySQL();

        System.out.println("BotGreffier " + Info.getVer());

        if (mySql.GetSQLstatus() && mySql.GetAvailability()) {

            System.out.println("Connecté à la BDD");

            while (mySql.Difference() <= 35) {
                System.out.println("Une instance est déjà démarrée, nouvel essai dans 30s...");
                delay();
            }

            if (mySql.Difference() > 35) {
                mySql.UpdateRunTime();
                if (JDAManager.getShardManager() == null) {
                    System.err.println("Greffier non connecté");
                    return;
                } else {
                    System.out.println("Greffier connecté !");
                }
                while (true) {
                    mySql.UpdateRunTime();
                    delay();
                }

            }

        }

    }

    private static void delay() {

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
