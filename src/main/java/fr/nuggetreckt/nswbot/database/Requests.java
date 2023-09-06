package fr.nuggetreckt.nswbot.database;

import fr.nuggetreckt.nswbot.NSWBot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
