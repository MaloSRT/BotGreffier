package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.utils.FinderUtil;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.awt.Color;
import java.util.List;

public class Avatar extends Command {

    public Avatar() {
        this.name = "avatar";
        this.aliases = new String[]{"av", "pdp"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        Member member;

        if (event.getArgs().isEmpty()) {

            member = event.getMember();

        } else {

            List<Member> members = FinderUtil.findMembers(event.getArgs(), event.getGuild());
            if (members.isEmpty()) {
                event.reply(":question: **Membre non trouvé**");
                return;
            } else if (members.size() > 1) {
                event.reply(":question: **" + members.size() + " membres trouvés**");
                return;
            } else {
                member = members.get(0);
            }

        }

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("Avatar de " + member.getUser().getName() + "#" + member.getUser().getDiscriminator(),
                        member.getUser().getAvatarUrl() + "?size=1024",
                        member.getUser().getDefaultAvatarUrl())
                .setImage(member.getUser().getAvatarUrl() + "?size=512")
                .setColor(Color.CYAN);
        event.reply(embed.build());

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
