//package adbs.main.ui.jpanels.act.model;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Objects;
//
//public class AppTask2 {
//
//    /**
//     * 格式为yyyy-MM-dd的日期字符串。
//     */
//    private String date;
//
//    /**
//     * 任务列表
//     */
//    public HashMap<String, TaskTimeSet> tasks = new HashMap<>();
//
//
//    public AppTask2() {
//        HashSet<TaskTime> dianTaoTasks = new HashSet<>();
//        dianTaoTasks.add(new TaskTime("商城", false, -1));
//        dianTaoTasks.add(new TaskTime("红包", false, 0));
//        dianTaoTasks.add(new TaskTime("购金", false, -1));
//        dianTaoTasks.add(new TaskTime("开店", false, -1));
//        dianTaoTasks.add(new TaskTime("签到", false, 0));
//        dianTaoTasks.add(new TaskTime("打工", false, 0));
//        dianTaoTasks.add(new TaskTime("睡觉", false, 0));
//        dianTaoTasks.add(new TaskTime("走路", false, 0));
//
//        HashSet<TaskTime> fqctyybTask = new HashSet<>();
//        fqctyybTask.add(new TaskTime("商城", false, -1));
//        fqctyybTask.add(new TaskTime("红包", false, 0));
//        fqctyybTask.add(new TaskTime("购金", false, -1));
//        fqctyybTask.add(new TaskTime("开店", false, -1));
//        fqctyybTask.add(new TaskTime("签到", false, 0));
//        fqctyybTask.add(new TaskTime("打工", false, 0));
//        fqctyybTask.add(new TaskTime("睡觉", false, 0));
//        fqctyybTask.add(new TaskTime("走路", false, 0));
//
//        //        AppTaskTime dianTao = new AppTaskTime("点淘", dianTaoTasks);
//        //        AppTaskTime fqctyyb = new AppTaskTime("番茄畅听音乐版", fqctyybTask);
//
//        TaskTimeSet dianTao = new TaskTimeSet(dianTaoTasks);
//        TaskTimeSet fqctyyb = new TaskTimeSet(fqctyybTask);
//
//        tasks.put("点淘", dianTao);
//        tasks.put("番茄畅听音乐版", fqctyyb);
//        date = getDate_yyyyMMdd();
//    }
//
//    /**
//     * 线程安全的获取yyyy-MM-dd格式的日期字符串。
//     *
//     * @return
//     */
//    private static String getDate_yyyyMMdd() {
//        // 获取当前日期
//        LocalDate currentDate = LocalDate.now();
//        // 定义一个 DateTimeFormatter 使用 yyyy-MM-dd 的格式
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        // 将 LocalDate 格式化为字符串
//        String formattedDate = currentDate.format(formatter);
//        System.out.println("formattedDate = " + formattedDate);
//        return formattedDate;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public HashMap<String, TaskTimeSet> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(HashMap<String, TaskTimeSet> tasks) {
//        this.tasks = tasks;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        AppTask2 appTask2 = (AppTask2) o;
//        return Objects.equals(date, appTask2.date);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(date);
//    }
//}
