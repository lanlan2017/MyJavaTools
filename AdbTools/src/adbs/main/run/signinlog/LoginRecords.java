package adbs.main.run.signinlog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 签到记录文件 LoginRecords
 */
public class LoginRecords {

    /*
    日期:2024-07-23
    应用:[中青看点, 淘宝, 淘特, 点淘, 趣头条, 速读免费小说]
    签到:[中青看点, 淘宝, 点淘, 趣头条]
     */
    // 保存位置：E:\Tools\runnable\AdbToolsPythons\op
    /**
     * 日期
     */
    private String date;
    /**
     * 保存所有可赚钱的APP名称的列表
     */
    // private ArrayList<String> apps;
    private String apps;
    /**
     * 保存今日签到的app名称的列表
     * <p>
     * 今日打开的APP
     */
    // private ArrayList<String> appOpened;
    private String appOpened;
    // private String oppenedStartFlag;
    // private String appStartFlag;
    // private String dateStartFlag;

    private final String dateStartFlag = "日期:";
    private final String appStartFlag = "应用:";
    private final String oppenedStartFlag = "签到:";

    public LoginRecords() {
        updateDate();
    }

    public void updateDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        this.date = date;
    }

    public LoginRecords(String date, String apps, String appOpened) {
        this.date = date;
        this.appOpened = appOpened;
        this.apps = apps;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setApps(String apps) {
        this.apps = apps;
    }

    public void setAppOpened(String appOpened) {
        this.appOpened = appOpened;
    }

    public String getDate() {
        return date;
    }

    public String getApps() {
        return apps;
    }

    public String getAppOpened() {
        return appOpened;
    }

    public LoginRecords(String filePath) {
        // 判断是否需要创建文件，如果文件不存在，则先创建文件，如果不需要创建文件，则说明有历史记录。
        if (!FileUtil.isNeedToCreateFile(filePath)) {
            // 读取文件内容
            String string = FileUtil.readStringFromFile(filePath);
            // System.out.println("string = " + string);
            if (string.contains(dateStartFlag) && string.contains(appStartFlag) && string
                .contains(oppenedStartFlag)) {

                String[] split = string.split("\n");
                int length = split.length;
                // System.out.println("length = " + length);
                if (length == 3) {
                    String dateStr = split[0];
                    String appStr = split[1];
                    String oppenedStr = split[2];

                    this.date = dateStr.substring(dateStartFlag.length());
                    // System.out.println("date = " + date);

                    this.apps = appStr.substring(appStartFlag.length());
//                    System.out.println("apps = " + appStr);

                    this.appOpened = oppenedStr.substring(oppenedStartFlag.length());
                    // System.out.println("appOpened = " + oppenedStr);
                    // System.out.println("this = \n" + this);
                }
            }

        }
    }

    @Override public String toString() {
        // dateStartFlag = "日期:";
        // appStartFlag = "应用:";
        // oppenedStartFlag = "签到:";

        String out =
            dateStartFlag + date + "\n" + appStartFlag + apps + "\n" + oppenedStartFlag + appOpened;
        return out;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginRecords that = (LoginRecords) o;
        return date.equals(that.date);
    }

    @Override public int hashCode() {
        return Objects.hash(date);
    }
}
