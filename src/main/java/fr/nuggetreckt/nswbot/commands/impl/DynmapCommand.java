package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.MessageManager;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class DynmapCommand extends Command {

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {

        TextChannel botChannel = NSWBot.getConfig().getBotChannel();

        if (event.getName().equals("dynmap")) {
            if (event.getChannel().equals(botChannel)) {
                event.reply("> **Dynmap :** https://play.noskillworld.fr/dynmap")
                        .queue();
            } else {
                event.reply(String.format(MessageManager.BAD_CHANNEL.getMessage(), botChannel.getAsMention()))
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
