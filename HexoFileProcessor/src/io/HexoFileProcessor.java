package io;

import model.HexoFrontMatter;
import tools.file.processor.FileProcessor;
import model.MyScript;
import model.TOC;
import regex.UrlEscape;
import regex.Regex;

import java.io.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author francis
 * create at 2019/12/16-14:56
 */
public class HexoFileProcessor extends FileProcessor {
    private File file;
    private StringBuilder toc;
    private String tocModel;
    private String relativeURL;
    private String scriptModel;
    HexoFrontMatter hexoFrontMatter;
    /**
     * 如果aisAddMyFM设置为true的话，则不添加自定义的FrontMatter
     */
    private boolean addJS = false;

    /**
     * Hexo Markdown文件处理器的构造函数。
     *
     * @param filePath 文件的路径
     */
    public HexoFileProcessor(String filePath) {
        super(filePath);
        // 从该文件的路径中，取得Hexo站点的路径.
        String hexoRoot = filePath.substring(0, filePath.lastIndexOf(File.separator + "source" + File.separator + "_posts"));
        try {
            // 读取配置文件
            Properties fmPt = new Properties();
            // 加载Hexo站点下的配置文件“FM.properties”
            fmPt.load(new InputStreamReader(new FileInputStream(hexoRoot + File.separator + "FM.properties"), "gbk"));
            // 从配置文件中 取出 子站点的相对路径.
            this.relativeURL = fmPt.getProperty("relativeURL");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 取得JS代码模板
        this.scriptModel = MyScript.getInstance().getScript();
        // 取得目录列表项的模板
        this.tocModel = TOC.getInstance().getToc();
    }

    /**
     * 处理文章内容.
     *
     * @param fileContent 要处理的内容.
     * @return 添加了Hexo FrontMatter和自定义JS代码的文章内容.
     */
    @Override
    protected String processingFileContent(String fileContent) {

        String oldHexoFM;

        Pattern myFmP = Pattern.compile(Regex.MyFrontMatter.toString());
        Matcher myFmM = myFmP.matcher(fileContent);
        Pattern hexoFmP = Pattern.compile(Regex.HexoFrontMatter.toString());
        Matcher hexoFmM = hexoFmP.matcher(fileContent);
        // 如果存在自定义的FrontMatter
        if (myFmM.find()) {
            System.out.println("=============================MyFM==================================");
            // 获取原来的FrontMatter
            oldHexoFM = myFmM.group(1);
            // 从原来的文件内容中删除掉旧的FrontMatter
            String articleBody = fileContent.substring(myFmM.end());
            // 根据文章正文和旧的FromMatter生成新的文章
            return generateContent(articleBody, oldHexoFM);
        }
        // 如果存在Hexo默认的FrontMatter
        else if (hexoFmM.find()) {
            System.out.println("=============================FM==================================");
            // 截取出旧的FrontMatter
            oldHexoFM = fileContent.substring(hexoFmM.start(), hexoFmM.end());
            // 截取出文章的正文
            String articleBody = fileContent.substring(hexoFmM.end());

            return generateContent(articleBody, oldHexoFM);
        }
        // 如果不存在Frontmatter
        else {
            System.out.println("=============================NoFM==================================");
            // 生成FrontMatter
            this.hexoFrontMatter = new HexoFrontMatter(file);
            // 返回新文件的内容，FrontMatter加上原来文件的内容
            return this.hexoFrontMatter.toString() + "\n" + fileContent;
        }
    }

    /**
     * 设置isAddMyFM。
     * 如果aisAddMyFM设置为true的话，则不添加自定义的FrontMatter
     *
     * @param addJS 是否添加自定义脚本
     */
    public void setAddJS(boolean addJS) {
        this.addJS = addJS;
    }

    /**
     * 返回addJS成员变量的值。
     * @return addJS成员变量的值。
     */
    public boolean isAddJS() {
        return addJS;
    }

    /**
     * 生成更新后的文章内容.
     *
     * @param articleBody 文章正文.
     * @param oldHexoFM   旧的HexoFrontMatter.
     * @return 更新后的HexoFrontMatter和自定义JS+文章正文.
     */
    private String generateContent(String articleBody, String oldHexoFM) {
        // System.out.println("generateContent");
        this.hexoFrontMatter = new HexoFrontMatter(file, oldHexoFM);
        // System.out.println(this.hexoFrontMatter.getAbbrlink());
        // 如果没有永久链接
        if (this.hexoFrontMatter.getAbbrlink() == null) {
            // 没有永久链接，无法根据永久链接生成脚本，不加入脚本
            return this.hexoFrontMatter.toString() + "\n" + articleBody;
        }
        // 如果存在永久链接，并且要加脚本
        if (this.addJS) {
            // 生成脚本
            String script = this.scriptModel.replace("INSERT_TOC_HERE", toc.toString().replace("Relative__Address", relativeURL + hexoFrontMatter.getAbbrlink() + "/"));
            // 添加脚本
            return this.hexoFrontMatter.toString() + "\n" + script + articleBody;
        }
        // 如果存在永久链接,但是要求不加脚本，那就不加脚本
        return this.hexoFrontMatter.toString() + "\n" + articleBody;
    }

    /**
     * 读取Hexo的Markdown文件
     *
     * @param file 要读取的文件.
     * @return 文件的内容
     */
    @Override
    public String readFile(File file) {
        this.file = file;
        this.toc = new StringBuilder();
        StringBuilder content = new StringBuilder();

        boolean isCodeBlock = false;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));) {
            String line;
            // 逐行扫描文件
            while ((line = reader.readLine()) != null) {
                // 如果是代码块的话
                if (line.startsWith("```")) {
                    // 转换
                    isCodeBlock = !isCodeBlock;
                }
                // 如果是标题的话
                // 非代码块中的以井号开头的一行就是标题
                if (!isCodeBlock && line.startsWith("#")) {
                    // 把markdown的标题转为目录项
                    header2Toc(line);
                }
                // 其他的就是文章的主题部分
                content.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // 输出文章的内容
        return content.toString();
    }

    /**
     * markdown标题转成TOC
     *
     * @param header 标题
     */
    private void header2Toc(String header) {
        String tocItem;
        String HeaderName;
        String AnchorName;
        // <a href="#2019年12月15日">2019年12月15日</a>
        Pattern headerP = Pattern.compile("^(#+)[ ]+(.+?)(?:[ ]+#+)?$");
        Matcher matcher = headerP.matcher(header);
        // 如果是markdown的标题的话
        if (matcher.matches()) {
            // 获取标题的内容
            HeaderName = matcher.group(2);
            // 如果标题是超链接的话
            if (HeaderName.matches("\\[(.+?)\\]\\(.+?\\)")) {
                // System.out.println("超链接" + HeaderName);
                // 取下超链接的文字作为标题
                HeaderName = HeaderName.replaceAll("\\[(.+?)\\]\\(.+?\\)", "$1");
                // System.out.println("替换为:" + HeaderName);
            }
            //轻量级Java-EE企业应用实战-第5版-
            // AnchorName = HeaderName.replaceAll("[ :\\[\\]`\\(\\)]+", "-");
            // AnchorName = AnchorName.replaceAll("-$", "");
            // 给markdown标题转义
            AnchorName = UrlEscape.escapeURL(HeaderName);
            // 替换目录项模板中的标题名
            tocItem = this.tocModel.replace("Header__Name", HeaderName);
            // 替换目录项中的链接
            tocItem = tocItem.replace("Anchor__Name", AnchorName);
            tocItem = tocItem.replace("Toc_Depth", String.valueOf(matcher.group(1).length()));
            // 处理好的缓存保存到成员变量中
            this.toc.append(tocItem);
        }
    }
}
