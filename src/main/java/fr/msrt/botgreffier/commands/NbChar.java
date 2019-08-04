package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

public class NbChar extends Command {

    public NbChar() {
        this.name = "nbchar";
        this.aliases = new String[]{"compte"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (event.getArgs().isEmpty()) {

            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay() + " [texte]"));

        } else {

            int nbChar = event.getArgs().length();
            int cmdLength = event.getClient().getPrefix().length() + 7;
            if (nbChar == 1) {
                event.reply("Nombre de caractère (*en ignorant* `" + event.getMessage().getContentDisplay().substring(0, cmdLength) + "`) : **" + nbChar + "**");
            } else {
                event.reply("Nombre de caractères (*en ignorant* `" + event.getMessage().getContentDisplay().substring(0, cmdLength) + "`) : **" + nbChar + "**");
            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
