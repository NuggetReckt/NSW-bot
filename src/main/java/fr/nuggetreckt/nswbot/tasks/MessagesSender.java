package fr.nuggetreckt.nswbot.tasks;

import fr.nuggetreckt.nswbot.Main;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MessagesSender {

    final String rulesChannelid = new Config().getRulesChannelId();
    MessageChannel rulesChannel = Main.jda.getTextChannelById(rulesChannelid);

    final String supportChannelid = new Config().getSupportChannelId();
    MessageChannel supportChannel = Main.jda.getTextChannelById(supportChannelid);

    public void sendMessages() {
        this.sendRulesMessage();
        this.sendSupportMessage();
    }

    private void sendRulesMessage() {
        MessageHistory history = MessageHistory.getHistoryFromBeginning(rulesChannel).complete();
        List<Message> messages = history.getRetrievedHistory();

        if (messages.size() == 1) {
            return;
        }
        if (messages.size() == 0) {
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
                            """ + Objects.requireNonNull(Main.jda.getTextChannelById(new Config().getTicketPanelId())).getAsMention(), true)
                    .setColor(new Color(61, 189, 201, 1))
                    .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                    .setTimestamp(new Date().toInstant());


            Objects.requireNonNull(Main.jda.getTextChannelById(rulesChannelid)).sendMessageEmbeds(rules.build()).queue();
        }
    }

    private void sendSupportMessage() {
        MessageHistory history = MessageHistory.getHistoryFromBeginning(supportChannel).complete();
        List<Message> messages = history.getRetrievedHistory();

        if (messages.size() == 1) {
            return;
        }
        if (messages.size() == 0) {
            EmbedBuilder support = new EmbedBuilder();
            support.setTitle("🔗 ・ Nous Supporter", "https://play.noskillworld.fr/votes")
                    .addField("<:info_nsw:864197429729034250> __Introduction__", """
                            Si vous aimez le serveur et que vous souhaitez nous aider,
                            vous pouvez voter pour NoSkillWorld sur les différents sites de référencement de serveurs minecraft !
                            Cela nous aide beaucoup et permet au serveur de gagner en visibilité, merci à ceux qui le feront !
                            """, false)
                    .addField("<:info_nsw:864197429729034250> __Liens__", """
                            🔹**Serveurs-minecraft.org :** https://www.serveurs-minecraft.org/vote.php?id=60934
                            🔹**Serveur-minecraft.com :** https://serveur-minecraft.com/2598
                            🔹**Serveurs-mc.net :** https://serveurs-mc.net/index.php/serveur/251
                            🔹**Top-serveurs.net :** https://top-serveurs.net/minecraft/vote/noskillworld
                            🔹**Serveur-privé.net :** https://serveur-prive.net/minecraft/noskillworld-9550/vote
                            🔹**Serveursminecraft.org :** https://www.serveursminecraft.org/serveur/5870/
                            🔹**Liste-serveurs-minecraft.org :** https://www.liste-serveurs-minecraft.org/serveur-minecraft/noskillworld/
                            """, false)
                    .setColor(new Color(61, 189, 201, 1))
                    .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                    .setTimestamp(new Date().toInstant());

            Objects.requireNonNull(Main.jda.getTextChannelById(supportChannelid)).sendMessageEmbeds(support.build()).queue();
        }
    }
}
