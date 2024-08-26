package adbs.main.ui.jpanels.act.model;

import adbs.main.ui.jpanels.act.model.jaskson.file.JsonToFile;

import java.io.IOException;
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
    private HashSet<TaskTimes> taskTimesHashSet;

//    private static final AppTaskTime dianTao;

//    static {
//        HashSet<TaskTimes> dianTaoTaskTimes = new HashSet<>();
//        dianTaoTaskTimes.add(new TaskTimes("商城", -1));
//        dianTaoTaskTimes.add(new TaskTimes("红包", 0));
//        dianTaoTaskTimes.add(new TaskTimes("购金", -1));
//        dianTaoTaskTimes.add(new TaskTimes("开店", -1));
//        dianTaoTaskTimes.add(new TaskTimes("签到", 0));
//        dianTaoTaskTimes.add(new TaskTimes("打工", 0));
//        dianTaoTaskTimes.add(new TaskTimes("睡觉", 0));
//        dianTaoTaskTimes.add(new TaskTimes("走路", 0));
//
////        dianTaoTaskTimes.contains(new TaskTimes())
//
//        AppTaskTime  dianTao = new AppTaskTime("点淘", dianTaoTaskTimes);
//    }

    public AppTaskTime() {
    }

    public AppTaskTime(String appName, HashSet<TaskTimes> taskTimesHashSet) {
        this.appName = appName;
        this.taskTimesHashSet = taskTimesHashSet;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public HashSet<TaskTimes> getTaskTimesHashSet() {
        return taskTimesHashSet;
    }

    public void setTaskTimesHashSet(HashSet<TaskTimes> taskTimesHashSet) {
        this.taskTimesHashSet = taskTimesHashSet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //        sb.append("[");
        taskTimesHashSet.forEach(new Consumer<TaskTimes>() {
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
        dianTaoTaskTimes.add(new TaskTimes("商城", -1));
        dianTaoTaskTimes.add(new TaskTimes("红包", 0));
        dianTaoTaskTimes.add(new TaskTimes("购金", -1));
        dianTaoTaskTimes.add(new TaskTimes("开店", -1));
        dianTaoTaskTimes.add(new TaskTimes("签到", 0));
        dianTaoTaskTimes.add(new TaskTimes("打工", 0));
        dianTaoTaskTimes.add(new TaskTimes("睡觉", 0));
        dianTaoTaskTimes.add(new TaskTimes("走路", 0));

        //        dianTaoTaskTimes.contains(new TaskTimes())

        AppTaskTime  dianTao = new AppTaskTime("点淘", dianTaoTaskTimes);

        JsonToFile<AppTaskTime> jsonToFileHandler = new JsonToFile<>();
        //        System.out.println(dianTao);
        //        testPrintJSON();

        // 要序列化的文件
        String filePath = "dianTao.json";

        // 把这个对象序列化到文件中
        //            jsonToFileHandler.toJsonFile(user, filePath);
        try {
            jsonToFileHandler.toJsonFile(dianTao, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            AppTaskTime appTaskTime = jsonToFileHandler.fromJsonFile(filePath, AppTaskTime.class);
            System.out.println(appTaskTime);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
