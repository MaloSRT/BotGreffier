package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.Permission;

public class Invite extends Command {

    public Invite() {
        this.name = "invite";
        this.aliases = new String[]{"invitation", "inviter"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (!event.getChannelType().isGuild()) {

            event.reply(Constants.ERR_MP);

        } else if (!event.getSelfMember().hasPermission(Permission.CREATE_INSTANT_INVITE)) {

            event.reply(Constants.ERR_PERM_BOT + "Créer une invitation");

        } else if (!event.getMember().hasPermission(Permission.CREATE_INSTANT_INVITE)) {

            event.reply(Constants.ERR_PERM_USR + "Créer une invitation");

        } else if (!event.getArgs().isEmpty() && (!event.getArgs().equalsIgnoreCase("t") && !event.getArgs().equalsIgnoreCase("p"))) {

            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay(), "[t, p]")
                + "\n - `t` : créer une invitation temporaire (expire au bout de 24h)"
                + "\n - `p` : créer une invitation permanente");

        } else {

            String persistence;
            int expiration;

            if (event.getArgs().equalsIgnoreCase("p")) {
                persistence = "permanent";
                expiration = 0;
            } else {
                persistence = "temporaire";
                expiration = 86400;
            }

            event.reply("Lien d'invitation __" + persistence + "__ pour <#" + event.getTextChannel().getId() + "> : "
                    + event.getTextChannel().createInvite().setMaxAge(expiration).complete().getUrl());

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}