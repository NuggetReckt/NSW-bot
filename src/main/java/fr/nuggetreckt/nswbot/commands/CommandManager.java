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

        OptionData description = new OptionData(OptionType.STRING, "description", "Décrivez votre suggestion ici.", true);
        OptionData pseudo = new OptionData(OptionType.USER, "pseudo", "Exemple : @NuggetReckt#0001", true);
        OptionData toggle = new OptionData(OptionType.STRING, "switch", "ON (Active la création de tickets) / OFF (désactive la création de tickets)", true)
                .addChoice("ON", "ON")
                .addChoice("OFF", "OFF");
        OptionData linkCode = new OptionData(OptionType.STRING, "code", "Le code généré en ayant exécuté la commande /link en jeu.", true);

        SubcommandData closeSubCommandData = new SubcommandData("close", "Permet de fermer le ticket");
        SubcommandData addSubCommandData = new SubcommandData("add", "Permet d'ajouter un membre au ticket");
        SubcommandData removeSubCommandData = new SubcommandData("remove", "Permet de retirer un membre au ticket");
        SubcommandData toggleSubCommandData = new SubcommandData("toggle", "Active/Désactive la création de tickets");

        commandData.add(Commands.slash("ip", "Envoie l'ip du serveur."));
        commandData.add(Commands.slash("dynmap", "Envoie le lien vers la Dynmap du serveur."));
        commandData.add(Commands.slash("règles", "Envoie le lien vers les règles du serveur."));
        commandData.add(Commands.slash("shop", "Envoie le lien vers le shop du serveur."));
        commandData.add(Commands.slash("site", "Envoie le lien vers le site du serveur."));
        commandData.add(Commands.slash("vote", "Envoie le lien vers la page de votes du serveur."));
        commandData.add(Commands.slash("info", "Envoie les informations relatives au serveur."));
        commandData.add(Commands.slash("suggestion", "Permet de faire une suggestion.")
                .addOptions(description));
        commandData.add(Commands.slash("ticket", "Permet d'interagir avec les tickets.")
                .addSubcommands(addSubCommandData.addOptions(pseudo), removeSubCommandData.addOptions(pseudo), closeSubCommandData, toggleSubCommandData.addOptions(toggle)));
        commandData.add(Commands.slash("link", "Permet de relier son compte Discord au serveur Minecraft.")
                .addOptions(linkCode));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
