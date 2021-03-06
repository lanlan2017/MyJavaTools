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
        switch (args.length) {
            case 1:
                // 显示帮助文档
                help(args);
                break;
            default:
                // 处理命令
                processHardValue(args);
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
        String helpStr = fileHelp(args);
        if (!"".equals(helpStr)) {
            writeHelp(helpStr);
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
        String line = null;
        String previousLine = null;
        StringBuilder helpStr = new StringBuilder();
        boolean isStart = false;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceFileReader.getInputStream(this.getClass(), "config.yml")));
            while ((line = reader.readLine()) != null) {
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
                    helpStr.append(previousLine).append("\n");
                }
                previousLine = line;
            }
            if (!previousLine.startsWith("#")) {
                //System.out.println(previousLine);
                helpStr.append(previousLine).append("\n");
            }

            // 延时显示
            //Thread.sleep(1000 * 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isStart)
            return helpStr.toString();
        return "";
        //return isStart;
    }

    private void writeHelp(String helpStr) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(new File("help.txt"))));
            writer.write(helpStr.toString());
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
        ArrayList<String> commands = new ArrayList<>();
        commands.add("notepad.exe");
        //commands.add("code.exe");
        commands.add("help.txt");
        try {
            ProcessBuilder runner = new ProcessBuilder(commands);
            runner.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processHardValue(String[] args) {
        // 获取命令对应的值
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
