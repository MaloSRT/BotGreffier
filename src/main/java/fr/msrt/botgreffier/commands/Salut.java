package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class Salut extends Command {

    public Salut() {
        this.name = "salut";
        this.aliases = new String[]{"slt"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("Salut " + event.getAuthor().getAsMention() + " !");
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
