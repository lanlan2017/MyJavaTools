package tools.config;

import org.yaml.snakeyaml.Yaml;
import regex.RegexEnum;
import tools.copy.SystemClipboard;
import tools.file.reader.resouce.ResourceFileReader;
import tools.reflect.method.CallInstanceMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * 根据配置文件中的全限定方法名,运行方法.
 */
public class ConfigTools {
    /**
     * 配置文件得到的Map
     */
    private final Map<String, Object> configMap;
    // 直接创建实例
    private static final ConfigTools instance = new ConfigTools();

    // 私有化构造函数
    private ConfigTools() {
        Yaml yaml = new Yaml();
        // 配置文件解析为map
        configMap = yaml.load(ResourceFileReader.getInputStream(this.getClass(), "config.yml"));
    }

    // 提供获取实例的方法
    public static ConfigTools getInstance() {
        return instance;
    }

    /**
     * 程序入口
     *
     * @param args 命令行参数
     */
    public String forward(String... args) {
        // 根据参数的长度
        switch (args.length) {
            // 当只有一个参数的时候
            case 1:
                // 显示帮助文档
                help(args);
                return null;
            // 其他情况
            default:
                // 处理命令
                return runByArgs(args);
        }
    }

    /**
     * 显示帮助文档.
     *
     * @param args 命令列表
     */
    private void help(String[] args) {
        // 读取配置文件
        String helpStr = fileHelp(args);
        // 如果读取到内容
        if (!"".equals(helpStr)) {
            // 把帮助信息写入帮助文档
            writeHelp(helpStr);
            // 打开帮助文档
            openHelpFile();
        }
    }

    /**
     * 查找帮助文档
     *
     * @param args 命令参数
     * @return 帮组文档字符串
     */
    private String fileHelp(String[] args) {
        String line;
        String previousLine = null;
        StringBuilder helpBuff = new StringBuilder();
        boolean isStart = false;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceFileReader.getInputStream(this.getClass(), "config.yml"), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                // 如果遇到"第一个参数:"开头
                if (line.equals(args[0] + ":")) {
                    isStart = true;
                }
                if (isStart) {
                    // 遇到其他的一级命令时结束
                    if (!line.equals(args[0] + ":") && line.matches("^[a-zA-Z]+\\:")) {
                        break;
                    }
                    // 输出前一行
                    //System.out.println(new String(previousLine.getBytes("utf-8"),"gbk"));
                    //System.out.println(previousLine);
                    // 记录下前一行
                    helpBuff.append(previousLine).append("\n");
                }
                // 记录当前行，作为下一行
                previousLine = line;
            }
            // 最后一行的情况
            if (!previousLine.startsWith("#")) {
                //System.out.println(previousLine);
                helpBuff.append(previousLine).append("\n");
            }

