package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.Color;
import java.util.Objects;

public class Serveur extends Command {

    public Serveur() {
        this.name = "serveur";
        this.aliases = new String[]{"serveurinfo", "server", "serverinfo", "guild", "guildinfo"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        Guild guild = event.getGuild();
        
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("Informations sur le serveur",
                null,
                "https://cdn.discordapp.com/attachments/604768715371970583/604807047724269569/i.png")
                .setThumbnail(guild.getIconUrl())
                .addField("Nom du serveur", guild.getName(), true)
                .addField("ID du serveur", guild.getId(), true)
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
                        guild.getTimeCreated().getDayOfMonth() + "/"
                                + guild.getTimeCreated().getMonthValue() + "/"
                                + guild.getTimeCreated().getYear() + " "
                                + guild.getTimeCreated().getHour() + ":"
                                + guild.getTimeCreated().getMinute(),
                        true)
                .setColor(new Color(80, 255, 80));
        event.reply(embed.build());

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }
}
