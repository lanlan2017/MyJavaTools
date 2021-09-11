package tools.markdown.niuke;

import java.io.File;
import java.net.URL;

/**
 * 获取.jar文件所在的目录路径
 */
public class ToolsJarPath {
    /**
     * 获取当前可执行的jar包的路径。
     * @return 当前正在运行的jar包的路径
     */
    public static String getPath() {
        return getPath(ToolsJarPath.class);
    }

    /**
     * 获取Class对应的路径，
     * @param class1
     * @return jar
     */
    public static String getPath(Class<?> class1) {
        String path = "";
        try {
            //jar 中没有目录的概念
            //获得当前的URL
            // URL location = Test.class.getProtectionDomain().getCodeSource().getLocation();
            URL location = class1.getProtectionDomain().getCodeSource().getLocation();
            //构建指向当前URL的文件描述符
            File file = new File(location.getPath());
            //如果是目录,指向的是包所在路径，而不是文件所在路径
            if (file.isDirectory()) {
                // System.out.println("是目录:" + file.getAbsolutePath());
                //直接返回绝对路径
                path = file.getAbsolutePath();
            }
            //如果是文件,这个文件指定的是jar所在的路径(注意如果是作为依赖包，这个路径是jvm启动加载的jar文件名)
            else {
                // System.out.println("是jar：" + file.getAbsolutePath());
                //返回jar所在的父路径
                path = file.getParent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}
