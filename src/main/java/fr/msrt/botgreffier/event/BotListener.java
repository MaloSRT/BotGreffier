package fr.msrt.botgreffier.event;

import fr.msrt.botgreffier.ia.IA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.util.stream.Stream;

public class BotListener implements EventListener {

    @Override
    public void onEvent(GenericEvent event) {
        System.out.println(event.getClass().getSimpleName());
        if (event instanceof GuildMessageReceivedEvent) onMessage((GuildMessageReceivedEvent)event);
        if (event instanceof PrivateMessageReceivedEvent) onPrivateMessage((PrivateMessageReceivedEvent)event);
    }

    private void onMessage(GuildMessageReceivedEvent event) {

        if (event.getAuthor().equals(event.getJDA().getSelfUser())) return;

        IA ia = new IA();

        Message objMsg = event.getMessage();
        MessageChannel objChannel = event.getChannel();

        String msg = objMsg.getContentDisplay();
        // String errPing = ":x: **Cette commande a été ignorée pour éviter un ping**";

        try {

            if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {

                if (msg.equals("GREFFIER")) {
                    objChannel.sendMessage("OUI").queue();
                    sysoutCmd(msg);
                    return;
                }

                if (msg.equalsIgnoreCase("Greffier")) {
                    objChannel.sendMessage(":D").queue();
                    sysoutCmd(msg);
                    return;
                }

                /*

                if (msg.length() >= 13) {
                    if (msg.substring(0, 12).equalsIgnoreCase("=escapeHTML ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.escapeHtml4(msg.substring(12)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }
                if (msg.length() >= 15) {
                    if (msg.substring(0, 14).equalsIgnoreCase("=unescapeHTML ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.unescapeHtml4(msg.substring(14)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }

                if (msg.length() >= 13) {
                    if (msg.substring(0, 12).equalsIgnoreCase("=escapeJava ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.escapeJava(msg.substring(12)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }
                if (msg.length() >= 15) {
                    if (msg.substring(0, 14).equalsIgnoreCase("=unescapeJava ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.unescapeJava(msg.substring(14)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }

                if (msg.length() >= 11) {
                    if (msg.substring(0, 10).equalsIgnoreCase("=escapeJS ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.escapeEcmaScript(msg.substring(10)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }
                if (msg.length() >= 13) {
                    if (msg.substring(0, 12).equalsIgnoreCase("=unescapeJS ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.unescapeEcmaScript(msg.substring(12)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }

                if (msg.length() >= 12) {
                    if (msg.substring(0, 11).equalsIgnoreCase("=escapeXML ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.escapeXml11(msg.substring(11)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }
                if (msg.length() >= 14) {
                    if (msg.substring(0, 13).equalsIgnoreCase("=unescapeXML ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.unescapeXml(msg.substring(13)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }

                if (msg.length() >= 13) {
                    if (msg.substring(0, 12).equalsIgnoreCase("=escapeJson ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.escapeJson(msg.substring(12)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }
                if (msg.length() >= 15) {
                    if (msg.substring(0, 14).equalsIgnoreCase("=unescapeJson ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.unescapeJson(msg.substring(14)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }

                if (msg.length() >= 12) {
                    if (msg.substring(0, 11).equalsIgnoreCase("=escapeCSV ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.escapeCsv(msg.substring(11)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }
                if (msg.length() >= 14) {
                    if (msg.substring(0, 13).equalsIgnoreCase("=unescapeCSV ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.unescapeCsv(msg.substring(13)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }

                if (msg.length() >= 14) {
                    if (msg.substring(0, 13).equalsIgnoreCase("=escapeShell ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.escapeXSI(msg.substring(13)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }
                if (msg.length() >= 16) {
                    if (msg.substring(0, 15).equalsIgnoreCase("=unescapeShell ")) {
                        if (antiPing(msg)) {
                            objChannel.sendMessage("```\n" + StringEscapeUtils.unescapeXSI(msg.substring(15)) + "\n```").queue();
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }

                 */

                /* if (msg.length() >= 7) {
                    if (msg.substring(0, 7).equalsIgnoreCase("=party ")) {
                        final String errSyntaxe = ":x: **Syntaxe incorrecte**\nLa syntaxe pour cette commande est : `=party [jeu], [lien]`";
                        if (antiPing(msg)) {
                            String[] party = msg.substring(7).split(", ");
                            if (party.length == 2 && msg.length() >= 20) {
                                String game = party[0];
                                String link = party[1];
                                if (Utils.isLink(link)) {
                                    objMsg.delete().queue();
                                    objChannel.sendMessage("Une partie de **" + game + "** a commencé ! \nCliquez ici pour la rejoindre : " + link).queue();
                                } else {
                                    objChannel.sendMessage(errSyntaxe).queue();
                                }
                            } else {
                                objChannel.sendMessage(errSyntaxe).queue();
                            }
                            sysoutCmd(msg);
                        } else {
                            objChannel.sendMessage(errPing).queue();
                        }
                        return;
                    }
                }
                if (msg.equalsIgnoreCase("=party")) {
                    objChannel.sendMessage(":x: **Syntaxe incorrecte**\nLa syntaxe pour cette commande est : `=party [jeu], [lien]`").queue();
                    sysoutCmd(msg);
                    return;
                }

                // Message d'erreur
                if (Stream.of("=escapeHTML", "=unescapeHTML",
                        "=escapeJava", "=unescapeJava",
                        "=escapeJS", "=unescapeJS",
                        "=escapeXML", "=unescapeXML",
                        "=escapeJson", "=unescapeJson",
                        "=escapeCSV", "=unescapeCSV",
                        "=escapeShell", "=unescapeShell").anyMatch(msg::equalsIgnoreCase)) {
                    objChannel.sendMessage(":x: **Syntaxe incorrecte**\nLa syntaxe pour cette commande est : `" + msg + " [texte]`").queue();
                    sysoutCmd(msg);
                    return;
                }

                 */

            }

            /* if ((event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) && (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS))) {


                if (msg.length() >= 8) {
                    if (msg.substring(0, 7).equalsIgnoreCase("=embed ")) {
                        objMsg.delete().queue();

                        EmbedBuilder embed = new EmbedBuilder();
                        embed.setTitle(msg.substring(7))
                                .setFooter("Par " + objUser.getName() + "#" + objUser.getDiscriminator())
                                .setTimestamp(objMsg.getTimeCreated())
                                .setColor(Color.YELLOW);
                        objChannel.sendMessage(embed.build()).queue();
                        sysoutCmd(msg);
                    }
                }

            } */

            if ((event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) && (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION))) {

                /* if (msg.length() >= 7) {
                    if (msg.substring(0, 6).equalsIgnoreCase("=react")) {
                        Message previousMsg = objMsg;
                        ArrayList<Emote> reactions = new ArrayList<>();
                        if (msg.toLowerCase().contains("oui")) reactions.add(JDAManager.getShardManager().getEmoteById("453239882530291743"));
                        if (msg.toLowerCase().contains("non")) reactions.add(JDAManager.getShardManager().getEmoteById("453239911424983040"));
                        if (reactions.size() > 0) {
                            for (Emote reaction : reactions) {
                                previousMsg.addReaction(reaction).queue();
                            }
                        } else {
                            objChannel.sendMessage(":x: **Syntaxe incorrecte**\nLa syntaxe pour cette commande est : `=react [emote(s)]`").queue();
                        }
                        sysoutCmd(msg);
                    }
                } */

                if (msg.length() >= 9) {
                    if (msg.toLowerCase().substring(0, 9).contains("bayolante")) {
                        objMsg.addReaction("\ud83c\udde7").queue();
                        objMsg.addReaction("\ud83c\udd70").queue();
                        objMsg.addReaction("\ud83c\uddfe").queue();
                        objMsg.addReaction("\ud83c\uddf4").queue();
                        objMsg.addReaction("\ud83c\uddf1").queue();
                        objMsg.addReaction("\ud83c\udde6").queue();
                        objMsg.addReaction("\ud83c\uddf3").queue();
                        objMsg.addReaction("\ud83c\uddf9").queue();
                        objMsg.addReaction("\ud83c\uddea").queue();
                        sysoutCmd(msg);
                    }
                }

            }

            // COMMANDES PRIVEES
            /* if (objUser.getId().equals("333329500358180864")) {

                if (msg.length() >= 7) {

                    if (msg.substring(0, 6).equalsIgnoreCase("=echo ")) {
                        objChannel.sendMessage(msg.substring(6)).queue();
                        sysoutCmd(msg);
                        return;
                    }

                }

                if (msg.length() >= 10) {

                    if (msg.substring(0, 9).equalsIgnoreCase("=echodel ")) {
                        objMsg.delete().queue();
                        objChannel.sendMessage(msg.substring(9)).queue();
                        sysoutCmd(msg);
                        return;
                    }

                }

                if (msg.length() >= 11) {

                    if (msg.substring(0, 10).equalsIgnoreCase("=activity ")) {

                        event.getJDA().getPresence().setActivity(Activity.playing(msg.substring(10)));
                        objChannel.sendMessage(":white_check_mark: `Joue à " + msg.substring(10) + "`").queue();
                        sysoutCmd(msg);
                        return;

                    }

                }

            } */

            // IA
            if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {

                if (msg.toLowerCase().contains("greffier")) {

                    String answer = ia.getAnswer(msg.toLowerCase());
                    if (!answer.equals("0")) {
                        objChannel.sendTyping().queue();
                        delay();
                        objChannel.sendMessage(answer).queue();
                        System.out.println("IA has spoken");
                    }

                }

            }

        } catch (Exception | Error e) {

            if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                objChannel.sendMessage(":x: **Quelque chose a mal tourné :**\n`" + e + "`").queue();
                sysoutCmd(msg);
            }

            System.err.println("Erreur causée par le message suivant : " + msg);
            e.printStackTrace();

        }

    }

    private void onPrivateMessage(PrivateMessageReceivedEvent event) {

        if (event.getAuthor().equals(event.getJDA().getSelfUser())) return;

        IA ia = new IA();

        Message objMsg = event.getMessage();
        PrivateChannel objChannel = event.getChannel();

        String msg = objMsg.getContentDisplay();

        String answer = ia.getAnswer(msg.toLowerCase());
        if (!answer.equals("0")) {
            objChannel.sendTyping().queue();
            delay();
            objChannel.sendMessage(answer).queue();
            System.out.println("IA has spoken");
        }

    }

    private static void sysoutCmd(String msgContent) {

        if (msgContent.length() <= 35) {
            System.out.println("Command : " + msgContent);
        } else {
            System.out.println("Command : " + msgContent.substring(0, 35) + " [...]");
        }

    }

    private static void delay() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
