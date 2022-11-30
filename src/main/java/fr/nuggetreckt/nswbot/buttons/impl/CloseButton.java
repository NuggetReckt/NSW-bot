package fr.nuggetreckt.nswbot.buttons.impl;

import fr.nuggetreckt.nswbot.util.Logs;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class CloseButton extends fr.nuggetreckt.nswbot.buttons.Button {

    @Override
    public void execute(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("close")) {
            EmbedBuilder confirm = new EmbedBuilder();

            confirm.setTitle("Confirmation requise d'un administrateur")
                    .setColor(new Color(61, 189, 201, 1));

            event.replyEmbeds(confirm.build())
                    .addActionRow(
                            Button.danger("confirm", "Supprimer définitivement."),
                            Button.secondary("abort", "Annuler"))
                    .queue();

            new Logs().TicketClose(Objects.requireNonNull(event.getMember()), event.getChannel());
        }
    }
}
