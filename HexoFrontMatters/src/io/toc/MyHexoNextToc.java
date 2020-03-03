package io.toc;

import dir.processor.DirProcessor;
import regex.UrlEscape;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 生成Hexo Next主题博客的目录文件
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
            // 遍历目录树,生成目录链接
            processingDir(rootDir);
            // System.out.println(tocFileContents.toString());
            String hexoFrontMatter = "---\n" +
                    "title: 网站目录\n" +
                    "abbrlink: 508a2e34\n" +
                    "date: 2019-12-17 04:08:18\n" +
                    "top: true\n" +
                    "---\n";
            saveTocFile(hexoFrontMatter + tocFileContents.toString());
        }
    }

    /**
     * 将保存目录链接的字符串保存到文件中.
     *
     * @param contents 保存目录信息的字符串
     */
    private void saveTocFile(String contents) {
        System.out.println(tocFile.getAbsolutePath());
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tocFile), StandardCharsets.UTF_8))) {
            writer.write(contents);
            writer.flush();
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
        // 对目录列表进行排序
        sortChapterDirList(dirFileList);

        // 如果列表不为空
        if (dirFileList != null) {
            // 遍历目录列表
            for (File file : dirFileList) {
                // 如果列表项是目录
                if (file.isDirectory()) {
                    //根据当前目录项生成目录的链接
                    generateLink(file);
                    // 递归遍历下一级目录.
                    processingDir(file);
                }
            }
        }
    }

    /**
     * 对章节目录和小节目录进行排序
     *
     * @param dirFileList 目录列表数组
     */

    private void sortChapterDirList(File[] dirFileList) {
        // 如果是章节目录
        if (isChapterDir(dirFileList)) {
            // 对章节目录进行排序
            sortChapter(dirFileList);
        }
        // 如果是小节目录
        else if (isSectionDirectory(dirFileList)) {
            // 对小节目录进行排序
            sortSectionDir(dirFileList);
        }
    }

    /**
     * 排序小节目录,这些目录一般以\d+(\.\d+)+开头
     *
     * @param dirFileList 保存小节目录的数组
     */
    private void sortSectionDir(File[] dirFileList) {
        File tmp;
        for (int i = 0; i < dirFileList.length; i++) {
            for (int j = 0; j < dirFileList.length - i - 1; j++) {
                // 如果前面的小节大
                if (sectionValues(dirFileList[j].getName()) > sectionValues(dirFileList[j + 1].getName())) {
                    //    将大的换到后面,实现增序排序
                    //缓存大的
                    tmp = dirFileList[j];
                    //小的换到前面
                    dirFileList[j] = dirFileList[j + 1];
                    //大的换到后面
                    dirFileList[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 排序章节目录,这些目录以 第\d+章 开头
     *
     * @param dirFileList 保存章节的目录数组
     */
    private void sortChapter(File[] dirFileList) {
        File tmp;// 冒泡排序
        // 执行n趟
        for (int i = 0; i < dirFileList.length; i++) {
            // 遍历剩下未排序的元素
            for (int j = 0; j < dirFileList.length - i - 1; j++) {
                String numStrI = dirFileList[j].getName().replaceAll("第(\\d+)章 .+", "$1");
                String numStrJ = dirFileList[j + 1].getName().replaceAll("第(\\d+)章 .+", "$1");
                //System.out.println(numStrI + " " + numStrJ);
                ////// 如果前面的大
                //if (dirFileList[j] > dirFileList[j + 1])
                if (Integer.parseInt(numStrI) > Integer.parseInt(numStrJ)) {
                    // 保存大的数
                    tmp = dirFileList[j];
                    // 小的放前面
                    dirFileList[j] = dirFileList[j + 1];
                    // 大的放后面
                    dirFileList[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 计算小节的数字大小
     *
     * @param sectionName 小节的名称
     * @return 小节对应的大小
     */
    private static int sectionValues(String sectionName) {
        // 取得前面的数字"12.12 创建格式文本"中的12.12

        String sectionId = sectionName.substring(0, sectionName.indexOf(" "));
        //System.out.println(sectionId);
        // 将12.12分割成{12,12}数组
        String[] sectionNums = sectionId.split("\\.");
        int sectionValues = 0;
        for (String num : sectionNums) {
            sectionValues = sectionValues * 10 + Integer.valueOf(num);
        }
        //System.out.println(sectionValues);
        return sectionValues;
    }

    /**
     * 判断是否是小节目录
     *
     * @param dirFileList 保存目录列表的数组
     * @return 如果是小节目录则返回true, 否则就返回false
     */
    private static boolean isSectionDirectory(File[] dirFileList) {

        boolean isSectionDirectory = true;
        for (File file : dirFileList) {
            if (!file.getName().matches("\\d+(?:.\\d+)+ .+")) {
                isSectionDirectory = false;
                break;
            }
        }
        return isSectionDirectory;
    }

    /**
     * 判断是否是章节文件夹
     *
     * @param dirFileList 一个目录下的文件列表数组
     * @return 如果是章节目录的话就返回true
     */
    private boolean isChapterDir(File[] dirFileList) {
        boolean isChapterDirectory = true;
        for (File file : dirFileList) {
            if (!file.getName().matches("第(\\d+)章 .+")) {
                isChapterDirectory = false;
                break;
            }
        }
        return isChapterDirectory;
    }

    /**
     * 更加目录的路径生成目录链接
     *
     * @param dir 目录
     */
    private void generateLink(File dir) {
        // 如果是一级目录(根目录下吗的子目录)
        if (dir.getParentFile().equals(rootDir)) {
            // System.out.println("直接子目录:" + file.getAbsolutePath());
            String directSubDirName = dir.getAbsolutePath().substring(rootPath.length() + 1);
            // System.out.println("\n# [" + directSubDirName + "](" + "/categories/" + directSubDirName + ")");
            if (isFirst) {
                isFirst = false;
            } else {
                tocFileContents.append("\n");
            }
            tocFileContents.append("# [").append(directSubDirName).append("](").append(relativeURL).append("categories/").append(UrlEscape.escapeURL(directSubDirName)).append(")").append("\n");

        }
        // 如果是二级目录或者更深层数的目录
        else {
            // System.out.println("间接子目录:" + file.getAbsolutePath());
            String indirectSubDirName = dir.getAbsolutePath().substring(rootPath.length() + 1);
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
            tocFileContents.append(generateTabs(count - 1)).append("- [").append(text).append("](").append(relativeURL).append("categories/").append(UrlEscape.escapeURL(indirectSubDirName)).append(")").append("\n");
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
