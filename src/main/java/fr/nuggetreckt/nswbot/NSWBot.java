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
import fr.nuggetreckt.nswbot.util.LogsUtils;
import fr.nuggetreckt.nswbot.util.ProfileUtils;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NSWBot {

    private JDA jda;

    public final Logger logger;

    public Dotenv dotenv;

    private boolean canCreateTicket = true;

    private String token;

    private final NSWBot instance;
    private final Pinger pinger;
    private final Connector connector;
    private final Requests requestsManager;
    private final ProfileUtils profileUtils;
    private final Config config;
    private final LinkUtils linkUtils;
    private final LogsUtils logsUtils;

    public NSWBot() {
        instance = this;

        logger = LoggerFactory.getLogger(NSWBot.class);

        logger.info("Lancement du bot...");

        pinger = new Pinger();
        connector = new Connector();
        requestsManager = new Requests(this);
        config = new Config(this);
        linkUtils = new LinkUtils(this);
        profileUtils = new ProfileUtils(this);
        logsUtils = new LogsUtils(this);

        setToken();
        buildJDA();

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

    private void registerEvents() {
        logger.info("Chargement des events...");

        //Basic events
        jda.addEventListener(new ReadyListener(this));
        jda.addEventListener(new ShutdownListener(this));
        jda.addEventListener(new GronazListener());
        jda.addEventListener(new MessageListener(this));
        jda.addEventListener(new MemberJoinListener(this));

        //Register commands
        jda.addEventListener(new CommandManager());

        //Commands/Buttons events
        jda.addEventListener(new CommandListener(this));
        jda.addEventListener(new ButtonListener(this));
    }

    public void setCanCreateTicket(boolean b) {
        canCreateTicket = b;

        if (canCreateTicket) {
            new MessagesSender(this).enableTicketCreation();
        } else {
            new MessagesSender(this).disableTicketCreation();
        }
    }

    public boolean getCanCreateTicket() {
        return canCreateTicket;
    }

    public NSWBot getInstance() {
        return instance;
    }

    public Pinger getPinger() {
        return pinger;
    }

    public Connector getConnector() {
        return connector;
    }

    public Requests getRequestsManager() {
        return requestsManager;
    }

    public Config getConfig() {
        return config;
    }

    public LinkUtils getLinkUtils() {
        return linkUtils;
    }

    public ProfileUtils getProfileUtils() {
        return profileUtils;
    }

    public LogsUtils getLogsUtils() {
        return logsUtils;
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
        return this.getClass().getPackage().getImplementationVersion();
    }
}