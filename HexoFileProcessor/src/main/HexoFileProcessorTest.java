package main;

import io.exam.ExamHexoMore;
import tools.copy.SystemClipboard;
import tools.dir.DirProcessor;
import tools.file.processor.FileProcessor;
import io.HexoFileProcessor;
import io.toc.HexoNextTocPage;

import java.io.File;

/**
 * Hexo文件处理器测试
 */
public class HexoFileProcessorTest {
    public static void main(String[] args) {
        // 文件处理器
        FileProcessor fileProcessor;
        // 目录处理器
        DirProcessor dirProcessor;
        switch (args.length) {
            //没有参数的时候，
            case 0:
                // 从系统剪贴板中读取文件或者目录的路径
                String path = SystemClipboard.getSysClipboardText();
                //if (isMarkDownFile(path)) {
                // System.out.println("通过 剪贴板 传入文件地址");
                // 创建Hexo文件处理器
                fileProcessor = new HexoFileProcessor(path);
                // 处理文件
                fileProcessor.processing();
                //}

                break;
            // 有一个参数的时候，通过命令行参数读取地址
            case 1:
                //System.out.println(args[0]);
                // 使用第一个命令行参数作为 要处理的文件的路径
                if ("exam".equals(args[0])) {
                    System.out.println("in fm exam(copyPath)");
                    // 从系统剪贴板中读取文件或者目录的路径
                    String path1 = SystemClipboard.getSysClipboardText();
                    // 如果是markdown文件的话
                    //if (isMarkDownFile(path1)) {
                    fileProcessor = new ExamHexoMore(path1);
                    fileProcessor.processing();
                    //}
                }
                // 如果这个参数是文件路径的话
                else {
                    //System.out.println("in fm path");
                    fileProcessor = new HexoFileProcessor(args[0]);
                    // 处理文件
                    fileProcessor.processing();
                    // System.out.println("通过 命令行参数 传入文件地址");
                }

                break;
            // 有两个命令行参数的时候
            case 2:
                // 如果第一个参数是"toc"
                if ("toc".equals(args[0])) {
                    System.out.println("生成目录");
                    File dir = new File(args[1]);
                    // 遍历所有的目录,将一级目录转为1级标题,二级目录站位2级标题,三级以上的目录站位无序列表
                    dirProcessor = new HexoNextTocPage(dir);
                    // 处理该目录
                    dirProcessor.processing();
                    // 根据上面生成的markdown文件,生成目录摘要
                    //fileProcessor = new HexoMarkdownFileProcessor(args[1] + File.separatorChar + "网站目录.md");
                    //fileProcessor.processing();
                }
                // 如果第一个参数是exam
                else if ("exam".equals(args[0])) {
                    System.out.println("in fm exam path");
                    //
                    //System.out.println("in fm exam");
                    String path1 = args[1];
                    //System.out.println("args1:"+path1);
                    //System.out.println("exam");
                    //if (isMarkDownFile(path1)) {
                    fileProcessor = new ExamHexoMore(path1);
                    fileProcessor.processing();
                    //}
                }

                break;
        }
    }

    ///**
    // * 判断是否是.md文件
    // *
    // * @param path 文件的路径
    // * @return 如果是.md文件的话则返回true, 否则返回false.
    // */
    //private static boolean isMarkDownFile(String path) {
    //    File file = new File(path);
    //    return file.isFile() && file.getAbsolutePath().endsWith(".md");
    //}
}
