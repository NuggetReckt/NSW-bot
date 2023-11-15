package fr.nuggetreckt.nswbot.util;

import fr.nuggetreckt.nswbot.NSWBot;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.StringJoiner;
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
        return formatTime(NSWBot.getRequestsManager().getTimePlayed(playerUUID) / 20);
    }

    public String formatTime(final long time) {
        if (time < 1) {
            return "";
        }

        if (time < 60) {
            return time + "s";
        }

        long seconds = time;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long weeks = days / 7;

        seconds %= 60;
        minutes %= 60;
        hours %= 24;
        days %= 7;

        final StringJoiner joiner = new StringJoiner(" ");
        appendTime(joiner, weeks, "w");
        appendTime(joiner, days, "d");
        appendTime(joiner, hours, "h");
        appendTime(joiner, minutes, "m");
        appendTime(joiner, seconds, "s");
        return joiner.toString();
    }

    private void appendTime(final StringJoiner joiner, final long value, final String unit) {
        if (value > 0) {
            joiner.add(value + unit);
        }
    }

    public int getKillCount() {
        return NSWBot.getRequestsManager().getKillCount(playerUUID);
    }

    public int getDeathCount() {
        return NSWBot.getRequestsManager().getDeathCount(playerUUID);
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
        String job = NSWBot.getRequestsManager().getCurrentJob(playerUUID);
        return NSWBot.getRequestsManager().getJobLevel(playerUUID, job);
    }

    public float getCurrentXP() {
        return NSWBot.getRequestsManager().getCurrentJobXP(playerUUID);
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
