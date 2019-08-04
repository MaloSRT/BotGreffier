package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Aide extends Command {

    public Aide() {
        this.name = "aide";
        this.aliases = new String[]{"help"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("**Liste des commandes :** https://msrt.ml/botgreffier/commandes");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
