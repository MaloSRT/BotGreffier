package fr.msrt.botgreffier.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.Objects;

public class MusicPlayer {

    private final AudioPlayer audioPlayer;
    private final Guild guild;
    private final AudioListener listener;
    private TextChannel textChannel;

    public MusicPlayer(AudioPlayer audioPlayer, Guild guild) {
        this.audioPlayer = audioPlayer;
        this.guild = guild;
        listener = new AudioListener(this);
        audioPlayer.addListener(listener);
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public Guild getGuild() {
        return guild;
    }

    public TextChannel getTextChannel() throws NullPointerException {
        if (Objects.isNull(textChannel)) {
            throw new NullPointerException("MusicPlayer.getTextChannel: textChannel is null.");
        }
        return textChannel;
    }

    public AudioListener getListener() {
        return listener;
    }

    public AudioHandler getAudioHandler() {
        return new AudioHandler(audioPlayer);
    }

    public boolean isPaused() {
        return listener.isPaused();
    }

    public synchronized void setTextChannel(TextChannel textChannel) {
        this.textChannel = textChannel;
    }

    public synchronized void playTrack(AudioTrack track) {
        listener.queue(track);
    }

    public synchronized void skipTrack() {
        listener.nextTrack();
    }

    public synchronized void pause() {
        listener.pause();
    }

    public synchronized void resumeFromActive() {
        listener.resumeFromActive();
    }

    public synchronized void resumeFromInactive(VoiceChannel channel) {
        listener.resumeFromInactive(channel);
    }

    public synchronized void stop() {
        listener.stop();
    }

    public synchronized void replay() {
        listener.replay();
    }

}