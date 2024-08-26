package adbs.main.ui.jpanels.act.model;

import java.util.Objects;

/**
 * 任务，任务要完成的次数
 */
public class TaskTimes {
    /**
     * 任务名称
     */
    private String name;
    /**
     * 已经签到
     */
    private boolean selected;

    /**
     * 任务完成次数
     */
    private int times;

    public TaskTimes(String name, boolean selected, int times) {
        this.name = name;
        this.selected = selected;
        this.times = times;
    }

//    public TaskTimes(String name, int times) {
//        this.name = name;
//        this.times = times;
//    }

    public TaskTimes(String name) {
        this.name = name;
    }

    /**
     * 必须提供默认构造器，一边使用JackSon进行序列化
     */
    public TaskTimes() {
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        TaskTimes taskTimes = (TaskTimes) o;
        return Objects.equals(name, taskTimes.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
