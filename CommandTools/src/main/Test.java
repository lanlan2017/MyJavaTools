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
        // argsTest = new String[]{"cb", "html"};
        // argsTest = new String[]{"s", "lc"};
        // argsTest = new String[]{"s", "uc"};
        // argsTest = new String[]{"s", "ucf"};
        // argsTest = new String[]{"s", "lcf"};
        // argsTest = new String[]{"s", "2cccn"};
        // argsTest = new String[]{"s", "2ccmn"};
        // argsTest = new String[]{"g", "s"};
        // argsTest = new String[]{"m","cb","j"};
        // argsTest = new String[]{"m","cb","html"};
        // argsTest = new String[]{"m","table","cp"};
        // argsTest = new String[]{"s",""};
        // argsTest = new String[]{"s","2ccpn"};
        // argsTest = new String[]{"suanfa","maopao","js"};
        // argsTest = new String[]{"suanfa","maopao","j"};
        // argsTest = new String[]{"h", "t"};
        // argsTest = new String[]{"h", "cm"};
         argsTest = new String[]{"h", "code"};
        // argsTest = new String[]{"h", "linkcss"};
        // argsTest = new String[]{"h", "strong"};
        //argsTest = new String[]{"suanfa", "m"};
        //argsTest = new String[]{"m", "table","apim"};
        //argsTest = new String[]{"m", "cb"};
        //argsTest = new String[]{"m", "cb","xxxxx"};
        //argsTest = new String[]{"m", "b"};
        ConfigTools.getInstance().forward(argsTest);
    }
}
