package adbs.main.ui.jpanels.act.model;

import java.util.ArrayList;

public class AppTaskTimeSet {
    private String appName;
//    private HashSet<TaskTime> taskTimeSet;
    private ArrayList<TaskTime> taskTimeSet;

//    public AppTaskTimeSet(String appName, HashSet<TaskTime> taskTimeSet) {
//        this.appName = appName;
//        this.taskTimeSet = taskTimeSet;
//    }

    public AppTaskTimeSet(String appName, ArrayList<TaskTime> taskTimeSet) {
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

    public ArrayList<TaskTime> getTaskTimeSet() {
        return taskTimeSet;
    }

    public void setTaskTimeSet(ArrayList<TaskTime> taskTimeSet) {
        this.taskTimeSet = taskTimeSet;
    }

    //    public HashSet<TaskTime> getTaskTimeSet() {
//        return taskTimeSet;
//    }
//
//    public void setTaskTimeSet(HashSet<TaskTime> taskTimeSet) {
//        this.taskTimeSet = taskTimeSet;
//    }

    @Override
    public String toString() {
        return "AppTaskTimeSet{" + "appName='" + appName + '\'' + ", taskTimeSet=" + taskTimeSet + '}';
    }
}
