package config.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SiteProperties {
    /**
     * @param filePath xxx.properties配置文件的路径
     * @return 配置文件中的site配置项，也就是网站的地址信息
     */
    public static String getSite(String filePath) {
        //1 实例化
        Properties properties = new Properties();
        String site = null;
        try (InputStream inputStream = new FileInputStream(filePath)) {
            // 2加载配置文件到Properties对象中
            properties.load(inputStream);
            // 3读取配置文件中的站点信息
            site = properties.getProperty("site");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return site;
    }
    public static void main(String[] args)
    {
        String filePath = "G:\\dev2\\idea_workspace\\MyJavaTools\\Demo\\src\\config\\properties\\Test.properties";
        System.out.print("网站地址:"+SiteProperties.getSite(filePath));
    }

}

