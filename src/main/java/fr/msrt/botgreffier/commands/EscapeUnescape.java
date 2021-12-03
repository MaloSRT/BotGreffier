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
            boolean escape = !cmdName.startsWith("un");

            if (escape) {
                switch (lang) {
                    case "html":  sendReply(event, StringEscapeUtils.escapeHtml4(event.getArgs()));
                                  break;
                    case "java":  sendReply(event, StringEscapeUtils.escapeJava(event.getArgs()));
                                  break;
                    case "js":    sendReply(event, StringEscapeUtils.escapeEcmaScript(event.getArgs()));
                                  break;
                    case "xml":   sendReply(event, StringEscapeUtils.escapeXml11(event.getArgs()));
                                  break;
                    case "json":  sendReply(event, StringEscapeUtils.escapeJson(event.getArgs()));
                                  break;
                    case "csv":   sendReply(event, StringEscapeUtils.escapeCsv(event.getArgs()));
                                  break;
                    case "shell": sendReply(event, StringEscapeUtils.escapeXSI(event.getArgs()));
                                  break;
                    default:      event.reply(Constants.ERR_PROG);
                                  break;
                }
            } else {
                switch (lang) {
                    case "html":  sendReply(event, StringEscapeUtils.unescapeHtml4(event.getArgs()));
                                  break;
                    case "java":  sendReply(event, StringEscapeUtils.unescapeJava(event.getArgs()));
                                  break;
                    case "js":    sendReply(event, StringEscapeUtils.unescapeEcmaScript(event.getArgs()));
                                  break;
                    case "xml":   sendReply(event, StringEscapeUtils.unescapeXml(event.getArgs()));
                                  break;
                    case "json":  sendReply(event, StringEscapeUtils.unescapeJson(event.getArgs()));
                                  break;
                    case "csv":   sendReply(event, StringEscapeUtils.unescapeCsv(event.getArgs()));
                                  break;
                    case "shell": sendReply(event, StringEscapeUtils.unescapeXSI(event.getArgs()));
                                  break;
                    default:      event.reply(Constants.ERR_PROG);
                                  break;
                }
            }

        }

        CmdUtils.sysoutCmd(msg);

    }

    private static void sendReply(CommandEvent event, String reply) {
        event.reply("```\n" + reply + "\n```");
    }

}