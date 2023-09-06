package fr.nuggetreckt.nswbot.util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import static fr.nuggetreckt.nswbot.NSWBot.jda;

public class Config {

    //FOR TESTING
    final String guildId = "986026862406950993";
    final String ticketPanelId = "987861295472713738";
    final String ticketCategoryId = "986978012014714890";
    final String logChannelId = "987317580773589013";
    final String generalChannelId = "986026863371616289";
    final String rulesChannelId = "994796686033563688";
    final String supportChannelId = "994796706073944134";
    final String welcomeChannelId = "995102579413487686";
    final String botChannelId = "998975933987967028";
    final String suggestionChannelId = "999414109781102613";
    final String playerRoleId = "998976455847452773";
    final String staffRoleId = "998976421529649183";
    final String linkRoleId = "1148722123951591446";

    public Guild getGuild() {
        return jda.getGuildById(guildId);
    }

    public TextChannel getTicketPanel() {
        return jda.getTextChannelById(ticketPanelId);
    }

    public Category getTicketCategory() {
        return jda.getCategoryById(ticketCategoryId);
    }

    public TextChannel getLogChannel() {
        return jda.getTextChannelById(logChannelId);
    }

    public TextChannel getGeneralChannel() {
        return jda.getTextChannelById(generalChannelId);
    }

    public TextChannel getRulesChannel() {
        return jda.getTextChannelById(rulesChannelId);
    }

    public TextChannel getSupportChannel() {
        return jda.getTextChannelById(supportChannelId);
    }

    public TextChannel getWelcomeChannel() {
        return jda.getTextChannelById(welcomeChannelId);
    }

    public TextChannel getBotChannel() {
        return jda.getTextChannelById(botChannelId);
    }

    public TextChannel getSuggestionChannel() {
        return jda.getTextChannelById(suggestionChannelId);
    }

    public Role getPlayerRole() {
        return jda.getRoleById(playerRoleId);
    }

    public Role getStaffRole() {
        return jda.getRoleById(staffRoleId);
    }

    public Role getLinkRole() {
        return jda.getRoleById(linkRoleId);
    }
}