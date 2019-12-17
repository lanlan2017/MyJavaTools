package dir.processor.sub;

import dir.processor.DirProcessor;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FilenameFilter;

/**
 * @author francis
 * create at 2019/12/17-14:43
 */
public class DirNameProcessor extends DirProcessor {
    File rootDir;
    private String rootPath;
    private static StringBuilder tocFileContents = new StringBuilder();

    public DirNameProcessor(File dir) {
        super(dir);
        this.rootDir = dir;
        rootPath = dir.getAbsolutePath();
    }

    // @Override
    // public void processing() {
    //     if (rootDir.isDirectory()) {
    //         processingDir(rootDir);
    //         // System.out.println(tocFileContents.toString());
    //     }
    // }

    @Override
    protected void processingDir(File dir) {
        if (dir == null)
            return;
        // 获取符合文件名过滤器的文件列表.
        File[] dirFileList = dir.listFiles(filenameFilter);
        // 如果列表不为空
        if (dirFileList != null) {
            // 遍历目录列表
            for (File file : dirFileList) {
                if (file.isDirectory()) {
                    if (file.getParentFile().equals(rootDir)) {
                        // System.out.println("直接子目录:" + file.getAbsolutePath());
                        String directSubDirName = file.getAbsolutePath().substring(rootPath.length() + 1);
                        System.out.println("\n# [" + directSubDirName + "](" + "/categories/" + directSubDirName + ")");
                        // tocFileContents.append("\n# [" + directSubDirName + "](" + "/categories/" + directSubDirName + ")");
                    } else {
                        // System.out.println("间接子目录:" + file.getAbsolutePath());
                        String indirectSubDirName = file.getAbsolutePath().substring(rootPath.length() + 1);
                        // System.out.println("间接子目录名称:" + indirectSubDirName);
                        int count = 0;
                        for (int i = 0; i < indirectSubDirName.length(); i++) {
                            if (File.separatorChar == indirectSubDirName.charAt(i)) {
                                count++;
                            }
                        }
                        String text = indirectSubDirName.substring(indirectSubDirName.lastIndexOf(File.separator) + 1);
                        System.out.println(generateTabs(count - 1) + "- [" + text + "](" + "/categories/" + indirectSubDirName.replace("\\", "/") + ")");
                        // tocFileContents.append(generateTabs(count - 1) + "- [" + text + "](" + "/categories/" + indirectSubDirName.replace("\\", "/") + ")");
                    }
                    // 递归遍历下一级目录.
                    processingDir(file);
                }
            }
        }
    }

    private String generateTabs(int times) {
        if (times > 0) {
            String tab = "    ";
            StringBuilder sb = new StringBuilder(40);
            for (int i = 0; i < times; i++) {
                sb.append(tab);
            }
            return sb.toString();
        }
        return "";
    }
}
