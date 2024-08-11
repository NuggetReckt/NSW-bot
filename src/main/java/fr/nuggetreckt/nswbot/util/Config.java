package fr.nuggetreckt.nswbot.util;

import fr.nuggetreckt.nswbot.NSWBot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class Config {

    private final NSWBot instance;

    public Config(NSWBot instance) {
        this.instance = instance;
    }

    final String guildId = "700249004721700955";
    final String ticketPanelId = "845218977458028544";
    final String ticketCategoryId = "845220724012482561";
    final String logChannelId = "995722672979849216";
    final String generalChannelId = "700249004721700958";
    final String rulesChannelId = "794911904296075265";
    final String supportChannelId = "991508521482981446";
    final String welcomeChannelId = "700255318520889385";
    final String botChannelId = "820064946162499594";
    final String suggestionChannelId = "1000757264707223583";
    final String playerRoleId = "794887765942468628";
    final String staffRoleId = "845229494151413761";
    final String linkRoleId = "1148569236566966273";

    public Guild getGuild() {
        return instance.getJDA().getGuildById(guildId);
    }

    public TextChannel getTicketPanel() {
        return instance.getJDA().getTextChannelById(ticketPanelId);
    }

    public Category getTicketCategory() {
        return instance.getJDA().getCategoryById(ticketCategoryId);
    }

    public TextChannel getLogChannel() {
        return instance.getJDA().getTextChannelById(logChannelId);
    }

    public TextChannel getGeneralChannel() {
        return instance.getJDA().getTextChannelById(generalChannelId);
    }

    public TextChannel getRulesChannel() {
        return instance.getJDA().getTextChannelById(rulesChannelId);
    }

    public TextChannel getSupportChannel() {
        return instance.getJDA().getTextChannelById(supportChannelId);
    }

    public TextChannel getWelcomeChannel() {
        return instance.getJDA().getTextChannelById(welcomeChannelId);
    }

    public TextChannel getBotChannel() {
        return instance.getJDA().getTextChannelById(botChannelId);
    }

    public TextChannel getSuggestionChannel() {
        return instance.getJDA().getTextChannelById(suggestionChannelId);
    }

    public Role getPlayerRole() {
        return instance.getJDA().getRoleById(playerRoleId);
    }

    public Role getStaffRole() {
        return instance.getJDA().getRoleById(staffRoleId);
    }

    public Role getLinkRole() {
        return instance.getJDA().getRoleById(linkRoleId);
    }
}