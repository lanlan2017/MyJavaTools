package adbs.main.ui.jpanels.auto.test;

import adbs.main.ui.jpanels.auto.AdbTap;
import adbs.main.ui.jpanels.auto.ScreenPosition;
import adbs.main.ui.jpanels.auto.ScreenPositionRatio;
import adbs.model.Device;

public class Test {
    public static void main(String[] args) {


        ScreenPositionRatio closeBtn = new ScreenPositionRatio(0.8148148148148148, 0.4265625);
        ScreenPositionRatio readBtn = new ScreenPositionRatio(0.8314814814814815, 0.6151041666666667);
        Device device = new Device("75aed56d", "");

        printCode(closeBtn, device);

        printCode(readBtn, device);
        // System.out.println("position = " + position);

        // ScreenPosition readPosition = AdbTap.getPosition(1920, 1080, readBtn);
        // System.out.println("readPosition = " + readPosition);
        // String readCode = AdbTap.getAdbTapCode(device, readPosition);
        // System.out.println("readCode = " + readCode);


        ScreenPositionRatio audioBtn = new ScreenPositionRatio(0.7592592592592593, 0.8635416666666667);
        // ScreenPositionRatio closeBtn1 = new ScreenPositionRatio(0.8185185185185185, 0.4265625);

        printCode(audioBtn, device);

    }

    private static void printCode(ScreenPositionRatio closeBtn, Device device) {
        ScreenPosition closePosition = AdbTap.getPosition(1920, 1080, closeBtn);
        System.out.println("Position = " + closePosition);
        String closeCode = AdbTap.getAdbTapCode(device, closePosition);
        System.out.println("code = " + closeCode);
    }
}
