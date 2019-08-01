package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.info.Info;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class MiseAJour extends Command {

    public MiseAJour() {
        this.name = "maj";
        this.aliases = new String[]{"màj", "miseajour", "miseàjour"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("Dernière mise à jour : " + Info.getMaj());
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}