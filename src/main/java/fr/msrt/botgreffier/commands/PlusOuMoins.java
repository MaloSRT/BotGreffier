package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import fr.msrt.botgreffier.utils.StringUtils;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PlusOuMoins extends Command {

    private final EventWaiter waiter;
    private int nombreATrouver;
    private boolean indvdl;

    public PlusOuMoins(EventWaiter waiter) {
        this.name = "plusoumoins";
        this.aliases = new String[]{"+ou-"};
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

        nombreATrouver = new Random().nextInt(101);
        event.reply(":1234: **Le nombre est compris entre 0 et 100,** essayez de le trouver");
        waitForNumber(event, 1);

    }

    private void waitForNumber(CommandEvent event, int essais) {

        if (indvdl) {

            waiter.waitForEvent(GuildMessageReceivedEvent.class,
                    e -> e.getAuthor().equals(event.getAuthor())
                            && e.getChannel().equals(event.getChannel()),
                    e -> {
                        if (CmdUtils.isCancelled(e.getMessage().getContentDisplay())
                                || CmdUtils.isCommand(e.getMessage().getContentDisplay())) {
                            event.reply("*Partie annulée*\nLe nombre était : `" + nombreATrouver + "`");
                            System.out.println("PenduCancel");
                        } else if (StringUtils.isInteger(e.getMessage().getContentDisplay())
                                && Integer.parseInt(e.getMessage().getContentDisplay()) >= 0
                                && Integer.parseInt(e.getMessage().getContentDisplay()) <= 100) {
                            play(event, essais, Integer.parseInt(e.getMessage().getContentDisplay()));
                        } else {
                            event.reply("*Veuillez saisir un nombre compris entre 0 et 100 !*");
                            waitForNumber(event, essais);
                        }
                    },
                    2, TimeUnit.MINUTES, () -> {
                        event.reply(Constants.GAME_TIMEOUT + "\nLe nombre était : `" + nombreATrouver + "`");
                        System.out.println("PlusOuMoinsTimeout");
                    });

        } else {

            waiter.waitForEvent(GuildMessageReceivedEvent.class,
                    e -> !e.getAuthor().isBot()
                            && e.getChannel().equals(event.getChannel()),
                    e -> {
                        if (CmdUtils.isCancelled(e.getMessage().getContentDisplay())) {
                            event.reply("*Partie annulée*\nLe nombre était : `" + nombreATrouver + "`");
                            System.out.println("PenduCancel");
                        } else if (StringUtils.isInteger(e.getMessage().getContentDisplay())
                                && Integer.parseInt(e.getMessage().getContentDisplay()) >= 0
                                && Integer.parseInt(e.getMessage().getContentDisplay()) <= 100) {
                            play(event, essais, Integer.parseInt(e.getMessage().getContentDisplay()));
                        } else {
                            waitForNumber(event, essais);
                        }
                    },
                    2, TimeUnit.MINUTES, () -> {
                        event.reply(Constants.GAME_TIMEOUT + "\nLe nombre était : `" + nombreATrouver + "`");
                        System.out.println("PlusOuMoinsTimeout");
                    });

        }
    }

    private void play(CommandEvent event, int essais, int nb) {

        System.out.println("PlusOuMoinsEvent");
        if (nb == nombreATrouver) {
            if (essais == 1) {
                event.reply(Constants.EMOTE_VICTORY + " **Gagné !** Vous avez trouvé le nombre du premier coup !");
            } else {
                event.reply(Constants.EMOTE_VICTORY + " **Gagné !** Vous avez trouvé le nombre en " + essais + " essais");
            }
            System.out.println("PlusOuMoinsFin");
        } else {
            if (essais < 50) {
                if (nb < nombreATrouver) {
                    event.reply(":chart_with_upwards_trend: C'est plus !");
                } else {
                    event.reply(":chart_with_downwards_trend: C'est moins !");
                }
                waitForNumber(event, essais + 1);
            } else {
                event.reply(Constants.EMOTE_DEFEAT + " **Vous n'avez toujours pas trouvé le nombre après 50 essais !** Ça commence à faire beaucoup..."
                        + "\nLe nombre était : `" + nombreATrouver + "`");
                System.out.println("PlusOuMoinsFin");
            }
        }

    }

}