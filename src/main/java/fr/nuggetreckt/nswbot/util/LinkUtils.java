package fr.nuggetreckt.nswbot.util;

import fr.nuggetreckt.nswbot.NSWBot;
import net.dv8tion.jda.api.entities.Member;
import org.jetbrains.annotations.NotNull;

public class LinkUtils {

    private final NSWBot instance;

    public LinkUtils(NSWBot instance) {
        this.instance = instance;
    }

    public void setLinked(@NotNull Member member, String linkCode) {
        instance.getGuild().addRoleToMember(member, instance.getConfig().getLinkRole())
                .queue();
        instance.getRequestsManager().insertDiscordId(member.getIdLong(), linkCode);
        instance.getRequestsManager().setLinked(member.getIdLong());
    }

    public void updateMemberName(@NotNull Member member, String newName) {
        member.modifyNickname(newName)
                .queue();
    }

    public String getPlayerNameByDiscordId(@NotNull Member member) {
        return instance.getRequestsManager().getPlayerNameByDiscordId(member.getIdLong());
    }

    public String getPlayerUUIDByDiscordId(@NotNull Member member) {
        return instance.getRequestsManager().getPlayerUUIDByDiscordId(member.getIdLong());
    }

    public boolean isLinked(@NotNull Member member) {
        return member.getRoles().contains(instance.getConfig().getLinkRole());
    }
}
