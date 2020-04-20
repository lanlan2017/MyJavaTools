package regex;

/**
 * @author francis
 * create at 2019/12/16-16:54
 */
public enum Regex {

    MyFrontMatter("^(---(?:\\n|\\r\\n)(?:.+(?:\\n|\\r\\n))+---(?:\\n|\\r\\n))(.*(?:\\n|\\r\\n))+<!--end-->(?:\\n|\\r\\n)"),
    HexoFrontMatter("^---(?:\\n|\\r\\n)(?:.+(?:\\n|\\r\\n))+---(?:\\n|\\r\\n)"),
    ToBeHyphen("[ :\\[\\]`\\(\\).+,?@]+"),
    HyphenDollar("-$"),
    HyphenSlash("-/");
    private String regex;
    
    Regex(String regex) {
        this.regex = regex;
    }

    public String toString() {
        return regex;
    }
}
