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

public class Ban extends Command {

    public Ban() {
        this.name = "ban";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (!event.getChannelType().isGuild()) {

            event.reply(Constants.ERR_MP);

        } else if (!event.getSelfMember().hasPermission(Permission.BAN_MEMBERS)) {

            event.reply(Constants.ERR_PERM_BOT + "Bannir des membres");

        } else if (event.getArgs().isEmpty()) {

            event.reply(CmdUtils.warnSyntax(event.getClient().getPrefix() + "ban [membre], [raison]"));

        } else {

            Member memberToBan;
            String[] args = event.getArgs().split(", ", 2);
            String strMemberToBan = StringUtils.strip(args[0]);
            List<Member> members = FinderUtil.findMembers(strMemberToBan, event.getGuild());

            if (members.size() > 1) {

                event.reply(Constants.EMOTE_DOUBT + " **" + members.size() + " membres trouvés**\nVeuillez mentionner le membre ou donner son nom exact");

            } else if (members.isEmpty()
                    || !("<@!" + members.get(0).getUser().getId() + ">").equals(strMemberToBan)
                    && !members.get(0).getUser().getName().equalsIgnoreCase(strMemberToBan)
                    && !members.get(0).getEffectiveName().equalsIgnoreCase(strMemberToBan)) {

                event.reply(Constants.EMOTE_DOUBT + " **Membre non trouvé**\nVeuillez mentionner le membre ou donner son nom exact");

            } else {

                memberToBan = members.get(0);

                if (!event.getMember().canInteract(memberToBan) || !event.getMember().hasPermission(Permission.BAN_MEMBERS)) {

                    event.reply(Constants.ERR_PERM_USR + "Bannir des membres");

                } else if (!event.getSelfMember().canInteract(memberToBan)) {

                    event.reply(Constants.EMOTE_ERR + " **Je ne peux pas bannir ce membre**");

                } else {

                    String motif;
                    if (args.length == 2 && !args[1].isEmpty()) {
                        motif = StringUtils.strip(args[1]);
                    } else {
                        motif = "aucun motif donné";
                    }

                    event.getGuild().ban(memberToBan, 0).reason(motif).queue(
                            success -> {
                                if (!memberToBan.getUser().isBot()) {
                                    memberToBan.getUser().openPrivateChannel().flatMap(
                                            privateChannel -> privateChannel.sendMessage(Constants.EMOTE_WARN
                                                    + " Vous avez été banni du serveur **"
                                                    + event.getGuild().getName()
                                                    + "** pour la raison suivante : `"
                                                    + motif
                                                    + "`")
                                    ).queue();
                                }
                                event.reply(Constants.EMOTE_SUCCESS + " **" + memberToBan.getAsMention() + " a été banni pour la raison suivante :** `" + motif + "`");
                            },
                            error -> event.reply(Constants.EMOTE_ERR + " **Impossible de bannir ce membre**")
                    );

                }

            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}