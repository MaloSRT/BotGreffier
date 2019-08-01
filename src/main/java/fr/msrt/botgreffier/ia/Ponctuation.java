package fr.msrt.botgreffier.ia;

import java.util.Random;

class Ponctuation {

    static String getRandomExclamation() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return " !";
        } else {
            return "";
        }
    }

}
