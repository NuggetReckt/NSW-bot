package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.Main;
import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

import static fr.nuggetreckt.nswbot.Main.pinger;

public class InfoCommand extends Command {

    String botchannelid = new Config().getBotChannelId();

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {

        EmbedBuilder builder = new EmbedBuilder();

        if (event.getName().equals("info")) {
            if (event.getChannel().getId().equals(botchannelid)) {

                builder.setTitle("<:info_nsw:864197429729034250> ・ Infos")
                        .addField("__Stats__", getPlayers() + "\n" + getStatus(), true)
                        .addField("__Serveur__", "\uD83C\uDFAE ・ play.noskillworld.fr\n" + getVersion(), true)
                        .setColor(new Color(61, 189, 201, 1))
                        .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/static/img/logo_nsw.png")
                        .setTimestamp(new Date().toInstant());

                event.replyEmbeds(builder.build()).queue();
            } else {
                event.reply("Mauvais salon ! Merci d'utiliser le salon " + Objects.requireNonNull(Main.jda.getTextChannelById(botchannelid)).getAsMention())
                        .setEphemeral(true)
                        .queue();
            }
        }
    }

    @NotNull
    private String getStatus() {
        if (pinger.fetchData()) {
            return "<a:online:999970001966608446> ・ Serveur en ligne";
        }
        else {
            return "<a:offline:999980343580962836> ・ Serveur hors ligne";
        }
    }

    @NotNull
    private String getPlayers() {
        return "\uD83D\uDC65 ・ " + pinger.getPlayersOnline() + "/" + pinger.getMaxPlayers() + " Joueurs connectés";
    }

    @NotNull
    private String getVersion() {
        return "\uD83D\uDCBB ・ " + pinger.getGameVersion();
    }
}