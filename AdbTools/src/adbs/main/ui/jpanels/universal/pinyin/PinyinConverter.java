package adbs.main.ui.jpanels.universal.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinyinConverter {

    /**
     * 中文汉字转成首字符大写其他字符小写没有声调的的汉语拼音。
     *
     * @param chinese 中文字符串
     * @return 首字母大写其他字母小写没有声调的汉语拼音字符串。
     */
    public static String convertToPinyin(String chinese) {
        StringBuilder sb = new StringBuilder();
        char[] chars = chinese.toCharArray();
        for (char aChar : chars) {
            // 判断是否是中文字符
            if (Character.toString(aChar).matches("[\\u4E00-\\u9FA5]+")) {
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(aChar);
                if (pinyinArray != null) {
                    // 默认情况下，取第一个拼音
                    String pinyin = pinyinArray[0];
                    // 去掉声调
                    pinyin = pinyin.replaceAll("\\d", "");
                    // 首字母大写，其余小写
                    pinyin = Character.toUpperCase(pinyin.charAt(0)) + pinyin.substring(1).toLowerCase();
                    sb.append(pinyin);
                }
            } else {
                sb.append(aChar); // 非中文字符直接添加  
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String chinese1 = "淘宝";
        String pinyin1 = convertToPinyin(chinese1);
        System.out.println(pinyin1); // 输出: TaoBao
        String chinese2 = "今日头条";
        String pinyin2 = convertToPinyin(chinese2);
        System.out.println(pinyin2); // 输出: JinRiTouTiao  
    }
}