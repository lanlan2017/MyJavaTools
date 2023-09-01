package adbs.main.run;

import adbs.cmd.AdbCommands;

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
