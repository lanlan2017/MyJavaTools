package adbs.main.ui.jpanels.act.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;

public class AppTask3 {
    /**
     * 格式为yyyy-MM-dd的日期字符串。
     */
    private String date;
    private HashSet<AppTaskTimeSet> tasks;

    public AppTask3(String date) {
        this.date = date;
        tasks = new HashSet<>();
    }

    public AppTask3(String date, HashSet<AppTaskTimeSet> tasks) {
        this.date = date;
        this.tasks = tasks;
    }

    public AppTask3() {
        tasks = new HashSet<>();
        date = getDate_yyyyMMdd();

        HashSet<TaskTime> dianTaoTasks = new HashSet<>();
        dianTaoTasks.add(new TaskTime("商城", false, -1));
        dianTaoTasks.add(new TaskTime("红包", false, 0));
        dianTaoTasks.add(new TaskTime("购金", false, -1));
        dianTaoTasks.add(new TaskTime("开店", false, -1));
        dianTaoTasks.add(new TaskTime("签到", false, 0));
        dianTaoTasks.add(new TaskTime("打工", false, 0));
        dianTaoTasks.add(new TaskTime("睡觉", false, 0));
        dianTaoTasks.add(new TaskTime("走路", false, 0));

        HashSet<TaskTime> fqctyybTask = new HashSet<>();
        fqctyybTask.add(new TaskTime("商城", false, -1));
        fqctyybTask.add(new TaskTime("红包", false, 0));
        fqctyybTask.add(new TaskTime("购金", false, -1));
        fqctyybTask.add(new TaskTime("开店", false, -1));
        fqctyybTask.add(new TaskTime("签到", false, 0));
        fqctyybTask.add(new TaskTime("打工", false, 0));
        fqctyybTask.add(new TaskTime("睡觉", false, 0));
        fqctyybTask.add(new TaskTime("走路", false, 0));

        AppTaskTimeSet dianTao = new AppTaskTimeSet("点淘", dianTaoTasks);
        AppTaskTimeSet fqctyyb = new AppTaskTimeSet("番茄畅听音乐版", fqctyybTask);
        tasks.add(dianTao);
        tasks.add(fqctyyb);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashSet<AppTaskTimeSet> getTasks() {
        return tasks;
    }

    public void setTasks(HashSet<AppTaskTimeSet> tasks) {
        this.tasks = tasks;
    }
    //    public TaskTimeSet[] getTasks() {
    //        return tasks;
    //    }
    //
    //    public void setTasks(TaskTimeSet[] tasks) {
    //        this.tasks = tasks;
    //    }

    /**
     * 线程安全的获取yyyy-MM-dd格式的日期字符串。
     *
     * @return
     */
    public static String getDate_yyyyMMdd() {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 定义一个 DateTimeFormatter 使用 yyyy-MM-dd 的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 将 LocalDate 格式化为字符串
        String formattedDate = currentDate.format(formatter);
        System.out.println("formattedDate = " + formattedDate);
        return formattedDate;
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
