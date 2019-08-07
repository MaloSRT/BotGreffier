package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.info.Info;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Version extends Command {

    public Version() {
        this.name = "version";
        this.aliases = new String[]{"ver"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("Version du bot : `" + Info.VERSION + "`");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
