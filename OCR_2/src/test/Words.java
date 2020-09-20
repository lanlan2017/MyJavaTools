package test;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author francis
 * create at 2020/9/5-17:53
 */
public class Words {
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