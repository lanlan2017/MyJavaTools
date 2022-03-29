package tools.file;

import java.io.*;

public class Files {

    /**
     * 把字符串覆盖写入文件
     *
     * @param file 要写入的文件
     * @param str  要写入文件的字符串
     */
    public static void writerFile(File file, String str) {
        BufferedWriter writer = null;
        try {
            // 覆盖写入
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(str);
            // 多写入换行符
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 向文件追加字符串
     *
     * @param file 要追加内容的文件
     * @param str  要追加的字符串
     */
    public static void appendFile(File file, String str) {
        BufferedWriter writer = null;
        try {
            // 设置模式为true，表示追加写入。
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(str);
            // 多写入一个换行符
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取文件中的字符串
     *
     * @param file 要读取的文件
     * @return 文件中的字符串
     */
    public static String readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
            // 定义缓冲字符数组
            char[] buff = new char[512];
            StringBuilder sb = new StringBuilder();
            // 每次读取的字符数
            int size = 0;
            while ((size = reader.read(buff)) != -1) {
                sb.append(buff, 0, size);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
