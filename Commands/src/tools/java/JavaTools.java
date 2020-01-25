package tools.java;

import reader.resouce.ResourceFileReader;

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
}
