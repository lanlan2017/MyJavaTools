package qq.input.method;

import tools.file.Files;
import tools.print.Print;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 生成QQ输入法自定义短语汇总文件
 */
public class GenerateCustomPhraseSummaryFile {
    /**
     * 汇总文件，以"0_"开头
     */
    private static File summaryFile;

    public static void main(String[] args) {
        // 自定义短语系列文件所在目录
        File dir = new File("G:\\Blog\\blogRoot\\source\\download\\QQ输入法\\qq自定义短语");
        //如果是目录的话
        if (dir.isDirectory()) {
            // 筛选出子文件序列
            File[] files = getSubFiles(dir);
            // 对子文件序列进行排序
            List<File> fileList = sortSubFiles(files);
            System.out.println("--合并下列文件,到汇总文件：" + summaryFile.getName() + " ---");
            Print.printFileList_getName(fileList);
            System.out.println("------------------- 合并完成 --------------");
            writeSummaryFile(fileList);
        }
    }

    /**
     * 写入汇总文件。
     *
     * @param fileList 保存子文件序列的List
     */
    private static void writeSummaryFile(List<File> fileList) {
        for (int i = 0, size = fileList.size(); i < size; i++) {
            // 读取文件内容
            String strs = Files.readFile(fileList.get(i));
            if (i == 0) {
                // 第1个文件，覆盖写入汇总文件中
                Files.writerFile(summaryFile, strs);
            } else {
                // 第2个文件以及之后的文件，追加写入到汇总文件中
                Files.appendFile(summaryFile, strs);
            }
        }
    }

    /**
     * 对子文件序列进行排序。
     *
     * @param files 保存子文件序列的数组
     * @return 排序后的List
     */
    private static List<File> sortSubFiles(File[] files) {
        List<File> fileList = Arrays.asList(files);
        // 给fileList排序
        fileList.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                // 获取名字
                String o1Name = o1.getName();
                // 获取开头的编号
                Integer o1Order = Integer.valueOf(o1Name.substring(0, o1Name.indexOf("_")));

                String o2Name = o2.getName();
                Integer o2Order = Integer.valueOf(o2Name.substring(0, o2Name.indexOf("_")));
                return o1Order.compareTo(o2Order);
            }
        });
        return fileList;
    }

    /**
     * 筛选出汇总文件和子文件系列
     *
     * @param dir 汇总文件和子文件所在的目录路径的File对象
     * @return 子文件系列的File素组
     */
    private static File[] getSubFiles(File dir) {
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().startsWith("0_")) {
                    summaryFile = pathname;
                    return false;
                }
                return true;
            }
        });
        return files;
    }
}
