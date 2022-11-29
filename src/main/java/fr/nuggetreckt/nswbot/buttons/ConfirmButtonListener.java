package fr.nuggetreckt.nswbot.buttons;

import fr.nuggetreckt.nswbot.util.Logs;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ConfirmButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("confirm")) {
            if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)) {
                event.reply("> Le ticket sera supprimé dans 10 secondes.")
                        .setEphemeral(true)
                        .queue();
                event.getMessage()
                        .delete()
                        .queue();
                event.getGuildChannel()
                        .delete()
                        .queueAfter(10000, TimeUnit.MILLISECONDS);

                new Logs().TicketDelete(event.getMember(), event.getChannel());
            } else {
                event.reply("> Vous n'avez pas la permission.")
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}