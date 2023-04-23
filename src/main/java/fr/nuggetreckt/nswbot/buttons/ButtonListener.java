package fr.nuggetreckt.nswbot.buttons;

import fr.nuggetreckt.nswbot.buttons.impl.*;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ButtonListener extends ListenerAdapter {
    HashMap<String, Buttons> buttons = new HashMap<>();

    public ButtonListener() {
        buttons.put("abort", new AbortButton());
        buttons.put("close", new CloseButton());
        buttons.put("save", new SaveButton());
        buttons.put("delete", new DeleteButton());
        buttons.put("create", new CreateButton());
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        for (String button : buttons.keySet()) {
            if (event.getComponentId().equalsIgnoreCase(button)) {
                buttons.get(button).execute(event);
            }
        }
    }
}
