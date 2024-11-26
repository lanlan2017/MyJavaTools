package adbs.main.run.act;

import java.util.HashSet;

public abstract class ActWait {
    protected HashSet<String> wait11s_Act;
    protected HashSet<String> wait13s_Act;
    protected HashSet<String> wait20s_Act;
    protected HashSet<String> wait1H_Act;
    protected HashSet<String> wait3M_Act;

    protected HashSet<String> touTiao95sApp;
    protected HashSet<String> kuaiShou95sApp;
    protected HashSet<String> wait95sApp;

    protected HashSet<String> touTiao180sApp;
    protected HashSet<String> wait180sApp;

    public ActWait() {
        //设置从哪些APP离开后需要等待180秒钟
        initWait180sApp();
        //射中从哪些APP离开后需要等待95秒钟
        initWait95sApp();

        initWait11sAct();
        // 设置在哪些Activity界面钟，线程等待间隔为15秒
        initWait15sAct();
        // 设置在哪些Activity界面钟，线程等待间隔为30秒
        initWait20sAct();
        //设置在哪些Activity界面中，线程等待间隔为3分钟
        initWait3M_Act();
        // 设置在哪些Activity界面中，线程等待间隔为1小时
        initWait1HAct();
    }

    protected void initWait20sAct() {
        // HashSet<String> wait30S = new HashSet<>();
        //        HashSet<String> wait30s_Act = new HashSet<>();

        wait20s_Act = new HashSet<>();
        // 华为桌面
        //        return wait30s_Act;
    }

    /**
     * 在哪些Activity中，线程等待间隔时间为1小时
     */
    protected void initWait1HAct() {
        // 需要等待1个小时的Activity
        // HashSet<String> wait1H_Act = new HashSet<>();
        wait1H_Act = new HashSet<>();
        //快手免费小说阅读界面
        wait1H_Act.add("com.kuaishou.kgx.novel/com.kuaishou.novel.read.ReaderActivityV2");
        //番茄免费小说阅读界面
        wait1H_Act.add("com.dragon.read/.reader.ui.ReaderActivity");
        //今日头条极速版电视剧播放界面
        wait1H_Act.add("com.ss.android.article.lite/com.ss.android.xigualongvideo.detail.LongVideoDetailActivity");
    }


    protected HashSet<String> initWait11sAct() {
        wait11s_Act = new HashSet<>();
        // 点淘
        wait11s_Act.add("com.taobao.live/.h5.BrowserUpperActivity");
        // 点淘省钱特辑，
        wait11s_Act.add("com.taobao.live/.h5.BrowserActivity");
        // 番茄畅听，音乐播放界面
        wait11s_Act.add("com.xs.fm.lite/com.dragon.read.pages.main.MainFragmentActivity");
        return wait11s_Act;

    }

    /**
     * 设置检查频率为15秒的Activity
     */
    protected void initWait15sAct() {
        // HashSet<String> wait30S = new HashSet<>();
        wait13s_Act = new HashSet<>();
        // 直播界面
        wait13s_Act.add("com.taobao.live/.TaoLiveVideoActivity");
        // 桌面
        wait13s_Act.add("com.huawei.android.launcher/.unihome.UniHomeLauncher");
        // //
        // wait15s_Act.add("com.taobao.live/.h5.BrowserUpperActivity");
        // //省钱特辑，
        // wait15s_Act.add("com.taobao.live/.h5.BrowserActivity");
    }


    /**
     * 在哪些Activity中，线程等待间隔为3分钟
     */
    protected void initWait3M_Act() {
        wait3M_Act = new HashSet<>();
        // 红果免费短剧，短剧播放界面
        wait3M_Act.add("com.phoenix.read/com.ss.android.excitingvideo.ExcitingVideoActivity");
        //番茄畅听音乐版，音频播放界面
        wait3M_Act.add("com.xs.fm.lite/com.dragon.read.reader.speech.page.AudioPlayActivity");
        //番茄畅听音乐版，全屏歌词显示界面
        wait3M_Act.add("com.xs.fm.lite/com.dragon.read.music.lyric.FullScreenLyricActivity");
    }

    /**
     * 从哪些APP离开之后需要等待95秒。
     */
    protected void initWait95sApp() {
        wait95sApp = new HashSet<>();
        // 百度极速版
        wait95sApp.add("com.baidu.searchbox.lite");
        initKuaiShou95sApp();
        wait95sApp.addAll(kuaiShou95sApp);
        //        touTiao95sApp();
        initTouTiao95sApp();
        wait95sApp.addAll(touTiao95sApp);

    }

    private void initKuaiShou95sApp() {
        kuaiShou95sApp = new HashSet<>();
        // 快手极速版
        kuaiShou95sApp.add("com.kuaishou.nebula");
        // 快手
        kuaiShou95sApp.add("com.smile.gifmaker");
    }

    private void initTouTiao95sApp() {
        touTiao95sApp = new HashSet<>();
        //        touTiao95sApp
        // 悟空浏览器
        touTiao95sApp.add("com.cat.readall");
    }

    /**
     * 确定从哪些APP离开后需要等待180秒
     */
    protected void initWait180sApp() {
        wait180sApp = new HashSet<>();
        // 趣头条
        wait180sApp.add("com.jifen.qukan");
        initTouTiao180sApp();
        wait180sApp.addAll(touTiao180sApp);

        //        touTiaoApp();
    }

    private void initTouTiao180sApp() {
        touTiao180sApp = new HashSet<>();
        //        touTiaoApp.add()
        //今日头条极速版
        touTiao180sApp.add("com.ss.android.article.lite");
        // 今日头条
        touTiao180sApp.add("com.ss.android.article.news");
        // 西瓜视频
        touTiao180sApp.add("com.ss.android.article.video");
        // 番茄免费小说
        touTiao180sApp.add("com.dragon.read");
        //番茄畅听
        touTiao180sApp.add("com.xs.fm");
        //番茄畅听音乐版
        touTiao180sApp.add("com.xs.fm.lite");
        // 抖音
        touTiao180sApp.add("com.ss.android.ugc.aweme");
        //抖音极速版
        touTiao180sApp.add("com.ss.android.ugc.aweme.lite");
        //抖音商城版
        touTiao180sApp.add("com.ss.android.ugc.livelite");
        //抖音火山版
        touTiao180sApp.add("com.ss.android.ugc.live");
        //红果免费短剧
        touTiao180sApp.add("com.phoenix.read");
        //蛋花免费小说
        touTiao180sApp.add("com.eggflower.read");
        //有柿
        touTiao180sApp.add("com.ss.android.article.search");
    }


    //    protected abstract void _wait(int endWait);
    protected boolean isTouTiaoApp(String currentPackageName) {
        return touTiao180sApp.contains(currentPackageName) || touTiao95sApp.contains(currentPackageName);
    }
}
