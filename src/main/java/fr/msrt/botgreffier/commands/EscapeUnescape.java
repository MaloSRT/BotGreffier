package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import org.apache.commons.text.StringEscapeUtils;

public class EscapeUnescape extends Command {

    public EscapeUnescape() {
        this.name = "escapeunescape";
        this.aliases = new String[]{
                  "escapehtml",   "escapejava",   "escapejs",   "escapexml",   "escapejson",   "escapecsv",   "escapeshell",
                "unescapehtml", "unescapejava", "unescapejs", "unescapexml", "unescapejson", "unescapecsv", "unescapeshell"
        };
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        String prefix = event.getClient().getPrefix();
        String msg = event.getMessage().getContentDisplay();

        if (msg.equalsIgnoreCase(prefix + "EscapeUnescape")) {

            CmdUtils.sysoutCmd(msg);
            return;

        } else if (event.getArgs().isEmpty()) {

            event.reply(CmdUtils.warnSyntax(msg, "[texte]"));

        } else if (!CmdUtils.antiPing(msg)) {

            event.reply(Constants.ERR_PING);

        } else {

            String cmdName = CmdUtils.getCmdName(msg.toLowerCase());
            System.out.println(cmdName);
            String lang = cmdName.split("escape")[1];
            boolean escape = !cmdName.substring(0, 2).equals("un");

            if (escape) {
                switch (lang) {
                    case "html":  event.reply("```\n" + StringEscapeUtils.escapeHtml4(event.getArgs())      + "\n```");
                                  break;
                    case "java":  event.reply("```\n" + StringEscapeUtils.escapeJava(event.getArgs())       + "\n```");
                                  break;
                    case "js":    event.reply("```\n" + StringEscapeUtils.escapeEcmaScript(event.getArgs()) + "\n```");
                                  break;
                    case "xml":   event.reply("```\n" + StringEscapeUtils.escapeXml11(event.getArgs())      + "\n```");
                                  break;
                    case "json":  event.reply("```\n" + StringEscapeUtils.escapeJson(event.getArgs())       + "\n```");
                                  break;
                    case "csv":   event.reply("```\n" + StringEscapeUtils.escapeCsv(event.getArgs())        + "\n```");
                                  break;
                    case "shell": event.reply("```\n" + StringEscapeUtils.escapeXSI(event.getArgs())        + "\n```");
                                  break;
                    default:      event.reply(Constants.ERR_PROG);
                                  break;
                }
            } else {
                switch (lang) {
                    case "html":  event.reply("```\n" + StringEscapeUtils.unescapeHtml4(event.getArgs())        + "\n```");
                                  break;
                    case "java":  event.reply("```\n" + StringEscapeUtils.unescapeJava(event.getArgs())         + "\n```");
                                  break;
                    case "js":    event.reply("```\n" + StringEscapeUtils.unescapeEcmaScript(event.getArgs())   + "\n```");
                                  break;
                    case "xml":   event.reply("```\n" + StringEscapeUtils.unescapeXml(event.getArgs())          + "\n```");
                                  break;
                    case "json":  event.reply("```\n" + StringEscapeUtils.unescapeJson(event.getArgs())         + "\n```");
                                  break;
                    case "csv":   event.reply("```\n" + StringEscapeUtils.unescapeCsv(event.getArgs())          + "\n```");
                                  break;
                    case "shell": event.reply("```\n" + StringEscapeUtils.unescapeXSI(event.getArgs())          + "\n```");
                                  break;
                    default:      event.reply(Constants.ERR_PROG);
                                  break;
                }
            }

        }

        CmdUtils.sysoutCmd(msg);

    }

}