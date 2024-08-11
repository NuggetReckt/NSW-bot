package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.MessageManager;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class ShopCommand extends Command {

    private final NSWBot instance;

    public ShopCommand(NSWBot instance) {
        this.instance = instance;
    }

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {

        TextChannel botChannel = instance.getConfig().getBotChannel();

        if (event.getName().equals("shop")) {
            if (event.getChannel().equals(botChannel)) {
                event.reply("> **Shop :** https://shop.noskillworld.fr")
                        .queue();
            } else {
                event.reply(String.format(MessageManager.BAD_CHANNEL.getMessage(), botChannel.getAsMention()))
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
