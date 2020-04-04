package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.features.PenduMot;
import fr.msrt.botgreffier.utils.CmdUtils;
import fr.msrt.botgreffier.utils.StringUtils;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.concurrent.TimeUnit;

public class Pendu extends Command {

    private final EventWaiter waiter;
    private PenduMot mot;
    private boolean indvdl;

    public Pendu(EventWaiter waiter) {
        this.name = "pendu";
        this.waiter = waiter;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

        if (!event.getChannelType().isGuild()) {
            event.reply(Constants.ERR_MP);
            return;
        }

        if (event.getArgs().equalsIgnoreCase("i")) {
            indvdl = true;
        } else if (event.getArgs().equalsIgnoreCase("c")) {
            indvdl = false;
        } else {
            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay(), "[i, c]")
                    + "\n - `i` : partie individuelle (vous seul pouvez jouer)"
                    + "\n - `c` : partie collective (tout le monde dans ce salon peut participer)");
            return;
        }

        mot = new PenduMot();
        disp(event, 11, 0, false, '0');
        waitForLetter(event, 11);

    }

    private void waitForLetter(CommandEvent event, int essais) {

        if (indvdl) {

            waiter.waitForEvent(GuildMessageReceivedEvent.class,
                    e -> e.getAuthor().equals(event.getAuthor())
                            && e.getChannel().equals(event.getChannel()),
                    e -> {
                        if (CmdUtils.isCancelled(e.getMessage().getContentDisplay())
                                || CmdUtils.isCommand(e.getMessage().getContentDisplay())) {
                            event.reply("*Partie annulée*\nLe mot était : `" + mot.getMot() + "`");
                            System.out.println("PenduCancel");
                        } else if (StringUtils.isAlphabetLetter(StringUtils.onlyAlphabetLetters(e.getMessage().getContentDisplay()).toLowerCase())) {
                            play(event, essais, StringUtils.onlyAlphabetLetters(e.getMessage().getContentDisplay()).toLowerCase().charAt(0));
                        } else {
                            event.reply("*Veuillez saisir une lettre !*");
                            waitForLetter(event, essais);
                        }
                    },
                    2, TimeUnit.MINUTES, () -> {
                        event.reply(Constants.GAME_TIMEOUT + "\nLe mot était : `" + mot.getMot() + "`");
                        System.out.println("PenduTimeout");
                    });

        } else {

            waiter.waitForEvent(GuildMessageReceivedEvent.class,
                    e -> !e.getAuthor().isBot()
                            && e.getChannel().equals(event.getChannel()),
                    e -> {
                        if (CmdUtils.isCancelled(event.getMessage().getContentDisplay())) {
                            event.reply("*Partie annulée*\nLe mot était : `" + mot.getMot() + "`");
                            System.out.println("PenduCancel");
                        } else if (StringUtils.isAlphabetLetter(StringUtils.onlyAlphabetLetters(e.getMessage().getContentDisplay()).toLowerCase())) {
                            play(event, essais, StringUtils.onlyAlphabetLetters(e.getMessage().getContentDisplay()).toLowerCase().charAt(0));
                        } else {
                            waitForLetter(event, essais);
                        }
                    },
                    2, TimeUnit.MINUTES, () -> {
                            event.reply(Constants.GAME_TIMEOUT + "\nLe mot était : `" + mot.getMot() + "`");
                            System.out.println("PenduTimeout");
                    });

        }

    }

    private void play(CommandEvent event, int essais, char lettre) {

        System.out.println("PenduEvent");
        int es = essais;
        boolean reussite = mot.testLettre(lettre);
        if (!reussite) es--;
        if (mot.getVictoire()) {
            disp(event, es, 2, reussite, lettre);
            System.out.println("PenduFin");
        } else if (es > 0) {
            disp(event, es, 1, reussite, lettre);
            waitForLetter(event, es);
        } else {
            disp(event, es, 3, reussite, lettre);
            System.out.println("PenduFin");
        }

    }

    private void disp(CommandEvent event, int essais, int statut, boolean reussite, char lettre) {

        /*
         *  statut:  0: début
         *           1: en cours
         *           2: gagné
         *           3: perdu
         */

        String pendu;
        String text;
        char l = Character.toUpperCase(lettre);

        switch (essais) {
            case 0:  pendu =
                     "      ________\n" +
                     "      | /    |\n" +
                     "      |/     O\n" +
                     "      |     /|\\\n" +
                     "      |     / \\\n" +
                     "   ___|___\n";
                     break;
            case 1:  pendu =
                     "      ________\n" +
                     "      | /    |\n" +
                     "      |/     O\n" +
                     "      |     /|\\\n" +
                     "      |     /\n" +
                     "   ___|___\n";
                     break;
            case 2:  pendu =
                     "      ________\n" +
                     "      | /    |\n" +
                     "      |/     O\n" +
                     "      |     /|\\\n" +
                     "      |\n" +
                     "   ___|___\n";
                     break;
            case 3:  pendu =
                     "      ________\n" +
                     "      | /    |\n" +
                     "      |/     O\n" +
                     "      |     /|\n" +
                     "      |\n" +
                     "   ___|___\n";
                     break;
            case 4:  pendu =
                     "      ________\n" +
                     "      | /    |\n" +
                     "      |/     O\n" +
                     "      |      |\n" +
                     "      |\n" +
                     "   ___|___\n";
                     break;
            case 5:  pendu =
                     "      ________\n" +
                     "      | /    |\n" +
                     "      |/     O\n" +
                     "      |\n" +
                     "      |\n" +
                     "   ___|___\n";
                     break;
            case 6:  pendu =
                     "      ________\n" +
                     "      | /    |\n" +
                     "      |/\n" +
                     "      |\n" +
                     "      |\n" +
                     "   ___|___\n";
                     break;
            case 7:  pendu =
                     "      ________\n" +
                     "      | /\n" +
                     "      |/\n" +
                     "      |\n" +
                     "      |\n" +
                     "   ___|___\n";
                     break;
            case 8:  pendu =
                     "      ________\n" +
                     "      |\n" +
                     "      |\n" +
                     "      |\n" +
                     "      |\n" +
                     "   ___|___\n";
                     break;
            case 9: pendu =
                     " \n" +
                     "      |\n" +
                     "      |\n" +
                     "      |\n" +
                     "      |\n" +
                     "   ___|___\n";
                     break;
            case 10: pendu =
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     "   _______\n";
                     break;
            case 11: pendu =
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n" +
                     " \n";
                     break;
            default: pendu = null;
                     break;
        }

        switch (statut) {
            case 0:  text = "Saisissez une lettre :";
                     break;
            case 1:  if (reussite) {
                         text = "La lettre `" + l + "` est bien dans le mot !\nSaisissez une lettre :";
                     } else {
                         text = "La lettre `" + l + "` n'est pas dans le mot !\nSaisissez une lettre :";
                     }
                     break;
            case 2:  text = "La lettre `" + l + "` est bien dans le mot !\n" + Constants.EMOTE_VICTORY + " **Gagné !** Vous avez trouvé le mot";
                     break;
            case 3:  text = "La lettre `" + l + "` n'est pas dans le mot !\n" + Constants.EMOTE_DEFEAT + " **Perdu !** Le mot était : `" + mot.getMot() + "`";
                     break;
            default: text = null;
                     break;
        }

        event.reply("```" + pendu + "\n   " + mot.getLettreMot() + "```" + text);

    }

}