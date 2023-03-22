package fr.nuggetreckt.nswbot.tasks;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import java.util.*;

import static fr.nuggetreckt.nswbot.Main.jda;
import static fr.nuggetreckt.nswbot.Main.pinger;

public class BotStatus {
    public Random r = new Random();
    public Timer timer = new Timer();

    final int changeStatusInterval = 15000;
    final int second = 1000;

    public List<String> status = new ArrayList<>();

    public int a;

    public BotStatus(boolean isUnderMaintenance) {
        if (!isUnderMaintenance) {
            setStatus();

            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    setCurrentStatus();
                }
            }, second, changeStatusInterval);
        } else {
            jda.getPresence().setPresence(OnlineStatus.IDLE, true);
        }
    }

    public void setStatus() {
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
        status.add("En version 1.20 !");
        status.add("Génération custom !");
        status.add("Rejoins-nous !");
        status.add("pain au chocolat ou chocolatine ?");
        status.add("NSW");
        status.add("@NoSkillWorld sur Twitter !");
        status.add("@NoSkillWorld sur Insta !");
        status.add("/bump !");
        status.add(pinger.getPlayersOnline() + "/" + pinger.getMaxPlayers() + " Joueurs connectés");
    }

    public List<String> getStatus() {
        return status;
    }

    public void setCurrentStatus() {
        a = r.nextInt(getStatus().size() - 1);
        jda.getPresence().setActivity(Activity.playing(String.valueOf(getStatus().get(a))));
    }
}
