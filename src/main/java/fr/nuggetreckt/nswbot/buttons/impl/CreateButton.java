package fr.nuggetreckt.nswbot.buttons.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.buttons.Buttons;
import fr.nuggetreckt.nswbot.util.MessageManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.EnumSet;
import java.util.Objects;

public class CreateButton extends Buttons {

    private final NSWBot instance;

    public CreateButton(NSWBot instance) {
        this.instance = instance;
    }

    @Override
    public void execute(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("create")) {
            String member = ((Objects.requireNonNull(event.getMember())).getEffectiveName());
            String memberFormatted = member.replaceAll("\\W+", "");
            String channelName = "ticket-de-" + memberFormatted;

            if (event.getGuild() != null && instance.getJDA().getTextChannelsByName(channelName, true).isEmpty()) {
                TextChannel channel = event.getGuild().createTextChannel(channelName, instance.getConfig().getTicketCategory())
                        .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                        .addPermissionOverride(instance.getConfig().getStaffRole(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                        .addPermissionOverride(event.getGuild().getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                        .complete();

                event.reply(String.format(MessageManager.TICKET_CREATED.getMessage(), channel.getAsMention()))
                        .setEphemeral(true)
                        .queue();

                instance.getLogsUtils().TicketCreate(event.getMember(), channel);

                EmbedBuilder welcome = new EmbedBuilder();

                welcome.setTitle("Bienvenue sur votre ticket, " + member + " 👋 !")
                        .setDescription("Un membre de l'équipe se chargera de celui-ci dans les plus brefs délais." +
                                "\nSi vous n'avez pas de réponse d'ici 24h, merci de contacter un administrateur.")
                        .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                        .setColor(new Color(61, 189, 201, 1))
                        .setTimestamp(new Date().toInstant());

                channel.getGuild().getTextChannelsByName(channelName, true).get(0).sendMessageEmbeds(welcome.build())
                        .setActionRow(
                                Button.primary("close", "Fermer le ticket")
                        )
                        .queue();
            } else {
                event.reply(MessageManager.TICKET_ALREADY_CREATED.getMessage())
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}