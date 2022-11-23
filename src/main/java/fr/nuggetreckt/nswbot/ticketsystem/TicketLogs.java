package fr.nuggetreckt.nswbot.ticketsystem;

import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

import static fr.nuggetreckt.nswbot.Main.jda;

public class TicketLogs {

    EmbedBuilder builder = new EmbedBuilder();

    public void TicketCreate(Member user, Channel channel) {
        builder.setTitle("Log - Création de ticket")
                .setDescription("Ticket : " + channel.getAsMention() + "\nCréé par : " + user.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                .setColor(new Color(76, 193, 80, 1))
                .setTimestamp(new Date().toInstant());

        Objects.requireNonNull(jda.getTextChannelById(new Config().getLogChannelId())).sendMessageEmbeds(builder.build()).queue();
    }

    public void TicketDelete(Member user, Channel channel) {
        builder.setTitle("Log - Suppression de ticket")
                .setDescription("Ticket : " + channel.getAsMention() + "\nFermé par : " + user.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                .setColor(new Color(218, 67, 54, 1))
                .setTimestamp(new Date().toInstant());

        Objects.requireNonNull(jda.getTextChannelById(new Config().getLogChannelId())).sendMessageEmbeds(builder.build()).queue();
    }

    public void TicketClose(Member user, Channel channel) {
        builder.setTitle("Log - Demande de suppression de ticket")
                .setDescription("Ticket : " + channel.getAsMention() + "\nPar : " + user.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                .setColor(new Color(255, 165, 54, 1))
                .setTimestamp(new Date().toInstant());

        Objects.requireNonNull(jda.getTextChannelById(new Config().getLogChannelId())).sendMessageEmbeds(builder.build()).queue();
    }

    public void TicketAbort(Member user, Channel channel) {
        builder.setTitle("Log - Annulation de suppression de ticket")
                .setDescription("Ticket : " + channel.getAsMention() + "\nPar : " + user.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                .setColor(new Color(76, 193, 80, 1))
                .setTimestamp(new Date().toInstant());

        Objects.requireNonNull(jda.getTextChannelById(new Config().getLogChannelId())).sendMessageEmbeds(builder.build()).queue();
    }

    public void TicketAdd(Member user, Member executor, Channel channel) {
        builder.setTitle("Log - Membre ajouté à un ticket")
                .setDescription("Ticket : " + channel.getAsMention()
                        + "\nMembre :" + user.getAsMention()
                        + "\nAjouté par : " + executor.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                .setColor(new Color(76, 193, 80, 1))
                .setTimestamp(new Date().toInstant());

        Objects.requireNonNull(jda.getTextChannelById(new Config().getLogChannelId())).sendMessageEmbeds(builder.build()).queue();
    }

    public void TicketRemove(Member user, Member executor, Channel channel) {
        builder.setTitle("Log - Membre retiré d'un ticket")
                .setDescription("Ticket : " + channel.getAsMention()
                        + "\nMembre :" + user.getAsMention()
                        + "\nRetiré par : " + executor.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                .setColor(new Color(218, 67, 54, 1))
                .setTimestamp(new Date().toInstant());

        Objects.requireNonNull(jda.getTextChannelById(new Config().getLogChannelId())).sendMessageEmbeds(builder.build()).queue();
    }
}