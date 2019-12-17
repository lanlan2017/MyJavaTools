package model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author francis
 * create at 2019/12/16-15:33
 */
public class HexoFrontMatter {
    private String title;
    private String categories;
    private String date;
    private String updated;
    private String comments;
    private String abbrlink;
    private String mathjax;
    private String top;

    public String getAbbrlink() {
        if (abbrlink == null) {
            abbrlink = "null";
        }
        return abbrlink.replace("'", "");
    }

    public HexoFrontMatter(File file) {
        String path = file.getAbsolutePath();
        // 根据文件名生成标题
        title = path.substring(path.lastIndexOf(File.separator) + 1, path.lastIndexOf(".md"));
        // 根据文件到\source\_post的目录间隔作为分类
        String postFlag = File.separator + "source" + File.separator + "_posts";
        // 设置多级分类
        categories = path.substring(path.lastIndexOf(postFlag) + postFlag.length(), path.lastIndexOf(File.separator)).replace(File.separator, "\n  - ");
        // 设置文章的更新实现
        updated = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(file.lastModified()));
    }

    public HexoFrontMatter(File file, String oldFM) {
        this(file);
        Scanner scanner = new Scanner(oldFM);
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.startsWith("date: ")) {
                date = line.substring(line.indexOf("date: ") + "date: ".length());
            } else if (line.startsWith("abbrlink: ")) {
                abbrlink = line.substring(line.indexOf("abbrlink: ") + "abbrlink: ".length());
            } else if (line.startsWith("comments: ")) {
                comments = line.substring(line.indexOf("comments: ") + "comments: ".length());
            } else if (line.startsWith("mathjax: ")) {
                mathjax = line.substring(line.indexOf("mathjax: ") + "mathjax: ".length());
            } else if (line.startsWith("top: ")) {
                top = line.substring(line.indexOf("top: ") + "top: ".length());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder hexoFrontMatter = new StringBuilder(500);
        hexoFrontMatter.append("---\n");
        if (title != null) {
            hexoFrontMatter.append("title: ").append(title).append("\n");
        }
        if (categories != null) {
            hexoFrontMatter.append("categories: ").append(categories).append("\n");
        }
        if (date != null) {
            hexoFrontMatter.append("date: ").append(date).append("\n");
        } else {
            hexoFrontMatter.append("date: ").append(updated).append("\n");
        }

        if (updated != null) {
            hexoFrontMatter.append("updated: ").append(updated).append("\n");
        }
        if (comments != null) {
            hexoFrontMatter.append("comments: ").append(comments).append("\n");
        }
        if (abbrlink != null) {
            hexoFrontMatter.append("abbrlink: ").append(abbrlink).append("\n");
        }
        if (mathjax != null) {
            hexoFrontMatter.append("mathjax: ").append(mathjax).append("\n");
        }
        if (top != null) {
            hexoFrontMatter.append("top: ").append(top).append("\n");
        }
        hexoFrontMatter.append("---");
        return hexoFrontMatter.toString();
    }
}
