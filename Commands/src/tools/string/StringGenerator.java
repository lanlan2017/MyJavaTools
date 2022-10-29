package tools.string;

import tools.format.date.DateFormatters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringGenerator {
    /**
     * 生成格式化的日期
     */
    public String dateStr() {
        // SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return DateFormatters.yyyyMMdd.format(new Date());
    }

    /**
     * 生成小写和大写的26为字母表
     *
     * @return 小写的26位字母表和大写的26为字母表。
     */
    public String alphabet() {
        return lowercaseAlphabet() + uppercaseAlphabet();
    }

    /**
     * 生成小写字母表
     *
     * @return 小写字母表a-z
     */
    public String lowercaseAlphabet() {
        StringBuilder sb = new StringBuilder(26);
        for (char ch = 'a'; ch <= 'z'; ch++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 生成大写字母表
     *
     * @return 大写字母表A-Z
     */
    public String uppercaseAlphabet() {
        StringBuilder sb = new StringBuilder(26);
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public String javaCodeAssist() {
        return "." + alphabet();
    }
}
