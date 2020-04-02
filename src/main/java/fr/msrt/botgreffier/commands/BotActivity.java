package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.entities.Activity;

public class BotActivity extends Command {

    public BotActivity() {
        this.name = "botactivity";
        this.aliases = new String[]{"activity"};
        this.ownerCommand = true;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (event.getArgs().isEmpty()) {
            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay(), "[l, p, w] [texte]"));
        } else {
            String[] activity = event.getArgs().split(" ", 2);
            if (activity.length == 2) {
                switch (activity[0]) {
                    case "l": event.getJDA().getPresence().setActivity(Activity.listening(activity[1]));
                              event.reply(":white_check_mark: `Écoute " + activity[1] + "`");
                              break;
                    case "p": event.getJDA().getPresence().setActivity(Activity.playing(activity[1]));
                              event.reply(":white_check_mark: `Joue à " + activity[1] + "`");
                              break;
                    case "w": event.getJDA().getPresence().setActivity(Activity.watching(activity[1]));
                              event.reply(":white_check_mark: `Regarde " + activity[1] + "`");
                              break;
                    default:  event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay(), "[l, p, w] [texte]"));
                              break;
                }
            } else {
                event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay(), "[l, p, w] [texte]"));
            }
        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}