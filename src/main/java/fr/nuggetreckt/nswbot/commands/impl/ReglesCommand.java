package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class ReglesCommand extends Command {

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {

        TextChannel botChannel = new Config().getBotChannel();

        if (event.getName().equals("règles")) {
            if (event.getChannel().equals(botChannel)) {
                event.reply("> **Règles :** https://play.noskillworld.fr/rules")
                        .queue();
            } else {
                event.reply("Mauvais salon ! Merci d'utiliser le salon " + botChannel.getAsMention())
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
