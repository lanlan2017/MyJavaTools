package blue.commands.thread;

import tools.config.ConfigTools;
import tools.copy.SystemClipboard;
import tools.string.PrintStr;

import javax.swing.*;

public class S_DSBC_Runnable implements Runnable {

    /**
     * 控制线程停止
     */
    boolean isStop = false;
    /**
     * 经过线程处理的内容
     */
    private String threadedText;
    /**
     * 剪贴板中的内容
     */
    private String sysClipboardText = "";

    JTextArea textArea;

    private static S_DSBC_Runnable instance = new S_DSBC_Runnable();

    public static S_DSBC_Runnable getInstance() {
        return instance;
    }

    private S_DSBC_Runnable() {
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * 停止线程
     */
    public void stop() {
        this.isStop = true;
    }

    @Override
    public void run() {
        System.out.println("s_dsbc_runnable is start");
        while (!isStop) {
            // 读取剪贴板的内容
            sysClipboardText = SystemClipboard.getSysClipboardText();
            // 如果剪贴板中的内容和线程处理之后的内容不一样
            if (sysClipboardText != null && !sysClipboardText.equals(threadedText)) {
                PrintStr.printStr(sysClipboardText);
                /**
                 * 需要执行的命令
                 */
                String[] commands = "s dsbc".split(" ");
                // 执行命令，处理该内容
                String result = ConfigTools.getInstance().forward(commands);
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
        System.out.println("s_dsbc_runnable is stop");
    }

    public static void main(String[] args) {
        S_DSBC_Runnable s_dsbc_runnable = new S_DSBC_Runnable();
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