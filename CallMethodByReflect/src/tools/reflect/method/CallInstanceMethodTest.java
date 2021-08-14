package tools.reflect.method;

/**
 * @author francis
 * create at 2019/12/18-23:06
 */
public class CallInstanceMethodTest {
    public static void main(String[] args) {
        testCallNoArgMethod();
        testCallOneArgMethod();
    }

    private static void testCallNoArgMethod() {
        CallInstanceMethod.runClassNameMethodName("Markdown","bold");
    }

    private static void testCallOneArgMethod() {
        String result = CallInstanceMethod.runClassNameMethodName("Markdown", "bold", "xxxxx");
        System.out.println(result);
    }
}
