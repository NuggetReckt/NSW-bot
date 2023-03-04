package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.Main;
import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ShopCommand extends Command {

    String botchannelid = new Config().getBotChannelId();

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("shop")) {
            if (event.getChannel().getId().equals(botchannelid)) {
                event.reply("> **Shop :** https://shop.noskillworld.fr")
                        .queue();
            } else {
                event.reply("Mauvais salon ! Merci d'utiliser le salon " + Objects.requireNonNull(Main.jda.getTextChannelById(botchannelid)).getAsMention())
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
