package yml.load;

import org.yaml.snakeyaml.Yaml;
import tools.reflect.method.CallInstanceMethod;

import java.util.Map;

/**
 * @author francis
 * create at 2019/12/18-22:18
 */
public class TestMarkdown {
    Map<String, Object> map;

    public TestMarkdown() {
        Yaml yaml = new Yaml();
        map = yaml.load(
                this.getClass().getClassLoader().getResourceAsStream("Map2.yml"));
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
        if (map.containsKey(args[0])) {
            Map<String, String> map1 = (Map<String, String>) map.get(args[0]);
            if (map1.containsKey(args[1])) {
                System.out.println(printArgs(args) + "=" + map1.get(args[1]));
            }
        }
    }

    private void getValueDeep3(String[] args) {
        if (map.containsKey(args[0])) {
            Map<String, Object> subMapDeepOne = (Map<String, Object>) map.get(args[0]);
            if (subMapDeepOne.containsKey(args[1])) {
                Map<String, String> subMapDeepTwo = (Map<String, String>) subMapDeepOne.get(args[1]);
                if (subMapDeepTwo.containsKey(args[2])) {
                    String fQMethodName = subMapDeepTwo.get(args[2]);
                    System.out.println(printArgs(args) + "=" + fQMethodName);
                    String className = fQMethodName.substring(0, fQMethodName.lastIndexOf("."));
                    String methodName = fQMethodName.substring(fQMethodName.lastIndexOf(".") + 1);
                    System.out.println("类名:" + className);
                    System.out.println("方法名:" + methodName);
                    // String input = SystemClipboard.getSysClipboardText();
                    String input = "xxxx";
                    CallInstanceMethod.runClassNameMethodName(className, methodName, input);
                }
            }
        }
    }

    private String printArgs(String... args) {
        boolean isPrintSplit = false;
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            if (isPrintSplit) {
                // System.out.print(" ");
                sb.append(" ");
            } else {
                isPrintSplit = true;
            }
            // System.out.print(arg);
            sb.append(arg);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TestMarkdown test = new TestMarkdown();
        // test.testMap2("m", "b");
        // test.testMap2("m", "k");
        test.testMap2("m", "h", "h1");
    }
}
