package config;

import tools.config.properties.PropertiesTools;

public class AdbToolsProperties {
    public static PropertiesTools propertiesTools = new PropertiesTools("AdbTools.properties");
    // public static PropertiesTools moneyApkPro=new PropertiesTools("Adbtools_money_apk.properties");
    public static PropertiesTools moneyApkPro = new PropertiesTools("Adbtools_money_apk.properties");

    /**
     * 获取包名对应的应用名
     *
     * @param packageName 包名
     * @return 如果找到包名对应的应用名，则返回该应用名，否则返回输入的包名
     */
    public static String getAppCHName(String packageName) {
        // 获取应用名（中文名）
        String chName = AdbToolsProperties.moneyApkPro.getProperty(packageName);
        return chName;
    }
}
