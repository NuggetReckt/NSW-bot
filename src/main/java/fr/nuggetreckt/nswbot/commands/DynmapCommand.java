package fr.nuggetreckt.nswbot.commands;

import fr.nuggetreckt.nswbot.Main;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class DynmapCommand extends ListenerAdapter {

    String botchannelid = new Config().getBotChannelId();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("dynmap")) {
            if (event.getChannel().getId().equals(botchannelid)) {
                event.reply("> **Dynmap :** https://play.noskillworld.fr/dynmap")
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
