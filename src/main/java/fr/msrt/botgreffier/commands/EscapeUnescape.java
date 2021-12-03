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
        String args = event.getArgs();

        if (msg.equalsIgnoreCase(prefix + "EscapeUnescape")) {

            CmdUtils.sysoutCmd(msg);
            return;

        } else if (args.isEmpty()) {

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
                    case "html":  sendReply(event, StringEscapeUtils.escapeHtml4(args));
                                  break;
                    case "java":  sendReply(event, StringEscapeUtils.escapeJava(args));
                                  break;
                    case "js":    sendReply(event, StringEscapeUtils.escapeEcmaScript(args));
                                  break;
                    case "xml":   sendReply(event, StringEscapeUtils.escapeXml11(args));
                                  break;
                    case "json":  sendReply(event, StringEscapeUtils.escapeJson(args));
                                  break;
                    case "csv":   sendReply(event, StringEscapeUtils.escapeCsv(args));
                                  break;
                    case "shell": sendReply(event, StringEscapeUtils.escapeXSI(args));
                                  break;
                    default:      event.reply(Constants.ERR_PROG);
                                  break;
                }
            } else {
                switch (lang) {
                    case "html":  sendReply(event, StringEscapeUtils.unescapeHtml4(args));
                                  break;
                    case "java":  sendReply(event, StringEscapeUtils.unescapeJava(args));
                                  break;
                    case "js":    sendReply(event, StringEscapeUtils.unescapeEcmaScript(args));
                                  break;
                    case "xml":   sendReply(event, StringEscapeUtils.unescapeXml(args));
                                  break;
                    case "json":  sendReply(event, StringEscapeUtils.unescapeJson(args));
                                  break;
                    case "csv":   sendReply(event, StringEscapeUtils.unescapeCsv(args));
                                  break;
                    case "shell": sendReply(event, StringEscapeUtils.unescapeXSI(args));
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