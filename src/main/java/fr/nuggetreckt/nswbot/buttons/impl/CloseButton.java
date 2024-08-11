package fr.nuggetreckt.nswbot.buttons.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.buttons.Buttons;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class CloseButton extends Buttons {

    private final NSWBot instance;

    public CloseButton(NSWBot instance) {
        this.instance = instance;
    }

    @Override
    public void execute(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("close")) {
            EmbedBuilder confirm = new EmbedBuilder();

            confirm.setTitle("Action requise d'un administrateur")
                    .setColor(new Color(61, 189, 201, 1));

            event.replyEmbeds(confirm.build())
                    .addActionRow(
                            Button.success("save", "Sauvegarder"),
                            Button.danger("delete", "Supprimer définitivement"),
                            Button.secondary("abort", "Annuler"))
                    .queue();

            instance.getLogsUtils().TicketClose(Objects.requireNonNull(event.getMember()), event.getChannel());
        }
    }
}
