package fr.nuggetreckt.nswbot.util;

import fr.nuggetreckt.nswbot.NSWBot;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.UUID;

public class ProfileUtils {

    private UUID playerUUID;

    public void setUUID(UUID uuid) {
        this.playerUUID = uuid;
    }

    public String getLastLogin() {
        Timestamp timestamp = NSWBot.getRequestsManager().getLastLogin(playerUUID);
        String format = "'Le 'dd/MM/yyyy' à 'hh'h'mm";

        return new SimpleDateFormat(format, Locale.FRANCE).format(timestamp);
    }

    public String getTimePlayed() {
        return formatTime(NSWBot.getRequestsManager().getTimePlayed(playerUUID) / 20);
    }

    private String formatTime(final long time) {
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
        return getJobFormatted(NSWBot.getRequestsManager().getCurrentJob(playerUUID));
    }

    private String getJobFormatted(@NotNull String job) {
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

    public int getJobLevel(@NotNull String job) {
        String current = NSWBot.getRequestsManager().getCurrentJob(playerUUID);

        if (job.equals(current)) {
            return NSWBot.getRequestsManager().getCurrentJobLevel(playerUUID);
        }
        return NSWBot.getRequestsManager().getJobLevel(playerUUID, job);
    }

    public int getCurrentRank() {
        return NSWBot.getRequestsManager().getHonorRankId(playerUUID);
    }

    public long getHonorPoints() {
        return NSWBot.getRequestsManager().getHonorPoints(playerUUID);
    }

    public int getNextRank() {
        int rankId = getCurrentRank();

        if (rankId == 6) {
            return rankId;
        } else {
            return rankId + 1;
        }
    }

    public String getHonorPointsNeeded() {
        int rankId = getCurrentRank();
        int pointsNeeded = 0;

        switch (rankId) {
            case 0 -> pointsNeeded = 10;
            case 1 -> pointsNeeded = 30;
            case 2 -> pointsNeeded = 60;
            case 3 -> pointsNeeded = 100;
            case 4 -> pointsNeeded = 150;
            case 5 -> pointsNeeded = 250;
        }

        if (rankId == 6) {
            return "Rang max !";
        } else {
            return "`" + pointsNeeded + "` points requis";
        }
    }
}
