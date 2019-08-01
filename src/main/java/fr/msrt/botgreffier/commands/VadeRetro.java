package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class VadeRetro extends Command {

    public VadeRetro() {
        this.name = "vaderetro";
        this.aliases = new String[]{"vaderetrosatanas", "satanas"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("https://chapon.ga/i/vaderetro.jpg");
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
