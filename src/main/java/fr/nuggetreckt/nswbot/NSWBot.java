package fr.nuggetreckt.nswbot;

import fr.nuggetreckt.nswbot.buttons.ButtonListener;
import fr.nuggetreckt.nswbot.commands.CommandListener;
import fr.nuggetreckt.nswbot.commands.CommandManager;
import fr.nuggetreckt.nswbot.database.Connector;
import fr.nuggetreckt.nswbot.database.Requests;
import fr.nuggetreckt.nswbot.listeners.*;
import fr.nuggetreckt.nswbot.tasks.MessagesSender;
import fr.nuggetreckt.nswbot.tasks.Pinger;
import fr.nuggetreckt.nswbot.util.Config;
import fr.nuggetreckt.nswbot.util.LinkUtils;
import fr.nuggetreckt.nswbot.util.ProfileUtils;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NSWBot {

    public static JDA jda;

    public static Logger logger;

    public static Dotenv dotenv;

    private static boolean canCreateTicket = true;

    private static String token;

    public static Pinger pinger;
    private static NSWBot instance;
    private static Connector connector;
    private static Requests requestsManager;
    private static ProfileUtils profileUtils;
    private static Config config;
    private static LinkUtils linkUtils;

    public NSWBot() {
        instance = this;

        logger = LoggerFactory.getLogger(NSWBot.class);
        pinger = new Pinger();
        connector = new Connector();
        requestsManager = new Requests();
        config = new Config();
        linkUtils = new LinkUtils();
        profileUtils = new ProfileUtils();

        logger.info("Lancement du bot...");

        this.setToken();
        this.buildJDA();

        pinger.fetchData();
    }

    private void setToken() {
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
    }

    private void buildJDA() {
        jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
        registerEvents();
    }

    private static void registerEvents() {
        //Basic events
        jda.addEventListener(new ReadyListener());
        jda.addEventListener(new ShutdownListener());
        jda.addEventListener(new GronazListener());
        jda.addEventListener(new MessageListener());
        jda.addEventListener(new MemberJoinListener());

        //Register commands
        jda.addEventListener(new CommandManager());

        //Commands/Buttons events
        jda.addEventListener(new CommandListener());
        jda.addEventListener(new ButtonListener());
    }

    public static void setCanCreateTicket(boolean b) {
        canCreateTicket = b;

        if (canCreateTicket) {
            new MessagesSender().enableTicketCreation();
        } else {
            new MessagesSender().disableTicketCreation();
        }
    }

    public static boolean getCanCreateTicket() {
        return canCreateTicket;
    }

    public static NSWBot getInstance() {
        return instance;
    }

    public static Connector getConnector() {
        return connector;
    }

    public static Requests getRequestsManager() {
        return requestsManager;
    }

    public static Config getConfig() {
        return config;
    }

    public static LinkUtils getLinkUtils() {
        return linkUtils;
    }

    public static ProfileUtils getProfileUtils() {
        return profileUtils;
    }

    public Guild getGuild() {
        return getConfig().getGuild();
    }

    public JDA getJDA() {
        return jda;
    }

    public Logger getLogger() {
        return logger;
    }

    public String getVersion() {
        return getInstance().getClass().getPackage().getImplementationVersion();
    }
}