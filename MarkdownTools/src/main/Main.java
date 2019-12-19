package main;

import clipboard.swing.SystemClipboard;
import org.yaml.snakeyaml.Yaml;
import tools.reflect.method.CallInstanceMethod;

import java.util.Map;

/**
 * @author francis
 * create at 2019/12/18-22:18
 */
public class Main {
    Map<String, Object> map;

    public Main() {
        Yaml yaml = new Yaml();
        map = yaml.load(
                this.getClass().getClassLoader().getResourceAsStream("config.yml"));
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
        System.out.println(printArgs(args));
        if (map.containsKey(args[0])) {
            Map<String, String> subMapDeepOne = (Map<String, String>) map.get(args[0]);
            if (subMapDeepOne.containsKey(args[1])) {
                String fQMethodName = subMapDeepOne.get(args[1]);
                System.out.println("=" + fQMethodName);
                callMethod(fQMethodName);
            }
        }
    }

    private void getValueDeep3(String[] args) {
        System.out.println(printArgs(args));
        if (map.containsKey(args[0])) {
            Map<String, Object> subMapDeepOne = (Map<String, Object>) map.get(args[0]);
            if (subMapDeepOne.containsKey(args[1])) {
                Map<String, String> subMapDeepTwo = (Map<String, String>) subMapDeepOne.get(args[1]);
                if (subMapDeepTwo.containsKey(args[2])) {
                    String fQMethodName = subMapDeepTwo.get(args[2]);
                    System.out.println("=" + fQMethodName);
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
        System.out.println("类名:" + className);
        System.out.println("方法名:" + methodName);
        String input = SystemClipboard.getSysClipboardText();
        // String input = "xxxx";
        String result = CallInstanceMethod.oneArgMethod(className, methodName, input);
        SystemClipboard.setSysClipboardText(result);
        System.out.println(result);
    }

    private void callMethod(String fQMethodName, String arg) {
        String className = fQMethodName.substring(0, fQMethodName.lastIndexOf("."));
        String methodName = fQMethodName.substring(fQMethodName.lastIndexOf(".") + 1);
        System.out.println("类名:" + className);
        System.out.println("方法名:" + methodName);
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

    public static void main(String[] args) {
        Main test = new Main();
        // test.testMap2(args);
        String[] argsTest;
        // argsTest = new String[]{"m", "h1"};
        // test.testMap2(argsTest);
        // argsTest = new String[]{"m", "h2"};
        // test.testMap2(argsTest);
        // argsTest = new String[]{"m", "h3"};
        // test.testMap2(argsTest);
        // argsTest = new String[]{"m", "h4"};
        // test.testMap2(argsTest);
        // argsTest = new String[]{"m", "h5"};
        // test.testMap2(argsTest);
        // argsTest = new String[]{"m", "h6"};
        // test.testMap2(argsTest);


        // argsTest = new String[]{"m", "b"};
        // test.testMap2(argsTest);
        // argsTest = new String[]{"m", "img"};
        // test.testMap2(argsTest);
        // argsTest = new String[]{"m", "cb","j"};
        // test.testMap2(argsTest);
        // argsTest = new String[]{"m", "cb","js"};
        // test.testMap2(argsTest);
        // argsTest = new String[]{"m", "cb","html"};
        // test.testMap2(argsTest);

// xxxx
// yyyyyy
//         argsTest = new String[]{"m", "u"};
//         test.testMap2(argsTest);
//         argsTest = new String[]{"m", "o"};
//         test.testMap2(argsTest);
//         argsTest = new String[]{"m", "q"};
//         test.testMap2(argsTest);
//         argsTest = new String[]{"m", "ks"};
//         test.testMap2(argsTest);
//         argsTest = new String[]{"m", "table","cp"};
//         test.testMap2(argsTest);
//         argsTest = new String[]{"m", "table","cphl"};
//         test.testMap2(argsTest);
//         argsTest = new String[]{"m", "table","api"};
//         test.testMap2(argsTest);
//         argsTest = new String[]{"m", "u2o"};
//         test.testMap2(argsTest);
//         argsTest = new String[]{"m", "o2u"};
//         test.testMap2(argsTest);
//         argsTest = new String[]{"m", "u2t"};
//         test.testMap2(argsTest);
//         argsTest = new String[]{"m", "read"};
//         test.testMap2(argsTest);
//         argsTest = new String[]{"m", "2ol"};
//         test.testMap2(argsTest);
//         argsTest = new String[]{"m", "dcb"};
//         test.testMap2(argsTest);
    }
}
