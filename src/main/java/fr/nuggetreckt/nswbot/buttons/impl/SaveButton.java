package fr.nuggetreckt.nswbot.buttons.impl;

import fr.nuggetreckt.nswbot.buttons.Button;
import fr.nuggetreckt.nswbot.util.FileUtils;
import fr.nuggetreckt.nswbot.util.Logs;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class SaveButton extends Button {

    @Override
    public void execute(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("save")) {
            if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)) {
                EmbedBuilder confirm = new EmbedBuilder();

                event.getMessage()
                        .delete()
                        .queue();

                new FileUtils().saveTranscript(event.getChannel().asTextChannel());

                confirm.setTitle("Transcript sauvé avec succès. Supprimer définitivement le ticket ?")
                        .setColor(new Color(61, 189, 201, 1));

                event.replyEmbeds(confirm.build())
                        .addActionRow(
                                net.dv8tion.jda.api.interactions.components.buttons.Button.danger("delete", "Oui"),
                                net.dv8tion.jda.api.interactions.components.buttons.Button.success("abort", "Non"))
                        .queue();

                new Logs().TicketSave(event.getMember(), event.getChannel());
            } else {
                event.reply("> Vous n'avez pas la permission.")
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
