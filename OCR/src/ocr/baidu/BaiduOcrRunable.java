package ocr.baidu;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JButton;

import clipboard.swing.SystemClipboard;
import org.json.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import net.sf.json.JSONArray;
import ocr.baidu.config.SingletonAipOcr;
import ocr.baidu.formatter.FormatterByCmd;
import ui.ScreenShotWindow;
import ui.ToolsWindow;

public class BaiduOcrRunable implements Runnable {
    /**
     * 触发的按钮
     */
    private JButton baiduOCRButton = ToolsWindow.getInstance()
            .getBaiduOCRButton();
    /**
     * 图片文件路径
     */
    private String path;
    /**
     * 格式化工具
     */
    private static FormatterByCmd formatter;

    /**
     * 设置格式化器
     *
     * @param formatter
     */
    public static void setFormatter(FormatterByCmd formatter) {
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
        String orcStr = baiduOCR(path);
        // 如果设置了格式化器
        if (formatter != null) {
            orcStr = formatter.format(orcStr);
        }
        // 输出处理结果
        System.out.println(orcStr);
        // 将识别结果写到剪贴板中.
        SystemClipboard.setSysClipboardText(orcStr);
        // 将按钮设置成原来的颜色
        baiduOCRButton.setBackground(defaultColor);
        // 移动工具栏到左上角，避免挡住屏幕不好阅读
        ToolsWindow.getInstance().setLocation(0, 0);
    }

    /**
     * 调用百度接口对识别图片中的文字.
     *
     * @param imagePath 图片的路径
     */
    private static String baiduOCR(String imagePath) {
        // 初始化一个AipOcr
        AipOcr client = SingletonAipOcr.getAipOcr();
        // 调用文字识别接口,返回JSON数据
        JSONObject res = client.basicGeneral(imagePath,
                new HashMap<String, String>());
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
                System.out.println(jsonArray.toString());
                for (Object object : jsonArray) {
                    sbBuilder.append(object.toString());
                }
            }
        }
        // 取出识别的文字结果
        String words = sbBuilder.toString();
        // 使用正则表达式删除无关字符.
        words = words.replaceAll(
                "(?:(?:\\\"\\})?\\{\\\"words\\\":\\\"|\\\"\\})", "");
        // 输出到剪贴板中
        // SysClipboard.setSysClipboardText(words);
        return words;
    }

    /**
     * 启动文字识别
     */
    public static void startBaiduOCR() {
        String imagePath = "1.png";
        try {
            ImageIO.write(ScreenShotWindow.getInstance().getSaveImage(), "png",
                    new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建线程执行体
        BaiduOcrRunable baiduOCRunable = new BaiduOcrRunable(imagePath);
        // 传入执行体,启动线程
        new Thread(baiduOCRunable).start();
    }
}
