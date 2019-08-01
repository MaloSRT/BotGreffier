package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class Oeuf extends Command {

    public Oeuf() {
        this.name = "œuf";
        this.aliases = new String[]{"oeuf", "egg"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        // DISCLAIMER: Hardcore algorithm happening here
        event.reply(":egg");
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
