package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.MessageManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

public class SuggestionCommand extends Command {

    private final NSWBot instance;

    public SuggestionCommand(NSWBot instance) {
        this.instance = instance;
    }

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {

        TextChannel suggestionChannel = instance.getConfig().getSuggestionChannel();
        TextChannel botChannel = instance.getConfig().getBotChannel();

        if (event.getName().equals("suggestion")) {
            if (event.getChannel().equals(botChannel)) {
                if (event.getOption("description") != null) {
                    event.reply(String.format(MessageManager.SUGGESTION_SENT.getMessage(), botChannel.getAsMention()))
                            .setEphemeral(true)
                            .queue();

                    EmbedBuilder suggestion = new EmbedBuilder();

                    suggestion.setTitle("💡 ・ Nouvelle suggestion !")
                            .addField("<:info_nsw:864197429729034250> __Joueur :__", Objects.requireNonNull(event.getMember()).getAsMention(), false)
                            .addField("<:info_nsw:864197429729034250> __Suggestion :__", Objects.requireNonNull(event.getOption("description")).getAsString(), false)
                            .setColor(new Color(61, 189, 201, 1))
                            .setFooter("NSW - Semi-RP", "https://play.noskillworld.fr/assets/images/embed-icon.png")
                            .setTimestamp(new Date().toInstant());

                    Message message = suggestionChannel.sendMessageEmbeds(suggestion.build())
                            .complete();

                    message.createThreadChannel("Suggestion de " + event.getMember().getEffectiveName())
                            .queue();

                    message.addReaction(Emoji.fromFormatted("oui_nsw:856148735721603153"))
                            .queue();
                    message.addReaction(Emoji.fromFormatted("non_nsw:856148735901958146"))
                            .queue();
                } else {
                    event.reply(String.format(MessageManager.NO_DESCRIPTION.getMessage(), botChannel.getAsMention()))
                            .setEphemeral(true)
                            .queue();
                }
            } else {
                event.reply(String.format(MessageManager.BAD_CHANNEL.getMessage(), botChannel.getAsMention()))
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
