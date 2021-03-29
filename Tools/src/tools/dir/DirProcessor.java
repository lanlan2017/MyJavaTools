package processor.dir;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 目录遍历器处理器
 */
public abstract class DirProcessor {
    /**
     * 目录.
     */
    protected File dir;
    /**
     * 文件名过滤器.
     */
    protected FilenameFilter filenameFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            File thisFile = new File(dir, name);
            // System.out.println(thisFile.getAbsolutePath());
            // System.out.println(thisFile.getName());
            // 以点号开头的不要处理.
            return thisFile.isDirectory() && !thisFile.getName().startsWith(".");
            // return true;
        }
    };

    /**
     * 构造函数
     * @param dir 表示目录的File对象.
     */
    public DirProcessor(File dir) {
        this.dir = dir;
    }

    /**
     * 需要执行的程序
     */
    public void processing() {
        // 如果是目录的话
        if (dir.isDirectory()) {
            // 调用目录处理器方法
            processingDir(dir);
        }
    }

    /**
     * 目录处理器.
     *
     * @param dir 要处理的目录.
     */
    protected abstract void processingDir(File dir);
}
