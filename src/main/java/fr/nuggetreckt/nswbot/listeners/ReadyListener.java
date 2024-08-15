package fr.nuggetreckt.nswbot.listeners;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.tasks.BotStatus;
import fr.nuggetreckt.nswbot.tasks.MessagesSender;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class ReadyListener implements EventListener {

    private final NSWBot instance;

    public ReadyListener(NSWBot instance) {
        this.instance = instance;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {

        if (event instanceof ReadyEvent) {
            instance.getLogger().info(instance.getJDA().getSelfUser().getName() + " v" + instance.getVersion() + " lancé avec succès.");
            instance.getLogger().info(instance.getJDA().getEventManager().getRegisteredListeners().size() + " listeners chargés.");
            System.out.println("""
                     _   _  _______          ___       ____        _  \s
                    | \\ | |/ ____\\ \\        / ( )     |  _ \\      | | \s
                    |  \\| | (___  \\ \\  /\\  / /|/ ___  | |_) | ___ | |_\s
                    | . ` |\\___ \\  \\ \\/  \\/ /   / __| |  _ < / _ \\| __|
                    | |\\  |____) |  \\  /\\  /    \\__ \\ | |_) | (_) | |_\s
                    |_| \\_|_____/    \\/  \\/     |___/ |____/ \\___/ \\__|""");

            new BotStatus(instance, false);
            new MessagesSender(instance).sendEmbeds();
        }
    }
}
