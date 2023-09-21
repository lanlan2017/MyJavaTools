package adbs.main.ui.jpanels.scrcpy;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.model.Device;

import java.util.Map;
import java.util.TreeMap;

/**
 * 打开手机管家
 */
public class OpenApp {

    /**
     * OPPO
     * HONOR
     * Redmi
     * Meizu
     */
    /**
     * 手机品牌和手机管家APP的映射
     * key手机品牌
     * value 手机管家APP的activity全限定名
     */
    // public final static Map<String, String> mapBrand_MobileButler = new HashMap();
    public final static Map<String, String> mapBrand_MobileButler = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    ;

    static {
        mapBrand_MobileButler.put("Meizu", "com.meizu.safe/.SecurityMainActivity");
        mapBrand_MobileButler.put("HONOR", "com.huawei.systemmanager/.mainscreen.MainScreenActivity");
        mapBrand_MobileButler.put("OPPO", "com.coloros.safecenter/.SecureSafeMainActivity");
        mapBrand_MobileButler.put("Redmi", "com.miui.securitycenter/com.miui.securityscan.MainActivity");
    }

    /**
     * 手机品牌和计步器APP的映射
     * key 手机品牌
     * value 运动健康APP的activity全限定名
     */
    // public final static Map<String, String> mapBrand_Pedometer = new HashMap();
    public final static Map<String, String> mapBrand_Pedometer = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        mapBrand_Pedometer.put("Meizu", "com.meizu.net.pedometer/.ui.PedometerMainActivitys");
        mapBrand_Pedometer.put("HONOR", "com.huawei.health/.MainActivity");
        mapBrand_Pedometer.put("OPPO", "com.free.pedometer/com.base.basepedo.ui.MainActivity");
        mapBrand_Pedometer.put("Redmi", "com.mi.health/com.xiaomi.fitness.main.MainActivity");
    }

    // public static final String SERIAL_NUMBER_FLAG = "SERIAL_NUMBER";
    // public final static Map<String, String> mapWiFiSettings = new HashMap<>();
    /**
     * 手机品牌和WiFi设置打开命令映射
     * 品牌名不区分大小写。
     */
    public final static Map<String, String> mapWiFiSettings = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    // private final static String AndroidWiFiSetting = "Android";

    static {
        // String serialNumberFlag = Flags.SERIAL_NUMBER_FLAG;
        // mapWiFiSettings.put(AndroidWiFiSetting, "adb -s " +  SERIAL_NUMBER_FLAG + " shell am start -n com.android.settings/.wifi.WifiSettings");
        mapWiFiSettings.put(OpenAppFlagStr.AndroidWiFiSettingCode, "adb -s " + OpenAppFlagStr.SERIAL_NUMBER_FLAG + " shell am start -n com.android.settings/.wifi.WifiSettings");
        // mapWiFiSettings.put("Meizu", "adb -s " + SERIAL_NUMBER_FLAG + " shell am start -n com.android.settings/.wifi.WifiSettings");
        // adb -s U8ENW17C13004746 shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings
        mapWiFiSettings.put("HONOR", "adb -s " + OpenAppFlagStr.SERIAL_NUMBER_FLAG + " shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
        // adb -s 75aed56d shell am start -n com.coloros.wirelesssettings/com.coloros.wirelesssettings.wifi.OppoWifiSettingsActivity
        mapWiFiSettings.put("OPPO", "adb -s " + OpenAppFlagStr.SERIAL_NUMBER_FLAG + " shell am start -n com.coloros.wirelesssettings/com.coloros.wirelesssettings.wifi.OppoWifiSettingsActivity");
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

    public static void openWiFiSetting() {
        Device device = AdbTools.getInstance().getDevice();
        String brand = device.getBrand();
        System.out.println("brand = " + brand);
        String wifiSettingCode = mapWiFiSettings.get(brand);
        System.out.println("wifiSettingCode = " + wifiSettingCode);
        if (wifiSettingCode != null) {
            openWiFiSetting(device, wifiSettingCode);
        } else {
            // 如果没有找打该品牌的 WiFi打开命令，
            wifiSettingCode = mapWiFiSettings.get(OpenAppFlagStr.AndroidWiFiSettingCode);
            openWiFiSetting(device, wifiSettingCode);
            // System.out.println(wifiSettingCode);
        }
    }

    private static void openWiFiSetting(Device device, String wifiSettingCode) {
        String serial = device.getSerial();
        String openWiFiSettingCode = wifiSettingCode.replace(OpenAppFlagStr.SERIAL_NUMBER_FLAG, serial);
        System.out.println("openWiFiSettingCode = " + openWiFiSettingCode);
        AdbCommands.runAbdCmd(openWiFiSettingCode);
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
        // openGuanJiaApp();
        openWiFiSetting();
    }

}
