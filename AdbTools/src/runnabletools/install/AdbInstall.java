package runnabletools.install;

import adbs.cmd.AdbCommands;
import adbs.cmd.CmdRun;
import adbs.model.Device;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class AdbInstall {
    public static void main(String[] args) {
        // 设备序列号列表
        ArrayList<String> idList = new ArrayList<>();
        // 设备别名
        LinkedHashMap<String, Device> simpleId_Device_map = new LinkedHashMap<>();
        // 执行adb命令
        String devicesListStr = AdbCommands.runAbdCmd("adb devices -l");

        // 分析adb devices -l命令结果
        Scanner scanner = new Scanner(devicesListStr);
        String line;
        while (scanner.hasNextLine()) {
            // 逐行读入
            line = scanner.nextLine();
            // System.out.println("line = " + line);
            // List of devices attached表示没有设备，
            // 如果是设备输出信息
            if (!line.equals("List of devices attached") && !"".equals(line)) {
                // 按两个或者更多的空格符作为分界 来分割字符串
                String[] deviceStrs = line.split("[ ]{2,}");
                // System.out.println("ID = " + deviceStrs[0]);
                // System.out.println("dir = " + deviceStrs[1]);
                // 创建设备对象
                // 分割得到的第1段是设备id，第2段是设备的描述信息
                Device device = new Device(deviceStrs[0], deviceStrs[1]);
                // 添加设备的id到列表中
                idList.add(device.getName());
                // 把id和设备作为键值对放入map中
                simpleId_Device_map.put(device.getName(), device);
            }
        }
        // 打印设备的简称列表
        System.out.println("-------------------------");
        for (int i = 0; i < idList.size(); i++) {
            System.out.println(i + "\t" + idList.get(i));
        }
        System.out.println("-------------------------");
        scanner.close();

        // 要求用户输入
        System.out.print("输入设备编号:");
        // 读取用户输入
        Scanner scanner1 = new Scanner(System.in);
        String inputNumber = scanner1.nextLine();
        System.out.println("-------------------------");
        String command = "";
        // 如果输入的是数字
        if (inputNumber.matches("\\d+")) {
            int selected = Integer.parseInt(inputNumber);
            if (selected >= 0 && selected <= idList.size()) {
                // 获取选中的设备
                Device device = simpleId_Device_map.get(idList.get(selected));
                System.out.println("device = " + device);
                command = "adb -s " + device.getSerial() + " install";
                System.out.println(command);
                System.out.println("-------------------------");
                // scanner1.close();

                File apkDir = new File("D:\\网络共享\\只读");
                if (apkDir.isDirectory()) {
                    // 获取.apk列表
                    String[] apkArray = apkDir.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return name.endsWith(".apk");
                        }
                    });

                    System.out.println("-------------------------");
                    for (int i = 0; i < apkArray.length; i++) {
                        System.out.println(i + "\t" + apkArray[i]);
                    }
                    System.out.println("-------------------------");
                    System.out.print("输入要安装的apk的编号:");
                    inputNumber = scanner1.nextLine();
                    // 如果输入的是数字
                    if (inputNumber.matches("\\d+")) {
                        selected = Integer.parseInt(inputNumber);
                        // 如果输入的是下标
                        if (selected >= 0 && selected < apkArray.length) {
                            command += " " + apkArray[selected];
                            command = "D: && cd D:\\网络共享\\只读 &&" + command;
                            System.out.println("command = " + command);
                            // 执行安装命令
                            String run = CmdRun.run(command);
                            System.out.println("run = " + run);
                        }
                    }


                }
            }
        }
    }
}