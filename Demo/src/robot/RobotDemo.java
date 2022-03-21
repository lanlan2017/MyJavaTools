package robot;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.logging.*;

public class RobotDemo {

    // 鼠标左键
    static final int leftMouseButton = InputEvent.BUTTON1_MASK;

    public static void main(String[] args) {

        TaoBaoSpecialEdition taoBaoSpecialEdition = new TaoBaoSpecialEdition();
        taoBaoSpecialEdition.watchVideo();

        new VPN().vpn();
        // System.out.println("5秒一次");

        // test();
        // switchWindowsWindows();

    }

    // private static void switchWindowsWindows() {
    //     Robot robot = null;
    //     try {
    //         robot = new Robot();
    //     } catch (AWTException e) {
    //         e.printStackTrace();
    //     }
    //     if (robot != null) {
    //         taoBaoSpecialEdition.switchWindowsWindows(robot);
    //     }
    // }

    private static void test() {
        try {
            //find the first window

            new Robot().keyPress(KeyEvent.VK_ALT);

            new Robot().keyPress(KeyEvent.VK_TAB);

            Thread.sleep(1000);
            //
            // new Robot().keyPress(KeyEvent.VK_ENTER);
            //
            // Thread.sleep(100);
            //
            // //find the second window
            //
            // new Robot().keyPress(KeyEvent.VK_ALT);
            //
            // new Robot().keyPress(KeyEvent.VK_TAB);
            //
            // new Robot().keyPress(KeyEvent.VK_TAB);
            //
            // Thread.sleep(100);
            //
            // new Robot().keyPress(KeyEvent.VK_ENTER);

        } catch (AWTException ex) {
            // Logger.getLogger(MyTimer.class.getName()).log(Level.SEVERE, null, ex);

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();

        }
    }

}

class LogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        Date date = new Date();
        String sDate = date.toString();
        return "[" + sDate + "]" + "[" + record.getLevel() + "]" + record.getClass() + record.getMessage() + "\n";
    }
}