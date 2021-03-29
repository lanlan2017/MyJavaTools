package io.exam;

import tools.file.processor.FileProcessor;

import java.io.File;

/**
 * @author francis
 * create at 2021/3/21-17:07
 */
public class ExamHexoMore extends FileProcessor {

    public ExamHexoMore(String path) {
        super(path);
    }

    @Override
    protected String processingFileContent(String fileContent) {
        System.out.println("处理文件");
        return null;
    }
}
