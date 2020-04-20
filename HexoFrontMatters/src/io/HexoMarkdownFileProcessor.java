package io;

import model.HexoFrontMatter;
import file.processor.FileProcessor;
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
public class HexoMarkdownFileProcessor extends FileProcessor {
    private File file;
    private StringBuilder toc;
    private String tocModel;
    private String relativeURL;
    private String scriptModel;
    HexoFrontMatter hexoFrontMatter;

    public HexoMarkdownFileProcessor(String filePath) {
        super(filePath);
        // 取得Hexo站点的路径.
        String hexoRoot = filePath.substring(0, filePath.lastIndexOf(File.separator + "source" + File.separator + "_posts"));
        try {
            Properties fmPt = new Properties();
            fmPt.load(new InputStreamReader(new FileInputStream(hexoRoot + File.separator + "FM.properties"), "gbk"));
            // 读取配置文件,取得子站点的相对路径.
            relativeURL = fmPt.getProperty("relativeURL");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 取得JS代码模板
        scriptModel = MyScript.getInstance().getScript();
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
        String script = this.scriptModel.replace("INSERT_TOC_HERE",
                toc.toString().replace("Relative__Address", relativeURL + hexoFrontMatter.getAbbrlink() + "/"));
        // System.out.println(script);
        return hexoFrontMatter.toString() + "\n" + script + fileContent;
    }

    @Override
    public String readFile(File file) {
        this.file = file;
        toc = new StringBuilder();
        StringBuilder content = new StringBuilder();
        String tocItem;
        // String hexoFMStr;
        // boolean isFrontMatter = false;
        boolean isCodeBlock = false;
        String AnchorName;
        String HeaderName;
        // boolean isFirstLine = true;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 如果是代码块的话
                if (line.startsWith("```")) {
                    //
                    isCodeBlock = !isCodeBlock;
                }
                // 标题:非代码块中的以井号开头的一行
                if (!isCodeBlock && line.startsWith("#")) {

                    // <a href="#2019年12月15日">2019年12月15日</a>
                    Pattern headerP = Pattern.compile("^(#+)[ ]+(.+?)(?:[ ]+#+)?$");
                    Matcher matcher = headerP.matcher(line);
                    if (matcher.matches()) {
                        // deep = matcher.group(1).length();
                        HeaderName = matcher.group(2);
                        if (HeaderName.matches("\\[(.+?)\\]\\(.+?\\)")) {
                            // System.out.println("超链接" + HeaderName);
                            HeaderName = HeaderName.replaceAll("\\[(.+?)\\]\\(.+?\\)", "$1");
                            // System.out.println("替换为:" + HeaderName);
                        }
                        //轻量级Java-EE企业应用实战-第5版-
                        // AnchorName = HeaderName.replaceAll("[ :\\[\\]`\\(\\)]+", "-");
                        // AnchorName = AnchorName.replaceAll("-$", "");
                        AnchorName = UrlEscape.escapeURL(HeaderName);
                        tocItem = tocModel.replace("Header__Name", HeaderName);
                        tocItem = tocItem.replace("Anchor__Name", AnchorName);
                        tocItem = tocItem.replace("Toc_Depth", String.valueOf(matcher.group(1).length()));
                        // System.out.println(tocItem);
                        toc.append(tocItem);
                        // margin

                    }
                }
                // 其他的就是文章的主题部分
                content.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // System.out.println(toc.toString());
        return content.toString();
    }

    // /**
    //  * 修复URL.
    //  *
    //  * @param headerName
    //  * @return
    //  */
    // private String checkURL(String headerName) {
    //     String AnchorName;
    //     AnchorName = headerName.replaceAll(Regex.HexoNextUrl1.getRegex(), "-");
    //     AnchorName = AnchorName.replaceAll(Regex.HexoNextUrl2.getRegex(), "");
    //     return AnchorName;
    // }
}
