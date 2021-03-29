package main;

import javafx.beans.binding.When;
import tools.config.ConfigTools;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        //ConfigTools.getInstance().forward("s","cc","c");
        //ConfigTools.getInstance().forward("s");
        //ConfigTools.getInstance().forward("h","pre");
        //ConfigTools.getInstance().forward("j","f","c");
        ConfigTools.getInstance().forward("h","2md","table");
    }

}
