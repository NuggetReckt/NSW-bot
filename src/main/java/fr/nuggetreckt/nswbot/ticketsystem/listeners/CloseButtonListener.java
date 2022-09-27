package fr.nuggetreckt.nswbot.ticketsystem.listeners;

import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

import static fr.nuggetreckt.nswbot.Main.jda;

public class CloseButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("close")) {
            EmbedBuilder confirm = new EmbedBuilder();

            confirm.setTitle("Confirmation requise d'un administrateur")
                    .setColor(new Color(61, 189, 201, 1));

            event.replyEmbeds(confirm.build())
                    .addActionRow(
                            Button.danger("confirm", "Supprimer définitivement."),
                            Button.secondary("abort", "Annuler"))
                    .queue();

            EmbedBuilder logs = new EmbedBuilder();

            logs.setTitle("Log - Demande de suppression de ticket")
                    .setDescription("Ticket : " + event.getTextChannel().getAsMention() + "\nPar : " + Objects.requireNonNull(event.getMember()).getAsMention())
                    .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                    .setColor(new Color(255, 165, 54, 1))
                    .setTimestamp(new Date().toInstant());

            Objects.requireNonNull(jda.getTextChannelById(new Config().getLogChannelId())).sendMessageEmbeds(logs.build()).queue();
        }
    }
}
