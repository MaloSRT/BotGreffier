package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.Objects;

public class Serveur extends Command {

    public Serveur() {
        this.name = "serveur";
        this.aliases = new String[]{"serveurinfo", "server", "serverinfo", "guild", "guildinfo"};
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

        Guild guild = event.getGuild();
        OffsetDateTime timeCreated = guild.getTimeCreated();
        
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("Informations sur le serveur",
                        null,
                        Constants.INFO_BLUE)
                .setThumbnail(guild.getIconUrl())
                .addField("Nom du serveur", guild.getName(), true)
                .addField("ID du serveur", "`" + guild.getId() + "`", true)
                .addField("Membres", String.valueOf(guild.getMembers().size()), true)
                .addField("Salons textuels", String.valueOf(guild.getTextChannels().size()), true)
                .addField("Salons vocaux", String.valueOf(guild.getVoiceChannels().size()), true)
                .addField("Catégories", String.valueOf(guild.getCategories().size()), true)
                .addField("Rôles", String.valueOf(guild.getRoles().size()), true)
                .addField("Emojis", String.valueOf(guild.getEmotes().size()), true)
                .addField("Nitro boosts", guild.getBoostCount() + " : Level " + guild.getBoostTier().getKey(), true)
                .addField("Région", guild.getRegion().getEmoji() + " " + guild.getRegion().getName(), true)
                .addField("Propriétaire", Objects.requireNonNull(guild.getOwner()).getAsMention(), true)
                .addField("Date de création",
                        String.format("%02d/%02d/%02d %02d:%02d",
                                timeCreated.getDayOfMonth(),
                                timeCreated.getMonthValue(),
                                timeCreated.getYear(),
                                timeCreated.getHour(),
                                timeCreated.getMinute()
                        ),
                        true)
                .setColor(new Color(80, 255, 80));
        event.reply(embed.build());

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}