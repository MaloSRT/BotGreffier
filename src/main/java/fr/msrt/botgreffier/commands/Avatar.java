package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.utils.FinderUtil;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.awt.Color;
import java.util.List;

public class Avatar extends Command {

    public Avatar() {
        this.name = "avatar";
        this.aliases = new String[]{"av", "pdp"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        User user;

        if (event.getArgs().isEmpty() || !event.getChannelType().isGuild()) {

            user = event.getAuthor();

        } else {

            List<Member> members = FinderUtil.findMembers(event.getArgs(), event.getGuild());
            if (members.isEmpty()) {
                event.reply(Constants.EMOTE_DOUBT + " **Membre non trouvé**");
                CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());
                return;
            } else if (members.size() > 1) {
                event.reply(Constants.EMOTE_DOUBT + " **" + members.size() + " membres trouvés**");
                CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());
                return;
            } else {
                user = members.get(0).getUser();
            }

        }

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