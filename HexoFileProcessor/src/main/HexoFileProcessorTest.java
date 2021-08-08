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
        // 根据命令行参数的数量来执行
        switch (args.length) {
            // 没有参数的时候
            case 0:
                doNoArgs();
                break;
            // 有一个参数的时候
            case 1:
                doOneArg(args[0]);
                break;
            // 有两个命令行参数的时候
            case 2:
                doTwoArg(args);
                break;
        }
    }

    /**
     * 当有两个命令行参数时执行。
     *
     * @param args 命令行参数。
     */
    private static void doTwoArg(String[] args) {
        // 文件处理器
        FileProcessor fileProcessor;
        // 目录处理器
        DirProcessor dirProcessor;
        // 如果第一个参数是"toc"时
        if ("toc".equals(args[0])) {
            System.out.println("生成站点目录");
            File dir = new File(args[1]);
            // 遍历所有的目录,将一级目录转为1级标题,二级目录站位2级标题,三级以上的目录站位无序列表
            dirProcessor = new HexoNextTocPage(dir);
            // 处理该目录
            dirProcessor.processing();
        }
        // 如果第一个参数是"exam"
        else if ("exam".equals(args[0])) {
            System.out.println("in fm exam path");
            // 第二个参数作为文件的路径
            String filePath = args[1];
            // 创建Hexo博客的exam站点的文件处理器
            fileProcessor = new ExamHexoMore(filePath);
            // 处理该文件
            fileProcessor.processing();
        }
    }

    /**
     * 当有一个命令行参数的时候执行.
     *
     * @param arg0 第一个命令行参数
     */
    private static void doOneArg(String arg0) {
        // 当第一个命令行参数时“exam”时
        if ("exam".equals(arg0)) {
            // 执行
            doOneArgIsExam();
        }
        // 如果第一个个命令行参数不是“exam”,那么认为它是文件的路径
        else {
            doOneArgIsFilePath(arg0);
        }
    }

    /**
     * 当第一个参数为字符串"exam"时执行
     */
    private static void doOneArgIsExam() {
        FileProcessor fileProcessor;
        System.out.println("in fm exam(copyPath)");
        // 从系统剪贴板中读取文件或者目录的路径
        String path1 = SystemClipboard.getSysClipboardText();
        fileProcessor = new ExamHexoMore(path1);
        fileProcessor.processing();
    }

    /**
     * 当第一个参数是文件的地址时，执行
     *
     * @param filePath 文件的地址
     */
    private static void doOneArgIsFilePath(String filePath) {
        FileProcessor fileProcessor;
        // 使用第一个参数作为文件地址，创建Hexo文件处理器
        fileProcessor = new HexoFileProcessor(filePath);
        // 使用Hexo文件处理器来处理文件
        fileProcessor.processing();
    }

    /**
     * 没有参数时执行
     */
    private static void doNoArgs() {
        // 从剪贴板中读取文件的内容
        String path = SystemClipboard.getSysClipboardText();
        // 创建Hexo文件处理器
        doOneArgIsFilePath(path);
    }

}
