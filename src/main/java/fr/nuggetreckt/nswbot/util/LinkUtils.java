package fr.nuggetreckt.nswbot.util;

import fr.nuggetreckt.nswbot.NSWBot;
import net.dv8tion.jda.api.entities.Member;
import org.jetbrains.annotations.NotNull;

public class LinkUtils {

    public void setLinked(@NotNull Member member, String linkCode) {
        NSWBot.getInstance().getGuild().addRoleToMember(member, NSWBot.getConfig().getLinkRole())
                .queue();
        NSWBot.getRequestsManager().insertDiscordId(member.getIdLong(), linkCode);
        NSWBot.getRequestsManager().setLinked(member.getIdLong());
    }

    public void updateMemberName(@NotNull Member member, String newName) {
        member.modifyNickname(newName)
                .queue();
    }

    public String getPlayerNameByDiscordId(@NotNull Member member) {
        return NSWBot.getRequestsManager().getPlayerNameByDiscordId(member.getIdLong());
    }

    public boolean isLinked(@NotNull Member member) {
        return member.getRoles().contains(NSWBot.getConfig().getLinkRole());
    }
}
