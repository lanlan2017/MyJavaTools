package config;

import adbs.cmd.CmdRun;
import tools.config.properties.PropertiesTools;

import java.util.Scanner;

public class AdbConnectPortProperties {
    // public static PropertiesTools portPro_ = new PropertiesTools("Adbtools_money_apk.properties");
    public static PropertiesTools propertiesTools = new PropertiesTools("AdbTools_Port.properties");
    private static String key = "NextPort";


    public static void main(String[] args) {
        // // 重置 adb连接的默认端口号
        // resetPort();
        // // 端口号加一
        // nextPort();

        String port = getPort();


        System.out.println("port = " + port);
    }

    public static String getPort() {
        String port;
        boolean adbTools_jar_running = isAdbTools_Jar_Running();
        // System.out.println(adbTools_jar_running);
        if (adbTools_jar_running) {
            port = nextPort();
        } else {
            port = resetPort();
        }
        return port;
    }

    /**
     * 判断是否有其他的Adbtools.jar正在运行。
     *
     * @return 如果存在正在运行的Adbtools.jar，则返回true，否则返回false。
     */
    private static boolean isAdbTools_Jar_Running() {
        boolean isOtherExistence = false;
        String code = "jps -l";
        String jps_l_output = CmdRun.run(code);
        // System.out.println("jps_l_output = " + jps_l_output);

        Scanner scanner = new Scanner(jps_l_output);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.endsWith("AdbTools.jar")) {
                isOtherExistence = true;
                break;
            }
        }
        return isOtherExistence;
    }

    /**
     * 设置配置文件中的端口号为默认值："5555"
     *
     * @return 默认端口号"5555"
     */
    private static String resetPort() {
        String value = "5555";
        propertiesTools.setProperty(key, value);
        propertiesTools.store();
        return value;
    }

    /**
     * 将配置文件中的端口号加一后返回，并把更新后的端口号保存回配置文件中。
     *
     * @return 配置文件中保存的端口号加一之后得到值
     */
    private static String nextPort() {

        String value = propertiesTools.getProperty(key);
        // System.out.println("value = " + value);
        int port = Integer.parseInt(value);
        String nextPort = String.valueOf(port + 1);
        // System.out.println("nextPort = " + nextPort);

        propertiesTools.setProperty(key, nextPort);
        // 保存到配置文件中
        propertiesTools.store();
        return nextPort;
    }

}
