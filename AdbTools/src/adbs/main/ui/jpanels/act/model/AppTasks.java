package adbs.main.ui.jpanels.act.model;

import java.util.HashMap;

public class AppTasks {

    public static final HashMap<String, String[]> tasks;
    static {
        tasks = new HashMap<>();
        String[] dianTaoTasks = {"商城", "红包_", "购金", "开店", "签到_", "打工_", "睡觉_", "走路_"};
        //        dianTao = new AppTasks("点淘", acts);
        tasks.put("点淘", dianTaoTasks);
        tasks.put("番茄畅听音乐版", dianTaoTasks);
    }
}
