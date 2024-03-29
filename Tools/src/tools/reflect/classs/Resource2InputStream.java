package tools.reflect.classs;

import java.io.InputStream;

/**
 * 获取 Sources Resource目录下的文件的InputStream
 * 参考资料：https://www.cnblogs.com/blogtech/p/11151780.html
 */
public class Resource2InputStream {
    /**
     * 获取项目中Sources,Resource目录下的文件的InputStream。
     * 默认情况下，这里的Sources指的是src目录。
     * 调用<code>class.getClassLoader().getResourceAsStream(String relativePath)</code>实现
     * 默认从classpath中找文件(文件放在resources目录下)，
     * 采用相对路径,所以path不能带"/"，否则会抛空指针。
     * "/"就相当于当前进程的根目录，即项目根目录。
     *
     * @param c            Class对象，一般传入this.getClass()
     * @param relativePath 项目中文件相对于Sources目录的相对路径，不能以"/"开头。
     * @return 项目中Sources目录下的文件的InputStream
     */

    public static InputStream relativePath(Class<?> c, String relativePath) {
        return c.getClassLoader().getResourceAsStream(relativePath);
    }

    // /**
    //  * 调用class.getResourceAsStream(absolutePath);方法来获取Sources，Resource资源文件。
    //  * class.getResourceAsStream(String path) 是采用绝对路径，
    //  * 绝对路径是相对于 classpath 根目录的路径，"/" 就代表着 classpath，
    //  * 所以 path 属性需要前面加上 "/"；
    //  *
    //  * @param c            Class对象，一般传入this.getClass()
    //  * @param absolutePath 相对于classpath的绝对路径，该路径必须以"/"开头
    //  * @return 资源文件的InputStream对象
    //  */
    //
    // public static InputStream absolutePath(Class<?> c, String absolutePath) {
    //     return c.getResourceAsStream(absolutePath);
    // }
}