            // 延时显示
            //Thread.sleep(1000 * 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isStart)
            return helpBuff.toString();
        return "";
        //return isStart;
    }

    /**
     * 把帮助文档写入文件中
     *
     * @param helpStr 帮助文档字符串
     */
    private void writeHelp(String helpStr) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("help.txt")), "UTF-8"));
            // 写入文件
            writer.write(helpStr);
            // 把缓存刷入文件，关闭输出流
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示帮助文档.
     */
    private static void openHelpFile() {
        // 命令列表
        ArrayList<String> commands = new ArrayList<>();
        // 要执行的命令
        commands.add("notepad.exe");
        //commands.add("code.exe");
        commands.add("help.txt");
        try {
            // 创建进程
            ProcessBuilder runner = new ProcessBuilder(commands);
            // 执行命令
            runner.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据参数执行方法
     *
     * @param args 命令行参数
     */
    private String runByArgs(String[] args) {
        // 查询命令行参数中这一长串key对应的value值
        // String valueOfKeys = keySequenceValue(args);
        // 更换算法
        String valueOfKeys = ConfigTools2.getInstance().keySequenceValue(args);
        // 如果value的格式为："全限定方法名_ControlCode" 的话
        if (valueOfKeys.matches(RegexEnum.FQ_MethodName_ControlCode.toString())) {
            // System.out.println("全限定方法名_ControlCode");
            return runFqMethodControlCode(args, valueOfKeys);
        }
        // 如果value的格式为"全限定方法名"
        else if (valueOfKeys.matches(RegexEnum.FQ_MethodName.toString())) {
            return runFqMethod(valueOfKeys);
        }
        // 如果value是 资源文件的地址
        else if (valueOfKeys.matches("(?:.+?\\/)+.+?\\.[a-zA-Z]+")) {
            // 读取资源文件中的内容
            String code = ResourceFileReader.getFileContent(this.getClass(), valueOfKeys);
            return code;
        }
        // 如果value是普通字符串，则直接返回该字符串。
        return valueOfKeys;
    }

    private String runFqMethodControlCode(String[] args, String valueOfKeys) {
        String result = "";
        // 摘出全限定方法名
        String fqMethodName = valueOfKeys.substring(0, valueOfKeys.lastIndexOf("_"));
        // 摘出控制串
        String controlCode = valueOfKeys.substring(valueOfKeys.lastIndexOf("_") + 1);
        // 如果控制串是"Parameterless"
        if ("Parameterless".equals(controlCode)) {
            // 运行无参数的方法
            result = CallInstanceMethod.runFQMethodName(fqMethodName);
            // return result;
        }
        // 如果控制串是
        else if ("LastArgIsMethodInput".equals(controlCode)) {
            //
            // System.out.println("使用最后一个命令行作为参数");
            // System.out.println(Arrays.toString(args));
            // 使用最后一个命令行参数作为方法的输入参数
            result = CallInstanceMethod.runFQMethodName(fqMethodName, args[args.length-1]);
        }
        // 如果控制串是"ControlCodeIsLastArg"
        else if ("ControlCodeIsLastArg".equals(controlCode)) {
            // 获取最后一个命令行参数
            String lastArg = args[args.length - 1];
            // 如果最后一个命令行参数是"cb"的话
            if ("cb".equals(lastArg)) {
                // 读取剪贴板中的数据
                String clipboardText = SystemClipboard.getSysClipboardText();
                // 执行方法
                result = CallInstanceMethod.runFQMethodName(fqMethodName, "", clipboardText);
                // return result;
            }
            // 如果最后一个命令行参数不是"cb"的话
            else {
                // 读取剪贴板中的数据
                String clipboardText = SystemClipboard.getSysClipboardText();
                // 执行方法，最后一个命令行参数作为方法的第1个参数，剪贴板中的内容作为方法的第2个参数
                result = CallInstanceMethod.runFQMethodName(fqMethodName, lastArg, clipboardText);
                // return result;
            }
        }
        // 如果控制串是"ControlCodeIsLastTwoArgs"
        else if ("ControlCodeIsLastTwoArgs".equals(controlCode)) {
            // 读取剪贴板中的数据
            String clipboardText = SystemClipboard.getSysClipboardText();
            String arg3 = args[args.length - 1];
            String arg2 = args[args.length - 2];
            // System.out.println("fqMethodName = " + fqMethodName);
            // System.out.println("arg1=clipboardText = " + clipboardText);
            // System.out.println("arg2 = " + arg2);
            // System.out.println("arg3 = " + arg3);
            result = CallInstanceMethod.runFqMethodName(fqMethodName, clipboardText, arg2, arg3);
        }
        return result;
    }

    /**
     * 执行全限定方法名对应的方法
     *
     * @param fQMethodName 全限定方法名
     * @return 全限定方法名的运行结果
     */
    private String runFqMethod(String fQMethodName) {
        // 读取剪贴板中的内容
        String clipboardText = SystemClipboard.getSysClipboardText();
        // 执行该方法，剪贴中的字符串作为该方法的第1个参数
        String result = CallInstanceMethod.runFQMethodName(fQMethodName, clipboardText);
        return result;
    }

    /**
     * 在嵌套map中取出key序列对应的value值。
     *
     * @param args 保存key序列的数组，第一个key作为最外层map的key,最后的key是最里层map的key
     * @return 嵌套map中，这一长串key定位到的value值
     */
    private String keySequenceValue(String[] args) {
        // 最后一个命令作为key的value
        Object objectOfLastKey;
        // 返回值
        String valueOfLastKey = null;
        // 配置文件对应的Map
        Map<String, Object> map = configMap;
        // 最后一个命令行参数的下标
        final int lastArgIndex = args.length - 1;
        // 逐层向下取出map,取出最后一层的map
        for (int i = 0; i < lastArgIndex; i++) {
            map = (Map<String, Object>) map.get(args[i]);
        }
        // 如果最后一层的map的key中找到最后一个命令行参数
        String defaultLastKey = "default";
        if (map.containsKey(args[lastArgIndex])) {
            // 取出最后一个命令行参数作为key时的value
            objectOfLastKey = map.get(args[lastArgIndex]);
            // 如果最后一个key得到的value,依然是map
            if (objectOfLastKey instanceof Map) {
                // 再次把value转换成map
                map = (Map<String, Object>) objectOfLastKey;
                // 获取default
                if (map.containsKey(defaultLastKey)) {
                    // 使用最后一层的map的default键的值作为最深层的值。
                    valueOfLastKey = (String) map.get(defaultLastKey);
                }
            }
            //  如果最后一个key得到的value,是字符串
            else if (objectOfLastKey instanceof String) {
                //
                valueOfLastKey = (String) objectOfLastKey;
            }
        }
        // 如果在map中的key中 找不到 最后一个命令行参数
        else if (map.containsKey(defaultLastKey)) {
            // 使用当前map中的default的key对应的值
            valueOfLastKey = (String) map.get(defaultLastKey);
        }
        return valueOfLastKey;
    }

    /**
     * 显示运行结果
     *
     * @param result 运行的结果
     */
    public void showResult(String result) {
        // 如果有返回值的话就输出返回值
        if (result != null) {
            // 输出到系统剪贴板
            SystemClipboard.setSysClipboardText(result);
            // 输出到控制台
            System.out.println(result);
        }
    }

    /**
     * 显示运行结果
     *
     * @param result 运行的结果
     */
    public void copyToSysClipboard(String result) {
        // 如果有返回值的话就输出返回值
        if (result != null) {
            // 输出到系统剪贴板
            SystemClipboard.setSysClipboardText(result);
            // 输出到控制台
            // System.out.println(result);
        }
    }
}
