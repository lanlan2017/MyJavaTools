package adbs.cmd;

import adbs.main.AdbTools;
import adbs.model.Device;

/**
 * 使用adb命令打开APP
 */
public class AdbOpenApp {
    public static void main(String[] args) {
        Device device = AdbTools.getInstance().getDevice();
        String serial = device.getSerial();
        String netHostName = device.getNetHostName().toLowerCase();
        if (netHostName.contains("huawei")) {
            String Code_OpenMobileButler = "adb -s " + serial + " shell am start -n com.huawei.systemmanager/com.huawei.systemmanager.mainscreen.MainScreenActivity";
            AdbCommands.runAbdCmd(Code_OpenMobileButler);
        }
    }

    /**
     * 打开运动健康APP
     */
    public static void openSportsAndHealthApp() {
        Device device = AdbTools.getInstance().getDevice();
        String serial = device.getSerial();
        String netHostName = device.getNetHostName().toLowerCase();
        if (netHostName.contains("huawei")) {
            String huaWaiYunDong = "adb -s " + serial + " shell am start -n com.huawei.health/.MainActivity";
            // 打开华为运动健康
            AdbCommands.runAbdCmd(huaWaiYunDong);
        }
    }


}
