package tools.cmd.tree;

import java.io.File;
import java.io.FilenameFilter;

public class MavenJavaWebFileNameFilter implements FilenameFilter {

    private String RootPath = null;

    MavenJavaWebFileNameFilter(String RootPath) {
        this.RootPath = RootPath;
    }

    @Override
    public boolean accept(File dir, String name) {
        // 如果该文件所在的目录是根目录
        if (RootPath.equals(dir.getAbsolutePath())) {
            // 第一级目录下的bin目录,
            // 或者target目录(maven,输出)不是java项目必须目录所以不输出
            if ("bin".equals(name) || "target".equals(name) || "process/tools".equals(name) || "build".equals(name) || ".git".equals(name)) {
                // System.out.println("不需要："+dir.getAbsolutePath()+"  "+name);
                return false;
            }
        }
        // 该文件名不能以点号开头,
        // 也不能以txt文件结尾
        // 文件名称不能是bin
        // 文件名称不能是target
        if (name.startsWith(".") || name.endsWith(".txt")) {
            // System.out.println("还不需要："+dir.getAbsolutePath()+"  "+name);
            return false;
        }
        return true;
    }
}
