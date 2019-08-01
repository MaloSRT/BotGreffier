package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class Snapchat extends Command {

    public Snapchat() {
        this.name = "snapchat";
        this.aliases = new String[]{"snap", "lens"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("http://bit.ly/SnApcode");
        event.reply("**Ou cliquez sur ce lien depuis un téléphone :** https://www.snapchat.com/unlock/?type=SNAPCODE&uuid=b8cf7b00dbd04874ba0a5657b73038ae&metadata=1");
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
