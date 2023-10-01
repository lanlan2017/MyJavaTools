package adbs.main.run;

import config.AdbToolsProperties;

public class IsTest {
    private static boolean isTest;
    static {
        String isTestStr = AdbToolsProperties.propertiesTools.getProperty("isTest");
        // System.out.println("isTest = " + isTestStr);
        if ("true".equals(isTestStr)) {
            isTest = true;
        }
    }
    public static boolean isTest() {
        return isTest;
    }
}
