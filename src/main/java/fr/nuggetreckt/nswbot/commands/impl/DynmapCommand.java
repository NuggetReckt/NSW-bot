package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class DynmapCommand extends Command {

    MessageChannel botChannel = new Config().getBotChannel();

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("dynmap")) {
            if (event.getChannel().equals(botChannel)) {
                event.reply("> **Dynmap :** https://play.noskillworld.fr/dynmap")
                        .queue();
            } else {
                event.reply("Mauvais salon ! Merci d'utiliser le salon " + botChannel.getAsMention())
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
