package runnabletools.install;

import adbs.cmd.AdbCommands;
import adbs.cmd.CmdRun;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;
import tools.constant.QuanJiao;
import tools.copy.SystemClipboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.Charset;
import java.util.*;

public class AdbInstall {

    // private static Scanner sysInscan = new Scanner(System.in);
    // private static Scanner sysInscan = new Scanner(System.in);
    // private static Scanner sysInscan = new Scanner(System.in, "UTF-8");
    private static Scanner sysInscan = new Scanner(System.in, "GBK");

    /**
     * 要执行的adb命令
     */
    private static String command = "";


    public static void main(String[] args) {

        // String encoding = System.getProperty("file.encoding");
        // System.out.println("encoding = " + encoding);

        // String chcp = CmdRun.run("chcp").trim();
        // System.out.println("chcp = " + chcp + "|");
        //
        // System.out.println("Charset.defaultCharset() = " + Charset.defaultCharset());


        // 执行 adb devices -l 命令获取设备名称和设备对象的map
        LinkedHashMap<String, Device> deviceMap = getDeviceLinkedHashMap();
        // String name = getUserSelectedName(deviceMap);
        Set<String> deviceNameSet = deviceMap.keySet();
        String name = null;
        if (args.length == 1) {
            String arg0 = args[0];
            System.out.println("args[0] = " + arg0);
            Iterator<String> iterator = deviceNameSet.iterator();
            boolean isLegalDeviceName = false;
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (next.equals(arg0)) {
                    isLegalDeviceName = true;
                    name = arg0;
                }
            }
            if (!isLegalDeviceName) {
                System.out.println("当前电脑上没有设备名:'" + arg0 + "'的设备!");
                System.out.println("当前电脑连接了如下设备:");
                // 获取用户选择的设备名称
                name = getUserSelectedName(deviceMap);
            }
        } else {
            // 获取用户选择的设备名称
            name = getUserSelectedName(deviceMap);
        }
        // 根据用户选择的设备名称从map中取出 设备对象
        Device device = deviceMap.get(name);
        // 给adb命令添加序列号选项,把设备对象中的序列号添加到adb命令中
        addSerialToAdbCommand(device);
        // 列出apk列表让用户进行安装
        listApksToInstall();
    }

    public static String getUserSelectedName(LinkedHashMap<String, Device> name_Device_map) {
        // 获取设备名称set集合
        Set<String> keySet = name_Device_map.keySet();
        // 打印设备名称列表
        printSet(keySet);
        String selectedDeviceName = "";
        // 让用户输入选项选择设备
        System.out.print("输入设备编号:");
        // 读取用户输入
        String userInput = sysInscan.nextLine();
        if (userInput.matches("\\d+")) {
            int parseInt = Integer.parseInt(userInput);
            System.out.println("parseInt = " + parseInt);
            if (parseInt >= 0 && parseInt < name_Device_map.size()) {
                selectedDeviceName = getUserSelected(keySet, parseInt);
            }
        }
        // 从设备名set中取出用户选择的设备名称
        System.out.println("-------------------------");
        return selectedDeviceName;
    }

    /**
     * 根据用户选择的设备，给adb命令添加序列号选项
     *
     * @param device 用户选择的设备
     */
    private static void addSerialToAdbCommand(Device device) {
        // System.out.println("device = " + device);
        // 拼接adb命令
        command = "adb -s " + device.getSerial() + " install";
        System.out.println(command);
        System.out.println("-------------------------");
    }

    /**
     * 列出电脑上的apk列表，并让用户选择某个apk进行安装
     */
    private static void listApksToInstall() {
        File apkDir = new File("D:\\网络共享\\可读可写\\apk");
        if (apkDir.isDirectory()) {
            // 获取.apk列表
            String[] apkArray = apkDir.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".apk");
                }
            });


            System.out.print("输入要安装的apk的部分名称(只输入回车将列出所有apk):");
            String apkName = sysInscan.nextLine();
            System.out.println("apkName = " + apkName);
            if ("".equals(apkName)) {
                System.out.println("没有输入apk命令,列出所有apk：");
                selectApkToInstall(apkArray);
            } else {
                // System.out.println(apkName);
                ArrayList<String> apkFinded = new ArrayList<>();
                for (String s : apkArray) {

                    if (s.toLowerCase().contains(apkName.toLowerCase())) {
                        apkFinded.add(s);
                    }
                }
                if (apkFinded.size() > 0) {
                    String[] apkFindedArr = new String[apkFinded.size()];
                    System.out.println("名称里包含：'" + apkName + "'的apk有：");
                    // 转换成数组
                    apkFinded.toArray(apkFindedArr);
                    // // 输出.apk列表
                    printAkpsList(apkFindedArr);
                    // 进行安装
                    selectApkToInstall(apkFindedArr);
                } else {
                    System.out.println("电脑中没有apk的名称中包含 '" + apkName + "',请先下载到电脑中，或者直接在手机上下载");
                    ThreadSleep.seconds(15);
                }

            }
        }
    }

    /**
     * 打印apk数组，让用户选择其中一个
     *
     * @param apkArray apk数组
     */
    private static void selectApkToInstall(String[] apkArray) {
        // // 输出.apk列表
        // printAkpsList(apkArray);
        System.out.print("输入要安装的apk的编号:");
        // 读取用户输入的编号
        String userInput = sysInscan.nextLine();
        System.out.println("-------------------------");

        // 如果输入的是数字
        if (userInput.matches("\\d+")) {
            // 解析成整数
            int apkSelected = Integer.parseInt(userInput);
            // 如果是合法的下标
            if (apkSelected >= 0 && apkSelected < apkArray.length) {
                // 执行adb install命令
                runAdbInstallCommand(apkArray[apkSelected]);
            } else {
                throw new ArrayIndexOutOfBoundsException("请输入列表中的apk编号");
            }
        }
    }

    /**
     * 执行adb install命令安装指定的apk
     *
     * @param apk apk名称
     */
    private static void runAdbInstallCommand(String apk) {
        command += " " + apk;
        command = "D: && cd D:\\网络共享\\可读可写\\apk &&" + command;
        System.out.println("command = " + command);
        SystemClipboard.setSysClipboardText(command);

        // // 执行安装命令
        String run = CmdRun.run(command);
        System.out.println("run = " + run);


        // ThreadSleep.minutes(1);
        // Threads.sleep(60);
    }

    /**
     * 获取keySet中的第index个元素
     *
     * @param keySet
     * @param index
     * @return
     */
    private static String getUserSelected(Set<String> keySet, int index) {
        String key;
        if (index >= 0 && index < keySet.size()) {
            int count = 0;
            Iterator<String> iterator = keySet.iterator();
            String next = "";
            do {
                next = iterator.next();
                count++;
            } while (count < index + 1);
            System.out.println("next = " + next);
            key = next;

        } else {
            // System.out.println("下标越界");
            throw new ArrayIndexOutOfBoundsException();
            // return;
        }
        return key;
    }


    /**
     * 执行adb devices -l命令，获取设备名和设备的map
     *
     * @return
     */
    public static LinkedHashMap<String, Device> getDeviceLinkedHashMap() {
        //
        LinkedHashMap<String, Device> name_Device_map = new LinkedHashMap<>();
        // 执行adb命令
        String adbDevicesL = AdbCommands.runAbdCmd("adb devices -l");

        // 分析adb devices -l命令结果
        Scanner scanner = new Scanner(adbDevicesL);
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
                // idList.add(device.getName());
                // 把id和设备作为键值对放入map中
                name_Device_map.put(device.getName(), device);
            }
        }
        scanner.close();
        return name_Device_map;
    }

    /**
     * 打印apk列表
     *
     * @param apkArray
     */
    private static void printAkpsList(String[] apkArray) {

        System.out.println("-------------------------");
        for (int i = 1; i <= apkArray.length; i++) {
            // System.out.printf("%3d " + "%-35s\n", i, getString(apkArray[i]));
            System.out.printf("%3d " + "%-35s\n", (i - 1), apkArray[i - 1]);
        }
        // System.out.println();
        System.out.println("-------------------------");
    }

    private static String getString(String input) {
        int zhongwen = 0;
        int yingwen = 0;
        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            if (isASCII(charAt)) {
                yingwen++;
                // System.out.println("英文:"+charAt);
            } else {
                zhongwen++;
                // System.out.println("中文:"+charAt);
            }
        }

        // 如果英文的个数不是偶数个
        if (yingwen % 2 != 0) {
            input = input + " ";
        }
        if (zhongwen % 2 != 0) {
            input = input + QuanJiao.kongGe;
        }
        return input;
    }

    /**
     * 判断是否是ASCII字符
     *
     * @param charAt
     * @return
     */
    private static boolean isASCII(char charAt) {
        // return charAt >= '0' && charAt <= '9' || charAt >= 'a' && charAt <= 'z' || charAt >= 'A' && charAt <= 'Z';
        // return charAt >= ' ' && charAt <= '~';
        return charAt >= 32 && charAt <= 126;
    }

    private static void printSet(Set<String> keySet) {
        System.out.println("-------------------------");
        Iterator<String> iterator = keySet.iterator();
        for (int i = 0; iterator.hasNext(); i++) {

            System.out.println("  " + i + "\t" + iterator.next());
        }
        System.out.println("-------------------------");
    }

}