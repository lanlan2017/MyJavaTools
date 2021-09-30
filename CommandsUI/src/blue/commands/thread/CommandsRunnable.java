package blue.commands.thread;

import tools.config.ConfigTools;
import tools.copy.SystemClipboard;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class CommandsRunnable implements Runnable {

    /**
     * 控制线程器是否循环运行
     */
    boolean canRunning = false;
    /**
     * 控制线程是否可以启动
     */
    boolean canStarting = true;
    /**
     * 经过线程处理的内容
     */
    private String threadedText;

    JTextArea textArea;

    private static final CommandsRunnable instance = new CommandsRunnable();

    private final ArrayList<String> commandList = new ArrayList<>();

    public static CommandsRunnable getInstance() {
        return instance;
    }

    private CommandsRunnable() {
    }

    public void setCanRunning(boolean canRunning) {
        this.canRunning = canRunning;
    }

    public void setOutputTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public boolean isCanStarting() {
        return canStarting;
    }

    public void setCanStarting(boolean canStarting) {
        this.canStarting = canStarting;
    }

    public void addCommand(String commandStr) {
        commandList.add(commandStr);
    }

    public void removeCommand(String commandStr) {
        Iterator<String> iterator = commandList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(commandStr)) {
                iterator.remove();
            }
        }
    }

    public ArrayList<String> getCommandList() {
        return commandList;
    }

    /**
     * 停止线程
     */
    public void stop() {
        this.canRunning = true;
    }

    @Override
    public void run() {
        System.out.println("runnable is start");
        // 如果可以继续循环运行
        while (canRunning) {
            // 读取剪贴板的内容
            String sysClipboardText = SystemClipboard.getSysClipboardText();
            // 如果剪贴板中的内容和线程处理之后的内容不一样
            if (sysClipboardText != null && !sysClipboardText.equals(threadedText)) {
                // 执行命令，返回结果
                String result = doCommands();

                // 显示处理结果，并把处理后的内容写入剪贴板中
                ConfigTools.getInstance().showResult(result);
                // 缓存线程的处理结果
                threadedText = result;
                if (textArea != null) {
                    textArea.setText(result);
                }
            }
            // 暂停该线程1秒钟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 显示线程结束信息。
        System.out.println("runnable is stop");
    }

    private String doCommands() {

        String result = null;
        Iterator<String> iterator = commandList.iterator();
        while (iterator.hasNext()) {
            String commandStr = iterator.next();
            // System.out.println("=========================================================");
            // System.out.println(commandStr);
            String[] commands = commandStr.split(" ");
            // 执行命令，处理该内容
            result = ConfigTools.getInstance().forward(commands);
            // 把处理后的内容写入剪贴板中
            SystemClipboard.setSysClipboardText(result);
        }
        return result;
    }

    public static void main(String[] args) {
        CommandsRunnable s_dsbc_runnable = new CommandsRunnable();
        Thread thread = new Thread(s_dsbc_runnable);
        thread.start();
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s_dsbc_runnable.stop();
    }
}