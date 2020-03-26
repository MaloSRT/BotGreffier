package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.jda.JDAManager;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class KillInstance extends Command {

    public KillInstance() {
        this.name = "killinstance";
        this.ownerCommand = true;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("**Arrêt de l'instance en cours**");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());
        event.getJDA().getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        event.getJDA().getPresence().setActivity(Activity.playing("aller dormir..."));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JDAManager.getShardManager().shutdown();
        System.out.println("Cette instance a été stoppée via la commande '=killInstance'");
        System.exit(0);

    }

}