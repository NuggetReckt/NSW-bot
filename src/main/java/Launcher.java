import fr.nuggetreckt.nswbot.NSWBot;

public class Launcher {
    public static void main(String[] args) {
        try {
            new NSWBot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
