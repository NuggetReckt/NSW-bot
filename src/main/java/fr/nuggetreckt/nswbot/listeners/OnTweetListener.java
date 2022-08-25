package fr.nuggetreckt.nswbot.listeners;

import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;
import fr.nuggetreckt.nswbot.Main;
import io.github.cdimascio.dotenv.Dotenv;

public class OnTweetListener {
    public static void main(String[] args) {
        TwitterApi twitterapi = new TwitterApi(new TwitterCredentialsOAuth2(
                Main.dotenv.get("TWITTER_OAUTH2_CLIENT_ID"),
                Main.dotenv.get("TWITTER_OAUTH2_CLIENT_SECRET"),
                Main.dotenv.get("TWITTER_OAUTH2_ACCESS_TOKEN"),
                Main.dotenv.get("TWITTER_OAUTH2_REFRESH_TOKEN")));
    }
}
