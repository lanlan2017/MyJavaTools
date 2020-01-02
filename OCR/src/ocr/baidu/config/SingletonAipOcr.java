package ocr.baidu.config;

import com.baidu.aip.ocr.AipOcr;

/**
 * AipOcr的单例子类.
 */
public class SingletonAipOcr extends AipOcr {
    private static Config config = Config.getInstance();

    // 单例模式第一步:私有化构造器
    private SingletonAipOcr() {
        // super(APP_ID, API_KEY, SECRET_KEY);
        super(config.getAPP_ID(), config.getAPI_KEY(), config.getSECRET_KEY());
    }

    // 单例模式第2步:设置当前类的实例
    private static AipOcr instance;

    // 单例模式第3步:给出获取当前类的实例的方法
    public static AipOcr getAipOcr() {
        if (instance == null) {
            instance = new SingletonAipOcr();
            // 可选：设置网络连接参数
            instance.setConnectionTimeoutInMillis(2000);
            instance.setSocketTimeoutInMillis(60000);
        }
        return instance;
    }
}