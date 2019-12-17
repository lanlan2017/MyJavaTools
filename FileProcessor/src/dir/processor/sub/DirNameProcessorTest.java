package dir.processor.sub;

import dir.processor.DirProcessor;

import java.io.File;

/**
 * @author francis
 * create at 2019/12/17-14:44
 */
public class DirNameProcessorTest {
    public static void main(String[] args) {
        String path = "E:\\Blog\\blog\\source\\_posts";
        File dir = new File(path);
        DirProcessor dirProcessor = new DirNameProcessor(dir);
        dirProcessor.processing();
    }
}
