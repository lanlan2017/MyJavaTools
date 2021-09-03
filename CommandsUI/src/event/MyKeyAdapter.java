package event;

import com.blue.demo.ToolIsChinese;
import tools.config.ConfigTools;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class MyKeyAdapter extends KeyAdapter {
    JFrame frame;
    JTextField textField;
    JTextArea textArea;
    JPanel scrollPaneFather;

    public MyKeyAdapter(JFrame frame, JTextField textField, JTextArea textArea, JPanel scrollPaneFather) {
        this.frame = frame;
        this.textField = textField;
        this.textArea = textArea;
        this.scrollPaneFather = scrollPaneFather;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 如果按下回车键
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // 获取文本框的内容
            String input = textField.getText();
            if (!"".equals(input) && input != null) {
                // 设置标题的名称作为提示信息
                frame.setTitle(input);
                // 处理输入的文本
                String output = doTextField(input);
                // System.out.println(output);
                if (output != null) {
                    // 统计结果有多少行
                    int[] line = countRows(output);
                    // 设置文本框的行数
                    textArea.setRows(line[0] + 1);
                    textArea.setColumns(line[1] + 1);
                    // 处理结果写到文本域中
                    textArea.setText(output);
                    // 显示textArea面板
                    // scrollPane.setVisible(true);
                    scrollPaneFather.setVisible(true);

                    // 重绘UI
                } else {
                    // 隐藏textArea面板
                    // scrollPane.setVisible(false);
                    scrollPaneFather.setVisible(false);
                    // 重绘UI
                }
                repaint();
            }
        }
    }

    private int[] countRows(String output) {
        int[] rowColLength = new int[2];
        String lineStr;
        String longestLine = null;
        int lineLength;
        Scanner scanner = new Scanner(output);
        while (scanner.hasNext()) {
            // 读取行
            lineStr = scanner.nextLine();
            // 获取行的长度
            lineLength = lineStr.length();
            // 记下最长的行的长度
            if (lineLength > rowColLength[1]) {
                // 记录行的长度
                rowColLength[1] = lineLength;
                longestLine = lineStr;
            }
            rowColLength[0]++;
        }
        scanner.close();
        if (longestLine != null) {
            char ch;
            double count = 1;
            for (int i = 0; i < longestLine.length(); i++) {
                ch = longestLine.charAt(i);
                if (ToolIsChinese.isContainChinese(ch)) {
                    count += 1.0;
                } else {
                    count += 0.5;
                }
            }
            rowColLength[1] = (int) count;
        }

        System.out.println("中英文列长度:" + rowColLength[1]);
        return rowColLength;
    }

    /**
     * 处理输入的内容
     *
     * @param input 文本框中输入的文本
     * @return 对文本框中的文本处理的结果
     */
    private String doTextField(String input) {
        String output;
        // 按空格分隔得到命令
        String[] args = input.split(" ");
        // 执行命令，返回执行结果
        output = ConfigTools.getInstance().forward(args);
        // 复制到系统剪贴板
        ConfigTools.getInstance().copyToSysClipboard(output);
        return output;
    }

    /**
     * 重绘组件
     */
    private void repaint() {
        // 重绘窗体
        frame.repaint();
        // 整个窗体最小显示
        frame.pack();
    }

}