package fr.nuggetreckt.nswbot.listeners;

import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (event.getAuthor().isBot()) return;
        if (Objects.requireNonNull(event.getMember()).getRoles().contains(new Config().getStaffRole())) return;
        if (content.contains("https://") && content.contains("http://")) return;

        if (event.getChannel().equals(new Config().getGeneralChannel())) {

            if (content.contains("aide") && content.contains("besoin")) {
                message.reply("> Si vous avez besoin d'aide, rendez-vous dans "
                                + new Config().getTicketPanel().getAsMention())
                        .queue();
            } else if (content.contains("support") && content.contains("besoin")) {
                message.reply("> Si vous avez besoin de contacter le staff, créez un ticket dans le salon "
                                + new Config().getTicketPanel().getAsMention())
                        .queue();
            } else if (content.contains("ip") && content.contains("quoi") && content.contains("?")) {
                message.reply("> IP du serveur : **play.noskillworld.fr**")
                        .queue();
            } else if (content.contains("site") && content.contains("quoi") && content.contains("?")) {
                message.reply("> Lien du site internet : https://play.noskillworld.fr")
                        .queue();
            } else if (content.contains("mumble") && content.contains("quoi") && content.contains("?")) {
                message.reply("> IP du mumble : **mumble.noskillworld.fr**")
                        .queue();
            }
        }
    }
}
