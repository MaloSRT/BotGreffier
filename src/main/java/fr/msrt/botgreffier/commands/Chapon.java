package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Chapon extends Command {

    public Chapon() {
        this.name = "chapon";
        this.aliases = new String[]{"chapon.ga"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("<:chapon:452484558055538708> https://chapon.ga");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}