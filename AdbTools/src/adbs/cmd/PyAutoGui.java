package adbs.cmd;

import java.awt.*;

public class PyAutoGui {

    /**
     * 执行Python文件，获取坐标点
     *
     * @param pyFilePath 要运行的python文件
     * @return 要点击的坐标点
     */
    public static Point getPoint(String pyFilePath) {
        // 运行python进程，获取进程的标准输出
        String pyStr = PythonRun.runPython(pyFilePath);
        // 打印进程输出
        System.out.println("pyStr=" + pyStr);
        if (pyStr.contains("(x=")) {
            // 从进程输出中取得横坐标x纵坐标Y
            String x = pyStr.substring(pyStr.indexOf("(x=") + "(x=".length(), pyStr.indexOf(","));
            String y = pyStr.substring(pyStr.indexOf("y=") + "y=".length(), pyStr.lastIndexOf(")"));
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