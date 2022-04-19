package tools.cmd.tree;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class MyTreeTest {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        File file = new File(path);
        // File[] dirlist=file.listFiles();
        // File[] dirlist=file.listFiles();
        File[] dirlist;

        dirlist = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                System.out.print(dir.getAbsolutePath() + "  ");
                System.out.println(name);
                if (name.equals(".git")) {
                    return false;
                }
                return true;
            }
        });

        // dirlist=file.listFiles(new FileFilter() {
        //     @Override
        //     public boolean accept(File pathname) {
        //         System.out.println(pathname);
        //         return false;
        //     }
        // });

        for (File file1 : dirlist) {
            System.out.println("列表：" + file1.getAbsolutePath());
        }

    }
}
