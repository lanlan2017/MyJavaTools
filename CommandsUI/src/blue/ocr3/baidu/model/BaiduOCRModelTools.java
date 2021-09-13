package blue.ocr3.baidu.model;

import com.alibaba.fastjson.JSON;

import java.util.Iterator;
import java.util.List;

public class BaiduOCRModelTools {
    public static void main(String[] args) {
        // String json = "{\n" + "    \"words_result\": [\n" + "        {\"words\": \"滤器使用 Name Filter匿名关来\"},\n" + "        {\"words\": \"名称。新创建的过滤器与\"},\n" + "        {\"words\": \"现在我们可以\"},\n" + "        {\"words\": \"地将对象转换为\"}\n" + "    ],\n" + "    \"log_id\": 1437367878297201496,\n" + "    \"words_result_num\": 4\n" + "}";
        String json = "{\"words_result\":[{\"words\":\"//取出一行词语\"},{\"words\":\"BaiduocRModel. WordsResultDTO wordsResultDTO= iterator. next(\"},{\"words\":\"//输出词语\"},{\"words\":\"System. out. printin( wordsResultDTO. getWordso)\"}],\"log_id\":1437372382780785360,\"words_result_num\":4}";
        // toLines(json);
        // toOneLine(json);
        // 解析JSON
        System.out.println(toOneLine(json));
    }

    public static String toOneLine(String json) {
        StringBuilder sb = new StringBuilder(json.length());
        BaiduOCRModel model = JSON.parseObject(json, BaiduOCRModel.class);
        // System.out.println(model);
        // 取出其中的词语列表
        List<BaiduOCRModel.WordsResultDTO> words_result = model.getWords_result();
        // 遍历词语列表
        Iterator<BaiduOCRModel.WordsResultDTO> iterator = words_result.iterator();
        while (iterator.hasNext()) {
            //取出一行词语
            BaiduOCRModel.WordsResultDTO wordsResultDTO = iterator.next();
            // 输出词语
            System.out.print(wordsResultDTO.getWords());
            sb.append(wordsResultDTO.getWords());
        }
        return sb.toString();
    }

    public static String toLines(String json) {
        StringBuilder sb = new StringBuilder(json.length());
        // 解析JSON
        BaiduOCRModel model = JSON.parseObject(json, BaiduOCRModel.class);
        // System.out.println(model);
        // 取出其中的词语列表
        List<BaiduOCRModel.WordsResultDTO> words_result = model.getWords_result();
        // 遍历词语列表
        Iterator<BaiduOCRModel.WordsResultDTO> iterator = words_result.iterator();
        while (iterator.hasNext()) {
            //取出一行词语
            BaiduOCRModel.WordsResultDTO wordsResultDTO = iterator.next();
            // 输出词语
            // System.out.println(wordsResultDTO.getWords());
            sb.append(wordsResultDTO.getWords()).append("\n");
        }
        return sb.toString();
    }
}
