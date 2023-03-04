package fr.nuggetreckt.nswbot.listeners;

import fr.nuggetreckt.nswbot.Main;
import fr.nuggetreckt.nswbot.util.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

public class MemberJoinListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        EmbedBuilder join = new EmbedBuilder();

        Role role = event.getGuild().getRoleById(new Config().getPlayerRoleId());
        Member member = event.getMember();

        join.setTitle("🎉 ・ Oh ! Un nouveau membre !")
                .setDescription("Bienvenue sur le serveur, " + member.getAsMention() + " 👋 !")
                .addField("", "Nous sommes maintenant **" + (long) event.getGuild().getMemberCount() + "** membres.", false)
                .setThumbnail(event.getMember().getEffectiveAvatarUrl())
                .setColor(new Color(61, 189, 201, 1))
                .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                .setTimestamp(new Date().toInstant());

        Objects.requireNonNull(Main.jda.getTextChannelById(new Config().getWelcomeChannelId())).sendMessageEmbeds(join.build())
                .queue();

        event.getGuild().addRoleToMember(member, Objects.requireNonNull(role))
                .queue();
    }
}
