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
    private ArrayList<String> package_3 = new ArrayList<>();
    private ArrayList<String> packages_3_money = new ArrayList<>();

    /**
     * 获取保存当前手机上安装的所有的可赚钱的apk的中文名称
     *
     * @return 一个保存当前手机上所有可赚钱的apk的中文名称字符串的ArrayList
     */
    public ArrayList<String> getPackages_3_money() {
        return packages_3_money;
    }

    /**
     * 获取手机上安装的所有第三方APP的包名列表
     *
     * @return 保存Android设备上所有的第三方APP的包名的ArrayList对象。
     */
    public ArrayList<String> getPackage_3() {
        return package_3;
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
                String packageName;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(flag)) {
                        packageName = line.substring(line.indexOf(flag) + flag.length());
                        package_3.add(packageName);

                        // System.out.println("line =" + line + "=");
                        String appName = AdbToolsProperties.moneyApkPro.getProperty(packageName);
                        // 如果没有返回传入的参数，则说明配置文件中有这个包名
                        if (!packageName.equals(appName)) {
                            packages_3_money.add(appName);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 排序可赚钱应用名列表
        Collections.sort(packages_3_money);
        // 排序第三方APP的包名列表
        Collections.sort(package_3);
        // System.out.println(packages_3_money);
    }

    public static void main(String[] args) {
        AdbShellPmListPackages_3 adbShellPmListPackages_3 = new AdbShellPmListPackages_3();
        ArrayList<String> packages_3_money = adbShellPmListPackages_3.getPackages_3_money();
        System.out.println("packages_3_money = " + packages_3_money);

        ArrayList<String> package_3 = adbShellPmListPackages_3.getPackage_3();
        System.out.println("package_3 = " + package_3);
    }
}
