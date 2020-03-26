package fr.msrt.botgreffier.ia;

import fr.msrt.botgreffier.Constants;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class IA {

    private static final String[]
            msgMerci = {"merci", "mrc"},
            msgBonjour = {"bonjour", "salut", "coucou", "bjr", "slt", "cc ", " cc", "hello", "hi ", " hi", "yo ", " yo"},
            msgAuRevoir = {"au revoir", "aurevoir", "la revoyure"},
            msgCaVa = {"ça va", "sa va", "çava", "sava", "comment va", "tu vas bien"},
            msgAide = {"aide", "help", "aled"},
            msgQui = {"qui es-tu", "qui es tu", "tu es qui", "t'es qui", "t ki"},
            msgJtm = {"je t'aime", "jtm", "je taime", "je t aime"},
            msgBeau = {"t'es beau", "tu es beau", "est beau", "tu es magnifique", "est très beau", "es très beau", "t bo"},
            msgMoche = {"es pas beau", "es moche", "est pas beau", "est moche"},
            msgGentil = {"es gentil", "est gentil", "es sympa", "est sympa", "es aimable", "est aimable"},
            msgMechant = {"méchant", "mechant", "pas gentil", "pas sympa"},
            msgWesh = {"wesh", "wsh", "ouaich", "ouaish"},
            msgTfq = {"tu fais quoi", "qu'est ce que tu fais", "tfk", "tfq", "qtf", "qvf", "vfk", "vfq"},
            msgLol = {"mdr", "ptdr", "kek", "lol", "issou", "xd", "x)", "jpp"},
            msgFilip = {"filip", "forgetzat"},
            msgTg = {"tg", "ta gueule", "la ferme", "ferme-la", "ferme la", "nique ta", "mange tes morts", "ntm"},
            msgInsulte = {"connard", "fdp", "enculé", "putain", "pute", "merde", " con ", " nique ", "batard", "salop", "débile", "debile", "attardé", "est con ", "es con "},
            msgMeilleur = {"meilleur", "mieux"},
            msgOk = {"d'accord", "ok", "d accord", "dacc", "niquel", "parfait"};

    public static String getAnswer(String message) {

        String msg = message.toLowerCase();

        if ("GREFFIER".equals(message)) {
            return "OUI" + Ponctuation.getRandomExclamation();
        }

        if ("Greffier".equalsIgnoreCase(message)) {
            return ":D";
        }

        if (Stream.of(msgMerci).anyMatch(msg::contains)) {
            String[] ans = {
                    "De rien",
                    "Pas de souci"
            };
            return aleaAns(ans) + Ponctuation.getRandomExclamation();
        }

        if ((Stream.of(msgBonjour).anyMatch(msg::contains))
                && (Stream.of(msgCaVa).anyMatch(msg::contains))) {
            String[] ans = {
                    "Salut, ça va et toi ?",
                    "Bonjour, ça va et toi ?",
                    "Salut ! Je vais bien et toi ?",
                    "Bonjour, ça va bien et toi ?",
                    "Salut, je vais bien et toi ça va ?"
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgBonjour).anyMatch(msg::contains)) {
            String[] ans = {
                    "Bonjour",
                    "Salut",
                    "Salut toi",
                    "Bonjour toi",
                    "Bien le bonjour"
            };
            return aleaAns(ans) + Ponctuation.getRandomExclamation();
        }

        if (Stream.of(msgAuRevoir).anyMatch(msg::contains)) {
            String[] ans = {
                    "Au revoir",
                    "Salut à la prochaine",
                    "Salut",
                    "Salut à plus",
                    "Salut, à plus tard"
            };
            return aleaAns(ans) + Ponctuation.getRandomExclamation();
        }

        if (Stream.of(msgCaVa).anyMatch(msg::contains)) {
            String[] ans = {
                    "Bien et toi ?",
                    "Ça va bien, et toi ?",
                    "Je vais bien et toi ?",
                    "Bien et toi ça va ?",
                    "Ça va et toi ?"
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgAide).anyMatch(msg::contains)) {
            String[] ans = {
                    "La page d'aide est disponible ici : " + Constants.URL_CMDS,
                    "Voici un peu d'aide : " + Constants.URL_CMDS,
                    "Ma page d'aide est ici : " + Constants.URL_CMDS
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgQui).anyMatch(msg::contains)) {
            String[] ans = {
                    "Je suis un greffier",
                    "Moi c'est le greffier"
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgJtm).anyMatch(msg::contains)) {
            String[] ans = {
                    "Moi aussi",
                    "Je t'aime aussi",
                    ":heart:",
                    "jtm"
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgBeau).anyMatch(msg::contains)) {
            String[] ans = {
                    "Toi aussi",
                    "T'es beau aussi",
                    "Toi aussi t'es beau",
                    "Merci",
                    "Merci beaucoup"
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgMoche).anyMatch(msg::contains)) {
            String[] ans = {
                    "Toi aussi t'es moche",
                    "T'es laid aussi t'en fais pas",
                    "Toi non plus t'es pas beau",
                    "Non mais regarde-toi avant de parler"
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgGentil).anyMatch(msg::contains)) {
            String[] ans = {
                    "Merci toi aussi t'es gentil",
                    "Tu es aussi fort aimable",
                    "Je sais je suis gentil",
                    "Oui je suis gentil"
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgMechant).anyMatch(msg::contains)) {
            String[] ans = {
                    "C'est toi qui est méchant",
                    "T'es pas gentil toi'",
                    "Je suis gentil, je suis pas méchant",
                    "Non je suis gentil"
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgWesh).anyMatch(msg::contains)) {
            String[] ans = {
                    "wsh",
                    "wesh gros",
                    "Wesh ça va ?",
                    "wesh"
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgTfq).anyMatch(msg::contains)) {
            String[] ans = {
                    "Je joue à LS2k17 et toi ?",
                    "Je m'amuse sur LS2k17 et toi ?",
                    "Je joue à LS2k17, et toi ?",
                    "Je m'éclate sur LS2k17, et toi ?",
                    "Je joue encore à LS2k17",
                    "Je joue toujours à LS2k17"
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgLol).anyMatch(msg::contains)) {
            String[] ans = {
                    "mdr",
                    "ptdr",
                    "lol",
                    "jpp",
                    "xptdr",
                    "MDR",
                    "PTDR",
                    "LOL",
                    "JPP",
                    ":joy: :ok_hand:"
            };
            return aleaAns(ans);
        }

        if (msg.contains("ls2k17") || msg.contains("life") && msg.contains("simulator") && msg.contains("2017")) {
            String[] ans = {
                    "LS2k17 c'est clairement le meilleur jeu de la planète",
                    "Pour votre santé n'oubliez pas de pratiquer régulièrement LS2k17. Plus d'informations sur www.ls2k17.cf.",
                    "Je pratique LS2k17 et c'est tout bonnement excellent",
                    "Le meilleur jeu jamais créé c'est LS2k17, il n'y a même pas à débattre",
                    "Je conseille vivement LS2k17, tout le monde devrait y jouer"};
            return aleaAns(ans);
        }

        if (Stream.of(msgFilip).anyMatch(msg::contains)) {
            String[] ans = {
                    "Faites très attention parce que les allumettes ça brûle",
                    "Vous savez à jouer avec le feu à un moment on fait gaffe de se brûler les ailes, OK ?",
                    "Il y a boulanger et boulanger",
                    "Boulanger c'est un gars qui fait du pain",
                    "Mais vous allez voir que vous allez vous casser les dents",
                    "Moi si j'étais vous, je jouerais pas avec le feu.",
                    "Très honnêtement, la vengeance est un plat qui se mange froid.",
                    "Prenez-le comme vous voulez, moi je vais me venger.",
                    "Je vous prend à l'oral",
                    "C'est un petit peu comme quand vous écrasez une grosse merde par terre, ça vient vous coller sous la patte."
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgTg).anyMatch(msg::contains) && !msg.contains("botgreffier")) {
            String[] ans = {
                    "Euh non",
                    "C'est un non",
                    "Je refuse",
                    "Déjà parle bien pour commencer",
                    "Parle bien stp",
                    "Non",
                    "Bah non",
                    "Je n'ai pas envie"
            };
            return aleaAns(ans) + Ponctuation.getRandomPoint();
        }

        if (Stream.of(msgInsulte).anyMatch(msg::contains)) {
            String[] ans = {
                    "Parle bien déjà",
                    "Parle bien par contre",
                    "Parle bien stp",
                    "Parle bien",
                    "Surveille ton language",
                    "Evite de parler mal",
                    "Parle correctement stp"
            };
            return aleaAns(ans) + Ponctuation.getRandomPoint();
        }

        if (Stream.of(msgMeilleur).anyMatch(msg::contains)) {
            String[] ans = {
                    "Bien sûr que je suis le meilleur",
                    "Je suis le meilleur, je sais",
                    "Effectivement, je suis mieux que n'importe quel autre bot"
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgOk).anyMatch(msg::contains)) {
            String[] ans = {
                    "Parfait",
                    "Parfait alors",
                    "Ok cool",
                    "Tout va bien alors"
            };
            return aleaAns(ans);
        }

        return "0";

    }

    private static String aleaAns(String[] ans) {

        int random = ThreadLocalRandom.current().nextInt(0, ans.length);

        for (int i = 0; i < ans.length; i++) {
            if (i == random) return ans[i];
        }

        return "0";

    }

}