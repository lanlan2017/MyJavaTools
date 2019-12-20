package reader.resouce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 资源文件读取器
 */
public class ResourceFileReader {
    /**
     * 获取资源文件的输入流.
     *
     * @param clazz             资源文件所在项目的任意一个类的Class对象.
     * @param relativePathToSrc 资源文件相对于src目录的相对路径。relativePathToSrc
     * @return 资源文件的InputStream对象.
     */
    public static InputStream getInputStream(Class<?> clazz, String relativePathToSrc) {
        return clazz.getClassLoader().getResourceAsStream(relativePathToSrc);
    }

    /**
     * 以utf-8读取资源文件内容.
     *
     * @param clazz             资源文件所在项目的任意一个类的Class对象
     * @param relativePathToSrc 资源文件相对于src目录的<strong>相对地址</strong>。
     * @return 资源文件的字符串内容
     */
    public static String getFileContent(Class<?> clazz, String relativePathToSrc) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream(clazz, relativePathToSrc), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder(1000);
            char[] cbuf = new char[512];
            int size = -1;
            while ((size = reader.read(cbuf)) != -1) {
                sb.append(cbuf, 0, size);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // public static void main(String[] args) {
    //     // System.out.println(getInputStream(ResourceFileReader.class, "config.yml"));
    //     // System.out.println(getInputStream(ResourceFileReader.class, "Replace.config"));
    //     // System.out.println(getFileContent(ResourceFileReader.class, "config.yml"));
    //     // System.out.println(getFileContent(ResourceFileReader.class, "Replace.config"));
    //     System.out.println(getInputStream(ResourceFileReader.class, "xunfei/sst/Replace.config"));
    //     System.out.println(getFileContent(ResourceFileReader.class, "xunfei/sst/Replace.config"));
    // }
}

