package main;

import processor.FileProcessor;
import processor.impl.PrintFile;

/**
 * Hexo FrontMatter生成器.
 */
public class AutoHexoFrontMatter {
    public static void main(String[] args) {
        String path="E:\\Blog\\blog\\source\\_posts\\Java";
        FileProcessor fileProcessor=new PrintFile(path);
        fileProcessor.processing();
    }
}
