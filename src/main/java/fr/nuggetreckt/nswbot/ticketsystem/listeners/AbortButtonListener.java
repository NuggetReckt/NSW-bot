package fr.nuggetreckt.nswbot.ticketsystem.listeners;

import fr.nuggetreckt.nswbot.ticketsystem.TicketLogs;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class AbortButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("abort")) {
            event.getMessage()
                    .delete()
                    .queue();

            new TicketLogs().TicketAbort(Objects.requireNonNull(event.getMember()), event.getChannel());
        }
    }
}
