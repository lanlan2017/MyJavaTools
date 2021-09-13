package blue.ocr3.baidu.model;

import com.alibaba.fastjson.JSON;

import java.util.Iterator;
import java.util.List;

public class BaiduOCRModel {

    private List<WordsResultDTO> words_result;
    private long log_id;
    private int words_result_num;

    public List<WordsResultDTO> getWords_result() {
        return words_result;
    }

    public void setWords_result(List<WordsResultDTO> words_result) {
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

    public static class WordsResultDTO {
        private String words;

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }
    }
}
