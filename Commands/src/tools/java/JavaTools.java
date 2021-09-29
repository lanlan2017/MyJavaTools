package tools.java;

import reader.resouce.ResourceFileReader;
import tools.java.formatter.JavaFormatter;
import tools.string.StringConverter;
import tools.string.StringDeleter;

import java.lang.annotation.Retention;
import java.util.Stack;

public class JavaTools {
    public String forString(String stringName) {
        // 读取模板文件
        String codeTemplate = ResourceFileReader.getFileContent(this.getClass(), "templace/java/for/ForString.txt");
        codeTemplate = codeTemplate.replace("STRINGNAME", stringName);
        return codeTemplate;
    }

    public String forArray(String args) {
        // 读取模板文件中的内容
        String codeTemplate = ResourceFileReader.getFileContent(this.getClass(), "templace/java/for/ForArray.txt");
        //替换变量名
        codeTemplate = codeTemplate.replace("VariableName", "i");
        //替换数组名
        codeTemplate = codeTemplate.replace("ARRAYNAME", args);
        return codeTemplate;
    }

    public String switchCaseDefault(String variableName) {
        //从剪贴读取参数
        String codeTemplate = ResourceFileReader.getFileContent(this.getClass(), "templace/java/switch/SwitchCase.txt");
        //替换变量名
        codeTemplate = codeTemplate.replace("VARIABLENAME", variableName);
        //替换变量类型
        codeTemplate = codeTemplate.replace("VALUE", "1");
        return codeTemplate;
    }

    public String switchCaseString(String variableName) {
        //从剪贴读取参数
        String codeTemplate = ResourceFileReader.getFileContent(this.getClass(), "templace/java/switch/SwitchCase.txt");
        //替换变量名
        codeTemplate = codeTemplate.replace("VARIABLENAME", variableName);
        //替换变量值
        codeTemplate = codeTemplate.replace("VALUE", "\"\"");
        return codeTemplate;
    }

    /**
     * 将<code>System.out.println(xxxxx);</code>转成sb.appand(xxxxxx);
     *
     * @param soutCode
     * @return appand语句.
     */
    public String sout2Append(String soutCode) {
        System.out.println("输出语句:" + soutCode);
        String soutLine = "System.out.println(";
        String sout = "System.out.print(";
        String codeEnd = ");";
        String result = "";
        StringBuilder sb = new StringBuilder(soutCode.length());
        if (soutCode.startsWith(soutLine)) {
            int outStart = soutCode.indexOf(soutLine) + soutLine.length();
            int outEnd = soutCode.lastIndexOf(codeEnd);
            String outText = soutCode.substring(outStart, outEnd);
            result = "sb.append(" + outText + "+\"\\n\");";
        } else if (soutCode.startsWith(sout)) {
            int outStart = soutCode.indexOf(sout) + sout.length();
            int outEnd = soutCode.lastIndexOf(codeEnd);
            String outText = soutCode.substring(outStart, outEnd);
            result = "sb.append(" + outText + ");";
        }
        System.out.println("append语句:" + result);
        return result;
    }

    /**
     * 格式化Java单行注释,将代码后的单行注释移动到代码的上方.
     *
     * @param javaCode java代码.
     * @return 格式化后的Java代码.
     */
    public String formatSingleLineComments(String javaCode) {
        javaCode = javaCode.replaceAll("([ \t]+)(.+)(//.+)", "$1$3\n$1$2");
        return javaCode;
    }

    public String formatFromPDF(String codeInOneLine) {
        // 替换中文单行注释
        // String regex = " ?(//[a-zA-Z0-9\\u4e00-\\u9fa5–—‘’“”…、。〈〉《》「」『』【】〔〕！（），．：；？～]+) ";
        // 替换单行注释
        String regex = " ?(//.+?) ";
        // codeInOneLine = codeInOneLine.replaceAll(regex, "__LF__$1__LF__");
        codeInOneLine = codeInOneLine.replaceAll(regex, "$1\n");
        codeInOneLine=codeInOneLine.replaceAll(" ?(/\\*\\*.+?\\*/) ", "$1\n");
        // 格式化java代码
        return JavaFormatter.formatJavaCodeInOneLine(codeInOneLine);
    }

    /**
     * 交换参数的位置
     *
     * @param parametersStr 两个参数字符串，以逗号作为分隔
     * @return 交换位置后的参数字符串
     */
    public String exchangeParameterOrder(String parametersStr) {
        // parametersStr = "property = \"id\",column = \"id\"";
        if (parametersStr.matches("^[^,]+?,[^,]+?$")) {
            String[] parameters = parametersStr.split(",");
            return parameters[1] + "," + parameters[0];
        }
        return "参数错误，请输入`参数1,参数2`形式的字符串！";
    }

    /**
     * 格式化mybatis，的@Results注解
     *
     * @param resultsAn 注解字符串
     * @return 格式化后的字符串。
     */
    public String mybatisResultsFormat(String resultsAn) {
        String input = "    @Results({ @Result(property = \"id\", column = \"id\", id = true), @Result(property = \"name\", column = \"name\"),\n" + "            @Result(property = \"sex\", column = \"sex\"), @Result(property = \"age\", column = \"age\"),\n" + "            @Result(property = \"card\", column = \"card_id\", one = @One(select = \"mapper.CardMapper.selectCardByCardID\", fetchType = FetchType.EAGER)) })\n";
        // 制表符转换为空格
        resultsAn = resultsAn.replaceAll("\t", "    ");
        // 交换@Result中property和column的位置，把column放到property的前面
        resultsAn = resultsAn.replaceAll("\\((property[ ]?=[ ]?\".+?\"),[ ]?(column[ ]?=[ ]?\".+?\")", "($2,$1");
        // 两个以上的空格转换为一个空格
        resultsAn = resultsAn.replaceAll("[ ]{2,}", " ");
        // 删除多余的换行符
        resultsAn = resultsAn.replaceAll("(?m)\\r?\\n", "");
        //  每个@Result()占据一行，并缩进四个空格
        resultsAn = resultsAn.replaceAll(" @Result\\(", "\n    @Result(");
        resultsAn = resultsAn.replaceAll("\\}\\)", "\n})");
        return resultsAn;
    }

    /**
     * 获取一个jar包的名称和版本
     *
     * @param jarPath jar包的路径,相对路径，或绝对路径
     * @return 两行字符串，第一行是jar包的名称，第二行是jar包的版本。
     */
    public String jarArtifactIdAndVersion(String jarPath) {
        String result = "输入错误，请输入jar包的相对路径或绝对路径(Windows格式)。";
        if (jarPath.endsWith(".jar")) {
            String jarName = jarPath.substring(jarPath.lastIndexOf("/") + 1);
            String artifactId = jarName.substring(0, jarName.lastIndexOf("-"));
            String version = jarName.substring(jarName.lastIndexOf("-") + 1, jarName.lastIndexOf(".jar"));
            // System.out.println(jarName);
            // System.out.println(artifactId);
            // System.out.println(version);
            result = artifactId + "\n" + version;
        }
        return result;
    }

    /**
     * 隐藏java方法实现
     *
     * @param methodCode java方法源码
     * @return 保留java方法的定义，隐藏方法体重的实现。
     */
    public String hiddenMethodImplementation(String methodCode) {
        // 编程一行
        methodCode = new StringDeleter().deleteCRLF(methodCode);
        methodCode = methodCode.replaceAll("\\{.+\\}", "{...}");
        return methodCode;
    }
}