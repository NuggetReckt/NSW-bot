package fr.nuggetreckt.nswbot.buttons.impl;

import fr.nuggetreckt.nswbot.buttons.Button;
import fr.nuggetreckt.nswbot.util.Logs;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.util.Objects;

public class AbortButton extends Button {

    @Override
    public void execute(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("abort")) {
            event.getMessage()
                    .delete()
                    .queue();

            new Logs().TicketAbort(Objects.requireNonNull(event.getMember()), event.getChannel());
        }
    }
}
