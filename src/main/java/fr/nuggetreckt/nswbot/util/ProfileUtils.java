package fr.nuggetreckt.nswbot.util;

import fr.nuggetreckt.nswbot.NSWBot;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class ProfileUtils {

    private UUID playerUUID;

    public void setUUID(UUID uuid) {
        this.playerUUID = uuid;
    }

    public String getLastLogin() {
        Timestamp timestamp = NSWBot.getRequestsManager().getLastLogin(playerUUID);
        String date = new SimpleDateFormat("dd/MM/yyyy").format(timestamp);
        String time = new SimpleDateFormat("HH:mm").format(timestamp);

        return "Le " + date + " à " + time;
    }

    public String getTimePlayed() {
        return "<time>";
    }

    public int getKillCount() {
        return 0;
    }

    public int getDeathCount() {
        return 0;
    }

    public String getCurrentJob() {
        String job = NSWBot.getRequestsManager().getCurrentJob(playerUUID);

        return switch (job) {
            case "bucheron" -> "Bûcheron";
            case "mineur" -> "Mineur";
            case "agriculteur" -> "Agriculteur";
            case "eleveur" -> "Éleveur";
            case "chasseur" -> "Chasseur";
            case "pecheur" -> "Pêcheur";
            default -> "Aucun";
        };
    }

    public int getCurrentJobLevel() {
        return 0;
    }

    public float getXP() {
        return 0.00F;
    }

    public float getCurrentXPNeeded() {
        return 0.00F;
    }

    public String getTopJob() {
        return "<top_job>";
    }

    public int getTopJobLevel() {
        return 0;
    }

    public int getCurrentRank() {
        return NSWBot.getRequestsManager().getHonorRankId(playerUUID);
    }

    public long getHonorPoints() {
        return NSWBot.getRequestsManager().getHonorPoints(playerUUID);
    }

    public int getNextRank() {
        int rankId = NSWBot.getRequestsManager().getHonorRankId(playerUUID);

        if (rankId == 6) {
            return rankId;
        } else {
            return rankId + 1;
        }
    }

    public String getHonorPointsNeeded() {
        int pointsNeeded = 0;

        if (getCurrentRank() == 6) {
            return "Rang max !";
        } else {
            return "`" + pointsNeeded + "` points requis";
        }
    }
}
