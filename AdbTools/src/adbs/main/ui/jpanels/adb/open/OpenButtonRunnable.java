package adbs.main.ui.jpanels.adb.open;

import adbs.cmd.AdbCommands;
import adbs.cmd.CmdRun;
import adbs.main.AdbTools;
import adbs.tools.thread.ThreadSleep;

import javax.swing.*;

/**
 * 打开设备 按钮事件处理程序
 */
public class OpenButtonRunnable implements Runnable {
    private JTextField widthTextField;

    public OpenButtonRunnable(JTextField widthTextField) {
        this.widthTextField = widthTextField;

    }

    public OpenButtonRunnable() {
    }

    @Override
    public void run() {
        // 获取选择的id
        // String serial = AdbTools.device.getId();
        // String name = AdbTools.device.getSimpleId();

        // 获取序列号
        String serial = AdbTools.getInstance().getDevice().getSerial();
        String name = AdbTools.getInstance().getDevice().getName();

        // 获取宽度
        String scrcpyWidth = widthTextField.getText();
        // 如果存在id
        if (serial != null && !"".equals(serial)) {
            System.out.println("scrcpy.exe 打开镜像");
            // turnOffAutoBrightness

            // 关闭亮度自动调节
            // String turnOffAutoBrightness = "adb -s U8ENW18118023171 shell settings put system screen_brightness_mode 0";
            String turnOffAutoBrightness = "adb -s " + serial + " shell settings put system screen_brightness_mode 0";
            AdbCommands.runAbdCmd(turnOffAutoBrightness);
            ThreadSleep.seconds(1);
            //调整屏幕亮度为0
            String miniBrightness = "adb -s " + serial + " shell settings put system screen_brightness 0";
            AdbCommands.runAbdCmd(miniBrightness);
            ThreadSleep.seconds(1);
            // 镜像静音
            String turnOffVolume = "adb -s " + serial + " shell input keyevent 164";
            AdbCommands.runAbdCmd(turnOffVolume);


            // 拼接命令
            String code;

            if (scrcpyWidth != null && scrcpyWidth.matches("\\d+")) {
                code = "adbtools_open_scrcpy.bat " + serial + " " + name + " " + scrcpyWidth;
            } else {
                code = "adbtools_open_scrcpy.bat " + serial + " " + name;
            }
            System.out.println("调用另一个Jar:" + code);
            CmdRun.run(code);
        }
        System.out.println("scrcpy.exe 已关闭");
    }
}
