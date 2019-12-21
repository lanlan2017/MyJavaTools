package tools.config;

import clipboard.swing.SystemClipboard;
import org.yaml.snakeyaml.Yaml;
import reader.resouce.ResourceFileReader;
import regex.RegexEnum;
import tools.reflect.method.CallInstanceMethod;

import java.util.Map;

/**
 * 根据配置文件中的全限定方法名,运行方法.
 */
public class ConfigTools {
    // 获取配置文件内容.
    private Map<String, Object> configMap;
    private static ConfigTools instance = new ConfigTools();

    private ConfigTools() {
        Yaml yaml = new Yaml();
        configMap = yaml.load(ResourceFileReader.getInputStream(this.getClass(), "config.yml"));
    }

    public static ConfigTools getInstance() {
        return instance;
    }

    public void forward(String... args) {
        switch (args.length) {
            case 1:
                argsLength1(args);
                break;
            case 2:
                argsLength2(args);
                break;
            case 3:
                argsLength3(args);
                break;
        }
    }

    private void argsLength1(String[] args) {
        // System.out.println(configMap);
        // 如果map中有这key
        if (configMap.containsKey(args[0])) {
            // 获取key对应的value(全限定方法名)
            String fQMethodName = (String) configMap.get(args[0]);
            processValue(fQMethodName);
        }
    }

    private void argsLength2(String[] args) {
        if (configMap.containsKey(args[0])) {
            Map<String, String> subMapDeepOne = (Map<String, String>) configMap.get(args[0]);
            if (subMapDeepOne.containsKey(args[1])) {
                String value = subMapDeepOne.get(args[1]);
                processValue(value);
            } else {
                // 如果父命令是h的话
                if ("h".equals(args[0])) {
                    htmlDefault(args[1]);
                }
            }
        }
    }


    private void argsLength3(String[] args) {
        if (configMap.containsKey(args[0])) {
            Map<String, Object> subMapDeepOne = (Map<String, Object>) configMap.get(args[0]);
            if (subMapDeepOne.containsKey(args[1])) {
                Map<String, String> subMapDeepTwo = (Map<String, String>) subMapDeepOne.get(args[1]);
                // System.out.println(subMapDeepTwo);
                if (subMapDeepTwo.containsKey(args[2])) {
                    String value = subMapDeepTwo.get(args[2]);
                    processValue(value);
                } else {
                    if ("cb".equals(args[1])) {
                        markdownCodeBlockDefualt(args[2]);
                    }
                }
            }
        }
    }
    /**
     * 生成默认的双标签代码.
     *
     * @param arg 标签的名称.
     */
    private void htmlDefault(String arg) {
        String subTag = SystemClipboard.getSysClipboardText();
        final String html = "<" + arg + ">" + subTag + "</" + arg + ">";
        SystemClipboard.setSysClipboardText(html);
        System.out.println(html);
    }

    /**
     * 处理value.
     *
     * @param value 配置文件中的value字符串.
     */
    private void processValue(String value) {
        // 如果是全限定方法名
        if (value.matches(RegexEnum.fullyQualifiedMethodName.toString())) {
            // System.out.println("调用方法");
            callMethod(value);
        }
        // 如果是地址
        else if (value.matches("(?:.+?\\/)+.+?\\.[a-zA-Z]+")) {
            // System.out.println("输出模板文件:");
            String code = ResourceFileReader.getFileContent(this.getClass(), value);
            // String code = ResourceFileReader.getFileContent(ConfigTools.class, value);
            System.out.println(code);
            SystemClipboard.setSysClipboardText(code);
        } else {
            SystemClipboard.setSysClipboardText(value);
            System.out.println(value);
        }
    }

    private void markdownCodeBlockDefualt(String language) {
        String fQMethodName = "tools.markdown.MarkdownTools.codeBlock";
        callMethod(fQMethodName, language);
    }

    private void callMethod(String fQMethodName) {
        String className = fQMethodName.substring(0, fQMethodName.lastIndexOf("."));
        String methodName = fQMethodName.substring(fQMethodName.lastIndexOf(".") + 1);
        // System.out.println("类名:" + className);
        // System.out.println("方法名:" + methodName);
        String input = SystemClipboard.getSysClipboardText();
        // String input = "xxxx";
        String result = CallInstanceMethod.oneArgMethod(className, methodName, input);
        SystemClipboard.setSysClipboardText(result);
        System.out.println(result);
    }

    private void callMethod(String fQMethodName, String arg) {
        String className = fQMethodName.substring(0, fQMethodName.lastIndexOf("."));
        String methodName = fQMethodName.substring(fQMethodName.lastIndexOf(".") + 1);
        // System.out.println("类名:" + className);
        // System.out.println("方法名:" + methodName);
        // 获取剪贴板数据
        String input = SystemClipboard.getSysClipboardText();
        // String input = "xxxx";
        String result = CallInstanceMethod.twoArgMethod(className, methodName, arg, input);
        // 输出到剪贴板
        SystemClipboard.setSysClipboardText(result);
        System.out.println(result);
    }
}
