package adbs.main.ui.jpanels.act.model;

import adbs.main.ui.jpanels.act.model.jaskson.file.JsonToFile;

import java.util.HashSet;
import java.util.function.Consumer;

public class AppTaskTime {
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 任务列表
     */
    private HashSet<TaskTimes> tasks;

    // JackSon需要默认的构造器用来反序列化
    public AppTaskTime() {
    }

    public AppTaskTime(String appName, HashSet<TaskTimes> tasks) {
        this.appName = appName;
        this.tasks = tasks;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public HashSet<TaskTimes> getTasks() {
        return tasks;
    }

    public void setTasks(HashSet<TaskTimes> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //        sb.append("[");
        tasks.forEach(new Consumer<TaskTimes>() {
            @Override
            public void accept(TaskTimes taskTimes) {
                String name = taskTimes.getName();
                int times = taskTimes.getTimes();
                sb.append("    ").append(name).append("_").append(times).append("\n");

            }
        });
        sb.delete(sb.length() - 1, sb.length());
        //        sb.append("]");
        return appName + ":\n" + sb;
    }

    public static void main(String[] args) {
        HashSet<TaskTimes> dianTaoTaskTimes = new HashSet<>();
        dianTaoTaskTimes.add(new TaskTimes("商城", false, -1));
        dianTaoTaskTimes.add(new TaskTimes("红包", false, 0));
        dianTaoTaskTimes.add(new TaskTimes("购金", false, -1));
        dianTaoTaskTimes.add(new TaskTimes("开店", false, -1));
        dianTaoTaskTimes.add(new TaskTimes("签到", false, 0));
        dianTaoTaskTimes.add(new TaskTimes("打工", false, 0));
        dianTaoTaskTimes.add(new TaskTimes("睡觉", false, 0));
        dianTaoTaskTimes.add(new TaskTimes("走路", false, 0));

        //        dianTaoTaskTimes.contains(new TaskTimes())

        AppTaskTime dianTao = new AppTaskTime("点淘", dianTaoTaskTimes);

        JsonToFile<AppTaskTime> jsonToFile = new JsonToFile<>();
        //        System.out.println(dianTao);
        //        testPrintJSON();

        // 要序列化的文件
        String filePath = "dianTao.json";

        //        tofile(dianTao, jsonToFile, filePath);

        // 把这个对象序列化到文件中
        //            jsonToFile.toJsonFile(user, filePath);
        jsonToFile.toJsonFile(dianTao, filePath);

        //            AppTaskTime appTaskTime = getAppTaskTime(jsonToFile, filePath, AppTaskTime.class);

        AppTaskTime appTaskTime = jsonToFile.fromJsonFile(filePath, AppTaskTime.class);
        System.out.println(appTaskTime);

        TaskTimes toFind = new TaskTimes("打工");
        HashSet<TaskTimes> taskTimesHashSet = appTaskTime.getTasks();
        //            if (taskTimesHashSet.contains(toFind)) {
        //                System.out.println("找到");
        //                taskTimesHashSet.
        //            }

        taskTimesHashSet.forEach(new Consumer<TaskTimes>() {
            @Override
            public void accept(TaskTimes taskTimes) {
                if (taskTimes.equals(toFind)) {
                    System.out.println("taskTimes = " + taskTimes);
                    String name = taskTimes.getName();
                    int times = taskTimes.getTimes();
                    System.out.println("name = " + name);
                    System.out.println("times = " + times);
                    taskTimes.setTimes(times + 11111);
                    jsonToFile.toJsonFile(appTaskTime, filePath);

                    return;
                }
            }
        });


    }

    //    private static AppTaskTime getAppTaskTime(JsonToFile<AppTaskTime> jsonToFileHandler, String filePath, Class<AppTaskTime> clazz) {
    //        AppTaskTime appTaskTime = jsonToFileHandler.fromJsonFile(filePath, clazz);
    //        System.out.println(appTaskTime);
    //        return appTaskTime;
    //    }

    //    private static void tofile(AppTaskTime dianTao, JsonToFile<AppTaskTime> jsonToFileHandler, String filePath) {
    //        // 把这个对象序列化到文件中
    //        //            jsonToFileHandler.toJsonFile(user, filePath);
    //        jsonToFileHandler.toJsonFile(dianTao, filePath);
    //    }

    //    private static void testPrintJSON() {
    //        ObjectMapper mapper = new ObjectMapper();
    //        String jsonString = null;
    //        try {
    //            jsonString = mapper.writeValueAsString(dianTao);
    //        } catch (JsonProcessingException e) {
    //            e.printStackTrace();
    //        }
    //        System.out.println(jsonString);
    //    }
}
