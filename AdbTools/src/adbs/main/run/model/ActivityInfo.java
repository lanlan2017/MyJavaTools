package adbs.main.run.model;

import java.util.Objects;

/**
 * Activity详细信息
 *
 */
public class ActivityInfo {
    /**
     * 全限定Activity名称，例如“com.huawei.android.launcher/.unihome.UniHomeLauncher”
     */
    private String actLongName;
    /**
     *
     * 应用包名，例如“com.huawei.android.launcher”
     */
    private String packageName;
    /**
     * 去掉包名的Activity名称，例如“.unihome.UniHomeLauncher”
     */
    private String actShortName;

    public ActivityInfo(String actLongName) {
        this.actLongName = actLongName;
        if (actLongName.contains("/")) {
            int slpitIndex = actLongName.indexOf("/");
            packageName = actLongName.substring(0, slpitIndex);
            actShortName = actLongName.substring(slpitIndex + 1);
        } else {
            packageName = "";
            actShortName = "";
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ActivityInfo activityInfo = (ActivityInfo) o;
        return Objects.equals(actLongName, activityInfo.actLongName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actLongName);
    }

    public String getPackageName() {
        return packageName;
    }

    public String getActShortName() {
        return actShortName;
    }

    public String getActLongName() {
        return actLongName;
    }

    @Override
    public String toString() {
        // return "actLongName=" + actLongName + "\n" + "packageName=" + packageName + "\n" +
        //         // "actShortName=" + actLongName + "\n";
        //         "actShortName=" + actShortName;
        // return "actLongName =" + actLongName;
        return actLongName;
        // return "actLongName='" + actLongName + '\'' + ", packageName='" + packageName + '\'' + ", actShortName='" + actShortName + '\'' + '}';
    }
}
