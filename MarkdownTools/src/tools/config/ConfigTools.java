package tools.config;

import clipboard.swing.SystemClipboard;
import org.yaml.snakeyaml.Yaml;
import reader.file.resouce.ResourceFileReader;
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
        }
    }

    private void argsLength1(String[] args) {
        // System.out.println(configMap);
        // 如果map中有这key
        if (configMap.containsKey(args[0])) {
            // 获取key对应的value(全限定方法名)
            String fQMethodName = (String) configMap.get(args[0]);
            // System.out.println(fQMethodName);
            // 调用该方法
            callMethod(fQMethodName);
        }
    }

    private void argsLength2(String[] args) {
        if (configMap.containsKey(args[0])) {
            Map<String, String> subMapDeepOne = (Map<String, String>) configMap.get(args[0]);
            if (subMapDeepOne.containsKey(args[1])) {
                String fQMethodName = subMapDeepOne.get(args[1]);
                callMethod(fQMethodName);
            } else {
                switch (args[0]) {
                    case "cb":
                        String fQMethodName = "tools.markdown.MarkdownTools.codeBlock";
                        callMethod(fQMethodName, args[1]);
                        break;
                }
            }
        }
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
