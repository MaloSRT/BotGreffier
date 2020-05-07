package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.utils.FinderUtil;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import fr.msrt.botgreffier.utils.EntitiesUtils;
import fr.msrt.botgreffier.utils.StringUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.List;

public class Role extends Command {

    public Role() {
        this.name = "role";
        this.aliases = new String[]{"rôle", "roleinfo", "rôleinfo", "grade", "gradeinfo"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (!event.getChannelType().isGuild()) {

            event.reply(Constants.ERR_MP);

        } else if (event.getArgs().isEmpty()) {

            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay(), "[rôle]"));

        } else {

            net.dv8tion.jda.api.entities.Role role;
            List<net.dv8tion.jda.api.entities.Role> roles = FinderUtil.findRoles(event.getArgs(), event.getGuild());

            if (roles.isEmpty()) {

                event.reply(Constants.EMOTE_DOUBT + " **Rôle non trouvé**");

            } else if (roles.size() > 1) {

                event.reply(Constants.EMOTE_DOUBT + " **" + roles.size() + " rôles trouvés :**"+ EntitiesUtils.listOfRoles(roles));

            } else {

                role = roles.get(0);

                String perms;
                String hexColor;
                EmbedBuilder embed = new EmbedBuilder();
                Color color = role.getColor();
                OffsetDateTime timeCreated = role.getTimeCreated();

                if (role.getPermissions().isEmpty()) {
                    perms = "Aucune";
                } else {
                    StringBuilder p = new StringBuilder();
                    for (Permission perm : role.getPermissions()) {
                        p.append("`").append(perm.getName()).append("`\n");
                    }
                    perms = p.toString();
                }

                if (color == null) {
                    hexColor = "000000";
                } else {
                    hexColor = Integer.toHexString(color.getRGB()).toUpperCase().substring(2);
                }

                embed.setAuthor("Informations sur le rôle " + role.getName(),
                                null,
                                Constants.INFO_BLUE)
                        .setThumbnail("https://fakeimg.pl/512/" + hexColor + "/EEEEFF/?text="+ StringUtils.formatURLArg(role.getName()))
                        .addField("Rôle", role.getAsMention(), true)
                        .addField("ID du rôle", "`" + role.getId() + "`", true)
                        .addField("Membres",
                                String.valueOf(
                                        role.isPublicRole() ?
                                                event.getGuild().getMemberCount() :
                                                event.getGuild().getMembersWithRoles(role).size()
                                ),
                                true)
                        .addField("Position", String.valueOf(role.getPosition() + 1), true)
                        .addField("Couleur", "#" + hexColor, true)
                        .addField("Mentionnable", role.isMentionable() ? "Oui" : "Non", true)
                        .addField("Affiché séparément", role.isHoisted() ? "Oui" : "Non", true)
                        .addField("Géré par une intégration", role.isManaged() ? "Oui" : "Non", true)
                        .addField("Date de création",
                                String.format("%02d/%02d/%02d %02d:%02d",
                                        timeCreated.getDayOfMonth(),
                                        timeCreated.getMonthValue(),
                                        timeCreated.getYear(),
                                        timeCreated.getHour(),
                                        timeCreated.getMinute()
                                ),
                                true)
                        .addField("Permissions", perms, false)
                        .setColor(color);

                event.reply(embed.build());

            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}