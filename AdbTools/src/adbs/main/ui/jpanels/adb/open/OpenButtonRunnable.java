package adbs.main.ui.jpanels.adb.open;

import adbs.cmd.CmdRun;
import adbs.main.AdbTools;

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
        // String id = AdbTools.device.getId();
        // String simpleId = AdbTools.device.getSimpleId();

        // 获取序列号
        String id = AdbTools.getInstance().getDevice().getSerial();
        String simpleId = AdbTools.getInstance().getDevice().getName();

        // 获取宽度
        String scrcpyWidth=widthTextField.getText();
        // 如果存在id
        if (id != null && !"".equals(id)) {
            System.out.println("scrcpy.exe 打开镜像");
            // 拼接命令
            String code;

            if(scrcpyWidth!=null&&scrcpyWidth.matches("\\d+")){
                code = "adbtools_open_scrcpy.bat " + id + " " + simpleId+ " " +scrcpyWidth;
            }else {
                code = "adbtools_open_scrcpy.bat " + id + " " + simpleId;
            }
            System.out.println("调用另一个Jar:" + code);
            CmdRun.run(code);
        }
        System.out.println("scrcpy.exe 已关闭");
    }
}
