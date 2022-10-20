package adbs.cmd;

import java.awt.*;

/**
 * 获取PyAutoGui找到的图片坐标对应的java.awt.Point对象。
 */
public class PyAutoGui {
    /**
     * 获取python执行结果中的坐标。
     *
     * @param pyOutput python执行输出
     * @return 屏幕中的坐标
     */
    public static Point getPoint(String pyOutput) {
        if (pyOutput.contains("(x=")) {
            // 从进程输出中取得横坐标x纵坐标Y
            String x = pyOutput.substring(pyOutput.indexOf("(x=") + "(x=".length(), pyOutput.indexOf(","));
            String y = pyOutput.substring(pyOutput.indexOf("y=") + "y=".length(), pyOutput.lastIndexOf(")"));
            // System.out.println("x = " + x);
            // System.out.println("y = " + y);
            // java.awt.Point point=new java.awt.Point(, );
            // 创建坐标点
            Point point = new Point(Integer.parseInt(x), Integer.parseInt(y));
            // System.out.println(point);
            // 返回坐标点
            return point;
        }
        return null;
    }
}