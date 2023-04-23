package fr.nuggetreckt.nswbot.buttons.impl;

import fr.nuggetreckt.nswbot.buttons.Buttons;
import fr.nuggetreckt.nswbot.util.FileUtils;
import fr.nuggetreckt.nswbot.util.Logs;
import fr.nuggetreckt.nswbot.util.MessageManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class SaveButton extends Buttons {

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
                                Button.danger("delete", "Oui"),
                                Button.success("abort", "Non"))
                        .queue();

                new Logs().TicketSave(event.getMember(), event.getChannel());
            } else {
                event.reply(MessageManager.NO_PERMISSION_MESSAGE.getMessage())
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
