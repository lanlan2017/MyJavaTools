package python;

import java.io.File;

/**
 * Python图片数组生成器
 */
public class PythonImageArrayGenerator {
    public static void main(String[] args) {
        // String pathname = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\KuaiShou";
        String pathname = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\DouYin";
        imagesInDir2Array(pathname);
    }

    /**
     * 生成一个存放 目录下所有'.png'文件的python数组
     *
     * @param pathname 目录的字符串名称（绝对路径）
     */
    private static void imagesInDir2Array(String pathname) {
        File dir = new File(pathname);
        if (dir.isDirectory()) {
            // 获取目录下的所有.png文件列表
            String[] pngList = dir.list((dir1, name) -> name.endsWith(".png"));
            if (pngList != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("images = [\n");
                for (int i = 0; i < pngList.length; i++) {
                    sb.append("    ");
                    sb.append("sys.path[0]+");
                    sb.append("\"\\");
                    // sb.append(png);
                    sb.append(pngList[i]);
                    sb.append("\"");
                    if (i < pngList.length - 1) {
                        sb.append(",");
                    }
                    sb.append("\n");
                }
                sb.append("]\n");
                System.out.println(sb);
            }
        }
    }
}
