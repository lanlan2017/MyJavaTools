package adbs.main.run.model;

public class AppNames {
    private String actLongName;
    private String packageName;
    private String actShortName;

    public AppNames(String actLongName) {
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
        return "actLongName =" + actLongName;
        // return "actLongName='" + actLongName + '\'' + ", packageName='" + packageName + '\'' + ", actShortName='" + actShortName + '\'' + '}';
    }
}
