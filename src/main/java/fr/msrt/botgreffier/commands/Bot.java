package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Bot extends Command {

    public Bot() {
        this.name = "bot";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("<:bot:449319834078150676>");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
