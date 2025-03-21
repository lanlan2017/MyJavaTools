package adbs.main.ui.jpanels.act.model;

import adbs.main.ui.jpanels.act.DateString;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 每个赚钱APP的详细任务
 */
public class AppTask3 {
    /**
     * 格式为yyyy-MM-dd的日期字符串。
     */
    private String date;
    // private HashSet<AppTaskTimeSet> tasks;
    private ArrayList<AppTaskTimeSet> tasks;

    public AppTask3(String date) {
        this.date = date;
        // tasks = new HashSet<>();
        tasks = new ArrayList<>();
    }

    public AppTask3(String date, ArrayList<AppTaskTimeSet> tasks) {
        this.date = date;
        this.tasks = tasks;
    }

    public AppTask3() {
        // tasks = new HashSet<>();
        tasks = new ArrayList<>();
        date = DateString.getDate_yyyyMMdd();

        AppTaskTimeSet dianTao = new AppTaskTimeSet("点淘", new TaskTime[]{
                new TaskTime("商城", -1),
                new TaskTime("红包", 0),
                new TaskTime("打工", 0),
                new TaskTime("睡觉", 0),
                new TaskTime("购金", 0),
                new TaskTime("开店", -1),
                new TaskTime("签到", 0),
                new TaskTime("走路", 0),
                new TaskTime("浏览", 0),
                new TaskTime("气泡", 0)
        });

        AppTaskTimeSet taoBao = new AppTaskTimeSet("淘宝", new TaskTime[]{
                new TaskTime("红包签到", 0),
                new TaskTime("视频", 0),
                new TaskTime("淘金币", 0),
                new TaskTime("淘宝秒杀", 0),
                new TaskTime("芭芭农场", 0),
                new TaskTime("充值中心", 0)
        });

        AppTaskTimeSet taoTe = new AppTaskTimeSet("淘特", new TaskTime[]{
                new TaskTime("天天领红包", 0),
                new TaskTime("淘宝秒杀", 0),
                new TaskTime("小鸡送好礼", 0),
                new TaskTime("现金签到", 0),
                new TaskTime("积分兑红包", 0)
        });

        AppTaskTimeSet fqctyyb = new AppTaskTimeSet("番茄畅听音乐版", new TaskTime[]{
                new TaskTime("签到"),
                new TaskTime("听歌"),
                new TaskTime("睡觉"),
                new TaskTime("走路")
        });

        AppTaskTimeSet quTouTiao = new AppTaskTimeSet("趣头条", new TaskTime[]{
                new TaskTime("签到"),
                new TaskTime("通知"),
                new TaskTime("小视频")
        });

        AppTaskTimeSet ksmfxs = new AppTaskTimeSet("快手免费小说", new TaskTime[]{
                new TaskTime("签到"),
                new TaskTime("阅读"),
                new TaskTime("广告"),
                new TaskTime("宝箱")
        });


        tasks.add(dianTao);
        tasks.add(taoBao);
        tasks.add(taoTe);
        tasks.add(quTouTiao);
        tasks.add(ksmfxs);
        tasks.add(fqctyyb);

        tasks.add(new AppTaskTimeSet("中青看点", new TaskTime[]{
                new TaskTime("签到"),
                new TaskTime("通知"),
                new TaskTime("提现")
        }));
        //        tasks.add(zhongQingKanDian);
        tasks.add(new AppTaskTimeSet("速读免费小说", new TaskTime[]{
                new TaskTime("签到"),
                new TaskTime("阅读"),
                new TaskTime("红包")
        }));
        tasks.add(new AppTaskTimeSet("蛋花免费小说", new TaskTime[]{
                new TaskTime("签到"),
                new TaskTime("阅读"),
                new TaskTime("听书"),
                new TaskTime("红包")
        }));

        //
        tasks.add(new AppTaskTimeSet("红果免费短剧", new TaskTime[]{
                new TaskTime("签到"),
                new TaskTime("短剧"),
                new TaskTime("走路"),
                new TaskTime("睡觉"),
                new TaskTime("吃饭")
        }));
        //
        tasks.add(new AppTaskTimeSet("喜马拉雅极速版", new TaskTime[]{
                new TaskTime("签到"),
                new TaskTime("听书"),
                new TaskTime("视频"),
                new TaskTime("喝水"),
                new TaskTime("答题")
        }));
        //
        tasks.add(new AppTaskTimeSet("抖音商城", new TaskTime[]{
                new TaskTime("签到"),
                new TaskTime("广告"),
//                new TaskTime("视频"),
//                new TaskTime("喝水"),
//                new TaskTime("答题")
        }));
        tasks.add(new AppTaskTimeSet("有柿", new TaskTime[]{
                new TaskTime("签到"),
                new TaskTime("广告"),
                new TaskTime("宝箱"),
                new TaskTime("走路"),
                new TaskTime("早餐"),
                new TaskTime("午餐"),
                new TaskTime("晚餐"),
//                new TaskTime("晚餐")
        }));
        tasks.add(new AppTaskTimeSet("美团", new TaskTime[]{
                new TaskTime("签到"),
                new TaskTime("预约"),
                new TaskTime("宝箱"),
//                new TaskTime("晚餐")
        }));


    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<AppTaskTimeSet> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<AppTaskTimeSet> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AppTask3 appTask3 = (AppTask3) o;
        return Objects.equals(date, appTask3.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
