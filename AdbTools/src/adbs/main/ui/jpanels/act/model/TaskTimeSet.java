package adbs.main.ui.jpanels.act.model;

import java.util.HashSet;
import java.util.function.Consumer;

public class TaskTimeSet {
    //    /**
    //     * 应用名称
    //     */
    //    private String appName;
    /**
     * 任务列表
     */
    private HashSet<TaskTime> tasks;

    // JackSon需要默认的构造器用来反序列化
    public TaskTimeSet() {
    }


    public TaskTimeSet(HashSet<TaskTime> tasks) {
        //        this.appName = appName;
        this.tasks = tasks;
    }

    public HashSet<TaskTime> getTasks() {
        return tasks;
    }

    public void setTasks(HashSet<TaskTime> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //        sb.append("[");
        tasks.forEach(new Consumer<TaskTime>() {
            @Override
            public void accept(TaskTime taskTime) {
                String name = taskTime.getTaskName();
                int times = taskTime.getTimes();
                sb.append("    ").append(name).append("_").append(times).append("\n");

            }
        });
        sb.delete(sb.length() - 1, sb.length());
        //        sb.append("]");
        //        return appName + ":\n" + sb;
        return sb.toString();
    }

}
