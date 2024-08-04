package adbs.main.run.signinlog;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    /**
     * 将给定的字符串写入指定的文本文件中。
     *
     * @param content  要写入的字符串内容
     * @param filePath 文件路径
     */
    public static void writeStringToFile(String content, String filePath) {
        Path path = Paths.get(filePath);
        try {
            Files.write(path, content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.err.println("写入文件时发生错误: " + filePath);
            e.printStackTrace();
        }
    }

    /**
     * 从指定的文本文件中读取字符串内容。
     *
     * @param filePath 文件路径
     * @return 文件中的字符串内容
     */
    public static String readStringFromFile(String filePath) {
        Path path = Paths.get(filePath);
        try {
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("读取文件时发生错误: " + filePath);
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 当文件不存在时创建该文件
     *
     * @param filePath 文件路径
     */
    public static boolean isNeedToCreateFile(String filePath) {
        // 使用File类来操作文件
        File file = new File(filePath);
        boolean flag = false;
        // 创建文件所在的目录结构（如果它们不存在的话）
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs(); // 注意是mkdirs()，它会创建所有必要的父目录
            flag = true;
        }

        // 现在可以安全地尝试创建文件了
        if (!file.exists()) {
            try {
                boolean isCreated = file.createNewFile();
                flag = true;
                if (isCreated) {
                    System.out.println("文件已创建: " + file.getAbsolutePath());
                } else {
                    System.out.println("文件已存在，未进行创建。");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("文件创建失败。");
            }
        } else {
            // 如果文件已存在
            System.out.println("文件已存在: " + file.getAbsolutePath());
        }
        return flag;
    }

}