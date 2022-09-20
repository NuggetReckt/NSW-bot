package fr.nuggetreckt.nswbot.ticketsystem.commands;

import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

public class CloseCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("close")) {
            if (event.getChannel().getName().contains("ticket-de-")) {
                EmbedBuilder confirm = new EmbedBuilder();

                confirm.setTitle("Confirmation requise d'un administrateur");
                confirm.setColor(new Color(61, 189, 201, 1));

                event.replyEmbeds(confirm.build())
                        .addActionRow(
                                Button.danger("confirm", "Supprimer définitivement."),
                                Button.secondary("abort", "Annuler"))
                        .queue();

                EmbedBuilder logs = new EmbedBuilder();

                logs.setTitle("Log - Demande de suppression de ticket")
                        .setDescription("Ticket : " + event.getGuildChannel().getName() + "\nPar : " + Objects.requireNonNull(event.getMember()).getAsMention())
                        .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                        .setColor(new Color(255, 165, 54, 1))
                        .setTimestamp(new Date().toInstant());

                Objects.requireNonNull(Objects.requireNonNull(event.getGuild()).getTextChannelById(new Config().getLogChannelId())).sendMessageEmbeds(logs.build()).queue();
            } else {
                event.reply("Vous n'êtes pas dans un ticket !")
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
