package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class Test extends Command {

    public Test() {
        this.name = "test";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("Ça fonctionne :ok_hand:");
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
