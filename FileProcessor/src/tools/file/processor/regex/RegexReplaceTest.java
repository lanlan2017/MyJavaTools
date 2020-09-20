package tools.file.processor.regex;


import tools.file.processor.FileProcessor;

public class RegexReplaceTest {
    public static void main(String[] args) {
        // deleteYuanWen();
        deleteMyScript();
    }

    private static void deleteYuanWen() {
        String path = "G:\\Desktop\\Tests\\source\\_posts";
        String regex = "\\n>原文链接.+?\\[.+?\\]\\(.+?\\)\\n?";
        String replacement = "";
        FileProcessor fileProcessor = new RegexReplaceFileProcessor(path, regex, replacement);
        fileProcessor.processing();
    }

    private static void deleteMyScript() {
        String filePath = "G:\\Desktop\\Tests\\source\\_posts";
        String regex = "(---(?:\\n|\\r\\n)(?:.+(?:\\n|\\r\\n))+---(?:\\n|\\r\\n))(?:.+(?:\\n|\\r\\n))+(?:\\n|\\r\\n)<!--end-->(?:\\n|\\r\\n)";
        String replacement = "$1";
        FileProcessor fileProcessor = new RegexReplaceFileProcessor(filePath, regex, replacement);
        fileProcessor.processing();
    }
}
