package main;

import tools.config.ConfigTools;

/**
 * 测试类.
 */
public class Test {
    public static void main(String[] args) {
        //ConfigTools.getInstance().forward("j", "forarr");
        //ConfigTools.getInstance().forward("j", "forstr");
        //ConfigTools.getInstance().forward("j", "switch");
        //ConfigTools.getInstance().forward("j", "switch","str");
        //ConfigTools.getInstance().forward("file", "gbkutf8");
        ConfigTools.getInstance().forward("file", "utf8gbk");
    }
}
