package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class Chien extends Command {

    public Chien() {
        this.name = "chien";
        this.aliases = new String[]{"chiot", "dog", "chi1"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        double nbAlea = Math.random() * 5;
        if (nbAlea < 1) {
            event.reply("``` \n    (\\_/)\n    (O O)\n     \\ᴥ/\n ```");
        } else if (nbAlea >= 1 && nbAlea < 2) {
            event.reply("``` \n    (\\_/)\n    (o o)\n     \\ᴥ/\n ```");
        } else if (nbAlea >= 2 && nbAlea < 3) {
            event.reply("``` \n    (\\_/)\n    (Ó Ò)\n     \\ᴥ/\n ```");
        } else if (nbAlea >= 3 && nbAlea < 4) {
            event.reply("``` \n    (\\_/)\n    (Ò Ó)\n     \\ᴥ/\n ```");
        } else if (nbAlea >= 4) {
            event.reply("``` \n    (\\_/)\n    (O O)\n     \\●/\n      U\n ```");
        }
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
