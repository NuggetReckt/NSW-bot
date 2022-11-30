package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.Config;
import fr.nuggetreckt.nswbot.util.Logs;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.Objects;

import static fr.nuggetreckt.nswbot.Main.jda;

public class TicketCommand extends Command {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (event.getChannel().getName().contains("ticket-de-")) {
            if (Objects.requireNonNull(event.getSubcommandName()).equals("close")) {
                EmbedBuilder confirm = new EmbedBuilder();
                confirm.setTitle("Confirmation requise d'un administrateur");
                confirm.setColor(new Color(61, 189, 201, 1));

                event.replyEmbeds(confirm.build())
                        .addActionRow(
                                net.dv8tion.jda.api.interactions.components.buttons.Button.danger("confirm", "Supprimer définitivement."),
                                Button.secondary("abort", "Annuler"))
                        .queue();

                new Logs().TicketClose(Objects.requireNonNull(event.getMember()), event.getChannel());
            } else {
                Member target = Objects.requireNonNull(event.getOption("pseudo")).getAsMember();
                Member executor = event.getMember();
                Role staffrole = jda.getRoleById(new Config().getStaffRoleId());

                assert target != null;
                assert executor != null;

                if (executor.getRoles().contains(staffrole)) {
                    if (Objects.requireNonNull(event.getSubcommandName()).equals("add")) {
                        if (!target.hasAccess(event.getTextChannel())) {
                            event.getTextChannel().upsertPermissionOverride(target)
                                    .setAllowed(Permission.VIEW_CHANNEL)
                                    .queue();

                            event.reply("> " + target.getAsMention() + " a été ajouté au ticket !")
                                    .setEphemeral(true)
                                    .queue();

                            new Logs().TicketAdd(target, executor, event.getChannel());
                        } else {
                            event.reply("> Ce membre est déjà présent dans le ticket !")
                                    .setEphemeral(true)
                                    .queue();
                        }
                    }
                    if (Objects.requireNonNull(event.getSubcommandName()).equals("remove")) {
                        if (target.hasAccess(event.getTextChannel())) {
                            event.getTextChannel().upsertPermissionOverride(target)
                                    .setDenied(Permission.VIEW_CHANNEL)
                                    .queue();

                            event.reply("> " + target.getAsMention() + " a été retiré du ticket !")
                                    .setEphemeral(true)
                                    .queue();

                            new Logs().TicketRemove(target, executor, event.getChannel());
                        } else {
                            event.reply("> Ce membre n'est pas présent dans le ticket !")
                                    .setEphemeral(true)
                                    .queue();
                        }
                    }
                } else {
                    event.reply("> Vous n'avez pas la permission !")
                            .setEphemeral(true)
                            .queue();
                }
            }
        } else {
            event.reply("Vous n'êtes pas dans un ticket !")
                    .setEphemeral(true)
                    .queue();
        }
    }
}
