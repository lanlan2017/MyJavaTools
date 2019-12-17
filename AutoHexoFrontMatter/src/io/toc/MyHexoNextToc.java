package io.toc;

import dir.processor.DirProcessor;
import regex.UrlEscape;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author francis
 * create at 2019/12/17-14:43
 */
public class MyHexoNextToc extends DirProcessor {
    File rootDir;
    File tocFile;
    boolean isFirst = true;
    private String rootPath;
    private static StringBuilder tocFileContents = new StringBuilder();
    private String relativeURL;
    // private static StringBuilder toc = new StringBuilder();


    public MyHexoNextToc(File dir) {
        super(dir);
        this.rootDir = dir;
        rootPath = dir.getAbsolutePath();
        String hexoRoot = rootPath.substring(0, rootPath.lastIndexOf(File.separator + "source" + File.separator + "_posts"));
        try {
            Properties fmPt = new Properties();
            fmPt.load(new InputStreamReader(new FileInputStream(hexoRoot + File.separator + "FM.properties"), "gbk"));
            // 读取配置文件,取得子站点的相对路径.
            relativeURL = fmPt.getProperty("relativeURL");
        } catch (IOException e) {
            e.printStackTrace();
        }
        tocFile = new File(rootPath + "/网站目录.md");
    }

    @Override
    public void processing() {
        if (rootDir.isDirectory()) {
            // HexoFrontMatter hexoFrontMatter = new HexoFrontMatter(tocFile);
            processingDir(rootDir);
            System.out.println(tocFileContents.toString());
            String hexoFrontMatter = "---\n" +
                    "title: 网站目录\n" +
                    "abbrlink: 508a2e34\n" +
                    "date: 2019-12-17 04:08:18\n" +
                    "top: true\n" +
                    "---\n";
            saveTocFile(hexoFrontMatter + tocFileContents.toString());
        }
    }

    private void saveTocFile(String contents) {
        System.out.println(tocFile.getAbsolutePath());
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tocFile), StandardCharsets.UTF_8))) {
            writer.write(contents);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
                        // System.out.println("\n# [" + directSubDirName + "](" + "/categories/" + directSubDirName + ")");
                        if (isFirst) {
                            isFirst = false;
                        } else {
                            tocFileContents.append("\n");
                        }
                        tocFileContents.append("# [" + directSubDirName + "](" + relativeURL + "categories/" + UrlEscape.escapeURL(directSubDirName) + ")" + "\n");

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
                        // String url = indirectSubDirName.replace("\\", "/");
                        // url = UrlCheck.checkURL(url);
                        tocFileContents.append(generateTabs(count - 1) + "- [" + text + "](" + relativeURL + "categories/" + UrlEscape.escapeURL(indirectSubDirName) + ")" + "\n");
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
