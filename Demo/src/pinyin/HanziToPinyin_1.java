//package pinyin;
//
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
//
//public class HanziToPinyin {
//
//    public static void main(String[] args) {
//        String chineseText = "淘宝";
//        String pinyinNoToneUpperCase = toPinyinNoToneUpperCase(chineseText);
//        System.out.println(pinyinNoToneUpperCase); // 输出: TaoBao
//    }
//
//    /**
//     * 将汉字转换为无声调拼音，每个词首字母大写形式。
//     *
//     * @param chineseText 汉字字符串
//     * @return 无声调拼音，每个词首字母大写的字符串
//     */
//    public static String toPinyinNoToneUpperCase(String chineseText) {
//        StringBuilder pinyinStr = new StringBuilder();
//        char[] chars = chineseText.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            char c = chars[i];
//            if (Character.isLetter(c)) {
//                // 如果字符已经是字母，则直接添加并考虑大写
//                pinyinStr.append(Character.toUpperCase(c));
//            } else {
//                try {
//                    // 获取拼音数组，去掉声调
//                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, PinyinHelper.Format.WITHOUT_TONE);
//                    if (pinyinArray != null && pinyinArray.length > 0) {
//                        String pinyin = pinyinArray[0];
//                        // 首个字符大写，其余小写
//                        if (i == 0 || !Character.isLetter(chars[i - 1])) {
//                            pinyinStr.append(Character.toUpperCase(pinyin.charAt(0)));
//                            if (pinyin.length() > 1) {
//                                pinyinStr.append(pinyin.substring(1).toLowerCase());
//                            }
//                        } else {
//                            pinyinStr.append(pinyin.toLowerCase());
//                        }
//                    } else {
//                        // 如果无法转换（如特殊字符），则保持原样
//                        pinyinStr.append(c);
//                    }
//                } catch (BadHanyuPinyinOutputFormatCombination e) {
//                    e.printStackTrace();
//                    // 处理异常，这里简单地忽略
//                }
//            }
//        }
//        return pinyinStr.toString();
//    }
//}