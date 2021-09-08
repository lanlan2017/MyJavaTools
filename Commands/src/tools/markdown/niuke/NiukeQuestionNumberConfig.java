package tools.markdown.niuke;

import java.io.*;
import java.util.Properties;

/**
 * 牛客网习题编号
 */
public class NiukeQuestionNumberConfig {
    // 试题编号
    private static int number;
    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            InputStream in = new FileInputStream("niuke.properties");
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 取得配置文件中的试题编号
        number = Integer.parseInt(properties.getProperty("number"));
    }

    public static int getNumber() {
        // 取得配置文件中的编号
        int number = NiukeQuestionNumberConfig.number;
        // 配置文件中的编号加1
        setNumber(number + 1);
        return number;
    }

    private static void setNumber(int number) {
        NiukeQuestionNumberConfig.number = number;
        properties.setProperty("number", "" + number);
        try {
            OutputStream out = new FileOutputStream("niuke.properties");
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
        setNumber(1);
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
