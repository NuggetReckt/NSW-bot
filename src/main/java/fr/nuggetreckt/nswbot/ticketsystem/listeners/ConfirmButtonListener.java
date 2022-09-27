package fr.nuggetreckt.nswbot.ticketsystem.listeners;

import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static fr.nuggetreckt.nswbot.Main.jda;

public class ConfirmButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("confirm")) {
            if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)) {
                event.reply("> Le ticket sera supprimé dans 10 secondes.")
                        .setEphemeral(true)
                        .queue();
                event.getMessage()
                        .delete()
                        .queue();
                event.getGuildChannel()
                        .delete()
                        .queueAfter(10000, TimeUnit.MILLISECONDS);

                EmbedBuilder logs = new EmbedBuilder();

                logs.setTitle("Log - Suppression de ticket")
                        .setDescription("Ticket : " + event.getGuildChannel().getName() + "\nFermé par : " + event.getMember().getAsMention())
                        .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                        .setColor(new Color(218, 67, 54, 1))
                        .setTimestamp(new Date().toInstant());

                Objects.requireNonNull(jda.getTextChannelById(new Config().getLogChannelId())).sendMessageEmbeds(logs.build()).queue();
            } else {
                event.reply("> Vous n'avez pas la permission.")
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
