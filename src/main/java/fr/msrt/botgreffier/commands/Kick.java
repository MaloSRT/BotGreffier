package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.utils.FinderUtil;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Kick extends Command {

    public Kick() {
        this.name = "kick";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (!event.getChannelType().isGuild()) {

            event.reply(Constants.ERR_MP);

        } else if (!event.getSelfMember().hasPermission(Permission.KICK_MEMBERS)) {

            event.reply(Constants.ERR_PERM_BOT + "Expulser des membres");

        } else if (!event.getMember().hasPermission(Permission.KICK_MEMBERS)) {

            event.reply(Constants.ERR_PERM_USR + "Expulser des membres");

        } else if (event.getArgs().isEmpty()) {

            event.reply(CmdUtils.warnSyntax(event.getClient().getPrefix() + "kick [membre], [raison]"));

        } else {

            Member memberToKick;
            String[] args = event.getArgs().split(", ", 2);
            String strMemberToKick = StringUtils.strip(args[0]);
            List<Member> members = FinderUtil.findMembers(strMemberToKick, event.getGuild());

            if (members.size() > 1) {

                event.reply(Constants.EMOTE_DOUBT + " **" + members.size() + " membres trouvés**\nVeuillez mentionner le membre ou donner son nom exact");

            } else if (members.isEmpty()
                    || !("<@!" + members.get(0).getUser().getId() + ">").equals(strMemberToKick)
                    && !members.get(0).getUser().getName().equalsIgnoreCase(strMemberToKick)
                    && !members.get(0).getEffectiveName().equalsIgnoreCase(strMemberToKick)) {

                event.reply(Constants.EMOTE_DOUBT + " **Membre non trouvé**\nVeuillez mentionner le membre ou donner son nom exact");

            } else {

                memberToKick = members.get(0);

                if (!event.getMember().canInteract(memberToKick)) {

                    event.reply(Constants.EMOTE_ERR + " **Vous ne pouvez pas expulser ce membre**");

                } else if (!event.getSelfMember().canInteract(memberToKick)) {

                    event.reply(Constants.EMOTE_ERR + " **Je ne peux pas expulser ce membre**");

                } else {

                    String motif;
                    if (args.length == 2 && !args[1].isEmpty()) {
                        motif = StringUtils.strip(args[1]);
                    } else {
                        motif = "aucun motif donné";
                    }

                    event.getGuild().kick(memberToKick).reason(motif).queue(
                            success -> {
                                if (!memberToKick.getUser().isBot()) {
                                    memberToKick.getUser().openPrivateChannel().flatMap(
                                            privateChannel -> privateChannel.sendMessage(Constants.EMOTE_WARN
                                                    + " Vous avez été expulsé du serveur **"
                                                    + event.getGuild().getName()
                                                    + "** pour la raison suivante : `"
                                                    + motif
                                                    + "`")
                                    ).queue();
                                }
                                event.reply(Constants.EMOTE_SUCCESS + " **" + memberToKick.getAsMention() + " a été expulsé pour la raison suivante :** `" + motif + "`");
                            },
                            error -> event.reply(Constants.EMOTE_ERR + " **Impossible d'expulser ce membre**")
                    );

                }

            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}