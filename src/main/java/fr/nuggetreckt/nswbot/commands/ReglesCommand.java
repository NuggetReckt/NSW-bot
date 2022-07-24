package fr.nuggetreckt.nswbot.commands;

import fr.nuggetreckt.nswbot.Main;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class ReglesCommand extends ListenerAdapter {

    String botchannelid = new Config().getBotChannelId();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("règles")) {
            if (event.getChannel().getId().equals(botchannelid)) {
                event.reply("> **Règles :** https://play.noskillworld.fr/rules")
                        .queue();
            }
            else {
                event.reply("Mauvais salon ! Merci d'utiliser le salon " + Objects.requireNonNull(Main.jda.getTextChannelById(botchannelid)).getAsMention())
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
