//package adbs.main.ui.jpanels.universal.pinyin;
//
//import net.sourceforge.pinyin4j.PinyinHelper;
//
//public class PinyinConverter_1 {
//
//    public static String convertToPinyin(String chinese) {
//        StringBuilder sb = new StringBuilder();
//        char[] chars = chinese.toCharArray();
//        for (char aChar : chars) {
//            if (Character.toString(aChar).matches("[\\u4E00-\\u9FA5]+")) { // 判断是否是中文字符
//                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(aChar);
//                if (pinyinArray != null) {
//                    String pinyin = pinyinArray[0]; // 默认情况下，取第一个拼音
////                    去掉声调
//                    pinyin = pinyin.replaceAll("\\d", "");
//                    sb.append(pinyin + " ");
////                    sb.append(pinyin.charAt(0)); // 只取首字母
//                } else {
//                    sb.append(aChar); // 非中文字符直接添加
//                }
//            } else {
//                sb.append(aChar); // 非中文字符直接添加
//            }
//        }
//        return sb.toString();
//    }
//
//    public static void main(String[] args) {
//        String chinese1 = "淘宝";
//        String pinyin1 = convertToPinyin(chinese1);
//        System.out.println(pinyin1); // 输出: TaoBao
//
//        String chinese2 = "今日头条";
//        String pinyin2 = convertToPinyin(chinese2);
//        System.out.println(pinyin2); // 输出: JinRiTouTiao
//    }
//}