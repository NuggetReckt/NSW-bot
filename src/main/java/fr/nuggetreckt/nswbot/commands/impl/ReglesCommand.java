package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.Main;
import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ReglesCommand extends Command {

    String botchannelid = new Config().getBotChannelId();

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("règles")) {
            if (event.getChannel().getId().equals(botchannelid)) {
                event.reply("> **Règles :** https://play.noskillworld.fr/rules")
                        .queue();
            } else {
                event.reply("Mauvais salon ! Merci d'utiliser le salon " + Objects.requireNonNull(Main.jda.getTextChannelById(botchannelid)).getAsMention())
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
