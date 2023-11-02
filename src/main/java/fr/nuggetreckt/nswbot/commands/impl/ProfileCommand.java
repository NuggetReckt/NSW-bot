package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.MessageManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

public class ProfileCommand extends Command {

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {

        TextChannel botChannel = NSWBot.getConfig().getBotChannel();

        if (event.getName().equals("profile")) {
            if (event.getChannel().equals(botChannel)) {
                if (event.getOption("name") == null) {
                    Member member = Objects.requireNonNull(event.getMember());

                    if (NSWBot.getLinkUtils().isLinked(member)) {
                        event.replyEmbeds(getProfileEmbed(member).build())
                                .queue();
                    } else {
                        event.reply(String.format(MessageManager.NOT_LINKED.getMessage()))
                                .setEphemeral(true)
                                .queue();
                    }
                } else {
                    Member target = Objects.requireNonNull(event.getOption("name")).getAsMember();

                    if (NSWBot.getLinkUtils().isLinked(Objects.requireNonNull(target))) {
                        event.replyEmbeds(getProfileEmbed(target).build());
                    } else {
                        event.reply(String.format(MessageManager.NOT_LINKED_OHTER.getMessage()))
                                .setEphemeral(true)
                                .queue();
                    }
                }
            } else {
                event.reply(String.format(MessageManager.BAD_CHANNEL.getMessage(), botChannel.getAsMention()))
                        .setEphemeral(true)
                        .queue();
            }
        }
    }

    @NotNull
    private EmbedBuilder getProfileEmbed(Member member) {
        String playerName = NSWBot.getLinkUtils().getPlayerNameByDiscordId(member);
        String playerUUID = NSWBot.getLinkUtils().getPlayerUUIDByDiscordId(member);

        EmbedBuilder profileEmbed = new EmbedBuilder();

        profileEmbed.setTitle("🪪 ・ Profil (" + playerName + ")")
                .setThumbnail("https://crafatar.com/avatars/" + playerUUID + "?overlay")
                .addField("📈 __Stats :__", """
                        ・ Dernière connexion : `<date>`
                        ・ Temps joué : `<temps>`
                        ・ Kills : `<kills>`
                        ・ Morts : `<morts>`
                        ・ ...""", false)
                .addField("⛏️ __Jobs :__", """
                        ・ Actuel : `<job_actuel>`
                        ・ Level : `<lvl>` (`<xp actuel>`/`<xp_requis>`)
                        ・ Top Job : `<job>` (`<lvl>`)""", false)
                .addField("💎 __Rangs d'Honneur :__", """
                        ・ Rang : `<rang actuel>`
                        ・ Points d'Honneur : `<points>`
                        ・ Prochain Rang : `<rang>` (`<points_requis>` points requis)""", false)
                .setColor(new Color(61, 189, 201, 1))
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setTimestamp(new Date().toInstant());
        return profileEmbed;
    }
}
