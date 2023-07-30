package runnabletools.serial;

/**
 * 给adb命令填充序列号
 */

import adbs.cmd.AdbCommands;
import adbs.model.Device;
import tools.copy.SystemClipboard;

import java.util.*;
import java.util.function.Consumer;

/**
 * 补全器
 * filltor
 */
public class AddSerial {
    public static void main(String[] args) {
        // 获取剪贴板中的文本
        String sysClipboardText = SystemClipboard.getSysClipboardText();
        System.out.println("-------------------------");
        System.out.println("剪贴板内容:" + sysClipboardText);
        // 如果剪贴板中的文本以adb开头
        if (canAddASerialNumber(sysClipboardText)) {
            // 设备序列号列表
            ArrayList<String> NameList = new ArrayList<>();
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
                    NameList.add(device.getName());
                    // 把id和设备作为键值对放入map中
                    simpleId_Device_map.put(device.getName(), device);
                }
            }


            // 打印设备的简称列表
            System.out.println("-------------------------");
            for (int i = 0; i < NameList.size(); i++) {
                System.out.println(i + "\t" + NameList.get(i));
            }
            System.out.println("-------------------------");

            System.out.println("-------------------------");
            System.out.println("通过map打印");
            Set<Map.Entry<String, Device>> entries = simpleId_Device_map.entrySet();
            entries.forEach(new Consumer<Map.Entry<String, Device>>() {
                @Override
                public void accept(Map.Entry<String, Device> stringDeviceEntry) {
                    System.out.println(stringDeviceEntry.getKey());
                }
            });
            System.out.println("-------------------------");


            // 要求用户输入
            System.out.print("输入设备编号:");

            // 读取用户输入
            Scanner scanner1 = new Scanner(System.in);
            String inputNumber = scanner1.nextLine();
            System.out.println("-------------------------");
            // 如果输入的是数字
            if (inputNumber.matches("\\d+")) {
                // 获取设备编号
                int selected = Integer.parseInt(inputNumber);
                if (selected >= 0 && selected <= NameList.size()) {
                    // 获取选中的设备
                    Device device = simpleId_Device_map.get(NameList.get(selected));
                    String adb_ = null;
                    //如果是adb命令
                    if (sysClipboardText.startsWith("adb")) {
                        // 如果adb命令中已经带有序列号了
                        if (sysClipboardText.startsWith("adb -s")) {
                            System.out.println("---修改adb命令中的序列号---");
                            // System.out.println("-------------------------");
                            adb_ = sysClipboardText.replaceAll("adb -s [a-zA-Z0-9:.]+ ", "adb -s " + device.getSerial() + " ");
                        } else {
                            System.out.println("----给adb命令添加序列号----");
                            adb_ = sysClipboardText.replace("adb ", "adb -s " + device.getSerial() + " ");
                            System.out.println("修改为如下adb命令:");
                        }
                    } else if (sysClipboardText.contains("scrcpy")) {
                        // 如果adb命令中已经带有序列号了
                        if (sysClipboardText.contains(" -s ")) {
                            System.out.println("---修改adb命令中的序列号---");
                            // System.out.println("-------------------------");
                            adb_ = sysClipboardText.replaceAll("scrcpy(?:.exe)? -s [a-zA-Z0-9:.]+ ", "scrcpy.exe -s " + device.getSerial() + " ");
                        } else {
                            System.out.println("----给adb命令添加序列号----");
                            adb_ = sysClipboardText.replaceAll("scrcpy(?:.exe)? ", "scrcpy.exe -s " + device.getSerial() + " ");
                            System.out.println("修改为如下adb命令:");
                        }
                    } else if (sysClipboardText.contains("gnirehtet")) {
                        adb_ = sysClipboardText.replaceAll("(.*)(gnirehtet )(stop|start|restart) ([a-zA-Z0-9]+)$", "$1$2$3 " + device.getSerial());
                    }
                    // System.out.println("-------------------------");
                    System.out.println("修改前:" + sysClipboardText);
                    System.out.println("修改后:" + adb_);
                    //写会剪贴板
                    SystemClipboard.setSysClipboardText(adb_);
                }
            }
            System.out.println("-------------------------");
            scanner1.close();
            scanner.close();
        } else {
            System.err.println("输入错误，请复制要修改的adb命令到剪贴板中！");
        }
    }

    /**
     * 判断命令是否可以加入序列号
     *
     * @param sysClipboardText
     * @return
     */
    private static boolean canAddASerialNumber(String sysClipboardText) {
        return sysClipboardText.contains("adb") || sysClipboardText.contains("scrcpy") || sysClipboardText.contains("gnirehtet");
    }
}
