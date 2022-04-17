package tools.markdown.hexo.frontmatter;

import tools.markdown.hexo.frontmatter.io.HexoFileProcessor;
import tools.markdown.hexo.frontmatter.io.exam.ExamHexoMore;
import tools.markdown.hexo.frontmatter.io.toc.HexoNextTocPage;
import tools.dir.DirProcessor;
import tools.file.processor.FileProcessor;

import java.io.File;

public class FrontMatterGenerator {

    /**
     * 当第一个参数是文件的地址时，执行
     *
     * @param filePath 文件的地址
     */
    // public void generatorFrontMatter(String filePath) {
    public void doOneArgIsFilePath(String filePath) {
        FileProcessor fileProcessor;
        // 使用第一个参数作为文件地址，创建Hexo文件处理器
        fileProcessor = new HexoFileProcessor(filePath);
        // 使用Hexo文件处理器来处理文件
        fileProcessor.processing();
    }

    /**
     * 当第一个参数为字符串"exam"时执行
     */
    public void doOneArgIsExam(String filePath) {
        FileProcessor fileProcessor;
        System.out.println("in fm exam(copyPath)");
        // // 从系统剪贴板中读取文件或者目录的路径
        // String filePath = SystemClipboard.getSysClipboardText();
        fileProcessor = new ExamHexoMore(filePath);
        fileProcessor.processing();
    }

    /**
     * 当第一个参数是文件的地址时，执行
     *
     * @param filePath 文件的地址
     */
    public void addMyHexoFrontMatter(String filePath) {
        // System.out.println("haveMyHexoFrontMatter");
        FileProcessor fileProcessor;
        // 使用第一个参数作为文件地址，创建Hexo文件处理器
        HexoFileProcessor hexoFileProcessor = new HexoFileProcessor(filePath);
        // 设置标志属性，true表示添加JS脚本
        hexoFileProcessor.setAddJS(true);
        // System.out.println(hexoFileProcessor.isAddMyFrontMatter());
        fileProcessor = hexoFileProcessor;
        // 使用Hexo文件处理器来处理文件
        fileProcessor.processing();
    }

    /**
     * 给hexo站点生成dir页面。
     *
     * @param path hexo存放文章的source\_posts\目录的绝对路径。例如G:\Blog\exam_new\source\_posts
     */
    public String siteTOC(String path) {
        // 输入合法性检查
        // 如果地址不以source\_posts结尾，并且包含source\_posts
        // 如果输入的路径时source\_posts\下的子路径时
        if (!path.endsWith("source\\_posts") && path.contains("source\\_posts")) {
            System.out.println("生成hexo dir/页面对应的文件");
            String postsPath = path.substring(0, path.indexOf("source\\_posts") + "source\\_posts".length());
            // System.out.println("postsPath = " + postsPath);
            // path = postsPath;

            DirProcessor dirProcessor;
            File dir = new File(postsPath);
            // 遍历所有的目录,将一级目录转为1级标题,二级目录站位2级标题,三级以上的目录站位无序列表
            dirProcessor = new HexoNextTocPage(dir);
            // 处理该目录
            dirProcessor.processing();
            return "生成dir页面成功！";
        } else {
            return "请输入source\\_posts\\目录的绝对路径";
        }

    }

}
