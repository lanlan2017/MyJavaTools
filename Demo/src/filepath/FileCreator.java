package filepath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCreator {

    public static void main(String[] args) {
//        String filePath = "path/to/your/file.txt"; // 替换为你的文件路径
        String filePath = "AdbToolsPythons\\h9\\Taobao\\1.py"; // 替换为你的文件路径

        createFile(filePath);
    }

    public static void createFile(String filePath) {
        // 将文件路径转换为Path对象
        Path path = Paths.get(filePath);
        try {
            // 创建目录树（如果它们不存在）
            Files.createDirectories(path.getParent());

            // 如果文件不存在，则创建文件
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("文件已创建: " + filePath);
            } else {
                System.out.println("文件已存在: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}