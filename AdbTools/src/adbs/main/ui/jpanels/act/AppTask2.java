package adbs.main.ui.jpanels.act;

import adbs.main.ui.jpanels.act.model.AppTaskTime;
import adbs.main.ui.jpanels.act.model.TaskTimes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class AppTask2 {

    private String date = new SimpleDateFormat("yyyy-MM-DD").format(new Date());

    public HashMap<String, AppTaskTime> tasks = new HashMap<>();
    private static final AppTask2 instance = new AppTask2();

    public static AppTask2 getInstance() {
        return instance;
    }

    public AppTask2() {
        //        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-DD").format(new Date());
        //        f.format(new Date());
    }

    public AppTask2(HashMap<String, AppTaskTime> tasks) {
        this.tasks = tasks;
    }

    //    static {
    {
        HashSet<TaskTimes> dianTaoTasks = new HashSet<>();

        dianTaoTasks.add(new TaskTimes("商城", false, -1));
        dianTaoTasks.add(new TaskTimes("红包", false, 0));
        dianTaoTasks.add(new TaskTimes("购金", false, -1));
        dianTaoTasks.add(new TaskTimes("开店", false, -1));
        dianTaoTasks.add(new TaskTimes("签到", false, 0));
        dianTaoTasks.add(new TaskTimes("打工", false, 0));
        dianTaoTasks.add(new TaskTimes("睡觉", false, 0));
        dianTaoTasks.add(new TaskTimes("走路", false, 0));

        HashSet<TaskTimes> fqctyybTask = new HashSet<>();
        fqctyybTask.add(new TaskTimes("商城", false, -1));
        fqctyybTask.add(new TaskTimes("红包", false, 0));
        fqctyybTask.add(new TaskTimes("购金", false, -1));
        fqctyybTask.add(new TaskTimes("开店", false, -1));
        fqctyybTask.add(new TaskTimes("签到", false, 0));
        fqctyybTask.add(new TaskTimes("打工", false, 0));
        fqctyybTask.add(new TaskTimes("睡觉", false, 0));
        fqctyybTask.add(new TaskTimes("走路", false, 0));

        //        dianTaoTaskTimes.contains(new TaskTimes())

        AppTaskTime dianTao = new AppTaskTime("点淘", dianTaoTasks);
        AppTaskTime fqctyyb = new AppTaskTime("番茄畅听音乐版", fqctyybTask);
        //        AppTaskTime fqctyyb = new AppTaskTime("番茄畅听音乐版", dianTaoTaskTimes);
        tasks.put(dianTao.getAppName(), dianTao);
        tasks.put(fqctyyb.getAppName(), fqctyyb);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public HashMap<String, AppTaskTime> getTasks() {
        return tasks;
    }

    public void setTasks(HashMap<String, AppTaskTime> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AppTask2 appTask2 = (AppTask2) o;
        return Objects.equals(date, appTask2.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
