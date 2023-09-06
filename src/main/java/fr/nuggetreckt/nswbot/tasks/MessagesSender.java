package fr.nuggetreckt.nswbot.tasks;

import fr.nuggetreckt.nswbot.NSWBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class MessagesSender {

    final MessageChannel rulesChannel = NSWBot.getConfig().getRulesChannel();
    final MessageChannel supportChannel = NSWBot.getConfig().getSupportChannel();
    final MessageChannel ticketChannel = NSWBot.getConfig().getTicketPanel();

    public MessagesSender() {
        this.sendRulesMessage();
        this.sendSupportMessage();
        this.sendTicketPanelMessage();
    }

    private void sendRulesMessage() {
        MessageHistory history = MessageHistory.getHistoryFromBeginning(rulesChannel).complete();
        List<Message> messages = history.getRetrievedHistory();

        if (messages.size() == 1) return;

        if (messages.isEmpty()) {
            EmbedBuilder rules = new EmbedBuilder();
            rules.setTitle("✅ ・ Règles", "https://play.noskillworld.fr/cgu-cgv")
                    .setDescription("En lisant ces règles ci-dessous, vous vous engagez à les respecter et à vous exposer à des **sanctions** dans le cas contraire.")
                    .addField("<:info_nsw:864197429729034250> __Sur le Discord__", """
                            🔹Respectez-vous les uns des autres
                            🔹Pas d'insultes
                            🔹Pas de pub
                            🔹Pas de spam
                            🔹Pas de contenu nsfw/raciste/homophobe/...
                            """, true)
                    .addField("<:info_nsw:864197429729034250> __En jeu__", """
                            Retrouvez les règles en jeu complètes ici : https://play.noskillworld.fr/règles
                                                        
                            🔹Vous êtes témoin d'un tp kill, cheat, insultes, ou grief ? Créez un ticket.
                            """ + NSWBot.getConfig().getTicketPanel().getAsMention(), true)
                    .setColor(new Color(61, 189, 201, 1))
                    .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                    .setTimestamp(new Date().toInstant());

            rulesChannel.sendMessageEmbeds(rules.build()).queue();
        }
    }

    private void sendSupportMessage() {
        MessageHistory history = MessageHistory.getHistoryFromBeginning(supportChannel).complete();
        List<Message> messages = history.getRetrievedHistory();

        if (messages.size() == 1) return;

        if (messages.isEmpty()) {
            EmbedBuilder support = new EmbedBuilder();
            support.setTitle("🔗 ・ Nous Soutenir", "https://play.noskillworld.fr")
                    .addField("<:info_nsw:864197429729034250> __Introduction__", """
                            Vous aimez le serveur et vous souhaitez nous aider ?
                            Si c'est le cas vous êtes au bon endroit !
                            Deux moyens s'offrent à vous pour nous soutenir :
                            \u00A0\u00A0\u00A0 - A l'aide des sites de référencement pour nous permettre de gagner en visibilité
                            \u00A0\u00A0\u00A0 - En achetant un grade ou en faisant un don
                            Merci à ceux qui le feront, ça aide grandement le serveur et permet de le garder ouvert ! ❤️
                            """, false)
                    .addField("<:info_nsw:864197429729034250> __Sites de référencement__", """
                            🔹**Serveurs-minecraft.org :** https://www.serveurs-minecraft.org/vote.php?id=60934
                            🔹**Serveur-minecraft.com :** https://serveur-minecraft.com/2598
                            🔹**Serveurs-mc.net :** https://serveurs-mc.net/index.php/serveur/251
                            🔹**Top-serveurs.net :** https://top-serveurs.net/minecraft/vote/noskillworld
                            🔹**Serveur-privé.net :** https://serveur-prive.net/minecraft/noskillworld-9550/vote
                            🔹**Serveursminecraft.org :** https://www.serveursminecraft.org/serveur/5870/
                            🔹**Liste-serveurs-minecraft.org :** https://www.liste-serveurs-minecraft.org/serveur-minecraft/noskillworld/
                            """, false)
                    .addField("<:info_nsw:864197429729034250> __Grades/Dons__", """
                            🛒 ・ **Shop :** https://shop.noskillworld.fr
                            💎 ・ **PayPal :** https://paypal.me/noskillworld
                            """, false)
                    .setColor(new Color(61, 189, 201, 1))
                    .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                    .setTimestamp(new Date().toInstant());

            supportChannel.sendMessageEmbeds(support.build()).queue();
        }
    }

    private void sendTicketPanelMessage() {
        MessageHistory history = MessageHistory.getHistoryFromBeginning(ticketChannel).complete();
        List<Message> messages = history.getRetrievedHistory();

        EmbedBuilder panel = new EmbedBuilder();

        panel.setTitle("📮 ・ Ouvrir un ticket", "https://play.noskillworld.fr")
                .setDescription("Pour créer un ticket, cliquez sur le bouton ci-dessous.\nTout abus/troll sera sanctionné.")
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setColor(new Color(61, 189, 201, 1))
                .setTimestamp(new Date().toInstant());

        if (messages.size() == 1) return;

        if (messages.isEmpty()) {
            ticketChannel.sendMessageEmbeds(panel.build())
                    .setActionRow(
                            Button.primary("create", "Créer un ticket"),
                            Button.link("https://play.noskillworld.fr/cgu-cgv", "Informations")
                    )
                    .queue();
        }
    }

    public void disableTicketCreation() {
        MessageHistory history = MessageHistory.getHistoryFromBeginning(ticketChannel).complete();
        List<Message> messages = history.getRetrievedHistory();

        String embed = messages.get(0).getId();
        EmbedBuilder panel = new EmbedBuilder();

        panel.setTitle("📮 ・ Ouvrir un ticket", "https://play.noskillworld.fr")
                .setDescription("La création de ticket est temporairement désactivée.\nNous revenons bientôt.")
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setColor(new Color(61, 189, 201, 1))
                .setTimestamp(new Date().toInstant());

        ticketChannel.editMessageEmbedsById(embed, panel.build()).setActionRow(
                Button.primary("create", "Créer un ticket").asDisabled(),
                Button.link("https://play.noskillworld.fr/cgu-cgv", "Informations")
        ).queue();
    }

    public void enableTicketCreation() {
        MessageHistory history = MessageHistory.getHistoryFromBeginning(ticketChannel).complete();
        List<Message> messages = history.getRetrievedHistory();

        String embed = messages.get(0).getId();
        EmbedBuilder panel = new EmbedBuilder();

        panel.setTitle("📮 ・ Ouvrir un ticket", "https://play.noskillworld.fr")
                .setDescription("Pour créer un ticket, cliquez sur le bouton ci-dessous.\nTout abus/troll sera sanctionné.")
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setColor(new Color(61, 189, 201, 1))
                .setTimestamp(new Date().toInstant());

        ticketChannel.editMessageEmbedsById(embed, panel.build()).setActionRow(
                Button.primary("create", "Créer un ticket").asEnabled(),
                Button.link("https://play.noskillworld.fr/cgu-cgv", "Informations")
        ).queue();
    }
}