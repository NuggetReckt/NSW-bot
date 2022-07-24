package fr.nuggetreckt.nswbot.ticketsystem.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;

public class CloseCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("close")) {
            if (event.getChannel().getName().contains("ticket-de-")) {
                EmbedBuilder confirm = new EmbedBuilder();

                confirm.setTitle("Confirmation requise d'un administrateur");
                confirm.setColor(new Color(61, 189, 201, 1));

                event.replyEmbeds(confirm.build())
                        .addActionRow(
                                Button.danger("confirm", "Supprimer définitivement."),
                                Button.secondary("abort", "Annuler"))
                        .queue();
            } else {
                event.reply("Vous n'êtes pas dans un ticket !")
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
