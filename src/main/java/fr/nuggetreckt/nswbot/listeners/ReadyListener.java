package fr.nuggetreckt.nswbot.listeners;

import fr.nuggetreckt.nswbot.Main;
import fr.nuggetreckt.nswbot.TicketMain;
import fr.nuggetreckt.nswbot.tasks.BotStatus;
import fr.nuggetreckt.nswbot.tasks.MessagesSender;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class ReadyListener implements EventListener {
    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent) {
            System.out.println(Main.jda.getSelfUser().getName() + " lancé avec succès. " + Main.jda.getEventManager().getRegisteredListeners().size() + " Listeners chargés.");
            System.out.println("""
                      _   _  _______          ___       ____        _  \s
                     | \\ | |/ ____\\ \\        / ( )     |  _ \\      | | \s
                     |  \\| | (___  \\ \\  /\\  / /|/ ___  | |_) | ___ | |_\s
                     | . ` |\\___ \\  \\ \\/  \\/ /   / __| |  _ < / _ \\| __|
                     | |\\  |____) |  \\  /\\  /    \\__ \\ | |_) | (_) | |_\s
                     |_| \\_|_____/    \\/  \\/     |___/ |____/ \\___/ \\__|
                    """);

            new BotStatus();
            new MessagesSender().sendMessages();
            new TicketMain();
        }
    }
}
