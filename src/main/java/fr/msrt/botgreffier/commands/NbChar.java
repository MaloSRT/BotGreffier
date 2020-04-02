package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;

public class NbChar extends Command {

    public NbChar() {
        this.name = "nbchar";
        this.aliases = new String[]{"compte"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        String command = Constants.PREFIX + CmdUtils.getCmdName(event.getMessage().getContentDisplay());

        if (event.getArgs().isEmpty()) {

            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay(), "[texte]"));

        } else {

            int nbChar = event.getArgs().length();
            if (nbChar == 1) {
                event.reply("Nombre de caractère (*en ignorant* `" + command + "`) : **" + nbChar + "**");
            } else {
                event.reply("Nombre de caractères (*en ignorant* `" + command + "`) : **" + nbChar + "**");
            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}