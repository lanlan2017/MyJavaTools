package tools.string.cal;

import java.math.BigDecimal;
import java.util.Scanner;

public class Calculation {
    /**
     * 精确减法计算
     * 计算电费
     *
     * @param input 原来的电费算术公式
     * @return 新的电费公式
     */
    public String subtraction(String input) {
        //电费:58.22-5.0=53.22
        System.out.println("input = " + input);
        if (input.contains("=")) {
            input = input.substring(input.indexOf("=") + 1);
        }
        // A - B = C 在这个公式中，A代表被减数，B代表减数，C代表差（即结果）
        // So in the formula A - B = C, A is the minuend, B is the subtrahend, and C is the difference.
        // 被减数
        BigDecimal minuend = new BigDecimal(input);
        Scanner scanner = new Scanner(System.in);
        System.out.print(minuend + " - ");
        String line = scanner.nextLine();
        // 减数
        BigDecimal subtrahend = new BigDecimal(line);
        // 计算 差值
        BigDecimal difference = minuend.subtract(subtrahend);

        scanner.close();
        System.out.println("difference = " + difference);
        // return minuend;
        // input = difference.toString();
        // input = difference.toString();
        input = "电费:" + minuend + "-" + subtrahend + "=" + difference;
        return input;
    }

}
