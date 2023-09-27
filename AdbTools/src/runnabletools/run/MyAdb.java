package runnabletools.run;

import adbs.cmd.AdbCommands;
import adbs.cmd.CmdRun;
import adbs.main.run.IsTest;
import adbs.model.Device;
import tools.copy.SystemClipboard;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

public class MyAdb {

    private static Scanner sysInSc = new Scanner(System.in);

    public static void main(String[] args) {
        // SystemClipboard.setSysClipboardText("adb devices -l");
        test();
        if (args.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < args.length; i++) {
                sb.append(args[i]).append(" ");
            }
            String argstr = sb.toString().trim();
            System.out.println("argstr = " + argstr);
            // String adb_ = "adb ";
            // if (argstr.startsWith(adb_)) {
            //     argstr = argstr.substring(argstr.indexOf(adb_) + adb_.length());
            //
            // }
            // adb -s BBBDU18106019646 shell dumpsys activity | findstr "mResume"
            cAdbs(argstr);

            // if()
        } else {

            String clipboardText = SystemClipboard.getSysClipboardText().trim();
            System.out.println("clipboardText = " + clipboardText);
            cAdbs(clipboardText);
        }


        sysInSc.close();
    }

    private static void cAdbs(String clipboardText) {
        if (isAdb(clipboardText)) {
            String adb_devices = CmdRun.run("adb devices").trim();
            // String adb_devices = AdbCommands.runAbdCmd("adb devices").trim();
            // System.out.println("adb_devices = |" + adb_devices + "|");
            // 获取命令输出的行数
            int lines = numberOfRows(adb_devices);
            // System.out.println("lines = " + lines);
            if (lines == 1) {
                noDevice();
            } else if (lines == 2) {
                onlyOneDevice(clipboardText);
            } else if (lines >= 3) {
                multipleDevices(clipboardText);
            }

            // AdbCommands.runAbdCmd(clipboardText);
        }
    }

    private static void noDevice() {
        System.out.println("电脑上没有连接Android设备，adb.exe命令执行也是没有的");
    }

    /**
     * 测试时使用，运行时忽略
     */
    private static void test() {
        if (IsTest.isIsTest()) {
            // SystemClipboard.setSysClipboardText("adb shell dumpsys window | findstr mCurrentFocus");
            // SystemClipboard.setSysClipboardText("adb -s 621QACQC3962X shell dumpsys window | findstr mCurrentFocus");
            SystemClipboard.setSysClipboardText("adb -s 621QACQC3962X");

        }

    }

    /**
     * 有多个设备时需要的操作。
     *
     * @param clipboardText
     */
    private static void multipleDevices(String clipboardText) {

        // System.out.println("电脑上连接多个Android设备，执行adb.exe命令时需要指定设备");

        String command = getCommand(clipboardText);

        LinkedHashMap<String, Device> nameDeviceMap = getNameDeviceMap();
        Set<String> nameSet = nameDeviceMap.keySet();
        String[] name = nameSet.toArray(new String[nameSet.size()]);
        printingOptions(name, "设备列表");
        int inputNum = enterCorrectOption(name, "输入设备编号");
        // System.out.println("enterCorrectOption = " + enterCorrectOption);
        // if (enterCorrectOption >= 0 && enterCorrectOption < name.length) {
        replaceAndExecution(clipboardText, command, getSerial(nameDeviceMap, name[inputNum]));
        // }


    }

    /**
     * 输入正确的数组下标
     *
     * @param arr
     * @param info 标题,提示信息
     * @return 数组下标
     */
    private static int enterCorrectOption(String[] arr, String info) {
        int inputNum = 0;
        do {
            inputNum = enterNumber(info);
        } while (!(inputNum >= 0 && inputNum < arr.length));
        return inputNum;
    }

    /**
     * @param name
     * @param info
     */
    private static void printingOptions(String[] name, String info) {
        System.out.println("---------------" + info + "---------------");
        // String[] names=new String[nameSet.size()];
        // String[] strings = nameSet.toArray(names);
        // printInputs(nameSet);

        for (int i = 0; i < name.length; i++) {
            System.out.printf("%3s:%s\n", i, name[i]);
        }
        System.out.println("------------------------------------");
    }

    private static void replaceAndExecution(String clipboardText, String head, String serial1) {
        String serial = serial1;

        String tail = "";
        if (clipboardText.contains(" -s ")) {
            String adb_s = "adb -s ";
            if (clipboardText.matches(adb_s + "[0-9A-Za-z]+ .+")) {
                // System.out.println("1111");
                tail = clipboardText.substring(clipboardText.indexOf(adb_s) + adb_s.length());
                tail = tail.substring(tail.indexOf(" ") + 1);
                // System.out.println("tail = |" + tail + "|");
            }
        } else {
            String adb_ = "adb ";
            if (clipboardText.startsWith(adb_)) {
                // System.out.println("2222");
                tail = clipboardText.substring(clipboardText.indexOf(adb_) + adb_.length());
                // System.out.println("tail = |" + tail + "|");
                // System.out.println("code = |" + code + "|");
            }

        }

        String code = head + " -s " + serial + " " + tail;
        System.out.println("原命令 = " + clipboardText);
        System.out.println("新命令 = " + code);
        SystemClipboard.setSysClipboardText(code);
        // run(code);


    }

    /**
     * 获取要改变的命令
     * command
     *
     * @return
     */
    private static String getCommand(String clipboardText) {
        String command;
        if (clipboardText.matches("adb -s [0-9A-Za-z]+$")) {
            System.out.println("可切换命令");
            String[] commands = {"adb", "scrcpy"};
            printingOptions(commands, "命令列表");
            int inputNum = enterCorrectOption(commands, "输入命令编号");
            command = commands[inputNum];
        } else {
            System.out.println("不可切换命令");
            command = "adb";
        }
        return command;
    }

    /**
     * 只有一个设备时的操作
     *
     * @param clipboardText
     */
    private static void onlyOneDevice(String clipboardText) {
        System.out.println("电脑上只连接一个Android设备，可直接执行adb.exe命令");
        System.out.println("原命令 = " + clipboardText);
        run(clipboardText);
    }

    private static void run(String code) {
        System.out.print("是否执行命令(y/n):");
        // sysInSc = new Scanner(System.in);
        String nextLine = sysInSc.nextLine();
        if (nextLine.equalsIgnoreCase("y")) {
            String adbOut = AdbCommands.runAbdCmd(code);
            System.out.println(adbOut);
        }
    }

    private static String getSerial(LinkedHashMap<String, Device> nameDeviceMap, String nameSelected1) {
        String nameSelected = nameSelected1;
        Device deviceSelected = nameDeviceMap.get(nameSelected);
        return deviceSelected.getSerial();
    }

    /**
     * enterNumber
     *
     * @param info
     * @return
     */
    private static int enterNumber(String info) {
        // Scanner scanner = new Scanner(System.in);
        // if (nextLine.matches("\\d+")) {
        //
        // }
        String nextLine;
        do {

            System.out.print(info + ":");
            nextLine = sysInSc.nextLine();
        } while (!nextLine.matches("\\d+"));
        // int enterCorrectOption = scanner.nextInt();
        // int enterCorrectOption = sysInSc.nextInt();
        int inputNum = Integer.parseInt(nextLine);
        return inputNum;
    }

    private static void printInputs(Set<String> nameSet) {
        int i = 0;
        for (String s : nameSet) {
            System.out.printf("%3s:%s\n", i++, s);
        }
    }

    /**
     * 获取设备列表
     * 获取设备map集合。
     *
     * @return
     */
    private static LinkedHashMap<String, Device> getNameDeviceMap() {
        // 设备别名
        LinkedHashMap<String, Device> name_Device_map = new LinkedHashMap<>();
        // 执行adb命令
        // String devicesListStr = AdbCommands.runAbdCmd("adb devices -l");
        String devicesListStr = CmdRun.run("adb devices -l");

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
                // 把id和设备作为键值对放入map中
                name_Device_map.put(device.getName(), device);
            }
        }
        return name_Device_map;
    }

    /**
     * 获取字符串的行数
     *
     * @param str 需要数行数的字符串
     * @return 字符串的行数，默认有一行。
     */
    private static int numberOfRows(String str) {
        // int lines = 0;
        int lines = 1;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\n') {
                lines++;
            }
        }
        // lines++;
        return lines;
    }

    private static boolean isAdb(String clipboardText) {
        // return true;
        return clipboardText.startsWith("adb") || clipboardText.startsWith("scrcpy");
    }
}
