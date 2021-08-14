package tools.config;

import tools.copy.SystemClipboard;
import org.yaml.snakeyaml.Yaml;
import reader.resouce.ResourceFileReader;
import regex.RegexEnum;
import tools.reflect.method.CallInstanceMethod;

import java.io.*;
import java.util.ArrayList;
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
        // 根据参数的长度
        switch (args.length) {
            // 当只有一个参数的时候
            case 1:
                // 显示帮助文档
                help(args);
                break;
            // 其他情况
            default:
                // 处理命令
                runByArgs(args);
                break;
        }
    }

    /**
     * 显示帮助文档.
     *
     * @param args 命令列表
     */
    private void help(String[] args) {
        //String line = null;
        //String previousLine = null;
        //boolean isStart = false;
        //StringBuilder helpStr = new StringBuilder();
        //isStart = fileHelp(args, previousLine, isStart, helpStr);
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceFileReader.getInputStream(this.getClass(), "config.yml")));
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
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("help.txt"))));
            // 写入文件
            writer.write(helpStr);
            // 把缓存刷入文件，关闭输出流
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
     * @param args 命令行参数
     */
    private void runByArgs(String[] args) {
        // 获取命令对应的值
        String configValue = getFinallyValue(args);
        // 如果 "全限定方法名_UseLastArg"的话
        if (configValue.matches(RegexEnum.FQ_MethodNameUseLastArg.toString())) {
            // 摘出全限定方法名
            String fQMethodName = configValue.substring(0, configValue.lastIndexOf("_"));
            // 获取最后的一个参数
            String lastArg = args[args.length - 1];
            // 如果最后一个参数时cb的话
            if ("cb".equals(lastArg)) {
                // 执行方法
                // callMethod(fQMethodName, "");
                String clipboardText = SystemClipboard.getSysClipboardText();
                // String result = MethodCall.getInstance().callByfQMethodName(fQMethodName, "", clipboardText);
                String result = CallInstanceMethod.runFQMethodName(fQMethodName, "", clipboardText);
                showResult(result);
            } else {
                String clipboardText = SystemClipboard.getSysClipboardText();
                // String result = MethodCall.getInstance().callByfQMethodName(fQMethodName, lastArg, clipboardText);
                String result = CallInstanceMethod.runFQMethodName(fQMethodName, lastArg, clipboardText);
                showResult(result);
            }
        }
        // 如果是全限定方法名
        else if (configValue.matches(RegexEnum.FQ_MethodName.toString())) {
            // System.out.println("调用方法");
            String input = SystemClipboard.getSysClipboardText();
            // String result = MethodCall.getInstance().callByfQMethodName(value, input);
            String result = CallInstanceMethod.runFQMethodName(configValue, input);
            showResult(result);
        }
        // 如果是地址
        else if (configValue.matches("(?:.+?\\/)+.+?\\.[a-zA-Z]+")) {
            // System.out.println("输出模板文件:");
            String code = ResourceFileReader.getFileContent(this.getClass(), configValue);
            // String code = ResourceFileReader.getFileContent(ConfigTools.class, value);
            System.out.println(code);
            SystemClipboard.setSysClipboardText(code);
        }
        // 如果都不是直接返回值
        else {
            SystemClipboard.setSysClipboardText(configValue);
            System.out.println(configValue);
        }

    }

    /**
     * 取出嵌套map中最深的一层map的值.
     *
     * @param args 命令行参数.对应map的key.
     * @return 配置文件中对应的value.
     */
    private String getFinallyValue(String[] args) {
        // 最后一个命令所对的value
        Object lastArgValue;
        // 返回值
        String resutl = null;
        // 缓存map
        Map<String, Object> map = configMap;
        // 最后一个命令所对的下标
        final int lastArgIndex = args.length - 1;
        // 根据前n-1个命令查找map
        for (int i = 0; i < lastArgIndex; i++) {
            map = (Map<String, Object>) map.get(args[i]);
            //System.out.println("key:" + args[i] + "|value:" + map);
        }
        // 如果存在最后一个参数
        if (map.containsKey(args[lastArgIndex])) {
            // 获取最后一个参数的value
            lastArgValue = map.get(args[lastArgIndex]);
            //System.out.println(args[lastArgsIndex] + ":" + value);
            // 如果最后一个参数为map
            if (lastArgValue instanceof Map) {
                map = (Map<String, Object>) lastArgValue;
                // 获取default
                if (map.containsKey("default")) {
                    //object = map.get("default");
                    //System.out.println("User Default|" + value);
                    resutl = (String) map.get("default");
                }
            }
            // 如果最后一个参数是字符串
            else if (lastArgValue instanceof String) {
                //System.out.println("User Last arg:key" + args[lastArgsIndex] + "|" + object);
                resutl = (String) lastArgValue;
            }
        }
        // 如果不存在最后一个参数,则取默认
        else if (map.containsKey("default")) {
            //object = map.get("default");
            //System.out.println("default|" + object);
            resutl = (String) map.get("default");
        }
        return resutl;
        //System.out.println(value);
    }
    /**
     * 显示运行结果
     *
     * @param result 运行的结果
     */
    private void showResult(String result) {
        // 如果有返回值的话就输出返回值
        if (result != null) {
            // 输出到系统剪贴板
            SystemClipboard.setSysClipboardText(result);
            // 输出到控制台
            System.out.println(result);
        }
    }
}
