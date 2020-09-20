package singleton;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import test.BaiduOCRResult;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author francis
 * create at 2020/9/5-17:50
 */
public class BaiduOCR {
    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client =SingletonAipOcr.getAipOcr();

        String path = "test.png";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        // 获取JSON
        final String resultsJSON = res.toString(2);
        System.out.println(resultsJSON);
        // 转成Java对象
        BaiduOCRResult baiduOCRResult = JSON.parseObject(resultsJSON, BaiduOCRResult.class);

        ArrayList<BaiduOCRResult.Words> words_result= baiduOCRResult.getWords_result();
        words_result.forEach(words -> System.out.println(words.getWords()));
    }
}
