package adbs.main.run;

import adbs.cmd.CmdRun;
import adbs.main.AdbTools;
import adbs.tools.thread.ThreadSleep;
import config.AdbToolsProperties;

import javax.swing.*;

public class ForegroundAppRun implements Runnable {
    private static boolean stop;

    public static void setStop(boolean stop) {
        ForegroundAppRun.stop = stop;
    }

    @Override
    public void run() {
        // 等待4秒
        ThreadSleep.seconds(4);
        final String id = AdbTools.getInstance().getDevice().getId();
        AdbTools adbTools = AdbTools.getInstance();
        while (!stop) {
            String run = CmdRun.run("adb -s " + id + " shell dumpsys activity | findstr \"mResume\"");
            run = run.trim();
            // 如果命令结果中有反斜杠，说明有包名
            if (run.contains("/")) {
                // 后面的activity不要了，
                // System.out.println(run);
                run = run.substring(0, run.lastIndexOf("/"));
                // System.out.println(run);
                run = run.substring(run.lastIndexOf(" ") + 1);
                // System.out.println("--" + run + "--");

                // 获取配置文件中的值
                String appName = AdbToolsProperties.propertiesTools.getProperty(run);
                // 输出应用的名称
                System.out.println("appName = " + appName);

                JFrame frame = adbTools.getFrame();
                String title = frame.getTitle();
                // 如果标题中有下划线，这表明标题中有旧的应用名称
                if (title.contains("|")) {
                    // 获取下划线在标题中的下标
                    int nameFlagIndex = title.lastIndexOf("|") + 1;
                    // 截取下旧的应用名称
                    String oldAppName = title.substring(nameFlagIndex);
                    // System.out.println("oldAppName =" + oldAppName+"|");

                    // 如果当然的APP名称与标题中的APP名称不一样
                    if (!appName.equals(oldAppName)) {
                        // 从标题中截取下标题标记以前的字符串，包括标题标记
                        String prefix = title.substring(0, nameFlagIndex);
                        // 新的标题=应用名前面的字符串+新的应用名
                        title = prefix + appName;

                        // System.out.println("prefix = " + prefix);
                        // System.out.println("title = " + title);
                        frame.setTitle(title);
                    }

                }
                // 如果标题以百分号结尾的话，说明还没有应用名称
                else if (title.endsWith("%")) {
                    // title=title+run;
                    // 标题=原来的标题+下划线+应用名
                    title = title + "|" + appName;
                    frame.setTitle(title);
                }
            }
            // 等到5分钟
            // ThreadSleep.minutes(2);
            ThreadSleep.minutes(1);

        }
    }
}
