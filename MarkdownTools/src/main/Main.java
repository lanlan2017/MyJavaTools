package main;

import tools.config.ConfigTools;

/**
 * 程序入口.
 */
public class Main {
    public static void main(String[] args) {
        ConfigTools.getInstance().forward(args);
    }
}
