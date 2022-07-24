package fr.nuggetreckt.nswbot.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class GronazListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (content.contains("gronaz")) {
            message.reply("> hehe")
                    .queue();
            message.delete().queueAfter(500, TimeUnit.MILLISECONDS);
        }
    }
}
