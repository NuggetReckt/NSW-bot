package fr.nuggetreckt.nswbot;

import fr.nuggetreckt.nswbot.commands.CommandListener;
import fr.nuggetreckt.nswbot.commands.CommandManager;
import fr.nuggetreckt.nswbot.listeners.*;
import fr.nuggetreckt.nswbot.buttons.AbortButtonListener;
import fr.nuggetreckt.nswbot.buttons.CloseButtonListener;
import fr.nuggetreckt.nswbot.buttons.ConfirmButtonListener;
import fr.nuggetreckt.nswbot.buttons.CreateButtonListener;
import fr.nuggetreckt.nswbot.tasks.BotStatus;
import fr.nuggetreckt.nswbot.tasks.MessagesSender;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {

    public static JDA jda;
    public static Dotenv dotenv;

    public static void main(String[] args) throws LoginException, InterruptedException, RuntimeException {

        final String token;

        System.out.println("Lancement du bot...");

        dotenv = Dotenv.configure()
                .directory("/env/")
                .filename(".env")
                .load();

        System.out.println("Chargement du token...");

        try {
            token = dotenv.get("DISCORD_TOKEN");
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }

        jda = JDABuilder.createDefault(token)

                //Basic Listeners
                .addEventListeners(new ReadyListener())
                .addEventListeners(new GronazListener())
                .addEventListeners(new MessageListener())
                .addEventListeners(new MemberJoinListener())
                .addEventListeners(new CommandManager())

                //Buttons Listeners
                .addEventListeners(new CreateButtonListener())
                .addEventListeners(new ConfirmButtonListener())
                .addEventListeners(new CloseButtonListener())
                .addEventListeners(new AbortButtonListener())

                //Server Commands
                .addEventListeners(new CommandListener(jda))

                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();

        jda.awaitReady();
    }
}
