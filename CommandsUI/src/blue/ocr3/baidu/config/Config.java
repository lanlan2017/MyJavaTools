package blue.ocr3.baidu.config;

import tools.reflect.classs.Resource2InputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final String APP_ID;
    private final String API_KEY;
    private final String SECRET_KEY;
    private static Config instance = new Config();

    private Config() {
        // InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        InputStream in = Resource2InputStream.relativePath(this.getClass(),"config.properties");
        // InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties");

        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        APP_ID = properties.getProperty("APP_ID");
        API_KEY = properties.getProperty("API_KEY");
        SECRET_KEY = properties.getProperty("SECRET_KEY");
        // opacity = Float.parseFloat(properties.getProperty("opacity", "0.1f"));
        //		System.out.println(APP_ID);
        //		System.out.println(API_KEY);
        //		System.out.println(SECRET_KEY);
    }

    public static Config getInstance() {
        return instance;
    }

    public String getAPP_ID() {
        return APP_ID;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public String getSECRET_KEY() {
        return SECRET_KEY;
    }

    // public float getOpacity() {
    //     return opacity;
    // }

    // public static void main(String[] args) {
    //     System.out.println(Config.getInstance().API_KEY);
    // }
}
