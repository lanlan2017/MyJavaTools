package com.lan.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.markdown.MarkdownTools;
import tools.reflect.method.ObjectMap;

@RestController
@RequestMapping("/m")
public class MarkdownController {
    MarkdownTools markdownTools = ObjectMap.get(MarkdownTools.class);

    /**
     * 生成Markdown加粗
     *
     * @param input 输入
     * @return Markdown加粗
     */
    @RequestMapping("b")
    public String bold(String input) {
        return markdownTools.bold(input);
    }

    /**
     * 生成Markdown格式的java代码块
     * @param input 输入参数
     * @return Markdown Java代码块
     */
    @RequestMapping("/cb/j")
    public String codeBlockJava(String input) {
        return markdownTools.codeBlockJava(input);
    }

    /**
     * 生成Markdown格式的JavaScript代码块
     * @param input JavaScript代码
     * @return Markdown格式的JavaScript代码块。
     */
    @RequestMapping("/cb/js")
    public String codeBlockJavaScript(String input){
        return markdownTools.codeBlockJavaScript(input);
    }

}
