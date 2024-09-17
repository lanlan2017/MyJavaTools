package adbs.main.ui.jpanels.act.model;

import java.util.ArrayList;
import java.util.Arrays;

public class AppTaskTimeSet {
    private String appName;
    //    private HashSet<TaskTime> taskTimeSet;
    private ArrayList<TaskTime> taskTimeList;

    //    public AppTaskTimeSet(String appName, HashSet<TaskTime> taskTimeSet) {
    //        this.appName = appName;
    //        this.taskTimeSet = taskTimeSet;
    //    }

    public AppTaskTimeSet(String appName, ArrayList<TaskTime> taskTimeList) {
        this.appName = appName;
        this.taskTimeList = taskTimeList;
    }

    public AppTaskTimeSet(String appName, TaskTime[] taskTimeArr) {
        this.appName = appName;
        //        this.taskTimeSet = taskTimeSet;
        this.taskTimeList = new ArrayList<>(Arrays.asList(taskTimeArr));
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

    public ArrayList<TaskTime> getTaskTimeList() {
        return taskTimeList;
    }

    public void setTaskTimeList(ArrayList<TaskTime> taskTimeList) {
        this.taskTimeList = taskTimeList;
    }

    //    public HashSet<TaskTime> getTaskTimeSet() {
    //        return taskTimeSet;
    //    }
    //
    //    public void setTaskTimeSet(HashSet<TaskTime> taskTimeSet) {
    //        this.taskTimeSet = taskTimeSet;
    //    }

//    @Override
//    public String toString() {
//        return "AppTaskTimeSet{" + "appName='" + appName + '\'' + ", taskTimeSet=" + taskTimeList + '}';
//    }

    @Override
    public String toString() {
        return "AppTaskTimeSet{" + "appName='" + appName + '\'' + ", taskTimeList=" + taskTimeList + '}';
    }
}
