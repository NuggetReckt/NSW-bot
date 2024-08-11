package fr.nuggetreckt.nswbot.listeners;

import fr.nuggetreckt.nswbot.NSWBot;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class ShutdownListener implements EventListener {

    private final NSWBot instance;

    public ShutdownListener(NSWBot instance) {
        this.instance = instance;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ShutdownEvent) {
            instance.getLogger().info("Extinction du bot...");
            instance.getConnector().close();
        }
    }
}
