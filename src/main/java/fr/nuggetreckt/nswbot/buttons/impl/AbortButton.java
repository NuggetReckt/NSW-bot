package fr.nuggetreckt.nswbot.buttons.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.buttons.Buttons;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AbortButton extends Buttons {

    private final NSWBot instance;

    public AbortButton(NSWBot instance) {
        this.instance = instance;
    }

    @Override
    public void execute(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("abort")) {
            event.getMessage()
                    .delete()
                    .queue();

            instance.getLogsUtils().TicketAbort(Objects.requireNonNull(event.getMember()), event.getChannel());
        }
    }
}
