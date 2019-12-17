package io;

import model.HexoFrontMatter;
import processor.FileProcessor;
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
    private StringBuilder toc = new StringBuilder();
    private String tocModel;
    private String relativeURL;
    private String script;
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
        script = MyScript.getInstance().getScript();
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
        Pattern myFmP = Pattern.compile(Regex.MyFrontMatter.getRegex());
        Matcher myFmM = myFmP.matcher(fileContent);
        Pattern hexoFmP = Pattern.compile(Regex.HexoFrontMatter.getRegex());
        Matcher hexoFmM = hexoFmP.matcher(fileContent);
        // 这个要放在前面
        if (myFmM.find()) {
            System.out.println("=============================MyFM==================================");
            oldHexoFM = myFmM.group(1);
            fileContent = fileContent.substring(myFmM.end());
            return generateContent(fileContent, oldHexoFM);
        } else if (hexoFmM.find()) {
            System.out.println("=============================FM==================================");
            oldHexoFM = fileContent.substring(hexoFmM.start(), hexoFmM.end());
            fileContent = fileContent.substring(hexoFmM.end());

            return generateContent(fileContent, oldHexoFM);
        } else {
            System.out.println("=============================NoFM==================================");
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
        script = script.replace("INSERT_TOC_HERE",
                toc.toString().replace("Relative__Address", relativeURL + hexoFrontMatter.getAbbrlink() + "/"));
        return hexoFrontMatter.toString() + "\n" + script + fileContent;
    }

    @Override
    public String readFile(File file) {
        this.file = file;
        StringBuilder content = new StringBuilder();
        String tocItem;
        // String hexoFMStr;
        // boolean isFrontMatter = false;
        boolean isCodeBlock = false;
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
                    Pattern headerP = Pattern.compile("^(#+)[ ]+(.+)(?:[ ]+#+)?$");
                    Matcher matcher = headerP.matcher(line);
                    // int deep;
                    // String AnchorName;
                    if (matcher.matches()) {
                        // deep = matcher.group(1).length();
                        // AnchorName = matcher.group(2);
                        tocItem = tocModel.replace("Anchor__Name", matcher.group(2));
                        tocItem = tocItem.replace("Toc_Depth", String.valueOf(matcher.group(1).length()));
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
}
