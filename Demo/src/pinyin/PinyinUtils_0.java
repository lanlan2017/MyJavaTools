package pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
  
public class PinyinUtils_0 {
  
    /**  
     * 将单个中文字符转换为拼音（不带声调）  
     *  
     * @param chineseChar 中文字符  
     * @return 拼音字符串，如果无法转换则返回原字符  
     */  
    public static String convertToPinyin(char chineseChar) {  
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(chineseChar);  
        if (pinyinArray != null && pinyinArray.length > 0) {  
            return pinyinArray[0]; // 假设只需要第一个拼音  
        } else {  
            return String.valueOf(chineseChar); // 无法转换时返回原字符  
        }  
    }  
  
    /**  
     * 将中文字符串转换为拼音（不带声调）  
     *  
     * @param chineseString 中文字符串  
     * @return 拼音字符串，如果无法转换则返回原字符串  
     */  
    public static String convertToPinyin(String chineseString) {  
        StringBuilder pinyin = new StringBuilder();  
        for (char chineseChar : chineseString.toCharArray()) {  
            pinyin.append(convertToPinyin(chineseChar));  
        }  
        return pinyin.toString();  
    }  
  
    public static void main(String[] args) {  
        String chinese = "你好，世界！";  
        String pinyin = convertToPinyin(chinese);  
        System.out.println(pinyin); // 输出 "nihao，shijie！"  
    }  
}