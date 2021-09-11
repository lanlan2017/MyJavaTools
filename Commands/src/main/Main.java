package main;

import tools.config.ConfigTools;
import tools.markdown.niuke.ToolsJarPath;

/**
 * 程序入口.
 */
public class Main {
    public static void main(String[] args) {
        // System.out.println("当前工作目录：" + System.getProperty("user.dir"));
        // 设置 当前jar包所在的目录 为工作路径
        // 只有把工作路径设置为jar包的目录，才能顺利读写jar包同级的配置文件niuke.properties
        // System.setProperty("user.dir", ToolsJarPath.getPath());
        // System.out.println("使用当前jar的目录 作为当前工作目录：" + System.getProperty("user.dir"));
        String result = ConfigTools.getInstance().forward(args);
        ConfigTools.getInstance().showResult(result);
    }
}
