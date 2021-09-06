package blue.ocr.baidu;

import blue.ocr.baidu.config.SingletonAipOcr;
import tools.copy.SystemClipboard;
import com.baidu.aip.ocr.AipOcr;
import net.sf.json.JSONArray;
import blue.ocr.formatter.Formatter;
import blue.ocr.formatter.FormatterMultiLine;
import org.json.JSONObject;
import blue.ocr.ui.ScreenShotWindow;
import blue.ocr.ui.ToolsWindow;
import blue.ocr.ui.toolbar.buttons.BaiduOCRButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaiduOcrRunable implements Runnable {
    /**
     * 触发的按钮
     */
    private JButton baiduOCRButton = BaiduOCRButton.getInstance().getBaiduOCRButton();
    /**
     * 图片文件路径
     */
    private String path;
    /**
     * 格式化工具
     */
    private static Formatter formatter;

    /**
     * 设置格式化器
     *
     * @param formatter 格式化器.
     */
    public static void setFormatter(Formatter formatter) {
        BaiduOcrRunable.formatter = formatter;
    }

    public BaiduOcrRunable(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        // 保存下原来的按钮颜色
        Color defaultColor = baiduOCRButton.getBackground();
        // 设置按钮颜色，表示开始处理
        baiduOCRButton.setBackground(Color.pink);
        // 让截屏的窗体不可以见
        ScreenShotWindow.getInstance().setVisible(false);
        // 获取图片中的文字
        String orcStr = formattedOcrString();
        // 输出处理结果
        System.out.println("--------------处理结果---------------");
        System.out.println(orcStr);
        // 将识别结果写到剪贴板中.
        SystemClipboard.setSysClipboardText(orcStr);
        // 将按钮设置成原来的颜色
        baiduOCRButton.setBackground(defaultColor);
        // 移动工具栏到左上角，避免挡住屏幕不好阅读
        ToolsWindow.defaultLocation();
    }


    /**
     * 格式化OCR得到的字符串.
     *
     * @return 格式化后的字符串.
     */
    private String formattedOcrString() {
        String orcStr;
        // 如果设置了格式化器
        if (formatter != null) {
            // 如果是多行的格式化器
            if (formatter instanceof FormatterMultiLine) {
                // 这识别为多行,保留换行符
                orcStr = baiduOCRMultiLine(path);
            }
            // 如果是不是多行的格式化器
            else {
                // 则合并为一行
                orcStr = baiduOCRToOneLine(path);
            }
            orcStr = formatter.format(orcStr);
        } else {
            //System.out.println("xxxx");
            // 默认识别为单行模式
            orcStr = baiduOCRToOneLine(path);
        }
        return orcStr;
    }

    /**
     * 调用百度OCR接口,识别图片中的文字.
     *
     * @param imagePath 图片的路径.
     * @return 包含文字信息的JSON字符字符串.
     */
    private static String baiduOCR(String imagePath) {
        // 初始化一个AipOcr
        AipOcr client = SingletonAipOcr.getAipOcr();
        // 调用文字识别接口,返回JSON数据
        JSONObject res = client.basicGeneral(imagePath, new HashMap<>());


        // 取出HashMap
        HashMap<String, Object> resMap = (HashMap<String, Object>) res.toMap();

        // 遍历hashMap
        Iterator<Map.Entry<String, Object>> it = resMap.entrySet().iterator();
        // 字符串缓冲,用于存放识别的结果
        StringBuilder sbBuilder = new StringBuilder();
        Map.Entry<String, Object> entry;
        while (it.hasNext()) {
            entry = it.next();
            if (entry.getKey().equals("words_result")) {
                // 获取词组
                JSONArray jsonArray = JSONArray.fromObject(entry.getValue());
                //System.out.println(jsonArray.toString());
                for (Object object : jsonArray) {
                    sbBuilder.append(object.toString());
                }
            }
        }
        //System.out.println(sbBuilder.toString());
        // 取出识别的文字结果
        return sbBuilder.toString();
    }

    /**
     * 调用百度接口对识别图片中的文字.并将所有的文字整理成一行.
     *
     * @param imagePath 图片的路径
     * @return 识别的单行文字
     */
    private static String baiduOCRToOneLine(String imagePath) {
        String words = baiduOCR(imagePath);
        System.out.println("---------------识别结果---------------------");
        System.out.println(words);
        //// 使用正则表达式删除JSON字符串中的无关字符.
        words = words.replace("\"}{\"words\":\"", "");
        words = words.replace("{\"words\":\"", "");
        words = words.replace("\"}", "");
        // 取消对双引号的转义
        words = words.replace("\\\"", "\"");
        return words;
    }

    /**
     * 识别为多行
     *
     * @param imagePath 图片路径
     * @return 删除json标记之后的多行字符串.
     */
    private static String baiduOCRMultiLine(String imagePath) {
        String words = baiduOCR(imagePath);
        System.out.println("------------识别结果-------------");
        System.out.println(words);
        words = words.replace("\"}{\"words\":\"", "\n");
        words = words.replace("{\"words\":\"", "\n");
        words = words.replace("\"}", "");
        // 取消对双引号的转义
        words = words.replace("\\\"", "\"");
        return words;
    }

    /**
     * 启动文字识别
     */
    public static void startBaiduOCR() {
        String imagePath = "1.png";
        try {
            ImageIO.write(ScreenShotWindow.getInstance().getSaveImage(), "png", new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建线程执行体
        BaiduOcrRunable baiduOCRunable = new BaiduOcrRunable(imagePath);
        // 传入执行体,启动线程
        new Thread(baiduOCRunable).start();
    }
}
