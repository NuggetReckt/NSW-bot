package fr.nuggetreckt.nswbot.buttons.impl;

import fr.nuggetreckt.nswbot.Main;
import fr.nuggetreckt.nswbot.util.Config;
import fr.nuggetreckt.nswbot.util.Logs;
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

public class CreateButton extends fr.nuggetreckt.nswbot.buttons.Button {

    public String member;
    public String channelname;

    @Override
    public void execute(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("create")) {

            member = ((Objects.requireNonNull(event.getMember())).getEffectiveName());

            String memberFormatted = member.replaceAll("\\W+", "");

            channelname = "ticket-de-" + memberFormatted;

            if (event.getGuild() != null && Main.jda.getTextChannelsByName(channelname, true).size() == 0) {
                TextChannel channel = Objects.requireNonNull(event.getGuild()).createTextChannel(channelname, event.getGuild().getCategoryById(new Config().getTicketCategoryId()))
                        .addPermissionOverride((event.getMember()), EnumSet.of(Permission.VIEW_CHANNEL), null)
                        .addPermissionOverride(Objects.requireNonNull(Main.jda.getRoleById(new Config().getStaffRoleId())), EnumSet.of(Permission.VIEW_CHANNEL), null)
                        .addPermissionOverride(event.getGuild().getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                        .complete();

                event.reply("> Ticket Créé avec succès ! " + channel.getAsMention())
                        .setEphemeral(true)
                        .queue();

                new Logs().TicketCreate(event.getMember(), channel);

                EmbedBuilder welcome = new EmbedBuilder();

                welcome.setTitle("Bienvenue sur votre ticket, " + member + " 👋 !")
                        .setDescription("Un membre de l'équipe se chargera de celui-ci dans les plus brefs délais." +
                                "\nSi vous n'avez pas de réponse d'ici 24h, merci de contacter un administrateur.")
                        .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                        .setColor(new Color(61, 189, 201, 1))
                        .setTimestamp(new Date().toInstant());

                channel.getGuild().getTextChannelsByName(channelname, true).get(0).sendMessageEmbeds(welcome.build())
                        .setActionRow(
                                Button.primary("close", "Fermer le ticket")
                        )
                        .queue();
            } else {
                event.reply("> Vous avez déjà créé un ticket !")
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}