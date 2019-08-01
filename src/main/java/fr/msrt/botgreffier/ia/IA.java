package fr.msrt.botgreffier.ia;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class IA {

    private static final String[]
            msgMerci = {"merci", "mrc"},
            msgBonjour = {"bonjour", "salut", "coucou", "bjr", "slt", "cc", "hello", "hi"},
            msgAuRevoir = {"au revoir", "aurevoir", "la revoyure"},
            msgCaVa = {"ça va", "sa va", "çava", "sava", "comment va"},
            msgAide = {"aide", "help"},
            msgJtm = {"je t'aime", "jtm", "je taime", "je t aime"},
            msgBeau = {"t'es beau", "tu es beau", "est beau", "tu es magnifique", "est très beau", "es très beau", "t bo"},
            msgMoche = {"es pas beau", "es moche", "est pas beau", "est moche"},
            msgWesh = {"wesh", "wsh", "ouaich", "ouaish"},
            msgTfq = {"tu fais quoi", "qu'est ce que tu fais", "tfk", "tfq", "qtf", "qvf", "vfk", "vfq"},
            msgLol = {"mdr", "ptdr", "kek", "lol", "issou", "xd", "x)", "jpp"},
            msgTg = {"tg", "ta gueule", "la ferme"},
            msgInsulte = {"connard", "ntm", "fdp", "enculé", "mange tes morts", "putain", "pute", "merde", " con ", " nique ", "batard", "salop", "débile", "debile", "attardé", "est con ", "es con "};

    public String getAnswer(String msg) {

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
                    "Oui ça va et toi ?"
            };
            return aleaAns(ans);
        }

        if (Stream.of(msgAide).anyMatch(msg::contains)) {
            String[] ans = {
                    "La page d'aide est disponible ici : https://msrt.ml/botgreffier/commandes",
                    "Voici un peu d'aide : https://msrt.ml/botgreffier/commandes",
                    "Ma page d'aide est ici : https://msrt.ml/botgreffier/commandes"
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
            return aleaAns(ans);
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