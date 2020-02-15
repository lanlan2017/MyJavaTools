package tools.file;

import clipboard.swing.SystemClipboard;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * 文件相关命令类
 */
public class Files {
    private static Set<String> charsetNames = Charset.availableCharsets().keySet();

    public static void main(String[] args) {
        switch (args.length) {

            case 2:
                String path = SystemClipboard.getSysClipboardText();
                transcoding(path, args[0].toUpperCase(), args[1].toUpperCase());
                break;
            case 3:
                if (args[2].equals(".")) {
                    final String userDir = System.getProperty("user.dir");
                    System.out.println(userDir);
                    transcoding(userDir, args[0].toUpperCase(), args[1].toUpperCase());
                } else {
                    transcoding(args[2], args[0].toUpperCase(), args[1].toUpperCase());
                }
                break;
            default:
                // 输出合法的JDK字符集名称
                showJDKAvailableCharset();
                break;
        }
    }


    /**
     * 将文件路径字符串path下的所有文件从srcCharset字符集转成targetCharset字符集.
     *
     * @param path          文件路径字符串,可以是文件的路径,也可以是目录的路径
     * @param srcCharset    原来文件的字符集.
     * @param targetCharset 转换后的文件的字符集.
     */
    private static void transcoding(String path, String srcCharset, String targetCharset) {
        // 如果输入的两个字符集的名称都合法
        if (isAllCharsetNameCorrect(srcCharset, targetCharset)) {
            //String path = SystemClipboard.getSysClipboardText();
            File file = new File(path);
            if (file.exists()) {
                // 转换文件编码
                CharacterEncodingConverter.transcoding(file, srcCharset, targetCharset);
            }
        }
    }

    /**
     * 判断两个输入的字符集名称是否是JDK中的合法字符集名称.
     *
     * @param srcCharset    源文件的字符集名称
     * @param targetCharset 目标文件的字符集名称
     * @return 如果过两个字符集都是JDK中的合法字符集这返回true, 否则返回false
     */
    private static boolean isAllCharsetNameCorrect(String srcCharset, String targetCharset) {
        boolean isSrcCharsetCorrect = false;
        boolean isTargetCharsetCorrect = false;
        String correctCharset;
        for (String charsetName : charsetNames) {
            //correctCharset = charsetName.toLowerCase();
            correctCharset = charsetName;
            // 判断源字符集是否合法
            if (correctCharset.equals(srcCharset)) {
                isSrcCharsetCorrect = true;
            }
            // 判断目标字符集是否合法
            if (correctCharset.equals(targetCharset)) {
                isTargetCharsetCorrect = true;
            }
            // 如果两个字符集都合法,则不用再查找了
            if (isSrcCharsetCorrect && isTargetCharsetCorrect) {
                break;
            }
        }
        return isSrcCharsetCorrect && isTargetCharsetCorrect;
    }

    /**
     * 查看当前jdk能支持的字符集
     */
    private static void showJDKAvailableCharset() {
        System.out.println("合法的JDK字符集名称如下:");
        // 统计所有字符集名称的最大长度
        int maxLenth = 0;
        for (String charsetName : charsetNames) {
            final int length = charsetName.length();
            if (length > maxLenth) {
                maxLenth = length;
            }
        }
        int count = 0;
        for (String charsetName : charsetNames) {
            System.out.printf("%-" + maxLenth + "s", charsetName);
            if (++count % 5 == 0) {
                System.out.println();
            }
        }
    }
}
