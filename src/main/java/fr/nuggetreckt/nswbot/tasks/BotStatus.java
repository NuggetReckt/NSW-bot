package fr.nuggetreckt.nswbot.tasks;

import fr.nuggetreckt.nswbot.Main;
import net.dv8tion.jda.api.entities.Activity;

import java.util.*;

public class BotStatus {
    final int ChangeStatusInterval = 15000;
    final int second = 1000;
    private int a;

    public void setStatus() {
        List<String> status = new ArrayList<>();

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
        status.add("Saison 2.5 !");
        status.add("En version 1.18.2 !");
        status.add("Génération custom !");
        status.add("Rejoins-nous !");
        status.add("pain au chocolat ou chocolatine ?");
        status.add("NSW");
        status.add("Nouveau bot !");

        Random r = new Random();
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                a = r.nextInt(status.size() - 1);
                Main.jda.getPresence().setActivity(Activity.playing(String.valueOf(status.get(a))));
            }
        }, second, ChangeStatusInterval);
    }
}
