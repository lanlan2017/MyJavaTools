package test;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

/**
 * @author francis
 * create at 2020/9/5-17:10
 */
public class BaiduOCRResult {
   public static class Words {
        @JSONField(name = "words")
        private String words;

        public void setWords(String words) {
            this.words = words;
        }

        public String getWords() {
            return words;
        }

        @Override
        public String toString() {
            return "Words{" +
                    "words='" + words + '\'' +
                    '}';
        }
    }

    @JSONField(name = "words_result")
    private ArrayList<Words> words_result;
    @JSONField(name = "log_id")
    private long log_id;
    @JSONField(name = "words_result_num")
    private int words_result_num;

    public ArrayList<Words> getWords_result() {
        return words_result;
    }

    public void setWords_result(ArrayList<Words> words_result) {
        this.words_result = words_result;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(int words_result_num) {
        this.words_result_num = words_result_num;
    }
}
