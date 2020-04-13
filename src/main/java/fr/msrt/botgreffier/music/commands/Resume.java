package fr.msrt.botgreffier.music.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.music.MusicUtils;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.Permission;

public class Resume extends Command {

    public Resume() {
        // alias pour 'play' sans argument
        this.name = "resume";
        this.aliases = new String[]{"unpause"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (MusicUtils.canControl(event)) {
            Play.resume(event);
        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}