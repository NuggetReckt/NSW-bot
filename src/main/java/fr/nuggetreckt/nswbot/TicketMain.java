package fr.nuggetreckt.nswbot;

import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.Date;
import java.util.List;

import static fr.nuggetreckt.nswbot.Main.jda;

public class TicketMain {

    public TicketMain() {

        MessageChannel channel = jda.getTextChannelById(new Config().getTicketPanelId());

        if (channel != null) {

            MessageHistory history = MessageHistory.getHistoryFromBeginning(channel).complete();
            List<Message> messages = history.getRetrievedHistory();

            if (messages.size() == 1) return;

            if (messages.size() == 0) {
                EmbedBuilder panel = new EmbedBuilder();

                panel.setTitle("📮 ・ Ouvrir un ticket", "https://play.noskillworld.fr")
                        .setDescription("Pour créer un ticket, cliquez sur le bouton ci-dessous.\nTout abus/troll sera sanctionné.")
                        .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                        .setColor(new Color(61, 189, 201, 1))
                        .setTimestamp(new Date().toInstant());

                channel.sendMessageEmbeds(panel.build())
                        .setActionRow(
                                Button.primary("create", "Créer un ticket"),
                                Button.link("https://play.noskillworld.fr/cgu-cgv", "Informations")
                        )
                        .queue();
            }
        }
    }
}