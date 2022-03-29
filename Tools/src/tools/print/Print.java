package tools.print;

import java.io.File;
import java.util.List;

public class Print {
    /**
     * 遍历List<File>,并调用file.getAbsolutePath()方法打印
     *
     * @param fileList
     */
    public static void printFileList_getAbsolutePath(List<File> fileList) {
        for (File file : fileList) {
            System.out.println(file.getAbsolutePath());
        }
    }

    /**
     * 遍历List<File>,并调用file.getAbsolutePath()方法打印
     *
     * @param fileList
     */
    public static void printFileList_getName(List<File> fileList) {
        for (File file : fileList) {
            System.out.println(file.getName());
        }
    }

    /**
     * 遍历File[]，并调用file.getName()打印。
     *
     * @param files File[]
     */
    public static void printFiles(File[] files) {
        for (File file1 : files) {
            System.out.println(file1.getName());
        }
    }
}
