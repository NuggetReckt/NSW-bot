package fr.nuggetreckt.nswbot.buttons.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.buttons.Buttons;
import fr.nuggetreckt.nswbot.util.MessageManager;
import fr.nuggetreckt.nswbot.util.TranscriptUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class SaveButton extends Buttons {

    private final NSWBot instance;

    public SaveButton(NSWBot instance) {
        this.instance = instance;
    }

    @Override
    public void execute(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("save")) {
            if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)) {
                EmbedBuilder confirm = new EmbedBuilder();
                TextChannel channel = event.getChannel().asTextChannel();
                FileUpload transcriptFile = TranscriptUtils.getTranscript(channel);

                event.getMessage()
                        .delete()
                        .queue();

                TranscriptUtils.sendTranscript(channel, transcriptFile);

                confirm.setTitle("Transcript sauvegardé avec succès. Supprimer définitivement le ticket ?")
                        .setColor(new Color(61, 189, 201, 1));

                event.replyEmbeds(confirm.build())
                        .addActionRow(
                                Button.danger("delete", "Oui"),
                                Button.success("abort", "Non"))
                        .queue();

                instance.getLogsUtils().TicketSave(event.getMember(), event.getChannel(), transcriptFile);
            } else {
                event.reply(MessageManager.NO_PERMISSION.getMessage())
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
