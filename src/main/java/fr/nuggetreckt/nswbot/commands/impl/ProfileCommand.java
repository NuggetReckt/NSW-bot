package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.MessageManager;
import fr.nuggetreckt.nswbot.util.ProfileUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

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
        UUID playerUUID = UUID.fromString(NSWBot.getLinkUtils().getPlayerUUIDByDiscordId(member));

        EmbedBuilder profileEmbed = new EmbedBuilder();
        ProfileUtils profile = NSWBot.getProfileUtils();

        profile.setUUID(playerUUID);

        profileEmbed.setTitle("🪪 ・ Profil (" + playerName + ")")
                .setThumbnail("https://crafatar.com/avatars/" + playerUUID + "?overlay")
                .addField("📈 __Stats :__", String.format("""
                                ・ Dernière connexion : `%s`
                                ・ Temps joué : `%s`
                                ・ Kills : `%d`
                                ・ Morts : `%d`
                                ・ ...""",
                        profile.getLastLogin(), profile.getTimePlayed(),
                        profile.getKillCount(), profile.getDeathCount()), false)
                .addField("⛏️ __Jobs :__", String.format("""
                                ・ Actuel : `%s`
                                ・ Level : `%d` (`%.2f`/`%.2f`)
                                ・ Top Job : `%s` (`%d`)""",
                        profile.getCurrentJob(), profile.getCurrentJobLevel(),
                        profile.getXP(), profile.getCurrentXPNeeded(),
                        profile.getTopJob(), profile.getTopJobLevel()), false)
                .addField("💎 __Rangs d'Honneur :__", String.format("""
                                ・ Rang : `%d`
                                ・ Points d'Honneur : `%d`
                                ・ Prochain Rang : `%d` (%s)""",
                        profile.getCurrentRank(), profile.getHonorPoints(),
                        profile.getNextRank(), profile.getHonorPointsNeeded()), false)
                .setColor(new Color(61, 189, 201, 1))
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setTimestamp(new Date().toInstant());
        return profileEmbed;
    }
}
