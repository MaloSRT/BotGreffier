package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;

import java.util.Random;
import java.util.stream.Stream;

public class PierreFeuilleCiseaux extends Command {

    private static final String[]
            p = {"pierre", "caillou", "roche"},
            f = {"feuille", "papier", "polycopié"},
            c = {"ciseaux", "ciseau", "6zo"};

    public PierreFeuilleCiseaux() {
        this.name = "pierrefeuilleciseaux";
        this.aliases = Stream.of(p, f, c).flatMap(Stream::of).toArray(String[]::new);
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        String prefix = event.getClient().getPrefix();

        if (event.getMessage().getContentDisplay().equalsIgnoreCase(prefix + "PierreFeuilleCiseaux")) {

            event.reply("Envoyez `" + prefix + "pierre`, `" + prefix + "feuille` ou `" + prefix + "ciseaux` pour jouer");

        } else {

            String cmd = event.getMessage().getContentDisplay().substring(prefix.length());
            int nbAlea = new Random().nextInt(3);

            if (Stream.of(p).anyMatch(cmd::equalsIgnoreCase)) {
                switch (nbAlea) {
                    case 0:
                        event.reply("**Pierre** - Match nul");
                        break;
                    case 1:
                        event.reply("**Feuille** - Vous avez perdu");
                        break;
                    case 2:
                        event.reply("**Ciseaux** - Vous avez gagné");
                        break;
                    default:
                        event.reply(Constants.ERR_PROG);
                        break;
                }
            } else if (Stream.of(f).anyMatch(cmd::equalsIgnoreCase)) {
                switch (nbAlea) {
                    case 0:
                        event.reply("**Pierre** - Vous avez gagné");
                        break;
                    case 1:
                        event.reply("**Feuille** - Match nul");
                        break;
                    case 2:
                        event.reply("**Ciseaux** - Vous avez perdu");
                        break;
                    default:
                        event.reply(Constants.ERR_PROG);
                        break;
                }
            } else if (Stream.of(c).anyMatch(cmd::equalsIgnoreCase)) {
                switch (nbAlea) {
                    case 0:
                        event.reply("**Pierre** - Vous avez perdu");
                        break;
                    case 1:
                        event.reply("**Feuille** - Vous avez gagné");
                        break;
                    case 2:
                        event.reply("**Ciseaux** - Match nul");
                        break;
                    default:
                        event.reply(Constants.ERR_PROG);
                        break;
                }
            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}