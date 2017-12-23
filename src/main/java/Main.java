import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

import java.awt.*;

/**
 * Created by Vivek on 12/18/2017.
 */
public class Main {

    static Emulator emu = Emulator.GBA;

    public static void main(String[] args) throws AWTException {
        IDiscordClient client = Client.createClient(config.token, true); // Gets the client object (from the first example)
        Robot robot = new Robot();

        EventDispatcher dispatcher = client.getDispatcher(); // Gets the EventDispatcher instance for this client instance
        dispatcher.registerListener(new AnnotationListener(client, robot, emu)); // Registers the @EventSubscriber example class from above
    }

    public enum Emulator {GBA, DS};
}