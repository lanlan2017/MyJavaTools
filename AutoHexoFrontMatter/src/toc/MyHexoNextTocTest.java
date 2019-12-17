package toc;

import dir.processor.DirProcessor;

import java.io.File;

/**
 * @author francis
 * create at 2019/12/17-14:44
 */
public class MyHexoNextTocTest {
    public static void main(String[] args) {
        String path = "E:\\Blog\\blog\\source\\_posts";
        File dir = new File(path);
        DirProcessor dirProcessor = new MyHexoNextToc(dir);
        dirProcessor.processing();
    }
}
