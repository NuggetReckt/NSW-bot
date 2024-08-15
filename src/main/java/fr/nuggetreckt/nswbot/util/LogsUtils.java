package fr.nuggetreckt.nswbot.util;

import fr.nuggetreckt.nswbot.NSWBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;

public class LogsUtils {

    private final NSWBot instance;

    public LogsUtils(NSWBot instance) {
        this.instance = instance;
    }

    final EmbedBuilder builder = new EmbedBuilder();

    public void TicketCreate(@NotNull Member user, @NotNull Channel channel) {
        builder.setTitle("Log - Création de ticket")
                .setDescription("Ticket : **" + channel.getName() + "**"
                        + "\nCréé par : " + user.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setColor(new Color(76, 193, 80, 1))
                .setTimestamp(new Date().toInstant());

        instance.getConfig().getLogChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public void TicketDelete(@NotNull Member user, @NotNull Channel channel) {
        builder.setTitle("Log - Suppression de ticket")
                .setDescription("Ticket : **" + channel.getName() + "**"
                        + "\nFermé par : " + user.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setColor(new Color(218, 67, 54, 1))
                .setTimestamp(new Date().toInstant());

        instance.getConfig().getLogChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public void TicketSave(@NotNull Member user, @NotNull Channel channel, FileUpload transcript) {
        builder.setTitle("Log - Sauvegarde de ticket")
                .setDescription("Ticket : **" + channel.getName() + "**"
                        + "\nSave par : " + user.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setColor(new Color(27, 224, 21, 1))
                .setTimestamp(new Date().toInstant());

        instance.getConfig().getLogChannel().sendMessageEmbeds(builder.build()).queue();
        TranscriptUtils.sendTranscript(instance.getConfig().getLogChannel(), transcript);
    }

    public void TicketClose(@NotNull Member user, @NotNull Channel channel) {
        builder.setTitle("Log - Demande de fermeture de ticket")
                .setDescription("Ticket : **" + channel.getName() + "**"
                        + "\nPar : " + user.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setColor(new Color(255, 165, 54, 1))
                .setTimestamp(new Date().toInstant());

        instance.getConfig().getLogChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public void TicketAbort(@NotNull Member user, @NotNull Channel channel) {
        builder.setTitle("Log - Annulation de suppression de ticket")
                .setDescription("Ticket : **" + channel.getName() + "**"
                        + "\nPar : " + user.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setColor(new Color(76, 193, 80, 1))
                .setTimestamp(new Date().toInstant());

        instance.getConfig().getLogChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public void TicketAdd(@NotNull Member user, @NotNull Member executor, @NotNull Channel channel) {
        builder.setTitle("Log - Membre ajouté à un ticket")
                .setDescription("Ticket : **" + channel.getName() + "**"
                        + "\nMembre :" + user.getAsMention()
                        + "\nAjouté par : " + executor.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setColor(new Color(76, 193, 80, 1))
                .setTimestamp(new Date().toInstant());

        instance.getConfig().getLogChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public void TicketRemove(@NotNull Member user, @NotNull Member executor, @NotNull Channel channel) {
        builder.setTitle("Log - Membre retiré d'un ticket")
                .setDescription("Ticket : **" + channel.getName() + "**"
                        + "\nMembre :" + user.getAsMention()
                        + "\nRetiré par : " + executor.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setColor(new Color(218, 67, 54, 1))
                .setTimestamp(new Date().toInstant());

        instance.getConfig().getLogChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public void TicketDisable(@NotNull Member executor) {
        builder.setTitle("Log - Création de tickets désactivée")
                .setDescription("Exécuté par : " + executor.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setColor(new Color(192, 0, 7, 1))
                .setTimestamp(new Date().toInstant());

        instance.getConfig().getLogChannel().sendMessageEmbeds(builder.build()).queue();
    }

    public void TicketEnable(@NotNull Member executor) {
        builder.setTitle("Log - Création de tickets activée")
                .setDescription("Exécuté par : " + executor.getAsMention())
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setColor(new Color(27, 224, 21, 1))
                .setTimestamp(new Date().toInstant());

        instance.getConfig().getLogChannel().sendMessageEmbeds(builder.build()).queue();
    }
}