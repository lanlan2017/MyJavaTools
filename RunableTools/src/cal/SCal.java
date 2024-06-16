package cal;

//import tools.jiantieban.ClipboardTools;

import tools.copy.SystemClipboard;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SCal {
    public static void main(String[] args) {
//        "C:\Users\18190\Desktop\欠款\欠款.md"
        String path = "C:\\Users\\18190\\Desktop\\欠款\\欠款.md";
        File file = new File(path);
        if (file.exists()) {
            String lastLineStr = getLastLineStr(file, "utf-8");
            System.out.println("lastLineStr = " + lastLineStr);

            String regex = "([0-9.]+)([+-])([0-9.]+)=([0-9.]+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(lastLineStr);

            if (matcher.find()) {
                String group_4 = matcher.group(4);
                BigDecimal bigDecimal_4 = new BigDecimal(group_4);
                Scanner scanner = new Scanner(System.in);
                System.out.println("输入：[+-*/]实数");
                System.out.print(bigDecimal_4);
//              获取输入
                String nextLine = scanner.nextLine();
                BigDecimal jieGuo = null;
                String out;

//              运算符和操作数
                Pattern input = Pattern.compile("([*/+-])([0-9.]+)");
//
                Matcher matcher_input = input.matcher(nextLine);
//               如果找到匹配项
                if (matcher_input.find()) {
//                    取下运算符
                    String g_1 = matcher_input.group(1);
//                    取下操作数
                    String g_2 = matcher_input.group(2);
                    BigDecimal decimal = new BigDecimal(g_2);
//                    根据运算符执行不同的运算
                    switch (g_1) {
                        case "+":
                            jieGuo = bigDecimal_4.add(decimal);
                            break;
                        case "-":
                            jieGuo = bigDecimal_4.subtract(decimal);
                            break;
                        case "*":
                            jieGuo = bigDecimal_4.multiply(decimal);
                            break;
                        case "/":
                            jieGuo = bigDecimal_4.divide(decimal);
                            break;
                        default:
                            break;
                    }
//                    拼接公式
                    out = group_4 + g_1 + decimal + "=" + jieGuo;

                    System.out.println(out);
//                    ClipboardTools.toClip(out);
                    SystemClipboard.setSysClipboardText(out);

                    System.out.print("加入日期?(y/n):");
                    String line = scanner.nextLine();
                    if (line.equalsIgnoreCase("y")) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formatStr = dateFormat.format(new Date());
                        out = "\n## " + formatStr + "\n" + out;
                        System.out.println("-------------------");
                        System.out.println(out);
                        System.out.println("-------------------");
                    }
                }
            }

        }

    }

    /**
     * 返回最后一行的起始位置,并移动文件指针到最后一行的起始位置。
     *
     * @param raf RandomAccessFile对象
     * @return 最后一行的起始位置
     */
    private static long getLastLinePos(RandomAccessFile raf) throws IOException {
        long lastLinePos = 0L;
        // 获取文件占用字节数
        long len = raf.length();
        if (len > 0L) {
            // 向前走一个字节
            long pos = len - 1;
            while (pos > 0) {
                pos--;
                // 移动指针
                raf.seek(pos);
                // 判断这个字节是不是回车符
                if (raf.readByte() == '\n') {
                    // lastLinePos = pos;// 记录下位置
                    // break;// 前移到会第一个回车符后结束
                    return pos;
                }

            }
        }
        return lastLinePos;
    }

    /**
     * 获取文本文件最后一行中的字符串。
     *
     * @param file    目标文件
     * @param charset 字符编码
     * @return 文本文件中最后一行中的字符串。
     */
    public static String getLastLineStr(File file, String charset) {
        String lastLine = null;
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(file, "rwd");
            //获取最后一行的起始位置，并移动指针到指定位置。
            long lastLinePos = getLastLinePos(raf);
            //获取文件的大小：也就是占用的字节数
            long length = raf.length();
            byte[] bytes = new byte[(int) ((length - 1) - lastLinePos)];
            //上面的getLastLinePos(raf);方法已经移动文件指针到最后一行的起始位置了，所以这里只需要读取即可。
            raf.read(bytes);

            if (charset == null) {
                lastLine = (new String(bytes));
            } else {
                lastLine = (new String(bytes, charset));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }
}
