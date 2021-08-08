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
            relativeURL = fmPt.getProperty("relativeURL");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 取得JS代码模板
        scriptModel = MyScript.getInstance().getScript();
        // 取得目录列表项的模板
        tocModel = TOC.getInstance().getToc();
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
        // 这个要放在前面
        if (myFmM.find()) {
            // System.out.println("=============================MyFM==================================");
            oldHexoFM = myFmM.group(1);
            fileContent = fileContent.substring(myFmM.end());
            return generateContent(fileContent, oldHexoFM);
        } else if (hexoFmM.find()) {
            // System.out.println("=============================FM==================================");
            oldHexoFM = fileContent.substring(hexoFmM.start(), hexoFmM.end());
            fileContent = fileContent.substring(hexoFmM.end());

            return generateContent(fileContent, oldHexoFM);
        } else {
            // System.out.println("=============================NoFM==================================");
            hexoFrontMatter = new HexoFrontMatter(file);
            return hexoFrontMatter.toString() + "\n" + fileContent;
        }
    }

    /**
     * 生成更新后的文章内容.
     *
     * @param fileContent 文章正文.
     * @param oldHexoFM   旧的HexoFrontMatter.
     * @return 更新后的HexoFrontMatter和自定义JS+文章正文.
     */
    private String generateContent(String fileContent, String oldHexoFM) {
        hexoFrontMatter = new HexoFrontMatter(file, oldHexoFM);
        // System.out.println(hexoFrontMatter.getAbbrlink());
        // 如果有没永久链接
        if (hexoFrontMatter.getAbbrlink() == null) {
            //System.out.println(hexoFrontMatter.getAbbrlink());
            // 则不添加自定义的JS脚本
            return hexoFrontMatter.toString() + "\n" + fileContent;
        }
        // 如果存在永久链接,则在FrontMatter和正文之间添加自定义脚本
        String script = this.scriptModel.replace("INSERT_TOC_HERE", toc.toString().replace("Relative__Address", relativeURL + hexoFrontMatter.getAbbrlink() + "/"));
        // System.out.println(script);
        return hexoFrontMatter.toString() + "\n" + script + fileContent;
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
        toc = new StringBuilder();
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
        // System.out.println(toc.toString());
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
