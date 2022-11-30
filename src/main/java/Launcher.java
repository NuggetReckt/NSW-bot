import fr.nuggetreckt.nswbot.Main;

public class Launcher {
    public static void main(String[] args) {
        try {
            new Main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
