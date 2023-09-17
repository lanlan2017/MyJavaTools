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

    /**
     * OPPO
     * HONOR
     * Redmi
     * Meizu
     */
    /**
     * key手机品牌
     * value 手机管家APP的activity全限定名
     */
    public final static Map<String, String> mapBrand_MobileButler = new HashMap();

    static {
        mapBrand_MobileButler.put("Meizu", "com.meizu.safe/.SecurityMainActivity");
        mapBrand_MobileButler.put("HONOR", "com.huawei.systemmanager/.mainscreen.MainScreenActivity");
        mapBrand_MobileButler.put("OPPO", "com.coloros.safecenter/.SecureSafeMainActivity");
        mapBrand_MobileButler.put("Redmi", "com.miui.securitycenter/com.miui.securityscan.MainActivity");
    }

    /*
     * key手机品牌
     * value 运动健康APP的activity全限定名
     */
    public final static Map<String, String> mapBrand_Pedometer = new HashMap();

    static {
        mapBrand_Pedometer.put("Meizu", "com.meizu.net.pedometer/.ui.PedometerMainActivitys");
        mapBrand_Pedometer.put("HONOR", "com.huawei.health/.MainActivity");
        mapBrand_Pedometer.put("OPPO", "com.free.pedometer/com.base.basepedo.ui.MainActivity");
        mapBrand_Pedometer.put("Redmi", "com.mi.health/com.xiaomi.fitness.main.MainActivity");
    }

    public static void openGuanJiaApp() {
        Device device = AdbTools.getInstance().getDevice();
        String brand = device.getBrand();
        System.out.println("brand = " + brand);
        String act = mapBrand_MobileButler.get(brand);
        System.out.println("act = " + act);
        // String huaWaiYunDong = "adb -s " + serial + " shell am start -n com.huawei.health/.MainActivity";
        // String huaWaiYunDong = "adb shell am start -n com.huawei.health/.MainActivity";
        // String huaWaiYunDong = "adb shell am start -n com.huawei.systemmanager/.mainscreen.MainScreenActivity";
        String serial = device.getSerial();
        openAct(serial, act);
    }

    public static void openPedometerAPP() {
        Device device = AdbTools.getInstance().getDevice();
        openPedometerAPP(device);
    }

    public static void openPedometerAPP(Device device) {
        String serial = device.getSerial();
        String brand = device.getBrand();
        System.out.println("brand = " + brand);
        String act = mapBrand_Pedometer.get(brand);
        System.out.println("act = " + act);
        openAct(serial, act);
    }

    // public static void openPedometerAPP(String serial) {
    //     new Device()
    //
    // }
    // public static void openPedometerAPP(String serial) {
    //
    // }

    /**
     * 执行adb命令打开指定的activity
     *
     * @param serial 要打开的APP所在的Android设备的序列号
     * @param act    要打开的APP的启动activity全限定名
     */
    private static void openAct(String serial, String act) {
        if (serial != null && act != null) {
            String openAct = "adb -s " + serial + " shell am start -n " + act;
            String actOut = AdbCommands.runAbdCmd(openAct);
            System.out.println("actOut = " + actOut);
        }
    }

    public static void main(String[] args) {
        openGuanJiaApp();
    }

}
