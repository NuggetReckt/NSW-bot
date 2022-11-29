package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.Logs;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Objects;

import static fr.nuggetreckt.nswbot.Main.jda;

public class RemoveCommand extends Command {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (event.getName().equals("remove")) {
            if (event.getChannel().getName().contains("ticket-de-")) {

                Member target = Objects.requireNonNull(event.getOption("pseudo")).getAsMember();
                Member executor = event.getMember();
                Role staffrole = jda.getRoleById(new Config().getStaffRoleId());

                assert target != null;
                assert executor != null;

                if (target.hasAccess(event.getTextChannel())) {
                    if (executor.getRoles().contains(staffrole)) {

                        event.getTextChannel().upsertPermissionOverride(target)
                                .setDenied(Permission.VIEW_CHANNEL)
                                .queue();

                        event.reply("> " + target.getAsMention() + " a été retiré du ticket !")
                                .setEphemeral(true)
                                .queue();

                        new Logs().TicketRemove(target, executor, event.getChannel());
                    } else {
                        event.reply("> Vous n'avez pas la permission !")
                                .setEphemeral(true)
                                .queue();
                    }
                } else {
                    event.reply("> Ce membre n'est pas présent dans le ticket !")
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
