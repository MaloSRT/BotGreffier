package fr.msrt.botgreffier.ia;

import java.util.Random;

class Punctuation {

     static String getRandomExclamation() {
         Random random = new Random();
         if (random.nextBoolean()) {
             return " !";
         } else {
             return "";
         }
     }

    static String getRandomDot() {
        Random random = new Random();
        if (random.nextInt(4) == 0) {
            return ".";
        } else {
            return "";
        }
    }

}