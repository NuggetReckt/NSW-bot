package fr.nuggetreckt.nswbot.util;

import me.ryzeon.transcripts.DiscordHtmlTranscripts;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TranscriptUtils {

    public static void createTranscript(@NotNull TextChannel channel) {
        DiscordHtmlTranscripts transcript = DiscordHtmlTranscripts.getInstance();
        String fileName = "transcript-" + channel.getName() + ".html";

        try {
            channel.sendFiles(transcript.createTranscript(channel, fileName))
                    .addContent(MessageManager.TRANSCRIPT_MESSAGE_FILE.getMessage())
                    .queue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
