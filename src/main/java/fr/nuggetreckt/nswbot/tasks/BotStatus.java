package fr.nuggetreckt.nswbot.tasks;

import fr.nuggetreckt.nswbot.NSWBot;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import java.util.*;

public class BotStatus {

    private final NSWBot instance;

    private final Random r;

    private final List<String> status;

    public BotStatus(NSWBot instance, boolean isUnderMaintenance) {
        this.instance = instance;
        r = new Random();
        Timer timer = new Timer();
        status = new ArrayList<>();

        int changeStatusInterval = 15000;
        int second = 1000;

        if (!isUnderMaintenance) {
            setStatus();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    setCurrentStatus();
                }
            }, second, changeStatusInterval);
        } else {
            instance.getJDA().getPresence().setPresence(OnlineStatus.IDLE, true);
        }
    }

    private void setStatus() {
        status.add("NSW's Bot");
        status.add("play.noskillworld.fr");
        status.add("discord.noskillworld.fr");
        status.add("dynmap.noskillworld.fr");
        status.add("shop.noskillworld.fr");
        status.add("mumble.noskillworld.fr");
        status.add("statut.noskillworld.fr");
        status.add("noskillworld.fr");
        status.add("Le feu ça brûle !");
        status.add("L'eau ça mouille !");
        status.add("les carrottes dorées c'est la vie");
        status.add("2+2=4.");
        status.add("HardCore S1 Incoming...");
        status.add("Quelle Dinguerie !");
        status.add("Vous trouvez pas que Herbyvor il est bg ?");
        status.add("Dev par NuggetReckt avec ❤");
        status.add("Saison 3 !");
        status.add(String.format("En version %s !", instance.getPinger().getGameVersion()));
        status.add("Génération custom !");
        status.add("Rejoins-nous !");
        status.add("Bon jeu sur NoSkillWorld !");
        status.add("pain au chocolat ou chocolatine ?");
        status.add("NSW");
        status.add("@NoSkillWorld sur Twitter !");
        status.add("@noskillworld sur Insta !");
        status.add("@noskillworld_mc sur YouTube !");
        status.add("@noskillworld_mc sur TikTok !");
        status.add("/bump !");
    }

    private List<String> getStatus() {
        return status;
    }

    private void setCurrentStatus() {
        int a = r.nextInt(getStatus().size() - 1);
        instance.getJDA().getPresence().setActivity(Activity.playing(String.valueOf(getStatus().get(a))));
    }
}
