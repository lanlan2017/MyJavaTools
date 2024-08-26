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
     * 任务完成次数
     */
    private int times;

    public TaskTimes(String name, int times) {
        this.name = name;
        this.times = times;
    }

    public TaskTimes() {
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
