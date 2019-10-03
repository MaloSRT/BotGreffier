package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

public class AVYE extends Command {

    public AVYE() {
        this.name = "avye";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        /* if (event.getArgs().isEmpty()) {
            event.reply(CmdUtils.warnSyntax(event.getClient().getPrefix() + "avye [texte]"));
        } else {
            MySQL mySQL = new MySQL();
            mySQL.InsertAVYE(event.getAuthor().getName(), event.getArgs());
            event.reply(":white_check_mark: **Enregistré :** https://msrt.ml/botgreffier/avye");
        } */

        event.reply(":toilet: Commande indisponible pour le moment...");

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }
}
