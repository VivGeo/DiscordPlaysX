import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

import java.io.IOException;
import java.lang.ProcessBuilder;
import java.awt.*;
import java.awt.event.KeyEvent;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.win32.StdCallLibrary;

public class Main {
    public interface User32 extends StdCallLibrary {

        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);

        boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);

        WinDef.HWND SetFocus(WinDef.HWND hWnd);

        int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);

        boolean SetForegroundWindow(WinDef.HWND hWnd);
    }



    static Emulator emu = Emulator.GBA; //change to DS when you want use DS



    public static void main(String[] args) throws AWTException, InterruptedException, IOException {
        final String EMULATORPATH, WTITLE, ETITLE;
        if (emu == Emulator.GBA) {
            EMULATORPATH = config.gbaEMULATORPATH;
            WTITLE = config.gbaWTITLE;
            ETITLE = config.gbaETITLE;
        }
        else if (emu == Emulator.DS) {
            EMULATORPATH = config.dsEMULATORPATH;
            WTITLE = config.dsWTITLE;
            ETITLE = config.dsETITLE;
        }
        else {
            EMULATORPATH = config.gbaEMULATORPATH;
            WTITLE = config.gbaWTITLE;
            ETITLE = config.gbaETITLE;
        }
        //path
        //window name
        //rom name
        Process process = new ProcessBuilder(EMULATORPATH).start();
        Thread.sleep(1500);




        //Open GBA emu
        final User32 user32 = User32.INSTANCE;
        user32.EnumWindows(new WNDENUMPROC() {
            int count = 0;

            @Override
            public int hashCode() {
                return super.hashCode();
            }

            public boolean callback(HWND hWnd, Pointer arg1) {
                byte[] windowText = new byte[512];
                user32.GetWindowTextA(hWnd, windowText, 512);
                String wText = Native.toString(windowText);

                // get rid of this if block if you want all windows regardless
                // of whether
                // or not they have text
                if (wText.length() == 0) {
                    return true;
                }

                //commented this out
                //System.out.println("Found window with text " + hWnd
                //        + ", total " + ++count + " Text: " + wText);
                if (wText
                        .equals(WTITLE)) {
                    user32.SetForegroundWindow(hWnd);
                    return false;
                }
                return true;
            }
        }, null);
        Robot robot = new Robot();
        openEmu(robot);
        Thread.sleep(1500);
        openRom(robot, ETITLE.toLowerCase().toCharArray());

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


        IDiscordClient client = Client.createClient(config.token, true); // Gets the client object (from the first example)


        EventDispatcher dispatcher = client.getDispatcher(); // Gets the EventDispatcher instance for this client instance
        dispatcher.registerListener(new AnnotationListener(client, robot, emu)); // Registers the @EventSubscriber example class from above
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Main() {
        super();
    }

    private static void openRom(Robot robot, char[] title) {
        for (char letter: title) {
            System.out.println(letter);
            int temp;
            int hex;
            if (Character.isLetter(letter)) {
                temp = (int)letter - 97;
                hex = 0x41 + temp;
            }
            else if (Character.isDigit(letter)) {
                temp = (int)letter - 49;
                hex = 0x30 + temp;
            }
            else if (letter == ' ') {
                hex = KeyEvent.VK_SPACE;
            }
            else if (letter == '-') {
                hex = KeyEvent.VK_UNDERSCORE; // I think this is right, anyways
            }
            /*
            else if (letter == '_') { //LOL not touching this yet till after

            }
            */
            else if (letter == '.') {
                hex = KeyEvent.VK_PERIOD;
            }
            else {
                throw new IllegalArgumentException("valid rom names only");
            }
            robot.keyPress(hex);
            robot.keyRelease(hex);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public enum Emulator {GBA, DS};

    private static void openEmu (Robot robot) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_O);
        robot.keyRelease(KeyEvent.VK_CONTROL);

    }

}