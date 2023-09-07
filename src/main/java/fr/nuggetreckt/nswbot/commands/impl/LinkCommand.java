package fr.nuggetreckt.nswbot.commands.impl;

import fr.nuggetreckt.nswbot.NSWBot;
import fr.nuggetreckt.nswbot.commands.Command;
import fr.nuggetreckt.nswbot.util.MessageManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LinkCommand extends Command {

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {

        TextChannel botChannel = NSWBot.getConfig().getBotChannel();

        if (event.getName().equals("link")) {
            if (event.getChannel().equals(botChannel)) {
                if (event.getOption("code") != null) {
                    Member member = Objects.requireNonNull(event.getMember());

                    if (!NSWBot.getLinkUtils().isLinked(member)) {
                        //Le membre n'est pas link
                        String givenCode = Objects.requireNonNull(event.getOption("code")).getAsString();
                        String generatedCode = NSWBot.getRequestsManager().getLinkCode(givenCode);

                        if (givenCode.equals(generatedCode)) {
                            //Le code du membre est valide
                            NSWBot.getLinkUtils().setLinked(member, givenCode);

                            event.reply(String.format(MessageManager.MEMBER_LINKED.getMessage()))
                                    .setEphemeral(true)
                                    .addActionRow(
                                            Button.success("namechange_yes", "Oui"),
                                            Button.danger("namechange_no", "Non")
                                    )
                                    .queue();
                        } else {
                            //Le code est invalide
                            event.reply(String.format(MessageManager.BAD_LINK_CODE.getMessage()))
                                    .setEphemeral(true)
                                    .queue();
                        }
                    } else {
                        //Le membre est déjà link
                        event.reply(String.format(MessageManager.ALREADY_LINKED.getMessage()))
                                .setEphemeral(true)
                                .queue();
                    }
                }
            } else {
                event.reply(String.format(MessageManager.BAD_CHANNEL.getMessage(), botChannel.getAsMention()))
                        .setEphemeral(true)
                        .queue();
            }
        }
    }
}
