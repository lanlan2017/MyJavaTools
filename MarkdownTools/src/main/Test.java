package main;

import tools.config.ConfigTools;

/**
 * 测试类.
 */
public class Test {
    public static void main(String[] args) {
        String[] argsTest;
        // argsTest = new String[]{"b"};
        // argsTest = new String[]{"cb", "j"};
        argsTest = new String[]{"cb", "html"};
        ConfigTools.getInstance().forward(argsTest);
    }
}
