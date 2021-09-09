package blue.commands.ui.event.textfield;

import blue.commands.demo.ToolIsChinese;
import blue.commands.ui.event.textfield.auto.AutoField;
import tools.config.ConfigTools;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class TextFieldKeyListener2 extends KeyAdapter {
    JFrame frame;
    JTextField textField;
    JComboBox jComboBox;
    JTextArea textArea;
    JPanel scrollPaneFather;

    public TextFieldKeyListener2(JComboBox jComboBox, JTextField textField) {
        this.jComboBox = jComboBox;
        this.textField = textField;
    }


    public TextFieldKeyListener2(JFrame frame, JTextField textField, JComboBox jComboBox, JTextArea textArea, JPanel scrollPaneFather) {
        this.frame = frame;
        this.textField = textField;
        this.jComboBox = jComboBox;
        this.textArea = textArea;
        this.scrollPaneFather = scrollPaneFather;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 设置JComboBox为真
        AutoField.setAdjusting(jComboBox, true);

        // 如果是回车键，或者上箭头 或者下箭头
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            //
            e.setSource(jComboBox);
            jComboBox.dispatchEvent(e);
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // 填充选项到文本框
                textField.setText(jComboBox.getSelectedItem().toString());
                // 隐藏文本框
                jComboBox.setPopupVisible(false);

                // 执行命令，打印结果
                pressedEnter();
            }
        }
        // 如果按下esc键
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jComboBox.setPopupVisible(false);
        }
        AutoField.setAdjusting(jComboBox, false);
    }

    /**
     * 按下回车键时的处理方式
     */
    private void pressedEnter() {
        // 获取文本框的内容
        String input = textField.getText();
        if (!"".equals(input) && input != null) {
            // 设置标题的名称作为提示信息
            frame.setTitle(input);
            // 处理输入的文本
            String output = doTextField(input);
            if (output != null) {
                // // 统计结果有多少行
                int[] line = countRows(output);
                // // 设置文本框的行数
                textArea.setRows(line[0]);
                // 设置textArea的列数，最高不超过80列。
                int maxColumns = 80;
                textArea.setColumns(line[1] < maxColumns ? line[1] : maxColumns);
                // 处理结果写到文本域中
                textArea.setText(output);
                // System.out.println("行数:"+line[0]);
                // System.out.println("=================================");
                // System.out.println(output);
                // System.out.println("=================================");

                // 设置选择区域的开始和结束都为0，
                // 这样textarea显示的时候就显示第一行，而不是显示最后一行
                // 设置选择光标的位置为开头
                textArea.setSelectionStart(0);
                textArea.setSelectionEnd(0);

                // 显示textArea面板
                scrollPaneFather.setVisible(true);

            } else {
                // 隐藏textArea面板
                scrollPaneFather.setVisible(false);
            }
            // 重绘UI
            repaint();
        }
    }

    private int[] countRows(String output) {
        int[] rowColLength = new int[2];
        String lineStr;
        String longestLine = null;
        int lineLength;

        Scanner scanner = new Scanner(output);
        // 记录最长的一行的长度
        int longestLineLength = 0;
        while (scanner.hasNextLine()) {
            // 读取行
            lineStr = scanner.nextLine();
            // 获取行的长度
            lineLength = lineStr.length();
            // 记下最长的行的长度
            if (lineLength > longestLineLength) {
                // 记录行的长度
                longestLineLength = lineLength;
                // 记录最长行的位置
                longestLine = lineStr;
            }
            // 行数加一
            rowColLength[0]++;
        }
        // 最后一行可能没有换行符，所以这里多加上一行。
        rowColLength[0]++;
        scanner.close();

        if (longestLine != null) {
            char ch;
            double count = 1;
            for (int i = 0; i < longestLine.length(); i++) {
                ch = longestLine.charAt(i);
                if (ToolIsChinese.isContainChinese(ch)) {
                    count += 1.0;
                } else if (ch >= 'A' && ch <= 'Z') {
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