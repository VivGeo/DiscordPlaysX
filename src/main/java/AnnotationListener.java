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
        mapping.put("i", KeyEvent.VK_UP);
        mapping.put("j", KeyEvent.VK_LEFT);
        mapping.put("k", KeyEvent.VK_DOWN);
        mapping.put("l", KeyEvent.VK_RIGHT);
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
        mapping.put("i", KeyEvent.VK_UP);
        mapping.put("j", KeyEvent.VK_LEFT);
        mapping.put("k", KeyEvent.VK_DOWN);
        mapping.put("l", KeyEvent.VK_RIGHT);
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
        boolean pressed = false;
        try {
        message = event.getMessage();
        if (!message.getChannel().getName().equals("stuff_and_things"))
            return;

        String content = message.getContent().toLowerCase();
        if (content.equals("i")) { //u d l r a b x y select //LS RS
            key = KeyEvent.VK_UP;
            pressed = true;
        }
        else if (content.equals("k")) {
            key = KeyEvent.VK_DOWN;
            pressed = true;
        }
        else if (content.equals("j")) {
            key = KeyEvent.VK_LEFT;
            pressed = true;
        }
        else if (content.equals("l")) {
            key = KeyEvent.VK_RIGHT;
            pressed = true;
        }

        else if (content.equals("a")) {
            key = KeyEvent.VK_X;
            pressed = true;
        }
        else if (content.equals("b")) {
            key = KeyEvent.VK_Z;
            pressed = true;
        }
        else if (content.equals("q")) {
            key = KeyEvent.VK_A;

        }
        else if (content.equals("e")) {
            key = KeyEvent.VK_S;
            pressed = true;
        }

        else if (content.equals("select")) {
            key = KeyEvent.VK_BACK_SPACE;
            pressed = true;
        }
        else if (content.equals("start")) {
            key = KeyEvent.VK_ENTER;
            pressed = true;
        }
        /* //DS controls - ABYYX, START SELECT, L R
        else if (content.equals("a")) {
            key = KeyEvent.VK_X;
            pressed = true;
        }
        else if (content.equals("b")) {
            key = KeyEvent.VK_Z;
            pressed = true;
        }
        else if (content.equals("y")) {
            key = KeyEvent.VK_A;
            pressed = true;
        }
        else if (content.equals("X")) {
            key = KeyEvent.VK_S;
            pressed = true;
        }
        else if (content.equals("start")) {
            key = KeyEvent.VK_ENTER;
            pressed = true;
        }
        else if (content.equals("select")) {
            key = KeyEvent.VK_V;
            pressed = true;
        }
        else if (content.equals("l")) {
            key = KeyEvent.VK_Q;
            pressed = true;
        }
        else if (content.equals("r")) {
            key = KeyEvent.VK_W;
            pressed = true;
        }
        // */
        if (pressed) {
            robot.keyPress(key);
            Thread.sleep(150);
            robot.keyRelease(key);
        }
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

