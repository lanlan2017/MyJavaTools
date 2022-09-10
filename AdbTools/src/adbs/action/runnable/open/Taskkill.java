package adbs.action.runnable.open;

import adbs.cmd.CmdRun;

import java.util.Scanner;

public class Taskkill {
    public static void main(String[] args) {
        // killScrcpy("75aed56d");

        // killAdbToolsJarAll();

    }

    /**
     * 杀死所有的AdbTools.jar命令
     */
    public static void killAdbToolsJarAll() {
        String jarName = "AdbTools.jar";
        // 执行
        String run = CmdRun.run("jps -m");
        Scanner sc = new Scanner(run);
        // System.out.println(run);
        String line;
        while (sc.hasNext()) {
            line = sc.nextLine();
            // if (line.contains("75aed56d OppoUSB")) {
            if (line.contains(jarName)) {
                System.out.println("--->" + line);
                // 获取pid
                String pid = line.substring(0, line.indexOf(" "));
                // System.out.println(pid);
                if (pid.matches("\\d+")) {
                    // 杀死该pid的进程树
                    killPidTree(pid);
                }
            }
        }
    }

    /**
     * 杀死打开的scrcpy.exe镜像
     *
     * @param id 手机设备的id
     */
    public static void killScrcpy(String id) {

        // 执行
        String run = CmdRun.run("jps -m");
        Scanner sc = new Scanner(run);
        // System.out.println(run);
        String line;
        while (sc.hasNext()) {
            line = sc.nextLine();
            // if (line.contains("75aed56d OppoUSB")) {
            if (line.contains(id)) {
                System.out.println("--->" + line);
                String pid = line.substring(0, line.indexOf(" "));
                System.out.println("pid = " + pid);
                // System.out.println(pid);
                if (pid.matches("\\d+")) {
                    killPidTree(pid);
                }
            }
        }
    }

    public static void killPidTree(String pid) {
        String x = CmdRun.run("taskkill -f -t -pid " + pid);
        System.out.println(x);
    }

    public static void killPid(String pid) {
        String x = CmdRun.run("taskkill -f -pid " + pid);
        System.out.println(x);
    }
}
