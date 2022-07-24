package fr.nuggetreckt.nswbot.ticketsystem.listeners;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AbortButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("abort")) {
            event.getMessage()
                    .delete()
                    .queue();
        }
    }
}
