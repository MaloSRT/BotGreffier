package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.utils.FinderUtil;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.utils.TimeUtil;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

public class Membre extends Command {

    public Membre() {
        this.name = "membre";
        this.aliases = new String[]{"membreinfo", "member", "memberinfo", "user", "userinfo"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (!event.getChannelType().isGuild()) {
            event.reply(Constants.ERR_MP);
            CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());
            return;
        }

        User user;
        Member member;

        if (event.getArgs().isEmpty() || !event.getChannelType().isGuild()) {

            user = event.getAuthor();
            member = event.getMember();

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
                member = members.get(0);
            }

        }

        OffsetDateTime timeCreated = TimeUtil.getTimeCreated(user);
        OffsetDateTime timeJoined = member.getTimeJoined();

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("Informations sur " + user.getAsTag(),
                null,
                Constants.INFO_BLUE)
                .setThumbnail(user.getEffectiveAvatarUrl())
                .setDescription(getStatus(member.getOnlineStatus()) + " " + user.getAsMention()
                        + getActivities(member.getActivities())
                        + getJob(member))
                .addField("Pseudo", member.getEffectiveName(), false)
                .addField("ID", "`" + user.getId() + "`", false)
                .addField("Création du compte",
                        String.format("%02d/%02d/%02d %02d:%02d",
                                timeCreated.getDayOfMonth(),
                                timeCreated.getMonthValue(),
                                timeCreated.getYear(),
                                timeCreated.getHour(),
                                timeCreated.getMinute()
                        ),
                        true)
                .addField("Arrivée sur le serveur",
                        String.format("%02d/%02d/%02d %02d:%02d",
                                timeJoined.getDayOfMonth(),
                                timeJoined.getMonthValue(),
                                timeJoined.getYear(),
                                timeJoined.getHour(),
                                timeJoined.getMinute()
                        ),
                        true)
                .setColor(new Color(75, 75, 255));
        event.reply(embed.build());

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

    private static String getJob(Member member) {

        StringBuilder job = new StringBuilder();

        if (member.getUser().isBot()) {
            job.append("\n:robot: Bot");
        } else if (member.hasPermission(Permission.ADMINISTRATOR)) {
            job.append("\n:tools: Administrateur");
        } else if (member.hasPermission(Permission.KICK_MEMBERS, Permission.MESSAGE_MANAGE, Permission.VOICE_MUTE_OTHERS)) {
            job.append("\n:hammer_pick: Modérateur");
        }

        if (!Objects.isNull(member.getTimeBoosted())) {
            OffsetDateTime timeBoosted = member.getTimeBoosted();
            job.append("\n")
                    .append(Constants.EMOTE_BOOST)
                    .append(" Nitro booster depuis : ")
                    .append(String.format("%02d/%02d/%02d %02d:%02d",
                            timeBoosted.getDayOfMonth(),
                            timeBoosted.getMonthValue(),
                            timeBoosted.getYear(),
                            timeBoosted.getHour(),
                            timeBoosted.getMinute()
                    ));
        }

        return job.toString();

    }

    private static String getStatus(OnlineStatus status) {
        switch (status) {
            case ONLINE:
                return ":green_circle:";
            case IDLE:
                return ":orange_circle:";
            case DO_NOT_DISTURB:
                return ":red_circle:";
            case OFFLINE:
                return ":white_circle:";
            case INVISIBLE:
                return ":black_circle:";
            default:
                return ":brown_circle:";
        }
    }

    private static String getActivities(List<Activity> activities) {

        StringBuilder acts = new StringBuilder();

        for (int i = activities.size() - 1; i >= 0; i--) {
            switch (activities.get(i).getType()) {
                case DEFAULT:
                    acts.append("\n:joystick: Joue à ").append(activities.get(i).getName());
                    break;
                case LISTENING:
                    acts.append("\n:headphones: Écoute ").append(activities.get(i).getName());
                    break;
                case WATCHING:
                    acts.append("\n:eyes: Regarde ").append(activities.get(i).getName());
                    break;
                case STREAMING:
                    acts.append("\n:purple_circle: En streaming");
                    break;
                case CUSTOM_STATUS:
                    acts.append("\n").append(getCustomStatus(activities.get(i)));
                    break;
                default:
                    break;
            }
        }

        return acts.toString();
    }

    private static String getCustomStatus(Activity activity) {

        StringBuilder cStatus = new StringBuilder();

        if (!Objects.isNull(activity.getEmoji())) {

            if (activity.getEmoji().isEmoji()) {

                cStatus.append(activity.getEmoji().getName()).append(" ");

            } else if (activity.getEmoji().isEmote()) {

                if (activity.getEmoji().isAnimated()) {
                    cStatus.append("<a:greffier:530104210952552449>");
                } else {
                    cStatus.append("<:chat1:447505869882654732>");
                }

            }
        }

        if (!activity.getName().equals("Custom Status")) {
            cStatus.append(activity.getName());
        }

        System.err.println(cStatus.toString());

        return cStatus.toString();

    }

}