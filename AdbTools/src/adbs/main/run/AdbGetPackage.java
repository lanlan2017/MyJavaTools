package adbs.main.run;

import adbs.cmd.AdbCommands;
import adbs.cmd.CmdRun;
import adbs.main.AdbTools;
import adbs.main.run.model.AppNames;

public class AdbGetPackage {
    /**
     * 根据不同的手机序列号返回不同的查询当前activity的adb命令
     *
     * @param id 手机的序列号
     * @return 查询当前activity的adb命令
     */
    public static String getTopActivityCommand(String id) {
        String code;
        if (id.equals("jjqsqst4aim7f675")) {
            code = "adb -s " + id + " shell dumpsys activity | findstr topResumedActivity";
        } else {
            code = "adb -s " + id + " shell dumpsys activity | findstr \"mResume\"";
        }
        return code;
    }

    /**
     * 从命令的输出中取出包名
     *
     * @param adbOutput adb命令输出结果
     * @return 包名字符串
     */
    public static String getPackageName(String adbOutput) {
        // System.out.println(adbOutput);
        String packageName = adbOutput.substring(0, adbOutput.lastIndexOf("/"));
        // System.out.println(adbOutput);
        packageName = packageName.substring(packageName.lastIndexOf(" ") + 1);
        // System.out.println("--" + adbOutput + "--");
        return packageName;
    }

    // /**
    //  * 获取完成的
    //  * @param adbOuput
    //  * @return
    //  */
    public static String getActName(String adbOuput) {
        // mResumedActivity: ActivityRecord{7fbc105 u0 com.huawei.health/.MainActivity t1573}
        adbOuput = adbOuput.substring(0, adbOuput.lastIndexOf(" "));
        adbOuput = adbOuput.substring(adbOuput.lastIndexOf(" ") + 1);
        // "adb -s " + id + " shell dumpsys activity | findstr topResumedActivity" 命令执行的结果
        /**
         * topResumedActivity=ActivityRecord{1a70f4a u0 com.smile.gifmaker/com.yxcorp.gifshow.HomeActivity} t6294}
         */
        if (adbOuput.endsWith("}")) {
            adbOuput = adbOuput.substring(0, adbOuput.length() - 1);
        }
        return adbOuput;
    }


    /**
     * 获取当前Android设备中顶部APP的activity名称
     *
     * @return 全限定activity名称字符串
     */
    public static String getActName() {
        String run = runActCmd();
        // 如果命令结果中有反斜杠，说明有包名
        String actName = "";
        if (run.contains("/")) {
            actName = getActName(run);
        }

        return actName;
    }

    public static AppNames getAppNames() {
        String actName = AdbGetPackage.getActName();
        // System.out.println("actName = " + actName);
        AppNames appNames = new AppNames(actName);
        return appNames;
    }

    /**
     * 获取当前activity名称，简称，不包括前面的包名。
     *
     * @return activity名称字符串
     */
    public static String getActShortName() {
        String actName = AdbGetPackage.getActName();
        System.out.println("actName = " + actName);
        String actShortName = "";
        if (actName.contains("/")) {
            actShortName = actName.substring(actName.indexOf("/") + 1);
            System.out.println("actShortName = " + actShortName);
        }
        return actShortName;
    }

    private static String runActCmd() {
        String serial = AdbTools.getInstance().getDevice().getSerial();
        String activityCommand = getTopActivityCommand(serial);
        String run = CmdRun.run(activityCommand).trim();
        return run;
    }

    public static String getTopPackageName(String serial) {
        String packageName = "";
        String activityCode = getTopActivityCommand(serial).trim();
        String actCmdOut = AdbCommands.runAbdCmd(activityCode);
        // System.out.println("actCmdOut = " + actCmdOut);
        if (actCmdOut.contains("/")) {
            packageName = getPackageName(actCmdOut);
        }
        return packageName;
    }

}
