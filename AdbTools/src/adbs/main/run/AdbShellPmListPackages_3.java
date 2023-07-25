package adbs.main.run;

import adbs.cmd.CmdRun;
import adbs.main.AdbTools;
import config.AdbToolsProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * adb列出所有第三方apk
 * 获取第三方所有可赚钱的apk的名称列表
 */
public class AdbShellPmListPackages_3 {
    private ArrayList<String> packages_3_money = new ArrayList<>();

    /**
     * 获取保存当前手机上安装的所有的可赚钱的apk的中文名称
     *
     * @return 一个保存当前手机上所有可赚钱的apk的中文名称字符串的ArrayList
     */
    public ArrayList<String> getPackages_3_money() {
        return packages_3_money;
    }

    public AdbShellPmListPackages_3() {
        String serial = AdbTools.getInstance().getDevice().getSerial();
        // System.out.println("serial = " + serial);
        // String run = CmdRun.run("adb shell pm list packages -3");
        String code = "adb -s " + serial + " shell pm list packages -3";
        // System.out.println("code = " + code);
        String run = CmdRun.run(code);
        // System.out.println("111111 = " + run);
        // 如果命令中存在空行的话，把空行替换成空字符串，则可以删除掉这个空行。
        run = run.replaceAll("(?m)^$\n", "");
        // run = run.replaceAll("^$\n", "");
        // System.out.println("111111 = " + run);
        if (run != null && !"".equals(run)) {
            BufferedReader reader = new BufferedReader(new StringReader(run));
            String flag = "package:";
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(flag)) {
                        line = line.substring(line.indexOf(flag) + flag.length());
                        // System.out.println("line =" + line + "=");
                        String property = AdbToolsProperties.moneyApkPro.getProperty(line);
                        if (!line.equals(property)) {
                            packages_3_money.add(property);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 排序
        Collections.sort(packages_3_money);
        // System.out.println(packages_3_money);
    }

    // public static void main(String[] args) {
    //     AdbTools.getInstance();
    //     new AdbShellPmListPackages_3();
    // }
}
