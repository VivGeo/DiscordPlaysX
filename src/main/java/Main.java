import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

/**
 * Created by Vivek on 12/18/2017.
 */
public class Main {

    public static void main(String[] args) {
        IDiscordClient client = Client.createClient(config.token, true); // Gets the client object (from the first example)
        EventDispatcher dispatcher = client.getDispatcher(); // Gets the EventDispatcher instance for this client instance
        dispatcher.registerListener(new AnnotationListener(client)); // Registers the @EventSubscriber example class from above
    }

}