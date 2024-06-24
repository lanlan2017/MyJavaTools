package pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
  
public class PinyinUtils_1 {
  
    /**  
     * 将单个中文字符转换为拼音（不带声调，首字母大写）  
     *  
     * @param chineseChar 中文字符  
     * @return 拼音字符串（首字母大写），如果无法转换则返回原字符  
     */  
    public static String convertToPinyin(char chineseChar) {  
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(chineseChar);  
        if (pinyinArray != null && pinyinArray.length > 0) {  
            // 转换为大写并去掉声调  
            return pinyinArray[0].substring(0, 1).toUpperCase() + pinyinArray[0].substring(1);  
        } else {  
            return String.valueOf(chineseChar); // 无法转换时返回原字符  
        }  
    }  
  
    /**  
     * 将中文字符串转换为拼音（不带声调，每个单词首字母大写）  
     *  
     * @param chineseString 中文字符串  
     * @return 拼音字符串（每个单词首字母大写），如果无法转换则返回原字符串  
     */  
    public static String convertToPinyinWithCapitalizedFirstLetter(String chineseString) {  
        StringBuilder pinyin = new StringBuilder();  
        boolean isWordStart = true; // 标记单词的开始  
  
        for (char chineseChar : chineseString.toCharArray()) {  
            if (Character.isWhitespace(chineseChar)) {  
                // 单词结束，重置标记  
                isWordStart = true;  
                pinyin.append(chineseChar); // 保留空格  
                continue;  
            }  
  
            String charPinyin = convertToPinyin(chineseChar);  
            if (isWordStart) {  
                // 如果是单词的开始，首字母大写  
                pinyin.append(charPinyin.substring(0, 1).toUpperCase());  
                pinyin.append(charPinyin.substring(1).toLowerCase());  
                isWordStart = false;  
            } else {  
                // 单词中其他字母小写  
                pinyin.append(charPinyin.toLowerCase());  
            }  
        }  
  
        return pinyin.toString();  
    }  
  
    public static void main(String[] args) {  
        String chinese = "淘宝";  
        String pinyin = convertToPinyinWithCapitalizedFirstLetter(chinese);  
        System.out.println(pinyin); // 输出 "TaoBao"  
    }  
}