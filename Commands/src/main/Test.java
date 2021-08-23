package main;

import tools.config.ConfigTools;

/**
 * 测试类.
 */
public class Test {
    public static void main(String[] args) {
        //ConfigTools.getInstance().forward("h","code");
        //ConfigTools.getInstance().forward("m","api","table","m");
        //ConfigTools.getInstance().forward("m","api","table","f");
        //ConfigTools.getInstance().forward("m","api","table","b","nd");
        //ConfigTools.getInstance().forward("m","api","table");
        //ConfigTools.getInstance().forward("m","k2b");
        //ConfigTools.getInstance().forward("h","2jspcm");
        //ConfigTools.getInstance().forward("h","clean");
        //ConfigTools.getInstance().forward("s","fqcn");
        //ConfigTools.getInstance().forward("m","nu");
        //ConfigTools.getInstance().forward("s");
        //ConfigTools.getInstance().forward("h","pre");
        //ConfigTools.getInstance().forward("j","f","c");
        //ConfigTools.getInstance().forward("h","2md","table");
        //ConfigTools.getInstance().forward("h","2md");
        // ConfigTools.getInstance().forward("m", "cb", "plantuml");
        // ConfigTools.getInstance().forward("suanfa", "2qq", "c");
        // ConfigTools.getInstance().forward("suanfa", "2qq");
        // ConfigTools.getInstance().forward("m", "cb");
        // ConfigTools.getInstance().forward("h", "t");
        // ConfigTools.getInstance().forward("s", "date");
        String result = ConfigTools.getInstance().forward("g", "s");
        ConfigTools.getInstance().showResult(result);

    }
}
