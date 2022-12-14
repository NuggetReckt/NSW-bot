package fr.nuggetreckt.nswbot;

import fr.nuggetreckt.nswbot.buttons.ButtonListener;
import fr.nuggetreckt.nswbot.commands.CommandListener;
import fr.nuggetreckt.nswbot.commands.CommandManager;
import fr.nuggetreckt.nswbot.listeners.GronazListener;
import fr.nuggetreckt.nswbot.listeners.MemberJoinListener;
import fr.nuggetreckt.nswbot.listeners.MessageListener;
import fr.nuggetreckt.nswbot.listeners.ReadyListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

public class Main {

    public static JDA jda;
    public static Dotenv dotenv;
    public static String token;

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public Main() throws LoginException {
        logger.info("Lancement du bot...");

        dotenv = Dotenv.configure()
                .directory("/env/")
                .filename(".env")
                .load();

        logger.info("Chargement du token...");

        try {
            token = dotenv.get("DISCORD_TOKEN");
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
        this.buildJDA();
    }

    private void buildJDA() {
        jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();

        registerEvents();
    }

    private static void registerEvents() {
        //Basic events
        jda.addEventListener(new ReadyListener());
        jda.addEventListener(new GronazListener());
        jda.addEventListener(new MessageListener());
        jda.addEventListener(new MemberJoinListener());

        //Register commands
        jda.addEventListener(new CommandManager());

        //Commands/Buttons events
        jda.addEventListener(new CommandListener());
        jda.addEventListener(new ButtonListener());
    }
}