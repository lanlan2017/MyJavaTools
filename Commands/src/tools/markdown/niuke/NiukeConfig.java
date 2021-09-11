package tools.markdown.niuke;

import tools.string.PrintStr;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * 牛客网习题编号
 */
public class NiukeConfig {
    // 试题编号
    private static int number;
    private static final Properties properties;
    // 当前工作目录下的niuke.properties
    private static File configFile;

    static {
        // 如果运行在.jar包中
        if (isRunInJar()) {
            // 运行在.jar中
            System.out.println("运行在.jar中");
            // 到.jar文件所在的目录下查找配置文件。
            configFile = new File(ToolsJarPath.getPath() + File.separator + "niuke.properties");
        } else {
            // 运行在IDE中时
            System.out.println("运行在IDE中");
            configFile = new File("niuke.properties");
            // 默认在当前目录下查找配置文件
        }
    }

    /**
     * 判断当前类是否运行在.jar文件中。
     *
     * @return 如果当前类运行在.jar文件中，则返回true,否则返回false。
     */
    private static boolean isRunInJar() {
        URL url = NiukeConfig.class.getResource("");
        String protocol = url.getProtocol();
        if ("jar".equals(protocol)) {
            // // 运行在.jar中
            // System.out.println("运行在.jar中");
            return true;
        }
        // else if ("file".equals(protocol)) {
        //     // 非jar 中 （文件class 中）
        //     System.out.println("运行在IDE中");
        //     return false;
        // }
        return false;
    }


    static {
        properties = new Properties();
        try {
            // System.out.println("读取配置文件:" + configFile.getAbsolutePath());
            InputStream in = new FileInputStream(configFile);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 取得配置文件中的试题编号
        number = Integer.parseInt(properties.getProperty("number"));
    }

    public static int getNumber() {
        // 取得配置文件中的编号
        int number = NiukeConfig.number;
        // 配置文件中的编号加1
        setNumber(number + 1);
        return number;
    }

    private static void setNumber(int number) {
        NiukeConfig.number = number;
        properties.setProperty("number", "" + number);
        try {
            // System.out.println("写入配置文件:" + configFile.getAbsolutePath());
            OutputStream out = new FileOutputStream(configFile);
            // 保存配置
            properties.store(out, "牛客网习题编号");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重置习题编号
     */
    public static int reset() {
        setNumber(2);
        return 1;
    }

    //习题编号
    public static void main(String[] args) {
        int number;
        number = getNumber();
        System.out.println(number);
        number = getNumber();
        System.out.println(number);
    }
}
