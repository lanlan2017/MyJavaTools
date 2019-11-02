package io.tools;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 文件处理器,可以处理文件或批量处理目录下的所有文件.
 */
public abstract class FileProcessor {
    /**
     * 需要处理的文件或目录.
     */
    private File inputFile;

    public FileProcessor(String filePath) {
        inputFile = new File(filePath);
        // 处理文件或目录
    }

    /**
     * 处理文件或目录中的所有文件.
     */
    public void processing() {
        // File file = new File(path);
        if (inputFile.isFile()) {
            processingSubFile(inputFile);
        } else if (inputFile.isDirectory()) {
            traversingDir(inputFile);
        }
    }

    /**
     * 使用正则表达式对文件进行替换操作.
     *
     * @param subFile 要处理的文件.
     */
    private void processingSubFile(File subFile) {
        String fileContent = readFile(subFile);
        String processedFileContent = processingFileContent(fileContent);
        if (processedFileContent.length() < fileContent.length()) {
            System.out.println("被修改的文件:" + subFile.getAbsolutePath());
            writeFile(subFile, processedFileContent);
        }
    }

    protected abstract String processingFileContent(String fileContent);


    /**
     * 递归遍历目录.
     *
     * @param dirFile 表示目录的File对象.
     */
    private void traversingDir(File dirFile) {
        if (dirFile == null)
            return;
        File[] subFileList = dirFile.listFiles((dir, name) -> {
            File file1 = new File(dir, name);
            // 返回.md文件.
            if (file1.isFile()) {
                return name.endsWith(".md");
            }
            // 返回不是.开头的目录.
            else if (file1.isDirectory()) {
                return !name.startsWith(".");
            }
            return false;
        });
        if (subFileList != null) {
            for (File subFile : subFileList) {
                // System.out.println(subFile.getAbsolutePath());
                if (subFile.isFile()) {
                    processingSubFile(subFile);

                } else if (subFile.isDirectory()) {
                    // 递归遍历下一级目录.
                    traversingDir(subFile);
                }
            }
        }
    }

    /**
     * 将字符串写入文件.
     *
     * @param file        要写入的文件.
     * @param fileContent 要写入文件的内容.
     */
    private void writeFile(File file, String fileContent) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write(fileContent);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读入文件中的内容.
     *
     * @param file 要读取的文件.
     * @return 保存文件的内容的字符串对象.
     */
    private String readFile(File file) {
        StringBuilder sb = new StringBuilder(10240);
        char[] timeChs = new char[1024];
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            int size;
            // 读入整个数组
            while ((size = reader.read(timeChs)) != -1) {
                sb.append(new String(timeChs, 0, size));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(sb.toString());
        return sb.toString();
    }
}