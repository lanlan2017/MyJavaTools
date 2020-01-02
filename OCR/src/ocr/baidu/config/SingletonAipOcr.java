package ocr.baidu.config;

import com.baidu.aip.ocr.AipOcr;
/**
 * AipOcr的单例子类.
 */
public class SingletonAipOcr extends AipOcr
{
	// 设置APPID/AK/SK
	// public static final String APP_ID = "11803566";
	// public static final String API_KEY = "n8ytVA3HxIYCWgqQiLRBhG0r";
	// public static final String SECRET_KEY =
	// "ZFGhGp6swLKjHAk8OSPlKQkAz0NgwHRt";
	private static Config config = Config.getInstance();
	// 单例模式第一步:私有化构造器
	private SingletonAipOcr()
	{
		// super(APP_ID, API_KEY, SECRET_KEY);
		super(config.getAPP_ID(), config.getAPI_KEY(), config.getSECRET_KEY());
	}
	// 单例模式第2步:设置当前类的实例
	private static AipOcr instance;
	// 单例模式第3步:给出获取当前类的实例的方法
	public static AipOcr getAipOcr()
	{
		if (instance == null)
		{
			instance = new SingletonAipOcr();
			// 可选：设置网络连接参数
			instance.setConnectionTimeoutInMillis(2000);
			instance.setSocketTimeoutInMillis(60000);
		}
		return instance;
	}
}