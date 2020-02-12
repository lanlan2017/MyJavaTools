package main;

import tools.config.ConfigTools;

/**
 * 测试类.
 */
public class Test {
    public static void main(String[] args) {
        //ConfigTools.getInstance().forward("h","code");
        //ConfigTools.getInstance().forward("m","table","api");
        ConfigTools.getInstance().forward("m","table","api","m");
    }
}
