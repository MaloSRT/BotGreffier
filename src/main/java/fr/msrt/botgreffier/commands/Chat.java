package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class Chat extends Command {

    public Chat() {
        this.name = "chat";
        this.aliases = new String[]{"chaton", "cat"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        double nbAlea = Math.random() * 5;
        if (nbAlea < 1) {
            event.reply("``` \n    /\\_/\\\n   =(O_O)=\n ```");
        } else if (nbAlea >= 1 && nbAlea < 2) {
            event.reply("``` \n    /\\_/\\\n   =(o_o)=\n ```");
        } else if (nbAlea >= 2 && nbAlea < 3) {
            event.reply("``` \n    /\\_/\\\n   =(O_o)=\n ```");
        } else if (nbAlea >= 3 && nbAlea < 4) {
            event.reply("``` \n    /\\_/\\\n   =(o_O)=\n ```");
        } else if (nbAlea >= 4) {
            event.reply("``` \n    /\\_/\\\n   =(-_-)=\n ```");
        }
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
