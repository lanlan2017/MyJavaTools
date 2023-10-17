package adbs.main.ui.jpanels.auto;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.ui.jpanels.auto.act.WeiZhi;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;
import tools.copy.SystemClipboard;

import java.util.Scanner;

public class AdbTap {

    public static void main(String[] args) {
        // String serial = "621QACQC3962X";
        // test1(serial);
        // test2(serial);

        Scanner scanner = new Scanner(System.in);
        double x = getX(scanner, "x=");
        double y = getX(scanner, "y=");
        // printRatio(1080, 1920, x, y);

        AdbTools instance = AdbTools.getInstance();
        Device device = instance.getDevice();
        // int width = device.getWidth();
        // int height = device.getHeight();
        printRatio(device, x, y);


    }

    private static double getX(Scanner scanner, String s) {
        double x = 0;
        System.out.print(s);
        String line = scanner.nextLine();
        if (line.matches("[0-9.]+")) {
            x = Double.parseDouble(line);
        }
        return x;
    }

    private static void test2(String serial) {
        Device device = new Device(serial, "device");
        ScreenPositionRatio taskBtnRatio = new ScreenPositionRatio(0.7796296296296297, 0.9729166666666667);
        tap(device, taskBtnRatio);
    }

    public static void tap(Device device, ScreenPositionRatio taskBtnRatio) {
        String adbTapCode = getAdbTapCode(device, taskBtnRatio);
        // System.out.println("adbTapCode = " + adbTapCode);
        AdbCommands.runAbdCmd(adbTapCode);
        // ThreadSleep.minutes(1);
        ThreadSleep.millisecond(600);
    }

    public static void tap(Device device, int x, int y) {
        // String adbTapCode = getAdbTapCode(device, taskBtnRatio);
        String adbTapCode = "adb -s " + device.getSerial() + " shell input tap " + x + " " + y;
        // System.out.println("adbTapCode = " + adbTapCode);
        AdbCommands.runAbdCmd(adbTapCode);
        // ThreadSleep.minutes(1);
        // ThreadSleep.millisecond(600);
    }

    public static void wait_tap(Device device, int seconds, WeiZhi tingShuZaiLing) {
        wait_tap(device, seconds, tingShuZaiLing.getX(), tingShuZaiLing.getY());
    }

    private static void wait_tap(Device device, int seconds, int x, int y) {
        System.out.print("操作 等待" + seconds + "秒,");
        ThreadSleep.seconds(seconds);
        // ThreadSleep.seconds(35);
        System.out.print("点击:" + x + "," + y);
        System.out.println();
        AdbTap.tap(device, x, y);
        // System.out.println();
    }

    public static void tapCenterPosition(Device device) {
        double centerX = device.getWidth() * 0.5;
        double centerY = device.getHeight() * 0.5;
        // String adbTapCode = getAdbTapCode(device, taskBtnRatio);
        String adbTapCode = "adb -s " + device.getSerial() + " shell input tap " + centerX + " " + centerY;
        // System.out.println("adbTapCode = " + adbTapCode);
        AdbCommands.runAbdCmd(adbTapCode);
        // ThreadSleep.minutes(1);
        // ThreadSleep.millisecond(600);
    }

    public static void tap_wait(Device device, ScreenPositionRatio readBtn, int i) {
        AdbTap.tap(device, readBtn);
        ThreadSleep.seconds(i);
    }

    private static String getAdbTapCode(Device device, ScreenPositionRatio taskBtnRatio) {
        ScreenPosition position = getScreenPosition(device, taskBtnRatio);
        // String adbTapCode = getAdbTapCode(serial, position);
        // String adbTapCode = getAdbTapCode(device.getSerial(), position);
        String adbTapCode = getAdbTapCode(device, position);
        return adbTapCode;
    }

    private static ScreenPosition getScreenPosition(Device device, ScreenPositionRatio taskBtnRatio) {
        int width = device.getWidth();
        int height = device.getHeight();
        // 任务键在屏幕的坐标的比例
        ScreenPosition position = getPosition(height, width, taskBtnRatio);
        return position;
    }

    private static void test1(String serial) {
        int height = 1920, width = 1080;
        ScreenPositionRatio ratio = printRatio(width, height, 842.0, 1868.0);

        ScreenPosition position = getPosition(height, width, ratio);
        // String code = "adb -s " + serial + " shell input tap " + x1 + " " + y1;
        String code = getAdbTapCode(serial, position);
        System.out.println("code = " + code);
    }

    private static String getAdbTapCode(String serial, ScreenPosition position) {
        String code = "adb -s " + serial + " shell input tap " + position.getX() + " " + position.getY();
        return code;
    }

    public static String getAdbTapCode(Device device, ScreenPosition position) {
        String code = "";
        if (device != null) {
            String serial = device.getSerial();
            code = "adb -s " + serial + " shell input tap " + position.getX() + " " + position.getY();
        }
        return code;
    }

    public static ScreenPosition getPosition(int height, int width, ScreenPositionRatio ratio) {
        // System.out.println("ratio = " + ratio);
        int x1 = (int) (width * ratio.getxRatio());
        int y1 = (int) (height * ratio.getyRatio());
        ScreenPosition position = new ScreenPosition(x1, y1);
        return position;
    }


    private static ScreenPositionRatio printRatio(Device device, double x, double y) {
        return printRatio(device.getWidth(), device.getHeight(), x, y);
    }

    public static ScreenPositionRatio printRatio(double x, double y) {
        Device device = AdbTools.getInstance().getDevice();
        return printRatio(device.getWidth(), device.getHeight(), x, y);
    }

    private static ScreenPositionRatio printRatio(int width, int height, double x, double y) {
        // double x = 842.0;
        // double y = 1868.0;
        double xRatio = x / width;
        double yRatio = y / height;

        System.out.println("double xRatio = " + xRatio + ",yRatio = " + yRatio + ";");
        ScreenPositionRatio ratio = new ScreenPositionRatio(xRatio, yRatio);
        System.out.println(ratio);
        SystemClipboard.setSysClipboardText(ratio.toString());
        return ratio;
    }
}
