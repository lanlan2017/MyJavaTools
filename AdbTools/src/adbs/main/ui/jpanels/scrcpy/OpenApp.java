package adbs.main.ui.jpanels.scrcpy;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.model.Device;

import java.util.HashMap;
import java.util.Map;

/**
 * 打开手机管家
 */
public class OpenApp {
    /**
     *
     */
    private static String MeizuGuanJiaAct = "com.meizu.safe/.SecurityMainActivity";

    /**
     * OPPO
     * HONOR
     * Redmi
     * Meizu
     */

    public final static Map<String, String> mobileButlerMap = new HashMap();

    static {
        mobileButlerMap.put("Meizu", "com.meizu.safe/.SecurityMainActivity");
        mobileButlerMap.put("key2", "value2");
    }

    public static void openGuanJiaApp() {
        Device device = AdbTools.getInstance().getDevice();
        String brand = device.getBrand();
        System.out.println("brand = " + brand);
        String act = mobileButlerMap.get(brand);
        System.out.println("act = " + act);
        // String huaWaiYunDong = "adb -s " + serial + " shell am start -n com.huawei.health/.MainActivity";
        String serial = device.getSerial();
        openAct(serial, act);
    }

    /**
     * 执行adb命令打开指定的activity
     *
     * @param serial 要打开的APP所在的Android设备的序列号
     * @param act    要打开的APP的启动activity全限定名
     */
    private static void openAct(String serial, String act) {
        String openAct = "adb -s " + serial + " shell am start -n " + act;
        String actOut = AdbCommands.runAbdCmd(openAct);
        System.out.println("actOut = " + actOut);
    }

    public static void main(String[] args) {
        openGuanJiaApp();
    }

}
