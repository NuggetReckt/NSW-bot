package fr.nuggetreckt.nswbot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public abstract class Command {

    public abstract void execute(SlashCommandInteractionEvent event);

}
