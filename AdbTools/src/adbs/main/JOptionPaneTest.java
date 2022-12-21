package adbs.main;

import adbs.cmd.AdbCommands;
import adbs.main.auto.listener.Device;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class JOptionPaneTest {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JPanel jPanel = new JPanel();
        JTextField jTextField = new JTextField();
        jTextField.setColumns(30);
        jPanel.add(jTextField);
        frame.add(jPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();


        ArrayList<String> idList = new ArrayList<>();
        String devicesListStr = AdbCommands.runAbdCmd("adb devices -l");
        // 分析adb devices -l命令结果
        Scanner scanner = new Scanner(devicesListStr);
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            // System.out.println("line = " + line);
            // List of devices attached表示没有设备，
            // 如果是设备输出信息
            if (!line.equals("List of devices attached") && !"".equals(line)) {
                // 按两个或者更多的空格符作为分界 来分割字符串
                String[] deviceStrs = line.split("[ ]{2,}");
                // System.out.println("ID = " + deviceStrs[0]);
                // System.out.println("dir = " + deviceStrs[1]);
                // 分割得到的第1段是设备id，第2段是设备的描述信息
                Device device = new Device(deviceStrs[0], deviceStrs[1]);
                idList.add(device.getSimpleId());
            }
        }

        System.out.println("idList = " + idList);


        // showConfirmDialog();
        Component parentComponent = jPanel;
        // Component parentComponent = null;
        String message = "请选择";
        String title = "弹出多选择框";
        // int optionType = JOptionPane.YES_NO_CANCEL_OPTION;
        int optionType = JOptionPane.YES_OPTION;
        int messageType = JOptionPane.PLAIN_MESSAGE;
        Icon icon = null;
        // String[] options = {"HonorWiFi", "RedmiWiFi"};
        String[] options = idList.toArray(new String[idList.size()]);
        int initialValue = 0;
        int optionDialogReturn = JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue);
        // switch (optionDialogReturn) {
        //     case 0:
        //         frame.setTitle(options[0]);
        //         break;
        //     case 1:
        //         frame.setTitle(options[1]);
        //         break;
        //     default:
        //         break;
        // }
        // for (int i = 0; i < options.length; i++) {
        //     if(optionDialogReturn==i){
        //         frame.setTitle(options[i]);
        //     }
        // }
        frame.setTitle(options[optionDialogReturn]);
        // System.out.println("你选择了" + frame.getTitle());
        // jTextField.setText(frame.getTitle());
    }

    private static void showConfirmDialog() {
        int returnVal = JOptionPane.showConfirmDialog(null, "已经退出，是否继续");
        // System.out.println(returnVal);
        // System.out.println("JOptionPane.OK_OPTION = " + JOptionPane.OK_OPTION);
        // if (returnVal == JOptionPane.OK_OPTION) {
        //     System.out.println("选择确认");
        // }
        switch (returnVal) {
            case JOptionPane.OK_OPTION:
                System.out.println("选择 确认(Y Yes)");
                break;
            case JOptionPane.CANCEL_OPTION:
                System.out.println("选择取消");
                break;
            case JOptionPane.NO_OPTION:
                System.out.println("选择(N No)");
                break;
        }
    }
}
