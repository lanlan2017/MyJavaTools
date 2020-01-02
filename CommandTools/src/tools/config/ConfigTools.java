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
    // 直接创建实例
    private static ConfigTools instance = new ConfigTools();

    // 私有化构造函数
    private ConfigTools() {
        Yaml yaml = new Yaml();
        configMap = yaml.load(ResourceFileReader.getInputStream(this.getClass(), "config.yml"));
    }

    // 提供获取实例的方法
    public static ConfigTools getInstance() {
        return instance;
    }

    public void forward(String... args) {
        processHardValue(args);
    }


    private void processHardValue(String[] args) {
        String value = getFinallyValue(args);
        if (value.matches(RegexEnum.FQ_MethodNameUseLastArg.toString())) {
            String fQMethodName = value.substring(0, value.lastIndexOf("_"));
            String lastArg = args[args.length - 1];
            if ("cb".equals(lastArg))
                callMethod(fQMethodName, "");
            else
                callMethod(fQMethodName, lastArg);
        } else {
            processValue(value);
        }
    }

    /**
     * 取出嵌套map中最深的一层map的值.
     *
     * @param args 命令行参数.对应map的key.
     * @return
     */
    private String getFinallyValue(String[] args) {
        Object value = null;
        Map<String, Object> map = configMap;
        for (int i = 0; i < args.length; i++) {
            value = map.get(args[i]);
            // 如果是Map的话
            if (value instanceof Map) {
                map = (Map<String, Object>) value;
                System.out.println("key:" + args[i] + "|value:" + map);
                // 不是最后一个参数
                // 下一个参数存在的话
                if ((i < args.length - 1) && map.containsKey(args[i + 1])) {
                    continue;
                }
                // 是最后一个参数时
                else if (map.containsKey("default")) {
                    value = map.get("default");
                    System.out.println("key:default" + "|value:" + value);
                    break;
                }
            }
            // 如果是字符串的话
            else if (value instanceof String) {
                System.out.println("String:" + value);
                break;
            }
        }
        return (String) value;
    }

    /**
     * 处理value.
     *
     * @param value 配置文件中的value字符串.
     */
    private void processValue(String value) {
        // 如果是全限定方法名
        if (value.matches(RegexEnum.FQ_MethodName.toString())) {
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
        }
        // 如果都不是直接返回值
        else {
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
        // 如果有返回值的话就输出返回值
        showResult(result);
    }

    private void callMethod(String fQMethodName, String arg) {
        String className = fQMethodName.substring(0, fQMethodName.lastIndexOf("."));
        String methodName = fQMethodName.substring(fQMethodName.lastIndexOf(".") + 1);
        // System.out.println("类名:" + className);
        // System.out.println("方法名:" + methodName);
        // 获取剪贴板数据
        String clipboardText = SystemClipboard.getSysClipboardText();
        // String input = "xxxx";
        String result = CallInstanceMethod.twoArgMethod(className, methodName, arg, clipboardText);
        showResult(result);
    }

    private void showResult(String result) {
        // 如果有返回值的话就输出返回值
        if (result != null) {
            SystemClipboard.setSysClipboardText(result);
            System.out.println(result);
        }
    }
}
