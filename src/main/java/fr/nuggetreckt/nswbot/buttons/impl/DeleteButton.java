package fr.nuggetreckt.nswbot.buttons.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.buttons.Buttons;
import fr.nuggetreckt.nswbot.util.MessageManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DeleteButton extends Buttons {

    private final NSWBot instance;

    public DeleteButton(NSWBot instance) {
        this.instance = instance;
    }

    @Override
    public void execute(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("delete")) {
            if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)) {
                event.reply(MessageManager.TICKET_DELETE_TIME.getMessage())
                        .setEphemeral(true)
                        .queue();
                event.getMessage()
                        .delete()
                        .queue();
                event.getGuildChannel()
                        .delete()
                        .queueAfter(10000, TimeUnit.MILLISECONDS);

                instance.getLogsUtils().TicketDelete(event.getMember(), event.getChannel());
            } else {
                event.reply(MessageManager.NO_PERMISSION.getMessage())
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
