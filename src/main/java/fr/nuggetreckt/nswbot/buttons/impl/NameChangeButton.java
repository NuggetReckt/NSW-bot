package fr.nuggetreckt.nswbot.buttons.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.buttons.Buttons;
import fr.nuggetreckt.nswbot.util.MessageManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NameChangeButton extends Buttons {

    @Override
    public void execute(@NotNull ButtonInteractionEvent event) {

        Member member = event.getMember();
        Role staffrole = NSWBot.getConfig().getStaffRole();

        if (event.getComponentId().equals("namechange_yes")) {
            if (!Objects.requireNonNull(member).getRoles().contains(staffrole)) {
                String newName = NSWBot.getLinkUtils().getPlayerNameByDiscordId(member);
                NSWBot.getLinkUtils().updateMemberName(member, newName);

                event.reply(String.format(MessageManager.MEMBER_NAME_CHANGED.getMessage()))
                        .setEphemeral(true)
                        .queue();
            } else {
                event.reply(String.format(MessageManager.CANNOT_CHANGE_NAME.getMessage()))
                        .setEphemeral(true)
                        .queue();
            }
        }
        if (event.getComponentId().equals("namechange_no")) {
            event.reply(String.format(MessageManager.MEMBER_NAMECHANGE_CANCELLED.getMessage()))
                    .setEphemeral(true)
                    .queue();
        }
    }
}
