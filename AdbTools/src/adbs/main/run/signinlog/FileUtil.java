package adbs.main.run.signinlog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    /**
     * 将给定的字符串写入指定的文本文件中。
     *
     * @param content 要写入的字符串内容
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
}