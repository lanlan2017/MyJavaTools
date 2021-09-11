package tools.string;

public class PrintStr {

    private static int count = 0;

    public static void printStr(String str) {

        System.out.println("==================== start_" + count + " ====================");
        System.out.println(str);
        System.out.println("===================== end_" + count + " =====================");
        count++;
    }
}
