package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class Chapon extends Command {

    public Chapon() {
        this.name = "chapon";
        this.aliases = new String[]{"chapon.ga"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("<:chapon:452484558055538708> https://chapon.ga");
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
