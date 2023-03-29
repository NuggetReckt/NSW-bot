package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class SiteCommand extends Command {

    MessageChannel botChannel = new Config().getBotChannel();

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("site")) {
            if (event.getChannel().equals(botChannel)) {
                event.reply("> **Site :** https://play.noskillworld.fr")
                        .queue();
            } else {
                event.reply("Mauvais salon ! Merci d'utiliser le salon " + botChannel.getAsMention())
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
