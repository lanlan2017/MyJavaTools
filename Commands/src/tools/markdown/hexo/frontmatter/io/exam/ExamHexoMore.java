package tools.markdown.hexo.frontmatter.io.exam;

import tools.file.processor.FileProcessor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author francis
 * create at 2021/3/21-17:07
 */
public class ExamHexoMore extends FileProcessor {
    private File file;
    /**
     * 保存该文件对应的旧的的FrontMatter
     */
    StringBuilder oldFmSb;

    //StringBuilder tocSB = new StringBuilder(2048);
    /**
     * 保存该文件的Markdown header1
     */
    ArrayList<String> header1;

    public class FrontMatter {
        // FrontMatter标记
        public static final String TITLE = "title: ";
        public static final String DATE = "date: ";
        public static final String UPDATED = "updated: ";
        public static final String ABBRLINK = "abbrlink: ";
        public static final String CATEGORIES = "categories:";
        public static final String CategoriesListFlag = "- ";

        private String title = null;
        private String categories = null;
        private String date = null;
        private String upDate = null;
        private String abbrlink = null;
        private String top = null;
        private String comments = null;
        private String mathjax = null;
        /**
         * 日期格式化器
         */
        private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /**
         * 文件没有FrontMatter，根据文件生成FrontMatter
         */
        public FrontMatter() {
            //生成标题
            generateTitle();
            //生成分类
            generateCategories();
            //生成创建日期
            generateDate();
            //生成更新日期
            generateUpDate();
        }


        /**
         * 根据已有的forntMatter字符串构造FrontMatter对象
         *
         * @param oldFontMatterStr 旧的FrontMatter字符串
         */
        public FrontMatter(String oldFontMatterStr) {
            Scanner scanner = new Scanner(oldFontMatterStr);
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                // 获取旧的创建日期
                if (date == null && line.startsWith(DATE)) {
                    //if (DATE.equals(line))
                    //{
                    //    System.out.println("empty date");
                    //}
                    date = line.substring(DATE.length());
                    //System.out.println("date=" + date);
                    //System.out.println("2");
                }
                // 获取旧的永久链接
                if (abbrlink == null && line.startsWith(ABBRLINK)) {
                    abbrlink = line.substring(ABBRLINK.length());
                    //System.out.println("4");
                    abbrlink = line.substring(line.indexOf("abbrlink: ") + "abbrlink: ".length());
                    // System.out.println(abbrlink);
                    if ("'0'".equals(abbrlink)) {
                        abbrlink = null;
                    }
                }
                // 获取其他的一些FrontMatter
                if (line.startsWith("top: ")) {
                    top = line.substring(line.indexOf("top: ") + "top: ".length());
                }
                if (line.startsWith("comments: ")) {
                    comments = line.substring(line.indexOf("comments: ") + "comments: ".length());
                }
                if (line.startsWith("mathjax: ")) {
                    mathjax = line.substring(line.indexOf("mathjax: ") + "mathjax: ".length());
                }
            }
            //生成标题
            generateTitle();
            //生成分类
            generateCategories();
            //生成更新日期
            generateUpDate();
            if (date == null) {
                generateDate();
            }
        }

        /**
         * 生成文章的标题
         */
        private void generateTitle() {
            // 根据文件生成标题
            if (title == null) {
                String path = file.getAbsolutePath();
                //System.out.println(path);
                title = path.substring(path.lastIndexOf(File.separator) + 1, path.lastIndexOf(".md"));
            }
        }


        /**
         * 根据文件生成分类列表
         *
         * @return 分类列表字符串。
         */
        private void generateCategories() {
            //File file = getInputFile();
            String path = file.getAbsolutePath();
            //System.out.println(path);
            //System.out.println(File.separator);
            // 从文件的绝对路径中窃取出想对于“source\_posts”目录下的子串
            String catalog = path.substring(path.indexOf("source\\_posts") + "source\\_posts".length(), path.lastIndexOf(File.separator));
            //catalog=catalog.replaceAll("\\\\", "\n  - ");
            catalog = catalog.replaceAll("\\\\", "\n  - ");
            //System.out.println(catalog);
            categories = catalog.replaceAll("\\\\", "\n  - ");
            //return catalog;
        }

        /**
         * 生成文章的创建时间
         */
        private void generateDate() {
            // 更加文件设置创建的时间
            if (this.date == null) {
                //System.out.println("null date");
                Date date = new Date(file.lastModified());
                this.date = dateFormat.format(date);
            }
        }


        /**
         * 生成文章的更新时间
         *
         * @return
         */
        private void generateUpDate() {
            Date date = new Date(file.lastModified());
            //dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //System.out.println(format.format(date));
            this.upDate = dateFormat.format(date);
        }

        public String getAbbrlink() {
            return abbrlink;
        }

