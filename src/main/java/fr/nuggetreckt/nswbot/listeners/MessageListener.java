package fr.nuggetreckt.nswbot.listeners;

import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static fr.nuggetreckt.nswbot.Main.jda;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        if (event.getChannel().getId().equals(new Config().getGeneralChannelId())) {

            Message message = event.getMessage();
            String content = message.getContentRaw();

            if (content.contains("aide")) {
                message.reply("> Si vous avez besoin d'aide, rendez-vous dans "
                                + Objects.requireNonNull(jda.getTextChannelById(new Config().getTicketPanelId())).getAsMention())
                        .queue();
            } else if (content.contains("support")) {
                message.reply("> Si vous avez besoin de contacter le staff, créez un ticket dans le salon "
                                + Objects.requireNonNull(jda.getTextChannelById(new Config().getTicketPanelId())).getAsMention())
                        .queue();
            } else if (content.contains("ip")) {
                message.reply("> IP du serveur : **play.noskillworld.fr**")
                        .queue();
            } else if (content.contains("site")) {
                message.reply("> Lien du site internet : https://play.noskillworld.fr")
                        .queue();
            } else if (content.contains("mumble")) {
                message.reply("> IP du mumble : **mumble.noskillworld.fr**")
                        .queue();
            }
        }
    }
}
