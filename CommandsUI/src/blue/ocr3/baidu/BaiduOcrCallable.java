package blue.ocr3.baidu;

import blue.ocr3.ScreenShotWindow;
import blue.ocr3.baidu.config.SingletonAipOcr;
import blue.ocr3.baidu.model.BaiduOCRModelTools;
import blue.ocr3.buttons.BaiduOCRButton;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import tools.copy.SystemClipboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class BaiduOcrCallable implements Callable<String> {
    /**
     * 触发的按钮
     */
    private JButton baiduOCRButton = BaiduOCRButton.getInstance().getButton();
    /**
     * 图片文件路径
     */
    private String path;

    public BaiduOcrCallable(String path) {
        this.path = path;
    }

    @Override
    public String call() throws Exception {
        Color defaultColor = null;
        if (baiduOCRButton != null) {
            // // 保存下原来的按钮颜色
            defaultColor = baiduOCRButton.getBackground();
            // // 设置按钮颜色，表示开始处理
            baiduOCRButton.setBackground(Color.pink);
        }
        // 让截屏的窗体不可以见
        ScreenShotWindow.getInstance().setVisible(false);
        // 获取图片中的文字
        String orcStr = getOcrString();

        // // 输出处理结果
        // System.out.println("--------------处理结果---------------");
        // System.out.println(orcStr);

        // // 将识别结果写到剪贴板中.
        // SystemClipboard.setSysClipboardText(orcStr);

        if (defaultColor != null) {
            // 将按钮设置成原来的颜色
            baiduOCRButton.setBackground(defaultColor);
        }

        return orcStr;
        // return null;
    }

    /**
     * 格式化OCR得到的字符串.
     *
     * @return 格式化后的字符串.
     */
    private String getOcrString() {
        String orcStr;
        // 这识别为多行,保留换行符
        orcStr = baiduOCR(path);
        return orcStr;
    }

    /**
     * 调用百度OCR接口,识别图片中的文字.
     *
     * @param imagePath 图片的路径.
     * @return 包含文字信息的JSON字符字符串.
     */
    private static String baiduOCR(String imagePath) {
        // 文档见： https://cloud.baidu.com/doc/OCR/s/Nkibizxlf#通用文字识别（高精度版）
        // 初始化一个AipOcr
        AipOcr client = SingletonAipOcr.getAipOcr();
        // 设置参数
        HashMap<String, String> options = new HashMap<>();
        //识别语言类型，默认为CHN_ENG
        options.put("language_type", "CHN_ENG");
        // 是否检测图像朝向，默认不检测，即：false
        options.put("detect_direction", "true");
        //是否检测语言，默认不检测
        options.put("detect_language", "true");
        //是否返回识别结果中每一行的置信度
        // options.put("probability", "true");

        // 普通精度，每个月1000次机会
        // JSONObject res = client.basicGeneral(imagePath, options);
        // 高精度每个月500次的机会
        JSONObject res = client.basicAccurateGeneral(imagePath, options);
        // PrintStr.printStr(res.toString());
        return BaiduOCRModelTools.toLines(res.toString());
    }

    /**
     * 启动文字识别
     */
    public static String startBaiduOCR() {
        // 图片的路径
        String imagePath = "1.png";
        // 截图，并保存
        try {
            // 截图，并获得图片
            ImageIO.write(ScreenShotWindow.getInstance().getSaveImage(), "png", new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建线程执行体
        // BaiduOcrRunable baiduOCRunable = new BaiduOcrRunable(imagePath);
        // 传入执行体,启动线程
        // new Thread(baiduOCRunable).start();


        FutureTask<String> futureTask = new FutureTask<>(new BaiduOcrCallable(imagePath));
        /***
         * futureTask 实现了 Runnable接口
         * 所以新建线程的时候可以传入futureTask
         * FutureTask重写的run方法中实际是调用了Callable接口在call()方法
         * 所以执行线程的时候回执行call方法的内容
         */
        Thread thread = new Thread(futureTask);
        thread.start();
        String value = null;
        try {
            value = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return value;
        // return null;
    }
}
