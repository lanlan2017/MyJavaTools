package tools.file.processor;

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
    protected String message = "被修改的文件:";
    /**
     * 文件名过滤器.
     */
    private FilenameFilter filenameFilter;

    /**
     * 文件处理器的构造函数
     *
     * @param filePath 文件的路径字符串
     */
    public FileProcessor(String filePath) {
        // 处理文件或目录
        this.inputFile = new File(filePath);
    }

    // 获取输入的文件的File对象
    public File getInputFile() {
        return inputFile;
    }

    /**
     * 递归处理文件或目录中的所有文件.
     */
    public void processing() {
        // 如果是文件的话
        if (inputFile.isFile()) {
            // 处理这个文件.
            processingFile(inputFile);
        }
        // 如果是目录的话
        else if (inputFile.isDirectory()) {
            // 处理这个目录.
            processingDir(inputFile, filenameFilter);
        }
    }

    /**
     * 处理文件.
     *
     * @param file 要处理的文件.
     */
    private void processingFile(File file) {
        // 读入文件中的内容
        String fileContent = readFile(file);
        // 处理文件中的内容
        String processedFileContent = processingFileContent(fileContent);
        // 如果文件内容改变了.
        if (processedFileContent != null) {
            // 输出提示信息
            System.out.println(message + file.getAbsolutePath());
            // 写入文件内容.
            writeFile(file, processedFileContent);
        }
    }

    /**
     * 处理文件内容.
     *
     * @param fileContent 要处理的内容.
     * @return 处理后的内容.
     */
    protected abstract String processingFileContent(String fileContent);

    /**
     * 设置文件名过滤器.
     */
    protected void setFilenameFilter(FilenameFilter filenameFilter) {
        this.filenameFilter = filenameFilter;
    }


    /**
     * 递归处理目录树。
     *
     * @param dirFile        表示目录的File对象
     * @param filenameFilter 文件名过滤器
     */
    private void processingDir(File dirFile, FilenameFilter filenameFilter) {

        if (dirFile == null)
            return;
        // 获取符合文件名过滤器的 文件列表.
        File[] dirFileList = dirFile.listFiles(filenameFilter);
        // 如果列表不为空
        if (dirFileList != null) {
            // 遍历目录列表
            for (File file : dirFileList) {
                // System.out.println(file.getAbsolutePath());
                // 如果是文件,
                if (file.isFile()) {
                    // 则处理这个文件.
                    processingFile(file);
                }
                // 如果是目录的话
                else if (file.isDirectory()) {
                    // 则递归 遍历下一级目录.
                    processingDir(file, filenameFilter);
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
            // 写入文件
            writer.write(fileContent);
            // 刷新缓冲
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读入文件中的内容字符串.
     *
     * @param file 要读取的文件.
     * @return 保存文件的内容的字符串对象.
     */
    protected String readFile(File file) {
        StringBuilder sb = new StringBuilder(10240);
        char[] timeChs = new char[1024];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            int size;
            // 读入整个数组
            while ((size = reader.read(timeChs)) != -1) {
                sb.append(new String(timeChs, 0, size));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}