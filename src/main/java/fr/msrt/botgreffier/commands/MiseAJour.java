package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.info.Info;
import fr.msrt.botgreffier.utils.CmdUtils;

public class MiseAJour extends Command {

    public MiseAJour() {
        this.name = "maj";
        this.aliases = new String[]{"màj", "miseajour", "miseàjour"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("Dernière mise à jour : `" + Info.LAST_UPDATE + "`");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}