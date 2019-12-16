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
    private String relativeURL;
    HexoFrontMatter hexoFrontMatter;

    public HexoMarkdownFileProcessor(String filePath) {
        super(filePath);
        String hexoRoot = filePath.substring(0, filePath.lastIndexOf(File.separator + "source" + File.separator + "_posts"));
        // System.out.println(hexoRoot + File.separator + "FM.properties");
        try {
            Properties fmPt = new Properties();
            fmPt.load(new InputStreamReader(new FileInputStream(hexoRoot + File.separator + "FM.properties"), "gbk"));
            relativeURL = fmPt.getProperty("relativeURL");
            // System.out.println(relativeURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String processingFileContent(String fileContent) {
        String oldHexoFM;
        Pattern myFmP = Pattern.compile(Regex.MyFrontMatter.getRegex());
        Matcher myFmM = myFmP.matcher(fileContent);
        Pattern hexoFmP = Pattern.compile(Regex.HexoFrontMatter.getRegex());
        Matcher hexoFmM = hexoFmP.matcher(fileContent);
        // 这个要放在前面
        if (myFmM.find()) {
            // String myFMStr = fileContent.substring(myFmM.start(), myFmM.end());
            fileContent = fileContent.substring(myFmM.end());
            // System.out.println("=============================MYFM==================================");
            // System.out.println(myFMStr);
            // System.out.println("=============================MYFM==================================");
            // System.out.println("========FM========");
            oldHexoFM = myFmM.group(1);
            hexoFrontMatter = new HexoFrontMatter(file, oldHexoFM);
            // System.out.println(hexoFrontMatter.toString());
            String myScript = "<div id='my_toc'>INSERT_TOC_HERE</div>\n" +
                    "<!--more-->\n" +
                    "<script>if (navigator.platform.search('arm')==-1){document.getElementById('my_toc').style.display = 'none';}</script>\n" +
                    "\n" +
                    "<!--end-->\n";
            myScript = myScript.replace("INSERT_TOC_HERE", toc.toString().replace("Insert_Relative_Address_Here", relativeURL + hexoFrontMatter.getAbbrlink() + "/"));
            // System.out.println(myScript);
            // System.out.println("========FM========");
            // System.out.println(fileContent);
            return hexoFrontMatter.toString() + "\n" + myScript + fileContent;
        } else if (hexoFmM.find()) {
            oldHexoFM = fileContent.substring(hexoFmM.start(), hexoFmM.end());
            fileContent = fileContent.substring(hexoFmM.end());
            System.out.println("=============================FM==================================");
            hexoFrontMatter = new HexoFrontMatter(file, oldHexoFM);
            System.out.println(hexoFrontMatter.toString());
            System.out.println("=============================FM==================================");
            System.out.println(fileContent);
        }
        return null;
    }

    @Override
    public String readFile(File file) {
        this.file = file;
        StringBuilder content = new StringBuilder();
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
                    int deep;
                    String alink;
                    if (matcher.matches()) {
                        deep = matcher.group(1).length();
                        alink = "<a href=\"Insert_Relative_Address_Here#" + matcher.group(2) + "\" style=\"margin-left:" + (deep * 2) + "em;\">" + matcher.group(2) + "</a><br>";
                        // toc.append(alink + "\n");
                        toc.append(alink);
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
