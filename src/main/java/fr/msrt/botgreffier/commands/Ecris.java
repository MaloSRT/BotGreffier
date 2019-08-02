package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class Ecris extends Command {

    public Ecris() {
        this.name = "écris";
        this.aliases = new String[]{"ecris", "tape"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.getChannel().sendTyping().queue();
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
