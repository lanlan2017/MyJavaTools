package tools.file;

import java.io.*;

/**
 * 项目编码转换器,可以把一个目录下的所有文件从一个编码(如GBK)转换成另一个编码(如UTF-8).<br>
 */
public class CharacterEncodingConverter {

    /**
     * 转换整个目录树的编码.
     *
     * @param dirFile     目录对应的File对象.
     * @param fromCharset 原来的文件编码.
     * @param toCharset   要转成的编码.
     */
    private static void convertDirEncode(File dirFile, String fromCharset,
                                         String toCharset) {
        File[] dirFileList = dirFile.listFiles((dir, name) -> {
            // 保留目录
            if (new File(dir, name).isDirectory())
                return true;
            // 对应后缀的文件.
            if (name.contains(".")) {
                // 取出文件后缀名称
                String suffix = name.substring(name.lastIndexOf("."));
                // 合法的文件后缀名
                String[] legalSuffixArr =
                        {".java", ".md", ".jsp", ".xml",".html", ".css", ".js"};
                for (String s : legalSuffixArr) {
                    if (suffix.equals(s)) {
                        return true;
                    }
                }
            }
            return false;
        });
        if (dirFileList != null) {
            // 遍历整个目录列表
            for (File file : dirFileList) {
                // 如果是目录
                if (file.isDirectory()) {
                    // 递归处理下一级目录
                    convertDirEncode(file, fromCharset, toCharset);
                }
                // 如果是文件
                else if (file.isFile()) {
                    // 转换文件的编码
                    convertFileEncode(file, fromCharset, toCharset);
                }
            }
        }
    }

    /**
     * 转换一个文件的编码
     *
     * @param file        需要转码的文件
     * @param fromCharset 文件原本的编码
     * @param toCharset   需要转换成的编码
     */
    private static void convertFileEncode(File file, String fromCharset,
                                          String toCharset) {
        // 生成临时文件
        File tempFile = createTempFile(file);
        // 把源文件复制到临时文件
        copyToTempFileByNewEncode(file, fromCharset, tempFile, toCharset);
        // 删除源文件,并把临时文件重名为源文件
        if (file.delete() && tempFile.renameTo(file)) {
            System.out.println("文件:" + file + "由 " + fromCharset + " 转为 "
                    + toCharset + " 成功!");
        }
    }

    /**
     * 从源文件以fromCharset编码读取,以toCharset编码写入临时文件.
     *
     * @param file        源文件.
     * @param fromCharset 以这个编码来读取源文件.
     * @param tempFile    临时文件.
     * @param toCharset   以这个编码来写临时文件
     */
    private static void copyToTempFileByNewEncode(File file, String fromCharset,
                                                  File tempFile, String toCharset) {
        char[] cbuf = new char[1024];
        // 读入文件缓存
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), fromCharset));
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(new FileOutputStream(tempFile), toCharset))) {

            int size;
            // 从源文件中读出
            while ((size = reader.read(cbuf)) != -1) {
                // 写入临时文件
                writer.write(cbuf, 0, size);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建一个文件的副本.
     *
     * @param file 要创建副本的文件.
     */
    private static File createTempFile(File file) {
        // 源文件名
        String fileName = file.getName();
        // 临时文件名
        int lastIndexOfDot = fileName.lastIndexOf(".");
        String tempFileName = fileName.substring(0, lastIndexOfDot) + "_Temp"
                + fileName.substring(lastIndexOfDot);
        return new File(file.getParent(), tempFileName);
    }

    /**
     * 将文件或目录树下的所有文件从srcCharset编码转成targetCharset编码。
     *
     * @param file          表示文件或者目录的File对象
     * @param srcCharset    文件原来的编码
     * @param targetCharset 要转换后的编码
     */
    public static void transcoding(File file, String srcCharset, String targetCharset) {
        if (file.isDirectory()) {
            convertDirEncode(file, srcCharset, targetCharset);
        } else if (file.isFile()) {
            convertFileEncode(file, srcCharset, targetCharset);
        }
    }
}
