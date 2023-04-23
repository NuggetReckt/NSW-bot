package fr.nuggetreckt.nswbot;

import fr.nuggetreckt.nswbot.buttons.ButtonListener;
import fr.nuggetreckt.nswbot.commands.CommandListener;
import fr.nuggetreckt.nswbot.commands.CommandManager;
import fr.nuggetreckt.nswbot.listeners.GronazListener;
import fr.nuggetreckt.nswbot.listeners.MemberJoinListener;
import fr.nuggetreckt.nswbot.listeners.MessageListener;
import fr.nuggetreckt.nswbot.listeners.ReadyListener;
import fr.nuggetreckt.nswbot.tasks.MessagesSender;
import fr.nuggetreckt.nswbot.tasks.Pinger;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static JDA jda;

    public static Dotenv dotenv;

    public static Pinger pinger;

    private static boolean canCreateTicket = true;

    public static String version;
    private static String token;

    private static Main instance;

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public Main() {
        instance = this;

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

        pinger = new Pinger();
        pinger.fetchData();
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

    public static Main getInstance() {
        return instance;
    }

    public static String getVersion() {
        version = getInstance().getClass().getPackage().getImplementationVersion();
        return version;
    }
}