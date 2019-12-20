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


    public void testMap2(String... args) {
        switch (args.length) {
            case 2:
                getValueDeep2(args);
                break;
            case 3:
                getValueDeep3(args);
                break;
        }
    }

    private void getValueDeep2(String[] args) {
        // System.out.println(printArgs(args));
        if (configMap.containsKey(args[0])) {
            Map<String, String> subMapDeepOne = (Map<String, String>) configMap.get(args[0]);
            if (subMapDeepOne.containsKey(args[1])) {
                String fQMethodName = subMapDeepOne.get(args[1]);
                // System.out.println("=" + fQMethodName);
                callMethod(fQMethodName);
            }
        }
    }

    private void getValueDeep3(String[] args) {
        // System.out.println(printArgs(args));
        if (configMap.containsKey(args[0])) {
            Map<String, Object> subMapDeepOne = (Map<String, Object>) configMap.get(args[0]);
            if (subMapDeepOne.containsKey(args[1])) {
                Map<String, String> subMapDeepTwo = (Map<String, String>) subMapDeepOne.get(args[1]);
                if (subMapDeepTwo.containsKey(args[2])) {
                    String fQMethodName = subMapDeepTwo.get(args[2]);
                    // System.out.println("=" + fQMethodName);
                    callMethod(fQMethodName);
                } else {
                    switch (args[1]) {
                        case "cb":
                            String fQMethodName = "tools.markdown.MarkdownTools.codeBlock";
                            callMethod(fQMethodName, args[2]);
                            break;
                    }
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
        String input = SystemClipboard.getSysClipboardText();
        // String input = "xxxx";
        String result = CallInstanceMethod.twoArgMethod(className, methodName, arg, input);
        SystemClipboard.setSysClipboardText(result);
        System.out.println(result);
    }

    private String printArgs(String... args) {
        boolean isPrintSplit = false;
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            if (isPrintSplit) {
                sb.append(" ");
            } else {
                isPrintSplit = true;
            }
            sb.append(arg);
        }
        return sb.toString();
    }
}
