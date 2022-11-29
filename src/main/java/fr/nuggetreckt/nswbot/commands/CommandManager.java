package fr.nuggetreckt.nswbot.commands;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        OptionData description = new OptionData(OptionType.STRING, "description", "Faites votre suggestion ici", true);
        OptionData pseudo = new OptionData(OptionType.USER, "pseudo", "Exemple : @NuggetReckt#0001", true);

        SubcommandData closeSubCommandData = new SubcommandData("close", "Permet de fermer le ticket");
        SubcommandData addSubCommandData = new SubcommandData("add", "Permet d'ajouter un membre au ticket");
        SubcommandData removeSubCommandData = new SubcommandData("remove", "Permet de retirer un membre au ticket");

        commandData.add(Commands.slash("dynmap", "Envoie le lien vers la Dynmap du serveur"));
        commandData.add(Commands.slash("ip", "Envoie l'ip du serveur"));
        commandData.add(Commands.slash("règles", "Envoie le lien vers les règles du serveur"));
        commandData.add(Commands.slash("shop", "Envoie le lien vers le shop du serveur"));
        commandData.add(Commands.slash("site", "Envoie le lien vers le site du serveur"));
        commandData.add(Commands.slash("suggestion", "Permet de faire une suggestion")
                .addOptions(description));
        commandData.add(Commands.slash("vote", "Envoie le lien vers la page de votes du serveur"));

        commandData.add(Commands.slash("ticket", "Permet d'interargir avec les tickets")
                .addSubcommands(addSubCommandData.addOptions(pseudo), removeSubCommandData.addOptions(pseudo), closeSubCommandData));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}