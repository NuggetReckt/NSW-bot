package fr.nuggetreckt.nswbot.buttons;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.buttons.impl.*;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ButtonListener extends ListenerAdapter {

    private final NSWBot instance;

    private final HashMap<String, Buttons> buttons;

    public ButtonListener(NSWBot instance) {
        this.instance = instance;
        buttons = new HashMap<>();

        setupButtons();
    }

    private void setupButtons() {
        buttons.put("abort", new AbortButton(instance));
        buttons.put("close", new CloseButton(instance));
        buttons.put("save", new SaveButton(instance));
        buttons.put("delete", new DeleteButton(instance));
        buttons.put("create", new CreateButton(instance));
        buttons.put("namechange_yes", new NameChangeButton(instance));
        buttons.put("namechange_no", new NameChangeButton(instance));
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
