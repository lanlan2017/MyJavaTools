package adbs.main.run;

import adbs.cmd.CmdRun;
import adbs.main.AdbTools;
import config.AdbToolsProperties;

import javax.swing.*;

public class ForegroundAppRun implements Runnable {
    private static boolean stop;

    @Override
    public void run() {
        try {
            // 先等5秒，免得和其他命令冲突
            Thread.sleep(1000 * 4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final String id = AdbTools.getInstance().getDevice().getId();
        AdbTools adbTools = AdbTools.getInstance();
        while (!stop) {
            String run = CmdRun.run("adb -s " + id + " shell dumpsys activity | findstr \"mResume\"");
            run = run.trim();
            // System.out.println(run);
            run = run.substring(0, run.lastIndexOf("/"));
            // System.out.println(run);
            run = run.substring(run.lastIndexOf(" ") + 1);
            // System.out.println("--" + run + "--");

            // 获取配置文件中的值
            String value = AdbToolsProperties.propertiesTools.getProperty(run);
            System.out.println("value = " + value);

            JFrame frame = adbTools.getFrame();
            String title = frame.getTitle();
            // 如果标题中有下划线，有应用名称
            if (title.contains("_")) {
                int nameFlagIndex = title.lastIndexOf("_")+1;
                String oldValue = title.substring(nameFlagIndex);
                // System.out.println("oldValue =" + oldValue+"_");
                // 如果当然的APP名称与标题中的APP名称不一样
                if(!value.equals(oldValue)){
                    // 从标题中截取下标题标记以前的字符串，包括标题标记
                    String substring = title.substring(0, nameFlagIndex);
                    // 生成新的标题
                    title= substring+value;

                    // System.out.println("substring = " + substring);
                    // System.out.println("title = " + title);
                    frame.setTitle(title);
                }

            }
            // 如果标题以百分号结尾的话，说明还没有应用名称
            else if (title.endsWith("%")) {
                // title=title+run;
                title = title + "_" + value;
                frame.setTitle(title);
            }
            try {
                // 一分钟检测一次
                Thread.sleep(1000 * 60);
                // Thread.sleep(1000*2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
