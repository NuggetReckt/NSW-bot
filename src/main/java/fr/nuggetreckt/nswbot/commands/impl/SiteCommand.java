package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.Config;
import fr.nuggetreckt.nswbot.util.MessageManager;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class SiteCommand extends Command {

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {

        TextChannel botChannel = new Config().getBotChannel();

        if (event.getName().equals("site")) {
            if (event.getChannel().equals(botChannel)) {
                event.reply("> **Site :** https://play.noskillworld.fr")
                        .queue();
            } else {
                event.reply(String.format(MessageManager.BAD_CHANNEL_MESSAGE.getMessage(), botChannel.getAsMention()))
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
