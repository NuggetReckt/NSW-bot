package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.MessageManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class TicketCommand extends Command {

    private final NSWBot instance;

    public TicketCommand(NSWBot instance) {
        this.instance = instance;
    }

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        Role staffrole = instance.getConfig().getStaffRole();
        Member executor = event.getMember();
        assert executor != null;

        if (event.getChannel().getName().contains("ticket-de-")) {
            if (Objects.requireNonNull(event.getSubcommandName()).equals("close")) {
                EmbedBuilder confirm = new EmbedBuilder();

                confirm.setTitle("Action requise d'un administrateur")
                        .setColor(new Color(61, 189, 201, 1));

                event.replyEmbeds(confirm.build())
                        .addActionRow(
                                Button.success("save", "Sauvegarder"),
                                Button.danger("delete", "Supprimer définitivement"),
                                Button.secondary("abort", "Annuler"))
                        .queue();

                instance.getLogsUtils().TicketClose(Objects.requireNonNull(event.getMember()), event.getChannel());
            } else {
                Member target = Objects.requireNonNull(event.getOption("pseudo")).getAsMember();
                assert target != null;

                if (executor.getRoles().contains(staffrole)) {
                    if (Objects.requireNonNull(event.getSubcommandName()).equals("add")) {
                        if (!target.hasAccess(event.getChannel().asTextChannel())) {
                            event.getChannel().asTextChannel().upsertPermissionOverride(target)
                                    .setAllowed(Permission.VIEW_CHANNEL)
                                    .queue();

                            event.reply(String.format(MessageManager.TICKET_MEMBER_ADDED.getMessage(), target.getAsMention()))
                                    .setEphemeral(true)
                                    .queue();

                            instance.getLogsUtils().TicketAdd(target, executor, event.getChannel());
                        } else {
                            event.reply(MessageManager.TICKET_MEMBER_ALREADY_PRESENT.getMessage())
                                    .setEphemeral(true)
                                    .queue();
                        }
                    }
                    if (Objects.requireNonNull(event.getSubcommandName()).equals("remove")) {
                        if (target.hasAccess(event.getChannel().asTextChannel())) {
                            event.getChannel().asTextChannel().upsertPermissionOverride(target)
                                    .setDenied(Permission.VIEW_CHANNEL)
                                    .queue();

                            event.reply(String.format(MessageManager.TICKET_MEMBER_REMOVED.getMessage(), target.getAsMention()))
                                    .setEphemeral(true)
                                    .queue();

                            instance.getLogsUtils().TicketRemove(target, executor, event.getChannel());
                        } else {
                            event.reply(MessageManager.TICKET_MEMBER_NOT_PRESENT.getMessage())
                                    .setEphemeral(true)
                                    .queue();
                        }
                    }
                } else {
                    event.reply(MessageManager.NO_PERMISSION.getMessage())
                            .setEphemeral(true)
                            .queue();
                }
            }
        } else if (Objects.equals(event.getSubcommandName(), "toggle")) {
            if (executor.hasPermission(Permission.ADMINISTRATOR)) {
                if (Objects.requireNonNull(event.getOption("switch")).getAsString().equals("ON")) {
                    if (instance.getCanCreateTicket()) {
                        event.reply(MessageManager.OPTION_ALREADY_ACTIVATED.getMessage())
                                .setEphemeral(true)
                                .queue();
                    } else {
                        event.reply(MessageManager.TICKET_CREATION_ENABLED.getMessage())
                                .setEphemeral(true)
                                .queue();

                        instance.setCanCreateTicket(true);
                        instance.getLogsUtils().TicketEnable(executor);
                    }
                }
                if (Objects.requireNonNull(event.getOption("switch")).getAsString().equals("OFF")) {
                    if (!instance.getCanCreateTicket()) {
                        event.reply(MessageManager.OPTION_ALREADY_DEACTIVATED.getMessage())
                                .setEphemeral(true)
                                .queue();
                    } else {
                        event.reply(MessageManager.TICKET_CREATION_DISABLED.getMessage())
                                .setEphemeral(true)
                                .queue();

                        instance.setCanCreateTicket(false);
                        instance.getLogsUtils().TicketDisable(executor);
                    }
                }
            } else {
                event.reply(MessageManager.NO_PERMISSION.getMessage())
                        .setEphemeral(true)
                        .queue();
            }
        } else {
            event.reply(MessageManager.NOT_IN_TICKET.getMessage())
                    .setEphemeral(true)
                    .queue();
        }
    }
}
