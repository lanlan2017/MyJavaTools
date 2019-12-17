package dir.processor;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author francis
 * create at 2019/12/17-14:37
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

    public DirProcessor(File dir) {
        this.dir = dir;
    }

    public void processing() {
        if (dir.isDirectory()) {
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
