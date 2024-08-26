package adbs.main.ui.jpanels.act.model;

import java.util.HashMap;

public class AppTasks {
//    private String appName;
//    private String[] actTasks;

    //        public static final AppTasks dianTao;
    public static final HashMap<String, String[]> tasks;

    static {
        tasks = new HashMap<>();

        String[] dianTaoTasks = {"商城", "红包_", "购金", "开店", "签到_", "打工_", "睡觉_", "走路_"};
        //        dianTao = new AppTasks("点淘", acts);
        tasks.put("点淘", dianTaoTasks);
        tasks.put("番茄畅听音乐版", dianTaoTasks);

    }

//
//    public AppTasks(String appName, String[] actTasks) {
//        this.appName = appName;
//        this.actTasks = actTasks;
//    }
//
//    public AppTasks() {
//    }
//
//    public String getAppName() {
//        return appName;
//    }
//
//    public String[] getActTasks() {
//        return actTasks;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        AppTasks appTasks = (AppTasks) o;
//        return Objects.equals(appName, appTasks.appName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(appName);
//    }
}
