package io.tools;


public class Test {
    public static void main(String[] args) {
        String path = "G:\\Desktop\\测试";
        // String path = "G:\\Desktop\\测试\\疯狂Java讲义 (第4版)\\第8章 Java集合\\8.2 Collection和Iterator接口\\8.2.3 使用Lambda表达式遍历Iterator.md";
        // String path = "G:\\Desktop\\测试1\\测试.md";
        // String path = "G:\\Desktop\\测试1\\";
        // String regex = "\\n(#{1,2}) 朗读文章 \\1\\n(.+\\n)+?</script>\\n";
        String regex = "\\n(#{1,2}) 朗读文章 \\1\\n(.+\\n)+?</script>\\n";
        // String regex = "^\n{2,}$";
        String replacement = "";
        // new RegexReplaceFileProcessor(path, ">原文链接: \\[.+\\](.+)", "").processing();
        new RegexReplaceFileProcessor(path, regex, replacement).processing();
    }
}
