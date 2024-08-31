package adbs.main.ui.jpanels.act.model;

import adbs.main.ui.jpanels.act.DateString;

import java.util.ArrayList;
import java.util.Objects;

public class AppTask3 {
    /**
     * 格式为yyyy-MM-dd的日期字符串。
     */
    private String date;
    //    private HashSet<AppTaskTimeSet> tasks;
    private ArrayList<AppTaskTimeSet> tasks;

    public AppTask3(String date) {
        this.date = date;
        //        tasks = new HashSet<>();
        tasks = new ArrayList<>();
    }

    public AppTask3(String date, ArrayList<AppTaskTimeSet> tasks) {
        this.date = date;
        this.tasks = tasks;
    }

    public AppTask3() {
        //        tasks = new HashSet<>();
        tasks = new ArrayList<>();
        date = DateString.getDate_yyyyMMdd();

        //        HashSet<TaskTime> dianTaoTasks = new HashSet<>();
        ArrayList<TaskTime> dianTaoTasks = new ArrayList<>();

        dianTaoTasks.add(new TaskTime("商城", false, -1));
        dianTaoTasks.add(new TaskTime("红包", false, 0));
        dianTaoTasks.add(new TaskTime("购金", false, -1));
        dianTaoTasks.add(new TaskTime("开店", false, -1));
        dianTaoTasks.add(new TaskTime("签到", false, 0));
        dianTaoTasks.add(new TaskTime("打工", false, 0));
        dianTaoTasks.add(new TaskTime("睡觉", false, 0));
        dianTaoTasks.add(new TaskTime("走路", false, 0));

        //        HashSet<TaskTime> fqctyybTask = new HashSet<>();
        ArrayList<TaskTime> fqctyybTask = new ArrayList<>();
        fqctyybTask.add(new TaskTime("商城", false, -1));
        fqctyybTask.add(new TaskTime("红包", false, 0));
        fqctyybTask.add(new TaskTime("购金", false, -1));
        fqctyybTask.add(new TaskTime("开店", false, -1));
        fqctyybTask.add(new TaskTime("签到", false, 0));
        fqctyybTask.add(new TaskTime("打工", false, 0));
        fqctyybTask.add(new TaskTime("睡觉", false, 0));
        fqctyybTask.add(new TaskTime("走路", false, 0));

        AppTaskTimeSet dianTao = new AppTaskTimeSet("点淘", dianTaoTasks);


        ArrayList<TaskTime> taoBaoTask = new ArrayList<>();
        taoBaoTask.add(new TaskTime("红包签到"));
        taoBaoTask.add(new TaskTime("视频"));
        taoBaoTask.add(new TaskTime("淘金币"));
        taoBaoTask.add(new TaskTime("芭芭农场"));
        //        taoBaoTask.add(new TaskTime("红包签到", false, -1));
        //        taoBaoTask.add(new TaskTime("视频", false, -1));
        //        taoBaoTask.add(new TaskTime("芭芭农场", false, -1));
        AppTaskTimeSet taoBao = new AppTaskTimeSet("淘宝", taoBaoTask);


        ArrayList<TaskTime> taskTimesTaoTe = new ArrayList<>();
        taskTimesTaoTe.add(new TaskTime("天天领红包"));
        taskTimesTaoTe.add(new TaskTime("小鸡送好礼"));
        taskTimesTaoTe.add(new TaskTime("现金签到"));
        taskTimesTaoTe.add(new TaskTime("发财鸭"));
        taskTimesTaoTe.add(new TaskTime("天天赚特币"));
        AppTaskTimeSet taoTe = new AppTaskTimeSet("淘特", taskTimesTaoTe);


        AppTaskTimeSet fqctyyb = new AppTaskTimeSet("番茄畅听音乐版", taskTimesTaoTe);
        AppTaskTimeSet hgmfdj = new AppTaskTimeSet("红果免费短剧", taoBaoTask);

        tasks.add(dianTao);
        tasks.add(taoBao);
        tasks.add(taoTe);
        tasks.add(fqctyyb);
        tasks.add(hgmfdj);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<AppTaskTimeSet> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<AppTaskTimeSet> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AppTask3 appTask3 = (AppTask3) o;
        return Objects.equals(date, appTask3.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
