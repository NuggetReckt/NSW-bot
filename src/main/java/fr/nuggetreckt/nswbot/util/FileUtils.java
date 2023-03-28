package fr.nuggetreckt.nswbot.util;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileUtils {

    public void saveTranscript(TextChannel currentChannel) {
        createDirIfAbsent();

        File transcriptFile = new File("/root/Bots/NSWsBot/transcripts/" + getCurrentChannelName(currentChannel) + ".txt");

        try {
            transcriptFile.createNewFile();

            FileWriter fileWriter = new FileWriter(transcriptFile);
            List<Message> messages = new ArrayList<>(getCurrentChannelHistory(currentChannel).getRetrievedHistory());

            fileWriter.write("Ticket Transcript\n\n");
            fileWriter.write("Ticket: " + getCurrentChannelName(currentChannel) + "\n");
            fileWriter.write("Messages: " + getCurrentMessageCount(currentChannel) + "\n");
            fileWriter.write("Membres: " + getCurrentMemberCount(currentChannel) + "\n");
            fileWriter.write("(" + getCurrentMembers(currentChannel) + ")\n\n");

            for (int i = 0, j = messages.size() - 1; i < j; i++) {
                messages.add(i, messages.remove(j));
            }

            for (Message i : messages) {
                if (!i.getAuthor().isBot()) {
                    fileWriter.write("----------------------------------------------------------------------\n");
                    fileWriter.write("Par " + i.getAuthor().getName() + ", " + i.getTimeCreated().format(DateTimeFormatter.ofPattern("'le' dd/MM/yyyy 'à' hh:mm")) + "\n");
                    fileWriter.write("  " + i.getContentRaw() + "\n");
                    if (i.isEdited()) {
                        fileWriter.write("(édité)\n");
                    }
                    fileWriter.write("----------------------------------------------------------------------\n");

                }
            }
            fileWriter.write("\nFin du transcript.");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createDirIfAbsent() {
        File transcriptDir = new File("/root/Bots/NSWsBot/transcripts/");

        if (!transcriptDir.exists()) {
            transcriptDir.mkdirs();
        }
    }

    public MessageHistory getCurrentChannelHistory(TextChannel currentChannel) {
        return MessageHistory.getHistoryFromBeginning(currentChannel).complete();
    }

    public int getCurrentMemberCount(@NotNull TextChannel currentChannel) {
        int memberCount = currentChannel.getMembers().size();
        int botCount = 0;

        for (Member i : currentChannel.getMembers()) {
            if (i.getUser().isBot()) {
                botCount++;
            }
        }
        return memberCount - botCount;
    }

    public String getCurrentMembers(@NotNull TextChannel currentChannel) {
        StringBuilder sb = new StringBuilder();
        int k = 0;

        for (Member i : currentChannel.getMembers()) {
            sb.append(i.getEffectiveName());

            if (k % 2 == 0) {
                sb.append(", ");
            }
            k++;
        }
        return sb.toString();
    }

    public String getCurrentChannelName(@NotNull TextChannel currentChannel) {
        return currentChannel.getName();
    }

    public int getCurrentMessageCount(@NotNull TextChannel currentChannel) {
        return getCurrentChannelHistory(currentChannel).size();
    }
}