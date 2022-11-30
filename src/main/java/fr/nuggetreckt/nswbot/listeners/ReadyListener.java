package fr.nuggetreckt.nswbot.listeners;

import fr.nuggetreckt.nswbot.tasks.BotStatus;
import fr.nuggetreckt.nswbot.tasks.MessagesSender;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import static fr.nuggetreckt.nswbot.Main.jda;
import static fr.nuggetreckt.nswbot.Main.logger;

public class ReadyListener implements EventListener {

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent) {
            logger.info(jda.getSelfUser().getName() + " lancé avec succès.");
            logger.info(jda.getEventManager().getRegisteredListeners().size() + " listeners chargés.");
            System.out.println("""
                      _   _  _______          ___       ____        _  \s
                     | \\ | |/ ____\\ \\        / ( )     |  _ \\      | | \s
                     |  \\| | (___  \\ \\  /\\  / /|/ ___  | |_) | ___ | |_\s
                     | . ` |\\___ \\  \\ \\/  \\/ /   / __| |  _ < / _ \\| __|
                     | |\\  |____) |  \\  /\\  /    \\__ \\ | |_) | (_) | |_\s
                     |_| \\_|_____/    \\/  \\/     |___/ |____/ \\___/ \\__|""");

            new BotStatus();
            new MessagesSender();
        }
    }
}
