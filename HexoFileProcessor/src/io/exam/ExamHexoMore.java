package io.exam;

import processor.dir.DirProcessor;

import java.io.File;

/**
 * @author francis
 * create at 2021/3/21-17:07
 */
public class ExamHexoMore extends DirProcessor {


    @Override
    protected void processingDir(File dir) {
        System.out.println("处理我的exam站点的文件");
    }
}
