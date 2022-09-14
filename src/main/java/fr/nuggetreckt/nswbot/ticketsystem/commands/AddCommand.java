package fr.nuggetreckt.nswbot.ticketsystem.commands;

import fr.nuggetreckt.nswbot.Main;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

public class AddCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("add")) {
            if (event.getChannel().getName().contains("ticket-de-")) {

                Member target = Objects.requireNonNull(event.getOption("pseudo")).getAsMember();
                Member executor = event.getMember();
                Role staffrole = Main.jda.getRoleById(new Config().getStaffRoleId());

                assert target != null;
                assert executor != null;

                if (!target.hasAccess(event.getTextChannel())) {
                    if (executor.getRoles().contains(staffrole)) {

                        event.getTextChannel().upsertPermissionOverride(target)
                                .setAllowed(Permission.VIEW_CHANNEL)
                                .queue();

                        event.reply("> " + target.getAsMention() + " a été ajouté au ticket !")
                                .setEphemeral(true)
                                .queue();

                        EmbedBuilder logs = new EmbedBuilder();

                        logs.setTitle("Log - Membre ajouté à un ticket")
                                .setDescription("Ticket : " + Objects.requireNonNull(Objects.requireNonNull(event.getOption("pseudo")).getAsMember()).getAsMention()
                                        + "\nAjouté par : " + Objects.requireNonNull(event.getMember()).getAsMention())
                                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                                .setColor(new Color(76, 193, 80, 1))
                                .setTimestamp(new Date().toInstant());

                        Objects.requireNonNull(Objects.requireNonNull(event.getGuild()).getTextChannelById(new Config().getLogChannelId())).sendMessageEmbeds(logs.build())
                                .queue();
                    } else {
                        event.reply("> Vous n'avez pas la permission !")
                                .setEphemeral(true)
                                .queue();
                    }
                } else {
                    event.reply("> Ce membre est déjà présent dans le ticket !")
                            .setEphemeral(true)
                            .queue();
                }
            } else {
                event.reply("> Vous n'êtes pas dans un ticket !")
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
