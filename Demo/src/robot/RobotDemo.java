package robot;

import java.awt.event.InputEvent;
import java.util.Date;
import java.util.logging.*;

public class RobotDemo {

    // 鼠标左键
    static final int leftMouseButton = InputEvent.BUTTON1_MASK;

    public static void main(String[] args) {
        TaoBaoSpecialEdition taoBao = new TaoBaoSpecialEdition();
        taoBao.watchVideo();
        // taoBao.browseHomepage();
        // taoBao.feedTheChickens(5);
        // taoBao.browseForSeconds(33);
        // taoBao.fortuneDuckFeedsWork(3);
        // taoBao.fortuneDuckFeeds(3);
        // new VPN().vpn();
    }
}

