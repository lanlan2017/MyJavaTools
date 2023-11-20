package adbs.main.ui.jpanels.scrcpy.run;

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
    private String scrcpyHeight;

    public OpenButtonRunnable(JTextField widthTextField) {
        this.widthTextField = widthTextField;
        // scrcpyHeight = widthTextField.getText();
    }

    public OpenButtonRunnable(String scrcpyHeight) {
        this.scrcpyHeight = scrcpyHeight;
    }

    public OpenButtonRunnable() {
    }

    @Override
    public void run() {
        // 获取选择的id
        // String serial = AdbTools.device.getId();
        // String name = AdbTools.device.getName();

        // 获取序列号
        String serial = AdbTools.getInstance().getDevice().getSerial();
        String name = AdbTools.getInstance().getDevice().getName();

        if (widthTextField != null) {
            // 获取宽度
            scrcpyHeight = widthTextField.getText();

        }
        // 如果存在id
        if (serial != null && !"".equals(serial)) {
            System.out.println("scrcpy.exe 打开镜像");
            // turnOffAutoBrightness

            setBrightnessAndVolume(serial);

            // 拼接命令
            String code;

            if (scrcpyHeight != null && !"".equals(scrcpyHeight)) {
                // if (scrcpyHeight.matches("\\d+")) {
                //     code = "adbtools_open_scrcpy.bat " + serial + " " + name + " " + scrcpyHeight;
                // } else {
                code = "adbtools_open_scrcpy.bat " + serial + " " + name + " " + scrcpyHeight;
                // }
                if (scrcpyHeight.equals("0")) {
                    scrcpyHeight = "full";
                    code = "adbtools_open_scrcpy.bat " + serial + " " + name + " " + scrcpyHeight;

                }

            } else {
                code = "adbtools_open_scrcpy.bat " + serial + " " + name;
            }
            System.out.println("调用另一个Jar:" + code);
            CmdRun.run(code);
        }
        System.out.println("scrcpy.exe 已关闭");
    }

    /**
     * 调整屏幕亮度为最低值，静音
     *
     * @param serial Android设备的序列号
     */
    private void setBrightnessAndVolume(String serial) {
        // 关闭亮度自动调节
        // String turnOffAutoBrightness = "adb -s U8ENW18118023171 shell settings put system screen_brightness_mode 0";
        String turnOffAutoBrightness = "adb -s " + serial + " shell settings put system screen_brightness_mode 0";
        AdbCommands.runAbdCmd(turnOffAutoBrightness);
        // ThreadSleep.seconds(1);
        // int millisecond = 20;
        int millisecond = 30;
        ThreadSleep.millisecond(millisecond);
        //调整屏幕亮度为0
        String miniBrightness = "adb -s " + serial + " shell settings put system screen_brightness 0";
        AdbCommands.runAbdCmd(miniBrightness);
        // ThreadSleep.seconds(1);
        ThreadSleep.millisecond(millisecond);
        // ThreadSleep.millisecond(50);

        // 音量减一
        String volumeDecrease = "adb -s " + serial + " shell input keyevent 25";
        AdbCommands.runAbdCmd(volumeDecrease);
        ThreadSleep.millisecond(millisecond);

        // 镜像静音
        String turnOffVolume = "adb -s " + serial + " shell input keyevent 164";
        AdbCommands.runAbdCmd(turnOffVolume);
    }
}
