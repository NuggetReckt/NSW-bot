package fr.nuggetreckt.nswbot.commands;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.commands.impl.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CommandListener extends ListenerAdapter {

    private final NSWBot instance;

    private final HashMap<String, Command> commands;

    public CommandListener(NSWBot instance) {
        this.instance = instance;
        commands = new HashMap<>();

        setupCommands();
    }

    private void setupCommands() {
        commands.put("dynmap", new DynmapCommand(instance));
        commands.put("ip", new IpCommand(instance));
        commands.put("règles", new ReglesCommand(instance));
        commands.put("shop", new ShopCommand(instance));
        commands.put("site", new SiteCommand(instance));
        commands.put("suggestion", new SuggestionCommand(instance));
        commands.put("vote", new VoteCommand(instance));
        commands.put("ticket", new TicketCommand(instance));
        commands.put("info", new InfoCommand(instance));
        commands.put("link", new LinkCommand(instance));
        commands.put("profile", new ProfileCommand(instance));
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        for (String command : commands.keySet()) {
            if (event.getName().equalsIgnoreCase(command)) {
                commands.get(command).execute(event);
            }
        }
    }
}
