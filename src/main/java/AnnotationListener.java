import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.RateLimitException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;


/**
 * Created by Vivek on 12/18/2017.
 */
public class AnnotationListener {

    IDiscordClient client;
    Robot robot;
    Main.Emulator emu;
    HashMap<String, Integer> mapping = new HashMap<String, Integer>();
    public AnnotationListener(IDiscordClient client, Robot robot, Main.Emulator emu) {
        this.client = client; this.robot = robot; this.emu = emu;
        if (emu == Main.Emulator.GBA)
            populateGBAMappings(mapping);
        else if (emu == Main.Emulator.DS)
            populateDSMappings(mapping);

    }

    private void populateDSMappings(HashMap<String, Integer> mapping) {
        mapping.put("up", KeyEvent.VK_UP);
        mapping.put("left", KeyEvent.VK_LEFT);
        mapping.put("down", KeyEvent.VK_DOWN);
        mapping.put("right", KeyEvent.VK_RIGHT);
        mapping.put("a", KeyEvent.VK_X);
        mapping.put("b", KeyEvent.VK_Z);
        mapping.put("y", KeyEvent.VK_A);
        mapping.put("x", KeyEvent.VK_S);
        mapping.put("ls",KeyEvent.VK_Q);
        mapping.put("rs",KeyEvent.VK_W);
        mapping.put("select", KeyEvent.VK_BACK_SPACE);
        mapping.put("start", KeyEvent.VK_ENTER);
    }

    private void populateGBAMappings(HashMap<String, Integer> mapping) {
        mapping.put("up", KeyEvent.VK_UP);
        mapping.put("left", KeyEvent.VK_LEFT);
        mapping.put("down", KeyEvent.VK_DOWN);
        mapping.put("right", KeyEvent.VK_RIGHT);
        mapping.put("a", KeyEvent.VK_X);
        mapping.put("b", KeyEvent.VK_Z);
        mapping.put("q", KeyEvent.VK_A);
        mapping.put("e", KeyEvent.VK_S);
        mapping.put("select", KeyEvent.VK_BACK_SPACE);
        mapping.put("start", KeyEvent.VK_ENTER);
    }

    //@EventSubscriber
        /*

        public void onReadyEvent(ReadyEvent event) { // This method is called when the ReadyEvent is dispatched
            foo(); // Will be called!
        }
        */
    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) throws InterruptedException { // This method is NOT called because it doesn't have the @EventSubscriber annotation
        IMessage message = null;
        int key = 0;

        try {
            message = event.getMessage();
            if (!message.getChannel().getName().equals("stuff_and_things"))
                return;

            String content = message.getContent().toLowerCase();
            if (!mapping.containsKey(content))
                return;
            key = mapping.get(content);
            robot.keyPress(key);
            Thread.sleep(150);
            robot.keyRelease(key);
        }


        catch (RateLimitException e) {
            new MessageBuilder(client).appendContent("**Rate Limit Reached**.").withChannel(message.getChannel()).send();
        }
        catch (Exception e) {
            new MessageBuilder(client).appendContent("**Exception**.").withChannel(message.getChannel()).send();
            e.printStackTrace();

        }
    }

    private int gbaStringToKeyEvent (String content) {
        /*
        if (!message.getChannel().getName().equals("stuff_and_things"))
            return;

        String content = message.getContent().toLowerCase();
        */
        if (content.equals("i")) { //u d l r a b x y select //LS RS
            return KeyEvent.VK_UP;

        }
        else if (content.equals("k")) {
            return KeyEvent.VK_DOWN;

        }
        else if (content.equals("j")) {
            return KeyEvent.VK_LEFT;

        }
        else if (content.equals("l")) {
            return KeyEvent.VK_RIGHT;

        }

        else if (content.equals("a")) {
            return KeyEvent.VK_X;

        }
        else if (content.equals("b")) {
            return KeyEvent.VK_Z;

        }
        else if (content.equals("q")) {
            return KeyEvent.VK_A;

        }
        else if (content.equals("e")) {
            return KeyEvent.VK_S;

        }
        return 0;
    }

}

