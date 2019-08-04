package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;

import java.awt.Color;

public class Avatar extends Command {

    public Avatar() {
        this.name = "avatar";
        this.aliases = new String[]{"av", "pdp"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        User user = event.getAuthor();

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("Avatar de " + user.getName() + "#" + user.getDiscriminator(),
                user.getAvatarUrl() + "?size=1024",
                user.getDefaultAvatarUrl())
                .setImage(user.getAvatarUrl() + "?size=512")
                .setColor(Color.CYAN);
        event.reply(embed.build());

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