        /**
         * 输出FrontMatter
         *
         * @return FrontMatter
         */
        @Override
        public String toString() {
            String str = "---" +
                    "\ntitle: " + title +
                    //"\ncategories: " + categories +
                    "\ncategories: " + categories;
            // 如果有永久链接的话
            if (abbrlink != null) {
                str += "\nabbrlink: " + abbrlink;
            }

            str += "\ndate: " + date +
                    "\nupdated: " + upDate +
                    "\n---";
            return str;
        }
    }

    public ExamHexoMore(String path) {
        super(path);
    }

    /**
     * 拼接文章正文，把FrontMatter，自定义脚本，文章正文。拼接文件的内容。
     *
     * @param fileContent 文章正文
     * @return 包含FrontMatter，自定义脚本，文章正文的markdown文本.
     */
    @Override
    protected String processingFileContent(String fileContent) {
        //System.out.println(fileContent);
        // 生成FrontMatter
        FrontMatter frontMatter = generateFrontMatter(oldFmSb);
        //System.out.println(frontMatter);
        // 生产自定义脚本
        String myScript = generateMyScript(header1, frontMatter);
        //System.out.println(myScript);
        // 拼接成完整的文件内容
        String newFileContent;
        // 如果没有自定义脚本
        if ("".equals(myScript)) {
            newFileContent = frontMatter.toString() + "\n" + fileContent;
        }
        // 如果有自定义脚本的话
        else {
            newFileContent = frontMatter.toString() + "\n" + myScript + "\n" + fileContent;
        }
        //System.out.println(newFileContent);
        //return null;
        return newFileContent;
    }

    @Override
    protected String readFile(File file) {
        this.file = file;
        // 为该文件创建保存旧的FrontMatter的字符串缓冲器
        oldFmSb = new StringBuilder(1024);
        // 为该文件创建保存markdown header 1的list
        header1 = new ArrayList<>();
        StringBuilder articleBody = new StringBuilder(10240);
        // 是否是文件的第一行
        boolean isFirstLine = true;
        // 是否是FrontMatter
        boolean hasFrontMatter = false;
        // 是否是正文
        boolean isContentStart = false;
        //Scanner scanner = new Scanner(fileContent);
        String line;
        //Scanner scanner = null;

        //(line = reader.readLine()) != null


        //try (Scanner scanner = new Scanner(file)) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));) {
            // 默认没有FrontMatter
            //boolean hasFrontMatter=false;
            //while (scanner.hasNextLine()) {
            //    line = scanner.nextLine();

            while ((line = reader.readLine()) != null) {
                // __________读取FrontMatter start__________
                // 如果文章的第一行是"---"
                if (isFirstLine && "---".equals(line)) {
                    // 那么表示有FrontMatter
                    hasFrontMatter = true;
                    // 跳过
                    continue;
                }
                // 如果文章有FrontMatter
                if (hasFrontMatter) {
                    // 如果再次遇到"---"
                    if ("---".equals(line)) {
                        // 则表示FrontMatter已经扫描完毕
                        hasFrontMatter = false;
                        continue;
                    }
                    //System.out.println(line);
                    // 获取FrontMatter的字符串
                    oldFmSb.append(line).append("\n");
                }
                // __________读取FrontMatter end__________
                // ——————————跳过HTML代码 读取正文——————————
                else {
                    // 正文还未开始，并且是html代码的话
                    if (!isContentStart) {
                        // 如果这行是HTML代码
                        if (line.matches("\\<.+\\>")) {
                            //如果这个html代码是自定义的结束符
                            if ("<!--end-->".equals(line)) {
                                // 则表示正文已经开始了，设置标记，表示正文开始
                                isContentStart = true;
                                // 不读取该行
                                continue;
                            }
                        }
                        // 如果这行既不是html代码，也不是空行
                        else if (!"".equals(line)) {
                            //System.out.println("---------------空行");
                            // 则表示正文已经开始了，设置标记，表示正文开始
                            isContentStart = true;
                            //articleBody.append(line).append("\n");
                        }
                        // 如果是其他的情况
                        else {
                            // 则跳过，不输出
                            continue;
                        }
                    }
                    //如果正文已经开始
                    if (isContentStart) {
                        //System.out.println(line);
                        articleBody.append(line).append("\n");
                        //如果该行以‘# ’开头，则认定为markdown的heaer1
                        if (line.matches("^# [^#]+$")) {
                            //tocSB.append("").append("\n");
                            //System.out.println("header1----:" + line.substring("# ".length()));
                            //获取header列表
                            header1.add(line.substring("# ".length()));
                        }
                    }
                }
                // ——————————跳过HTML代码 读取正文——————————
                isFirstLine = false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articleBody.toString();
    }

    /**
     * 生成自定义的more前面的文本，以及js代码
     *
     * @param header1     保存有文章中的markdown header1的内容的List
     * @param frontMatter 生成的或者更新的FrontMatter
     * @return 自定义的JS脚本代码
     */
    private String generateMyScript(ArrayList<String> header1, FrontMatter frontMatter) {
        String myScript;
        // 如果FrontMatter中没有永久链接的话，则不生成自定义脚本
        if (frontMatter.getAbbrlink() == null) {
            myScript = "";
            return "";
            //System.out.println("null");
        }
        //System.out.println("abblinks:" + frontMatter.getAbbrlink());
        StringBuilder tocListSB = new StringBuilder(1024 + 512);
        // 生成文章的相对路径
        String relativeURL = generateRelativeUrl(frontMatter);
        //System.out.println();
        String tocTemplate = readFilesInTheSrcDirectory("/toc.html");
        String myScriptTemplate = readFilesInTheSrcDirectory("/MyScript.html");
        //System.out.println("toc模板：" + tocTemplate);
        String htmlHeader1;
        //遍历Header列表
        for (int i = 0; i < header1.size(); i++) {

            // 取出markdown Header1的字符串
            final String headerStr = header1.get(i);
            // 生成锚点链接
            String anchorName = generateAnchorName(headerStr);
            // 替换模板中的标题名称
            htmlHeader1 = tocTemplate.replace("Header__Name", headerStr);
            // 替换模板中的相对主站的URL
            htmlHeader1 = htmlHeader1.replace("Relative__Address", relativeURL);
            // 替换模板中的锚点链接
            htmlHeader1 = htmlHeader1.replace("Anchor__Name", anchorName);
            // 替换超链接的class属性值
            htmlHeader1 = htmlHeader1.replace("header_Toc_Depth", "header_1");
            //System.out.println(htmlHeader1);
            tocListSB.append(htmlHeader1);
            //tocListSB.append(htmlHeader1).append("\n");
        }
        //System.out.println(tocListSB.toString());
        myScript = myScriptTemplate.replace("INSERT_TOC_HERE", tocListSB.toString());
        return myScript;
    }

    /**
     * 生成锚点名称
     *
     * @param headerStr markdown Header1的显示文本
     * @return html header1的锚点链接
     */
    private String generateAnchorName(String headerStr) {
        // 把特殊字符替换成'-'，匹配特殊字符的正则表达式不够完美，等待后续补充完整
        //String regex = "[ :_/~.-]+";
        String regex = "[ :\\[\\]`\\(\\).+,?@:_/~-]+";
        String anchorName = headerStr.replaceAll(regex, "-");
        //System.out.println("yyyyyy:" + anchorName);
        // 如果以"-"结尾的话，删除掉该字符
        if (anchorName.endsWith("-")) {
            anchorName = anchorName.substring(0, anchorName.lastIndexOf("-"));
        }
        if (anchorName.startsWith("-")) {
            anchorName = anchorName.substring(anchorName.indexOf("-") + 1);
        }

        //System.out.println("xxxxxx:" + anchorName);
        return anchorName;
    }

    /**
     * 生成该文章的相对路径
     *
     * @param frontMatter 更新后的文章的FrontMatter
     * @return 该文章相对于主站的相对路径字符串
     */
    private String generateRelativeUrl(FrontMatter frontMatter) {
        // 获取文件的绝对路径
        //String filePath = getInputFile().getAbsolutePath();
        String filePath = file.getAbsolutePath();
        // 获取站点路径
        filePath = filePath.substring(0, filePath.lastIndexOf("\\source\\_posts\\"));
        // 获取站点名称
        filePath = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
        // 拼接相对路径："/"+站点名称+"/"+永久链接+"/"
        filePath = "/" + filePath + "/" + frontMatter.getAbbrlink() + "/";
        return filePath;
        //System.out.println(filePath);
    }

    /**
     * 读取src目录下的文件.
     *
     * @param resourceFilePath 资源文件的路径，例如资源文件src/toc.html，则应该传入"/toc.html"
     * @return scr目录下的文件的内容
     */
    private String readFilesInTheSrcDirectory(String resourceFilePath) {
        StringBuilder tocSB = new StringBuilder(128);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(ExamHexoMore.class.getResourceAsStream(resourceFilePath), "utf-8"))) {

            char[] cbuf = new char[128];
            int size;
            while ((size = reader.read(cbuf, 0, cbuf.length)) != -1) {
                //System.out.print(new String(cbuf, 0, size));
                //
                tocSB.append(new String(cbuf, 0, size));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tocSB.toString();
    }

    /**
     * 生成FrontMatter对象.
     *
     * @param oldFmSb 旧的FrontMatter字符串。
     * @return 保存更新后的FrontMatter字符串的FrontMatter对象。
     */
    private FrontMatter generateFrontMatter(StringBuilder oldFmSb) {
        FrontMatter frontMatter;
        // 如果长度大于零，则表示存在FrontMatter
        if (oldFmSb.length() > 0) {
            // 处理旧的FrontMatter
            frontMatter = new FrontMatter(oldFmSb.toString());
            //System.out.println(frontMatter);
            //return frontMatter.toString();
        }
        //如果不存在FrontMatter
        else {
            //直接生成
            frontMatter = new FrontMatter();
            //System.out.println(frontMatter);
            //return frontMatter.toString();
        }
        return frontMatter;
    }
}
