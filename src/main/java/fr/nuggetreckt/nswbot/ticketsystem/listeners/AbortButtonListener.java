package fr.nuggetreckt.nswbot.ticketsystem.listeners;

import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

import static fr.nuggetreckt.nswbot.Main.jda;

public class AbortButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("abort")) {
            event.getMessage()
                    .delete()
                    .queue();

            EmbedBuilder logs = new EmbedBuilder();

            logs.setTitle("Log - Annulation de suppression de ticket")
                    .setDescription("Ticket : " + event.getTextChannel().getAsMention() + "\nPar : " + Objects.requireNonNull(event.getMember()).getAsMention())
                    .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                    .setColor(new Color(76, 193, 80, 1))
                    .setTimestamp(new Date().toInstant());

            Objects.requireNonNull(jda.getTextChannelById(new Config().getLogChannelId())).sendMessageEmbeds(logs.build()).queue();
        }
    }
}
