package adbs.main.ui.jpanels.act.model;

import adbs.main.ui.jpanels.act.DateString;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 每个赚钱APP的详细任务
 */
public class AppTask3 {
    /**
     * 格式为yyyy-MM-dd的日期字符串。
     */
    private String date;
    // private HashSet<AppTaskTimeSet> tasks;
    private ArrayList<AppTaskTimeSet> tasks;

    public AppTask3(String date) {
        this.date = date;
        // tasks = new HashSet<>();
        tasks = new ArrayList<>();
    }

    public AppTask3(String date, ArrayList<AppTaskTimeSet> tasks) {
        this.date = date;
        this.tasks = tasks;
    }

    public AppTask3() {
        // tasks = new HashSet<>();
        tasks = new ArrayList<>();
        date = DateString.getDate_yyyyMMdd();

        // HashSet<TaskTime> dianTaoTasks = new HashSet<>();
        ArrayList<TaskTime> dianTaoTasks = new ArrayList<>();


        dianTaoTasks.add(new TaskTime("商城", -1));
        dianTaoTasks.add(new TaskTime("红包", 0));
        dianTaoTasks.add(new TaskTime("打工", 0));
        dianTaoTasks.add(new TaskTime("睡觉", 0));
        dianTaoTasks.add(new TaskTime("购金", -1));
        dianTaoTasks.add(new TaskTime("开店", -1));
        dianTaoTasks.add(new TaskTime("签到", 0));
        dianTaoTasks.add(new TaskTime("走路", 0));

        AppTaskTimeSet dianTao = new AppTaskTimeSet("点淘", dianTaoTasks);


        ArrayList<TaskTime> taoBaoTask = new ArrayList<>();
        taoBaoTask.add(new TaskTime("红包签到", 0));
        taoBaoTask.add(new TaskTime("视频", 0));
        taoBaoTask.add(new TaskTime("淘金币", 0));
        taoBaoTask.add(new TaskTime("芭芭农场", 0));

        AppTaskTimeSet taoBao = new AppTaskTimeSet("淘宝", taoBaoTask);


        ArrayList<TaskTime> taskTimesTaoTe = new ArrayList<>();
        taskTimesTaoTe.add(new TaskTime("天天领红包", 0));
        taskTimesTaoTe.add(new TaskTime("小鸡送好礼", 0));
        taskTimesTaoTe.add(new TaskTime("现金签到", 0));
        taskTimesTaoTe.add(new TaskTime("积分兑红包", 0));
        // taskTimesTaoTe.add(new TaskTime("天天赚特币", 0));

        AppTaskTimeSet taoTe = new AppTaskTimeSet("淘特", taskTimesTaoTe);


        AppTaskTimeSet fqctyyb = new AppTaskTimeSet("番茄畅听音乐版", taoBaoTask);
        // AppTaskTimeSet hgmfdj = new AppTaskTimeSet("红果免费短剧", taoBaoTask);

        ArrayList<TaskTime> taskTimeQuTouTiao = new ArrayList<>();
        taskTimeQuTouTiao.add(new TaskTime("签到"));
        taskTimeQuTouTiao.add(new TaskTime("通知"));
        taskTimeQuTouTiao.add(new TaskTime("小视频"));
        AppTaskTimeSet quTouTiao = new AppTaskTimeSet("趣头条", taskTimeQuTouTiao);

        ArrayList<TaskTime> ksmfxsTask = new ArrayList<>();
        ksmfxsTask.add(new TaskTime("阅读"));
        ksmfxsTask.add(new TaskTime("广告"));
        ksmfxsTask.add(new TaskTime("宝箱"));
        AppTaskTimeSet ksmfxs = new AppTaskTimeSet("快手免费小说", ksmfxsTask);

        //        TaskTime[] zongQiangKanDianTask = new TaskTime[]{new TaskTime("看视频", 0), new TaskTime("看广告", 0), new TaskTime("看广告", 0),};
        //        AppTaskTimeSet zhongQingKanDian = new AppTaskTimeSet("中青看点", zongQiangKanDianTask);
        //        AppTaskTimeSet zhongQingKanDian = new AppTaskTimeSet("中青看点", new TaskTime[]{
        //                new TaskTime("看视频", 0), new TaskTime("看广告", 0),
        //                new TaskTime("看广告", 0),
        //        });

        tasks.add(dianTao);
        tasks.add(taoBao);
        tasks.add(taoTe);
        tasks.add(quTouTiao);
        tasks.add(ksmfxs);
        tasks.add(fqctyyb);
        //        tasks.add(zhongQingKanDian);
        tasks.add(new AppTaskTimeSet("中青看点", new TaskTime[]{new TaskTime("签到", 0), new TaskTime("通知", 0), new TaskTime("提现", 0)}));        //        tasks.add(zhongQingKanDian);
        tasks.add(new AppTaskTimeSet("速度免费小说", new TaskTime[]{new TaskTime("签到"), new TaskTime("阅读"), new TaskTime("红包")}));

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
