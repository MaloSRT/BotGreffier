package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import fr.msrt.botgreffier.features.PenduMotMystere;
import fr.msrt.botgreffier.utils.SysoutCmd;
import fr.msrt.botgreffier.utils.Utils;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.concurrent.TimeUnit;

public class Pendu extends Command {

    private final EventWaiter waiter;
    private PenduMotMystere mot;

    public Pendu(EventWaiter waiter) {
        this.waiter = waiter;
        this.name = "pendu";
        this.aliases = new String[]{"hangman"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

        mot = new PenduMotMystere();
        disp(event, 11, 0, false, '0');
        waitForLetter(event, 11);

    }

    private void waitForLetter(CommandEvent event, int essais) {

        waiter.waitForEvent(GuildMessageReceivedEvent.class,
                e -> e.getAuthor().equals(event.getAuthor())
                        && e.getChannel().equals(event.getChannel()),
                e -> {
                    if (e.getMessage().getContentDisplay().equalsIgnoreCase("stop")
                            || e.getMessage().getContentDisplay().equalsIgnoreCase("exit")
                            || e.getMessage().getContentDisplay().equalsIgnoreCase("cancel")
                            || e.getMessage().getContentDisplay().equalsIgnoreCase("annuler")
                            || e.getMessage().getContentDisplay().length() > 1
                                && e.getMessage().getContentDisplay().substring(0, 1).equals("=")) {
                        event.reply("*Partie annulée*\nLe mot était : `" + mot.getMot() + "`");
                        System.out.println("PenduCancel");
                        return;
                    }
                    if (Utils.isAlphabetLetter(Utils.onlyAlphabetLetters(e.getMessage().getContentDisplay()).toLowerCase())) {
                        play(event, essais, Utils.onlyAlphabetLetters(e.getMessage().getContentDisplay()).toLowerCase().charAt(0));
                    } else {
                        event.reply("*Veuillez saisir une lettre !*");
                        waitForLetter(event, essais);
                    }
                },
                2, TimeUnit.MINUTES, () -> event.reply(
                        ":stopwatch: *Désolé, vous n'avez pas répondu depuis 2 minute, la partie est annulée...*\n" +
                            "Le mot était : `" + mot.getMot() + "`"
                ));


    }

    private void play(CommandEvent event, int essais, char lettre) {

        boolean reussite = mot.testLettre(lettre);
        if (!reussite) essais--;
        if (mot.getVictoire()) {
            disp(event, essais, 2, reussite, lettre);
        } else if (essais > 0) {
            disp(event, essais, 1, reussite, lettre);
            waitForLetter(event, essais);
        } else {
            disp(event, essais, 3, reussite, lettre);
        }
        System.out.println("PenduEvent");

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
        lettre = Character.toUpperCase(lettre);

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
                         text = "La lettre `" + lettre + "` est bien dans le mot !\nSaisissez une lettre :";
                     } else {
                         text = "La lettre `" + lettre + "` n'est pas dans le mot !\nSaisissez une lettre :";
                     }
                     break;
            case 2:  text = "La lettre `" + lettre + "` est bien dans le mot !\n:white_check_mark: **Gagné !** Vous avez trouvé le mot";
                     break;
            case 3:  text = "La lettre `" + lettre + "` n'est pas dans le mot !\n:o2: **Perdu !** Le mot était : `" + mot.getMot() + "`";
                     break;
            default: text = null;
                     break;
        }

        event.reply("```" + pendu + "\n   " + mot.getLettreMot() + "```" + text);

    }

}