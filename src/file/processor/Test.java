package file.processor;


public class Test {
    public static void main(String[] args) {

        // 测试替换是否有效
        // String path = "G:\\Desktop\\Testss\\";
        // String regex = "我是小明嘻嘻嘻嘻嘻嘻嘻";
        // String replacement = "小明被替换掉了呢";

        // 将所有的制表符替换成4个空格.
        String path = "G:\\Desktop\\Testss\\";
        String regex = "\t";
        String replacement = "    ";

        // // 将所有的制表符替换成4个空格.
        // String path = "G:\\Desktop\\Testss\\";
        // String regex = "    ";
        // String replacement = "\t";
        new RegexReplaceFileProcessor(path, regex, replacement).processing();
    }
}
