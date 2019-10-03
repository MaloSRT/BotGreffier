package fr.msrt.botgreffier;

// import fr.msrt.botgreffier.bdd.MySQL;
import fr.msrt.botgreffier.info.Info;
import fr.msrt.botgreffier.jda.JDAManager;

public class BotGreffier {

    public static void main(String[] args) {

        // MySQL mySql = new MySQL();

        System.out.println("BotGreffier v" + Info.VERSION);

        /* if (mySql.GetSQLstatus() && mySql.GetAvailability()) {

            System.out.println("Connecté à la BDD");


            if (mySql.Difference() > 35) {
                mySql.UpdateRunTime(); */

                if (JDAManager.getShardManager() == null) {
                    System.err.println("Greffier non connecté");
                } else {
                    System.out.println("Greffier connecté !");
                }

            //}

        //}

    }

}
