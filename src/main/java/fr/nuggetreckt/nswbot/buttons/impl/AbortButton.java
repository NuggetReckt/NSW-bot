package fr.nuggetreckt.nswbot.buttons.impl;

import fr.nuggetreckt.nswbot.buttons.Buttons;
import fr.nuggetreckt.nswbot.util.LogsUtils;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AbortButton extends Buttons {

    @Override
    public void execute(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("abort")) {
            event.getMessage()
                    .delete()
                    .queue();

            new LogsUtils().TicketAbort(Objects.requireNonNull(event.getMember()), event.getChannel());
        }
    }
}
