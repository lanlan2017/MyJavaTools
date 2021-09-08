package tools.markdown.niuke;

import java.io.*;
import java.util.Properties;

/**
 * 牛客网习题编号
 */
public class NiukeConfig {
    // 试题编号
    private static int number;
    private static final Properties properties;
    // 当前工作目录下的niuke.properties
    private static String configPath = System.getProperty("user.dir") + File.separator + "niuke.properties";

    static {
        properties = new Properties();
        try {
            System.out.println("读取配置文件:" + configPath);
            InputStream in = new FileInputStream(configPath);
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
            System.out.println("写入配置文件:" + configPath);
            OutputStream out = new FileOutputStream(configPath);
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
