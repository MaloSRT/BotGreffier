package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.Color;

public class Snapchat extends Command {

    public Snapchat() {
        this.name = "snapchat";
        this.aliases = new String[]{"snap", "lens"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("Lens Snapchat LS2k17",
                        null,
                        Constants.ICON_SNAP)
                .setTitle("\uD83D\uDCF2 Cliquez ici si vous êtes sur téléphone",
                        "https://www.snapchat.com/unlock/?type=SNAPCODE&uuid=b8cf7b00dbd04874ba0a5657b73038ae&metadata=1")
                .setImage("http://bit.ly/SnApcode")
                .setColor(Color.DARK_GRAY);
        event.reply(embed.build());

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
