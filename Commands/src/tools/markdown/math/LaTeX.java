package tools.markdown.math;

public class LaTeX {
    /**
     * 生成LaTex行内公式
     *
     * @param inlineCode 行内公式代码
     * @return LaTeX行内公式。
     */
    public String s(String inlineCode) {
        return "$" + inlineCode + "$";
    }

    /**
     * 生成Latex行间公式
     *
     * @param code
     * @return
     */
    public String ss(String code) {
        return "\n$$\n" + code + "\n$$\n";
    }
}
