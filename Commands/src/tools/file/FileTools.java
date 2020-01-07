package tools.file;

import file.processor.FileProcessor;
import file.processor.regex.delete.IndentBySpacesFileProcessor;

public class FileTools {
    public void indentBySpaces(String filePath) {
        FileProcessor fileProcessor=new IndentBySpacesFileProcessor(filePath);
        fileProcessor.processing();
    }
}
