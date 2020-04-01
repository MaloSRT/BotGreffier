package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import fr.msrt.botgreffier.utils.StringUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

import static org.apache.commons.lang3.StringUtils.strip;

public class Slowmode extends Command {

    public Slowmode() {
        this.name = "slowmode";
        this.aliases = new String[]{"cooldown"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        String warnSyntax = CmdUtils.warnSyntax(event.getClient().getPrefix() + CmdUtils.getCmdName(event.getMessage().getContentDisplay()) + " [nombre, off]")
                + "\n - `nombre` : délai en seconde compris entre 0 et " + TextChannel.MAX_SLOWMODE
                + "\n - `off` : désactiver le mode lent";

        if (!event.getChannelType().isGuild()) {

            event.reply(Constants.ERR_MP);

        } else if (!event.getSelfMember().hasPermission(Permission.MANAGE_CHANNEL)) {

            event.reply(Constants.ERR_PERM_BOT + "Gérer les salons");

        } else if (!event.getMember().hasPermission(Permission.MANAGE_CHANNEL) && !event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {

            event.reply(Constants.ERR_PERM_USR + "Gérer les salons ou Gérer les messages");

        } else if (event.getArgs().isEmpty()) {

            event.reply(warnSyntax);

        } else {

            String args = strip(event.getArgs());
            if ("off".equalsIgnoreCase(args)) args = "0";

            if (!StringUtils.isInteger(args)) {

                event.reply(warnSyntax);

            } else {

                int cooldown = Integer.parseInt(args);

                if (cooldown < 0 || cooldown > TextChannel.MAX_SLOWMODE) {
                    event.reply(warnSyntax);
                } else {
                    event.getTextChannel().getManager().setSlowmode(cooldown).queue();
                    if (cooldown == 0) {
                        event.reply(Constants.EMOTE_SUCCESS + " **Mode lent désactivé**");
                    } else if (cooldown == 1) {
                        event.reply(Constants.EMOTE_SUCCESS + " **Mode lent activé : ** les membres ne peuvent envoyer qu'un message par seconde");
                    } else {
                        event.reply(Constants.EMOTE_SUCCESS + " **Mode lent activé : ** les membres ne peuvent envoyer qu'un message toutes les `" + cooldown + "` secondes");
                    }
                }

            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
