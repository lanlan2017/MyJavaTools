package adbs.python;

import java.awt.*;

public class Region {
    public static int parts;
    private static int yStart;
    private static int yEnd;
    private static int screen_width;
    private static int screen_height;

    static {
        // 获取屏幕的宽度和高度
        Dimension screenSize = getScreenSize();
        // 输出屏幕的宽度和高度
//        System.out.println("屏幕宽度（像素）: " + screenSize.width);
//        System.out.println("屏幕高度（像素）: " + screenSize.height);
        screen_width = screenSize.width;
        screen_height = screenSize.height;

        parts = 1;

        // 区域开始的纵坐标
        yStart = 140;
        // 区域开始的横坐标
        yEnd = screen_height - 30;

        if (screen_width == 1366 && screen_height == 768) {
            yStart = 150;
            yEnd = 738;
            parts = 5;
        } else if (screen_width == 1366 && screen_height == 768) {
            yStart = 150;
            yEnd = 780;
            parts = 7;
        }

    }

    public static String regionCode(String scrcpyOrderStr) {
        if (scrcpyOrderStr.matches("[0-9]+")) {
            int parseInt = Integer.parseInt(scrcpyOrderStr);
            return regionCode(parseInt);
        }
        return "";
    }

    public static String regionCode(int scrcpyOrder) {
        String code = "\n" +
                "# 笔记本的显示器的宽度和高度\n" +
                "screen_width = " + screen_width + "\n" +
                "screen_height = " + screen_height + "\n" +
                "# 要把屏幕分割成几份\n" +
                "parts = " + parts + "\n" +
                "# 计算每一份的宽度\n" +
                "part_width = screen_width // parts\n" +
                "# 确定是几个区域[0," + (parts - 1) + "]\n" +
                "scrcpyOrder = " + scrcpyOrder + "\n" +
                "# 要查找的区域\n" +
                "region = (scrcpyOrder * part_width, " + yStart + ", part_width, " + yEnd + ")\n";

        return code;
    }

    private static Dimension getScreenSize() {
        // 获取屏幕默认的Toolkit
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // 获取屏幕的尺寸（像素）
        Dimension screenSize = toolkit.getScreenSize();
        return screenSize;
    }
}
