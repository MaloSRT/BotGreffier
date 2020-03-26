package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Ecris extends Command {

    public Ecris() {
        this.name = "écris";
        this.aliases = new String[]{"ecris", "tape"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (!event.getChannelType().isGuild()) {
            event.reply(Constants.ERR_MP);
            return;
        }

        event.getChannel().sendTyping().queue();
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}