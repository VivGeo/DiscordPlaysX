import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageBuilder;

/**
 * Created by Vivek on 12/18/2017.
 */
public class AnnotationListener {

    IDiscordClient client;

    public AnnotationListener(IDiscordClient client) {
        this.client = client;
    }

    //@EventSubscriber
        /*

        public void onReadyEvent(ReadyEvent event) { // This method is called when the ReadyEvent is dispatched
            foo(); // Will be called!
        }
        */
    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) { // This method is NOT called because it doesn't have the @EventSubscriber annotation
        IMessage message = event.getMessage();
        String content = message.getContent();
        if (content.startsWith("!darren"))
        {
            new MessageBuilder(client).appendContent("\uD83D\uDE93 WEE OOO WEE OO \uD83D\uDE93\n\uD83D\uDC6E SEX CRIME DETECTED \uD83D\uDC6E").withChannel(message.getChannel()).send();
        }
        else if (content.toLowerCase().contains("dillon's dad"))
        {
            new MessageBuilder(client).appendContent("haha no. didn't code this yet.").withChannel(message.getChannel()).send();
        }
    }

    private void greeting() {
    }

}

