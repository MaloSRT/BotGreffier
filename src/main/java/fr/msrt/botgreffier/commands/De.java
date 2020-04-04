package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

import java.util.Random;

public class De extends Command {

    public De() {
        this.name = "dé";
        this.aliases = new String[]{"de", "dice"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        int random = new Random().nextInt(6) + 1;
        event.reply(":game_die: **" + random + "**");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}