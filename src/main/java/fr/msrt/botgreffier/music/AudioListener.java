package fr.msrt.botgreffier.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.awt.Color;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AudioListener extends AudioEventAdapter {

    private final BlockingQueue<AudioTrack> tracks = new LinkedBlockingQueue<>();
    private final MusicPlayer player;

    public AudioListener(MusicPlayer player) {
        this.player = player;
    }

    public BlockingQueue<AudioTrack> getTracks() {
        return tracks;
    }

    public AudioTrack getPlayingTrack() {
        return player.getAudioPlayer().getPlayingTrack();
    }

    public int getTracksCount() {
        return tracks.size();
    }

    public boolean isPaused() {
        return player.getAudioPlayer().isPaused();
    }

    public void queue(AudioTrack track) {
        if (!player.getAudioPlayer().startTrack(track, true)) {
            tracks.offer(track);
        } else {
            try {
                showTrack(track, player.getTextChannel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void nextTrack() {
        if (tracks.isEmpty()) {
            if (player.getGuild().getAudioManager().getConnectedChannel() != null) {
                player.getGuild().getAudioManager().closeAudioConnection();
            }
            return;
        }
        try {
            showTrack(tracks.element(), player.getTextChannel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.getAudioPlayer().startTrack(tracks.poll(), false);
    }

    public void pause() {
        player.getAudioPlayer().setPaused(true);
    }

    public void resumeFromActive() {
        player.getAudioPlayer().setPaused(false);
    }

    public void resumeFromInactive(VoiceChannel channel) {
        player.getGuild().getAudioManager().openAudioConnection(channel);
        player.getAudioPlayer().setPaused(false);
        try {
            showTrack(tracks.element(), player.getTextChannel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.getAudioPlayer().startTrack(tracks.poll(), false);
    }

    public void stop() {
        player.getAudioPlayer().stopTrack();
        player.getGuild().getAudioManager().closeAudioConnection();
    }

    public void replay() {
        player.getAudioPlayer().startTrack(player.getAudioPlayer().getPlayingTrack().makeClone(), false);
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            nextTrack();
        }
    }

    private void showTrack(AudioTrack track, TextChannel channel) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("\uD83D\uDD0A Lecture en cours")
                .setTitle(track.getInfo().title, track.getInfo().uri)
                .addField("Auteur", track.getInfo().author, true)
                .addField("Durée", MusicUtils.parseDuration(track.getDuration()), true)
                .setColor(new Color(67, 181, 129));
        channel.sendMessage(embed.build()).queue();
    }

}