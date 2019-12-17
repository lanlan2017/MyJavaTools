package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author francis
 * create at 2019/12/17-10:09
 */
public class MyScript {
    private String script;

    private static MyScript instance = new MyScript();

    private MyScript() {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("MyScript.html"), StandardCharsets.UTF_8))) {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        script = sb.toString();
    }

    public static MyScript getInstance() {
        return instance;
    }

    public String getScript() {
        return script;
    }

    public static void main(String[] args) {
        // 验证单例模式是否正确,应该返回true
        System.out.println(MyScript.getInstance() == MyScript.getInstance());
        System.out.println(MyScript.getInstance().getScript());
    }
}
