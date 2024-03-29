package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.features.LabyGrille;
import fr.msrt.botgreffier.utils.CmdUtils;
import fr.msrt.botgreffier.utils.StringUtils;

import java.util.Random;

import static org.apache.commons.lang3.StringUtils.strip;

public class Labyrinthe extends Command {

    public Labyrinthe() {
        this.name = "labyrinthe";
        this.aliases = new String[]{"laby"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        String labyMsg = "Si vous êtes sur téléphone et que le labyrinthe s'affiche mal, mettez vous en mode paysage.";

        if (event.getArgs().isEmpty()) {

            int nx = new Random().nextInt(10) + 5;
            int ny = new Random().nextInt(12) + 5;
            LabyGrille l = new LabyGrille(nx, ny);
            l.expExhaustive();

            if (nx < 14 && ny < 16) {
                event.reply("```\n" + l + "\n```Lignes : `" + nx + "`, Colonnes : `" + ny + "`\n" + labyMsg);
            } else {
                event.reply("```\n" + l + "\n```Lignes : `" + nx + "`, Colonnes : `" + ny + "`");
                event.reply(labyMsg);
            }

        } else {

            String err = CmdUtils.warnSyntax(event.getMessage().getContentDisplay(), "[lignes]" + Constants.SEPARATOR_DISP + "[colonnes]")
                    + "\n - `lignes` : nombre de lignes compris entre 1 et 14"
                    + "\n - `colonnes` : nombre de colonnes compris entre 1 et 16"
                    + "\n - Si aucun argument n'est donné, la taille est aléatoire";

            String[] dimensions = event.getArgs().split(Constants.SEPARATOR);
            if (dimensions.length == 2) {
                dimensions[0] = strip(dimensions[0]);
                dimensions[1] = strip(dimensions[1]);
                if (StringUtils.isInteger(dimensions[0]) && StringUtils.isInteger(dimensions[1])) {

                    int nx = Integer.parseInt(dimensions[0]);
                    int ny = Integer.parseInt(dimensions[1]);

                    if (nx > 0 && nx <= 14 && ny > 0 && ny <= 16) {

                        LabyGrille l = new LabyGrille(nx, ny);
                        l.expExhaustive();

                        if (nx < 14 && ny < 16) {
                            event.reply("```\n" + l + "\n```Lignes : `" + nx + "`, Colonnes : `" + ny + "`\n" + labyMsg);
                        } else {
                            event.reply("```\n" + l + "\n```Lignes : `" + nx + "`, Colonnes : `" + ny + "`");
                            event.reply(labyMsg);
                        }

                    } else {
                        event.reply(err);
                    }

                } else {
                    event.reply(err);
                }
            } else {
                event.reply(err);
            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}