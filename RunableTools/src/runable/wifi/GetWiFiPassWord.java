package runable.wifi;

/**
 * 获取windows电脑，连接过的WiFi的密码
 * 注意,此代码需要在cmd中运行，在ide中运行可能不会有效果。
 * 所以请打开cmd,然后javac编译本代码，然后再使用java命令运行。
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GetWiFiPassWord {
    /**
     * @param commandStr cmd 控制台命令
     * @return 该控制台命令commandStr运行的结果
     */
    public static String exeCmd(String commandStr) {
        String result = null;
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            // System.out.println(sb.toString());
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * @throws FileNotFoundException
     */
    public static void printWiFiPassWord(String result)
            throws FileNotFoundException {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(result);
        String line;
        String wifi;
        String passworld;
        while ((line = scanner.nextLine()) != null) {
            // SSID 名称 :“Hello”
            if (line.contains("SSID 名称")) {
                wifi = line.substring(line.lastIndexOf("“") + 1,
                        line.length() - 1);
                System.out.println("无线:" + wifi.trim());// trim()去掉多余的空白符
            }
            // 关键内容 : *********
            else if (line.contains("关键内容")) {
                passworld = line.substring(line.lastIndexOf(":") + 1);
                System.out.println("密码:" + passworld.trim());// trim()去掉多余的空白符
            }
        }
    }

    public static String getWiFiMap(String result) throws FileNotFoundException {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(result);
        String line;
        String wifi;
        String passworld;
        StringBuilder buff = new StringBuilder();
        HashMap<String, String> WiFiMap = new HashMap<String, String>();
        try {
            /*
             * 接口 WLAN 上的配置文件 哦: --->WiFi名是哦,位于"接口 WLAN 上的配置文件"这句话和冒号之间
             */
            // 有这句话说明包含有密码
            String WiFiNameLineFlag = "接口 WLAN 上的配置文件";
            // 捕获java.util.NoSuchElementException
            while ((line = scanner.nextLine()) != null) {
                // SSID 名称 :“Hello”

                if (line.contains(WiFiNameLineFlag)) {
                    wifi = line.substring(
                            line.lastIndexOf(WiFiNameLineFlag)
                                    + WiFiNameLineFlag.length(),
                            line.lastIndexOf(":"));
                    // System.out.print("无线:"+wifi.trim());//trim()去掉多余的空白符
                    buff.append("无线:" + wifi.trim() + "|");
                }
                // 关键内容 : *********
                if (line.contains("关键内容")) {
                    passworld = line.substring(line.lastIndexOf(":") + 1);
                    // System.out.println("|密码:"+passworld.trim());//trim()去掉多余的空白符
                    buff.append("密码:" + passworld.trim());
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return buff.toString();
    }

    /**
     * 获取连接过的WiFi的名称列表。
     *
     * @return 所有连接过的WiFi名称列表
     */
    public static ArrayList<String> getWiFiNameList() {
        String allWiFiName = "netsh wlan show profiles";
        String cmdResult = GetWiFiPassWord.exeCmd(allWiFiName);
        Scanner scanner = new Scanner(cmdResult);// 扫描结果
        ArrayList<String> WiFiNameList = new ArrayList<String>();
        String line = null;
        try {
            // 会抛出异常 java.util.NoSuchElementException:
            while ((line = scanner.nextLine()) != null) {
                // System.out.println(line);
                if (line.contains(":")) {
                    String name = line.substring(line.lastIndexOf(":") + 1)
                            .trim();
                    // :后面没有名字的表示这只是个冒号，不是我们想要的WiFi名
                    if (!name.equals(""))
                        WiFiNameList.add(name);
                }
            }
        } catch (Exception e) {
            // 不做处理，这里是为了让程序能运行下去
            // TODO: handle exception
        }
        return WiFiNameList;
    }

    /**
     * cmd查询name对应的WiFi名称配置文件，并返回cmd执行的结果
     *
     * @param name
     * @return
     */
    public static String getPassWordByName(String name) {
        String commandStr = "netsh wlan show profile name=" + name
                + " key=clear";
        String result = GetWiFiPassWord.exeCmd(commandStr);
        return result;
    }

    public static void main(String[] args)
            throws FileNotFoundException, InterruptedException {
        // 保存下标准输出流
        PrintStream out = System.out;
        System.out.println("请勿关闭当前窗口");
        System.out.println("正在生成WiFi密码文件...");
        String outFile = "所有连过的WiFi密码.txt";
        StringBuffer sb = new StringBuffer();
        String line;
        // 获取WiFi名列表
        ArrayList<String> WiFiNameList = getWiFiNameList();
        for (String string : WiFiNameList) {
            // 根据每个WiFi列表中的WiFi名称，获取WiFi的密码
            line = getWiFiMap(getPassWordByName(string));
            System.out.println(line);
            sb.append(line).append("\n");
        }


        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File("所有连过的WiFi密码.txt")));) {

            writer.write(sb.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 恢复到原来的标准输出流
        System.out.println("以生成WiFi密码文件,路径:.\\所有连过的WiFi密码.txt");
    }

}
