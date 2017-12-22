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
    public void onMessageReceivedEvent(MessageReceivedEvent event) { // This method is NOT called because it doesn't have the @EventSubscriber annotation
        IMessage message = null;
        try {
        message = event.getMessage();

        if (!message.getChannel().getName().equals("stuff_and_things"))
            return;

        String content = message.getContent();
        if (content.equals("up")) { //u d l r a b x y select //LS RS
            robot.keyPress(KeyEvent.VK_UP);
            robot.keyRelease(KeyEvent.VK_UP);
        }
        else if (content.equals("down")) {
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
        }
        else if (content.equals("left")) {
            robot.keyPress(KeyEvent.VK_LEFT);
            robot.keyRelease(KeyEvent.VK_LEFT);
        }
        else if (content.equals("right")) {

        }
        else if (content.equals("a")) {

        }
        else if (content.equals("b")) {

        }
        else if (content.equals("x")) {

        }
        else if (content.equals("y")) {

        }
        else if (content.equals("select")) {

        }
        /*(if (content.startsWith("!darren"))
        {
            new MessageBuilder(client).appendContent("\uD83D\uDE93 WEE OOO WEE OO \uD83D\uDE93\n\uD83D\uDC6E SEX CRIME DETECTED \uD83D\uDC6E").withChannel(message.getChannel()).send();
        }
        else if (content.toLowerCase().contains("dillon's dad"))
        {
            new MessageBuilder(client).appendContent("haha no. didn't code this yet.").withChannel(message.getChannel()).send();
        }*/ }

        catch (RateLimitException e) {
            new MessageBuilder(client).appendContent("haha no. didn't code this yet.").withChannel(message.getChannel()).send();
        }
    }

    private void greeting() {
    }

}

