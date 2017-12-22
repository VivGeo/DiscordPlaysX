import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.RateLimitException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * Created by Vivek on 12/18/2017.
 */
public class AnnotationListener {

    IDiscordClient client;
    Robot robot;
    public AnnotationListener(IDiscordClient client, Robot robot) {
        this.client = client; this.robot = robot;
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
        if (content.equals("up")) { //u d l r a b x y select //LS RS
            key = KeyEvent.VK_UP;
            pressed = true;
        }
        else if (content.equals("down")) {
            key = KeyEvent.VK_DOWN;
            pressed = true;
        }
        else if (content.equals("left")) {
            key = KeyEvent.VK_LEFT;
            pressed = true;
        }
        else if (content.equals("right")) {
            key = KeyEvent.VK_RIGHT;
            pressed = true;
        }
        /*
        else if (content.equals("a")) {
            key = KeyEvent.VK_X;
            pressed = true;
        }
        else if (content.equals("b")) {
            key = KeyEvent.VK_Z;
            pressed = true;
        }
        else if (content.equals("ls")) {
            key = KeyEvent.VK_A;

        }
        else if (content.equals("rs")) {
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
        */ //DS controls - ABYYX, START SELECT, L R
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
        //robot.keyPress(KeyEvent.VK_ENTER);
            //Thread.sleep(150);
            // robot.keyRelease(KeyEvent.VK_ENTER);
        /*(if (content.startsWith("!darren"))
        {
            new MessageBuilder(client).appendContent("\uD83D\uDE93 WEE OOO WEE OO \uD83D\uDE93\n\uD83D\uDC6E SEX CRIME DETECTED \uD83D\uDC6E").withChannel(message.getChannel()).send();
        }
        else if (content.toLowerCase().contains("dillon's dad"))
        {
            new MessageBuilder(client).appendContent("haha no. didn't code this yet.").withChannel(message.getChannel()).send();
        }*/ }

        catch (RateLimitException e) {
            new MessageBuilder(client).appendContent("**Rate Limit Reached**.").withChannel(message.getChannel()).send();
        }
        catch (Exception e) {
            new MessageBuilder(client).appendContent("**Exception**.").withChannel(message.getChannel()).send();
            e.printStackTrace();

        }
    }

    private void greeting() {
    }

}

