package test;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author francis
 * create at 2020/9/5-16:42
 */
public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "11803566";
    public static final String API_KEY = "n8ytVA3HxIYCWgqQiLRBhG0r";
    public static final String SECRET_KEY = "ZFGhGp6swLKjHAk8OSPlKQkAz0NgwHRt";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        //System.setProperty("aip.log4j.conf", "log4j.properties");

        // 调用接口
        //String path = "test.jpg";
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