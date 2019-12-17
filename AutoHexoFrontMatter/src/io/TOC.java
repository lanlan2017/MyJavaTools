package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author francis
 * create at 2019/12/17-10:56
 */
public class TOC {
    private String toc;
    private static TOC instance = new TOC();

    private TOC() {
        StringBuilder sb = new StringBuilder();
        char[] buff = new char[512];
        int size = -1;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("toc.html"), StandardCharsets.UTF_8))) {
            while ((size = reader.read(buff)) != -1) {
                sb.append(buff, 0, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        toc = sb.toString();
    }

    public String getToc() {
        return toc;
    }

    public static TOC getInstance() {
        return instance;
    }

    // public static void main(String[] args) {
    //     System.out.println(TOC.getInstance() == TOC.getInstance());
    //     System.out.println(TOC.getInstance().getToc());
    // }
}
