package robot;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

class Tt {
    public static void main(String args[]) {
        Timer tim = new Timer();
        tim.schedule(new MyTimer(), 1000, 2000);
    }
}
class MyTimer extends TimerTask {
    public void run() {
        System.out.println("2秒一次");
        try {
            new Robot().keyPress(KeyEvent.VK_ALT);
            new Robot().keyPress(KeyEvent.VK_TAB);
        } catch (AWTException ex) {
            Logger.getLogger(MyTimer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
