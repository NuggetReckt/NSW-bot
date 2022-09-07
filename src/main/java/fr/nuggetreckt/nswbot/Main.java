package fr.nuggetreckt.nswbot;

import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;
import fr.nuggetreckt.nswbot.commands.*;
import fr.nuggetreckt.nswbot.listeners.*;
import fr.nuggetreckt.nswbot.ticketsystem.TicketMain;
import fr.nuggetreckt.nswbot.ticketsystem.commands.AddCommand;
import fr.nuggetreckt.nswbot.ticketsystem.commands.CloseCommand;
import fr.nuggetreckt.nswbot.ticketsystem.commands.RemoveCommand;
import fr.nuggetreckt.nswbot.ticketsystem.listeners.AbortButtonListener;
import fr.nuggetreckt.nswbot.ticketsystem.listeners.CloseButtonListener;
import fr.nuggetreckt.nswbot.ticketsystem.listeners.ConfirmButtonListener;
import fr.nuggetreckt.nswbot.ticketsystem.listeners.CreateButtonListener;
import fr.nuggetreckt.nswbot.tasks.BotStatus;
import fr.nuggetreckt.nswbot.tasks.MessagesSender;
import fr.nuggetreckt.nswbot.twitter.TwitterApiInteractions;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

import static net.dv8tion.jda.api.interactions.commands.OptionType.MENTIONABLE;
import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class Main {

    public static JDA jda;
    public static Dotenv dotenv;
    public static TwitterApi apiInstance;

    public static void main(String[] args) throws LoginException, InterruptedException, RuntimeException {

        final String token;

        System.out.println("Lancement du bot...");

        dotenv = Dotenv.configure()
                .directory("/env/")
                .filename(".env")
                .load();

        System.out.println("Chargement de l'API Twitter...");

        try {
            token = dotenv.get("DISCORD_TOKEN");

            apiInstance = new TwitterApi(new TwitterCredentialsOAuth2(
                    Main.dotenv.get("TWITTER_OAUTH2_CLIENT_ID"),
                    Main.dotenv.get("TWITTER_OAUTH2_CLIENT_SECRET"),
                    Main.dotenv.get("TWITTER_OAUTH2_ACCESS_TOKEN"),
                    Main.dotenv.get("TWITTER_OAUTH2_REFRESH_TOKEN")));
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }

        jda = JDABuilder.createDefault(token)

                //Tickets Buttons Listeners
                .addEventListeners(new CreateButtonListener())
                .addEventListeners(new ConfirmButtonListener())
                .addEventListeners(new CloseButtonListener())
                .addEventListeners(new AbortButtonListener())

                //Basic Listeners
                .addEventListeners(new ReadyListener())
                .addEventListeners(new GronazListener())
                .addEventListeners(new MessageListener())
                .addEventListeners(new MemberJoinListener())

                //Tickets Commands
                .addEventListeners(new AddCommand())
                .addEventListeners(new RemoveCommand())
                .addEventListeners(new CloseCommand())

                //Server Commands
                .addEventListeners(new DynmapCommand())
                .addEventListeners(new IpCommand())
                .addEventListeners(new ReglesCommand())
                .addEventListeners(new ShopCommand())
                .addEventListeners(new SiteCommand())
                .addEventListeners(new VoteCommand())
                .addEventListeners(new SuggestionCommand())

                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();

        jda.upsertCommand("add", "Permet d'ajouter un membre au ticket")
                .addOption(MENTIONABLE, "pseudo", "Exemple : @NuggetReckt#0001").queue();
        jda.upsertCommand("remove", "Permet de retirer un membre d'un ticket")
                .addOption(MENTIONABLE, "pseudo", "Exemple : @NuggetReckt#0001").queue();
        jda.upsertCommand("suggestion", "Permet de faire une suggestion")
                .addOption(STRING, "description", "Faites votre suggestion ici").queue();
        jda.upsertCommand("close", "Permet de fermer le ticket").queue();
        jda.upsertCommand("dynmap", "Envoie le lien vers la Dynmap du serveur").queue();
        jda.upsertCommand("ip", "Envoie l'ip du serveur").queue();
        jda.upsertCommand("règles", "Envoie le lien vers les règles du serveur").queue();
        jda.upsertCommand("shop", "Envoie le lien vers le shop du serveur").queue();
        jda.upsertCommand("site", "Envoie le lien vers le site du serveur").queue();
        jda.upsertCommand("vote", "Envoie le lien vers les sites de vote").queue();
        jda.awaitReady();

        new BotStatus().setStatus();
        new MessagesSender().sendRulesMessage();
        new MessagesSender().sendSupportMessage();
        new TicketMain().mainMessageSetter();

        new TwitterApiInteractions().OnTweetListener();
    }
}
