package fr.msrt.botgreffier.music.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.music.AudioListener;
import fr.msrt.botgreffier.music.MusicUtils;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.Color;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue extends Command {

    public Queue() {
        this.name = "queue";
        this.aliases = new String[]{"q", "file", "liste"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (!event.getChannelType().isGuild()) {

            event.reply(Constants.ERR_MP);

        } else {

            AudioListener listener = Play.manager.getPlayer(event.getGuild()).getListener();
            int tracksCount = listener.getTracksCount();

            if (tracksCount < 1) {

                event.reply(":cd: **Aucune piste dans la file d'attente**");

            } else {

                BlockingQueue<AudioTrack> tracks = new LinkedBlockingQueue<>(listener.getTracks());
                StringBuilder builder = new StringBuilder();
                EmbedBuilder embed = new EmbedBuilder();
                int iMax = Math.min(tracksCount, 10);

                for (int i = 0; i < iMax; i++) {
                    try {
                        AudioTrack track = tracks.take();
                        builder.append(i + 1).append(".\t[").append(track.getInfo().title)
                                .append("](").append(track.getInfo().uri)
                                .append(") | `").append(MusicUtils.parseDuration(track.getDuration())).append("`");
                        if (i < iMax - 1) {
                            builder.append("\n");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (tracksCount > 10) {
                    builder.append("\n[...]");
                }

                embed.setAuthor("\uD83D\uDCBF File d'attente")
                        .setDescription(builder.toString())
                        .setColor(new Color(67, 96, 182));

                if (tracksCount > 1) {
                    embed.setFooter(tracksCount + " pistes dans la file d'attente");
                } else {
                    embed.setFooter("1 piste dans la file d'attente");
                }

                event.reply(embed.build());

            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}