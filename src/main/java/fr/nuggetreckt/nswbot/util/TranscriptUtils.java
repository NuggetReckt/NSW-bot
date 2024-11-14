package fr.nuggetreckt.nswbot.util;

import me.ryzeon.transcripts.DiscordHtmlTranscripts;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TranscriptUtils {

    public static FileUpload getTranscript(@NotNull TextChannel channel) {
        DiscordHtmlTranscripts transcript = DiscordHtmlTranscripts.getInstance();
        String fileName = "transcript-" + channel.getName() + "-" + channel.getId() + ".html";

        try {
            return transcript.createTranscript(channel, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendTranscript(@NotNull TextChannel channel, FileUpload file) {
        channel.sendFiles(file)
                .addContent(MessageManager.TRANSCRIPT_MESSAGE_FILE.getMessage())
                .queue();
    }
}
