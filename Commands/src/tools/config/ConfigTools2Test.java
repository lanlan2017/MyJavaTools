package tools.config;

public class ConfigTools2Test {

    public static void main(String[] args) {
        String commandsStr;
        // aaaaa
        // commandsStr = "s replace a b";
        // commandsStr = "s replace space underscore";
        // commandsStr = "m cb j";
        // commandsStr = "m cb";
        // commandsStr = "m cb js";
        // commandsStr = "s cc cn";
        commandsStr = "s date";
        // 命令行参数
        String[] commands = commandsStr.split(" ");
        System.out.println(ConfigTools2.getInstance().keySequenceValue(commands));
    }
}
