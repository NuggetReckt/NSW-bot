package fr.nuggetreckt.nswbot.util;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import static fr.nuggetreckt.nswbot.Main.jda;

public class Config {

    final String ticketPanelId = "845218977458028544";
    final String ticketCategoryId = "845220724012482561";
    final String logChannelId = "995722672979849216";
    final String generaChannelId = "700249004721700958";
    final String rulesChannelId = "794911904296075265";
    final String supportChannelId = "991508521482981446";
    final String welcomeChannelId = "700255318520889385";
    final String botChannelId = "820064946162499594";
    final String suggestionChannelId = "1000757264707223583";
    final String playerRoleId = "794887765942468628";
    final String staffRoleId = "845229494151413761";

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
        return jda.getTextChannelById(generaChannelId);
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

    public TextChannel getBotChannel(){
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
}