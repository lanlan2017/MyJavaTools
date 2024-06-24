//package adbs.main.ui.jpanels.universal.pinyin;
//
//import net.sourceforge.pinyin4j.PinyinHelper;
//public class PinyinUtils {
//    public static String convertToPinyin(char chineseChar) {
//        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(chineseChar);
//        if (pinyinArray != null && pinyinArray.length > 0) {
//            // 去除声调，这里使用 StringBuilder 进行拼接
//            StringBuilder pinyin = new StringBuilder();
//            for (String pinyinStr : pinyinArray) {
//                // 去除声调，这里假设声调总是位于最后一个字符，并且为数字
//                if (pinyinStr.length() > 0 && Character.isDigit(pinyinStr.charAt(pinyinStr.length() - 1))) {
//                    pinyin.append(pinyinStr.substring(0, pinyinStr.length() - 1));
//                } else {
//                    pinyin.append(pinyinStr);
//                }
//            }
//            return pinyin.toString();
//        } else {
//            return String.valueOf(chineseChar); // 无法转换时返回原字符
//        }
//    }
//    public static String convertToPinyinWithCapitalizedFirstLetter(String chineseString) {
//        StringBuilder pinyin = new StringBuilder();
//        boolean isWordStart = true; // 标记单词的开始
//
//        for (int i = 0; i < chineseString.length(); i++) {
//            char chineseChar = chineseString.charAt(i);
//            String charPinyin = convertToPinyin(chineseChar);
//
//            if (isWordStart) {
//                // 如果是单词的开始，首字母大写
//                pinyin.append(Character.toUpperCase(charPinyin.charAt(0)));
//                pinyin.append(charPinyin.substring(1).toLowerCase());
//                isWordStart = false;
//            } else {
//                // 单词中其他字母小写
//                pinyin.append(charPinyin.toLowerCase());
//            }
//
//            // 如果遇到非中文字符（如空格、标点符号等），则认为单词结束
//            if (!Character.toString(chineseChar).matches("[\\u4e00-\\u9fa5]")) {
//                isWordStart = true;
//            }
//        }
//
//        return pinyin.toString();
//    }
//
//    public static void main(String[] args) {
//        String chinese = "淘宝";
//        String pinyin = convertToPinyinWithCapitalizedFirstLetter(chinese);
//        System.out.println(pinyin); // 输出 "TaoBao"
//    }
//}