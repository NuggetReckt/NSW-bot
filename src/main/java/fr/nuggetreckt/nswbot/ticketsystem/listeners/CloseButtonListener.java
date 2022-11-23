package fr.nuggetreckt.nswbot.ticketsystem.listeners;

import fr.nuggetreckt.nswbot.ticketsystem.TicketLogs;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

import static fr.nuggetreckt.nswbot.Main.jda;

public class CloseButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("close")) {
            EmbedBuilder confirm = new EmbedBuilder();

            confirm.setTitle("Confirmation requise d'un administrateur")
                    .setColor(new Color(61, 189, 201, 1));

            event.replyEmbeds(confirm.build())
                    .addActionRow(
                            Button.danger("confirm", "Supprimer définitivement."),
                            Button.secondary("abort", "Annuler"))
                    .queue();

            new TicketLogs().TicketClose(event.getMember(), event.getChannel());
        }
    }
}
