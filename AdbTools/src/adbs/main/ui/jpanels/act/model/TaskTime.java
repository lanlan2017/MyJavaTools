package adbs.main.ui.jpanels.act.model;

import java.util.Objects;

/**
 * 任务，任务要完成的次数
 */
public class TaskTime {
    /**
     * 任务不需要完成多次时的标志
     */
    public static final int NotNeedTIMES = -1;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 已经签到
     */
    private boolean selected;

    /**
     * 任务完成次数
     */
    private int times;

    public TaskTime(String taskName, boolean selected, int times) {
        this.taskName = taskName;
        this.selected = selected;
        this.times = times;
    }

    public TaskTime(String taskName) {

        this.taskName = taskName;
        this.selected = false;
        this.times = NotNeedTIMES;
    }

    public TaskTime(String taskName, int times) {
        this.taskName = taskName;
        this.selected = false;
        this.times = times;
    }

    /**
     * 必须提供默认构造器，一边使用JackSon进行序列化
     */
    public TaskTime() {
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TaskTime taskTime = (TaskTime) o;
        return Objects.equals(taskName, taskTime.taskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName);
    }

    @Override
    public String toString() {
        return "TaskTime{" + "taskName='" + taskName + '\'' + ", selected=" + selected + ", times=" + times + '}';
    }
}
