package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.info.Info;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class Instance extends Command {

    public Instance() {
        this.name = "instance";
        this.aliases = new String[]{"nom"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("Nom de l'instance : " + Info.getInstanceName());
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
