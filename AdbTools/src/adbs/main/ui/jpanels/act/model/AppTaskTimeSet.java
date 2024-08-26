package adbs.main.ui.jpanels.act.model;

import java.util.HashSet;

public class AppTaskTimeSet {
    private String appName;
    private HashSet<TaskTime> taskTimeSet;

    public AppTaskTimeSet(String appName, HashSet<TaskTime> taskTimeSet) {
        this.appName = appName;
        this.taskTimeSet = taskTimeSet;
    }

    public AppTaskTimeSet() {
    }

    public AppTaskTimeSet(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public HashSet<TaskTime> getTaskTimeSet() {
        return taskTimeSet;
    }

    public void setTaskTimeSet(HashSet<TaskTime> taskTimeSet) {
        this.taskTimeSet = taskTimeSet;
    }

    @Override
    public String toString() {
        return "AppTaskTimeSet{" + "appName='" + appName + '\'' + ", taskTimeSet=" + taskTimeSet + '}';
    }
}
