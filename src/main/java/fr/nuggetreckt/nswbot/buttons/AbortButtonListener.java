package fr.nuggetreckt.nswbot.buttons;

import fr.nuggetreckt.nswbot.util.Logs;
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

            new Logs().TicketAbort(Objects.requireNonNull(event.getMember()), event.getChannel());
        }
    }
}
