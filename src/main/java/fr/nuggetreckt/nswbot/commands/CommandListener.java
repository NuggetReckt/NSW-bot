package fr.nuggetreckt.nswbot.commands;

import fr.nuggetreckt.nswbot.commands.impl.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CommandListener extends ListenerAdapter {

    HashMap<String, Command> commands = new HashMap<>();

    public CommandListener(JDA jda) {
        jda.addEventListener(this);

        commands.put("dynmap", new DynmapCommand());
        commands.put("ip", new IpCommand());
        commands.put("règles", new ReglesCommand());
        commands.put("shop", new ShopCommand());
        commands.put("site", new SiteCommand());
        commands.put("suggestion", new SuggestionCommand());
        commands.put("vote", new VoteCommand());
        commands.put("add", new AddCommand());
        commands.put("remove", new RemoveCommand());
        commands.put("close", new CloseCommand());
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