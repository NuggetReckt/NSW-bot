package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.MessageManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;

public class InfoCommand extends Command {

    private final NSWBot instance;
    private boolean isOnline;

    public InfoCommand(NSWBot instance) {
        this.instance = instance;
    }

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        TextChannel botChannel = instance.getConfig().getBotChannel();
        isOnline = instance.getPinger().fetchData();

        if (event.getName().equals("info")) {
            if (event.getChannel().equals(botChannel)) {
                builder.setTitle("<:info_nsw:864197429729034250> ・ Infos")
                        .addField("__Stats__", getPlayers() + "\n" + getStatus(), true)
                        .addField("__Serveur__", "\uD83C\uDFAE ・ play.noskillworld.fr\n" + getVersion(), true)
                        .addField("__Message dans la liste des serveurs__", getMotd(), false)
                        .setColor(new Color(61, 189, 201, 1))
                        .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                        .setTimestamp(new Date().toInstant());

                event.replyEmbeds(builder.build()).queue();
            } else {
                event.reply(String.format(MessageManager.BAD_CHANNEL.getMessage(), botChannel.getAsMention()))
                        .setEphemeral(true)
                        .queue();
            }
        }
    }

    @NotNull
    private String getStatus() {
        if (isOnline) {
            return "<a:online:999970001966608446> ・ Serveur en ligne";
        } else {
            return "<a:offline:999980343580962836> ・ Serveur hors ligne";
        }
    }

    @NotNull
    private String getMotd() {
        String version = instance.getPinger().getGameVersion();
        String motd;

        if (version.equals("§4§lServeur en Maintenance !")) {
            motd = """
                    ```ansi
                    [2;34m[2;36m [0m[2;34m[2;36m[1;36mNoSkillWorld[0m[2;36m[0m[2;34m[0m [2;30m|[0m [2;34mSaison 3[0m [2;30m[[0m[2;31mMaintenance[0m[2;30m][0m
                    [2;37mSemi-RP[0m [2;30m|[0m [2;37mCréatif[0m [2;30m|[0m [2;37mHardCore[0m [2;32mSaison 1 ![0m[2;36m[2;36m
                    [0m[2;36m[0m
                    ```
                    """;
        } else {
            motd = String.format("""
                    ```ansi
                       [2;36m[1;36mNoSkillWorld[0m[2;36m [0m[2;30m|[0m [2;34mSaison 3[0m[0;2m[0;2m[0;2m[0;2m [0;30m[[0m[0m[0m[0m[0m[2;40m[2;40m[2;42m[2;47m[2;44m[2;46m[2;42m[0;2m[0m[2;42m[0m[2;46m[0m[2;44m[0m[2;47m[0m[2;42m[0m[2;40m[0m[2;40m[0m[2;31m%s[0m[2;30m][0m
                    [2;37m[2;37mSemi-RP[0m[2;37m[0m [2;30m|[0m [2;37mCréatif [0m[2;30m|[0m [2;37mHardCore [0m[2;32mSaison 1 ![0m
                    ```
                    """, version.replaceAll(" ", ""));
        }
        return motd;
    }

    @NotNull
    private String getPlayers() {
        int playersOnline = instance.getPinger().getPlayersOnline();
        int maxPlayerSlots = instance.getPinger().getMaxPlayers();

        return "\uD83D\uDC65 ・ " + playersOnline + "/" + maxPlayerSlots + " Joueurs connectés";
    }

    @NotNull
    private String getVersion() {
        String version = instance.getPinger().getGameVersion();
        String versionFormatted;

        if (version.equals("§4§lServeur en Maintenance !")) {
            versionFormatted = "\uD83D\uDCBB ・ Maintenance !";
        } else {
            versionFormatted = String.format("\uD83D\uDCBB ・ %s", version);
        }
        return versionFormatted;
    }
}