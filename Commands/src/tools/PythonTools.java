package tools;

/**
 * 生成python代码
 */
public class PythonTools {

    /**
     * 生成<code>print(x)</code>语句
     *
     * @param code Python代码
     * @return <code>print(code)</code>语句
     */
    public String print(String code) {
        return "print(" + code + ")";
    }

    /**
     * 生成<code>print("x =",x)</code>语句
     *
     * @param code python代码
     * @return <code>print("code =",code)</code>语句
     */
    public String printv(String code) {
        // System.out.println("\\");
        String codeEscape = code.replaceAll("\\\\", "\\\\\\\\");
        // System.out.println(codeEscape);
        return "print(\"" + codeEscape + " =\"," + code + ")";
    }
}
