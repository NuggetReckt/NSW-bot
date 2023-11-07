package fr.nuggetreckt.nswbot.database;

import fr.nuggetreckt.nswbot.NSWBot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.UUID;

public class Requests {

    private Statement statement;
    private ResultSet resultSet;

    private String query;

    public void insertDiscordId(Long id, String linkCode) {
        query = "UPDATE core_players SET discordId = '" + id + "' WHERE linkCode = '" + linkCode + "';";
        updateData(query);
        close();
    }

    public void setLinked(Long id) {
        query = "UPDATE core_players SET isLinked = 1 WHERE discordId = '" + id + "';";
        updateData(query);
        close();
    }

    public String getLinkCode(String linkCode) {
        query = "SELECT * FROM core_players WHERE linkCode = '" + linkCode + "';";
        String result = null;

        retrieveData(query);
        try {
            if (resultSet.next()) {
                result = resultSet.getString("linkCode");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally {
            close();
        }
        return result;
    }

    public String getPlayerUUIDByDiscordId(Long id) {
        query = "SELECT * FROM core_players WHERE discordId = '" + id + "';";
        String result = null;

        retrieveData(query);
        try {
            if (resultSet.next()) {
                result = resultSet.getString("uuid");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally {
            close();
        }
        return result;
    }

    public String getPlayerNameByDiscordId(Long id) {
        query = "SELECT * FROM core_players WHERE discordId = '" + id + "';";
        String result = null;

        retrieveData(query);
        try {
            if (resultSet.next()) {
                result = resultSet.getString("playerName");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally {
            close();
        }
        return result;
    }

    public int getHonorRankId(UUID uuid) {
        query = "SELECT * FROM core_playerdata WHERE uuid = '" + uuid + "';";

        int result = 0;

        retrieveData(query);
        try {
            if (resultSet.next()) {
                result = resultSet.getInt("rankId");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally {
            close();
        }
        return result;
    }

    public long getHonorPoints(UUID uuid) {
        query = "SELECT * FROM core_playerdata WHERE uuid = '" + uuid + "';";

        long result = 0;

        retrieveData(query);
        try {
            if (resultSet.next()) {
                result = resultSet.getLong("honorPoints");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally {
            close();
        }
        return result;
    }

    public String getCurrentJob(UUID uuid) {
        query = "SELECT * FROM jobs_data WHERE uuid = '" + uuid + "';";
        String result = null;

        retrieveData(query);
        try {
            if (resultSet.next()) {
                result = resultSet.getString("currentJob");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally {
            close();
        }
        return result;
    }

    public int getJobLevel(UUID uuid, String job) {
        query = "SELECT * FROM jobs_data WHERE uuid = '" + uuid + "';";
        int result = 0;

        retrieveData(query);
        try {
            if (resultSet.next()) {
                result = resultSet.getInt(job + "_lvl");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally {
            close();
        }
        return result;
    }

    public float getCurrentJobXP(UUID uuid) {
        query = "SELECT * FROM jobs_data WHERE uuid = '" + uuid + "';";
        float result = 0.0F;

        retrieveData(query);
        try {
            if (resultSet.next()) {
                result = resultSet.getFloat("currentXp");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally {
            close();
        }
        return result;
    }

    public Timestamp getLastLogin(UUID uuid) {
        query = "SELECT * FROM core_players WHERE uuid = '" + uuid + "';";
        Timestamp result = null;

        retrieveData(query);
        try {
            if (resultSet.next()) {
                result = resultSet.getTimestamp("lastLogin");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally {
            close();
        }
        return result;
    }

    private void retrieveData(String query) {
        if (!isConnected()) {
            NSWBot.getInstance().getLogger().info("Disconnected from database. Reconnecting...");
            NSWBot.getConnector().connect();
        }

        try {
            statement = NSWBot.getConnector().getConn().createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    private void updateData(String query) {
        if (!isConnected()) {
            NSWBot.getInstance().getLogger().info("Disconnected from database. Reconnecting...");
            NSWBot.getConnector().connect();
        }

        try {
            statement = NSWBot.getConnector().getConn().createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    private void close() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ignored) {
            } // ignore
            resultSet = null;
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ignored) {
            } // ignore
            statement = null;
        }
    }

    private boolean isConnected() {
        return NSWBot.getConnector().isConnected();
    }
}
