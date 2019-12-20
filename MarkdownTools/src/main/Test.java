package main;

import tools.config.ConfigTools;

/**
 * 测试类.
 */
public class Test {
    public static void main(String[] args) {
        String[] argsTest = new String[]{"m", "duyin"};
        ConfigTools.getInstance().testMap2(argsTest);
    }
}
