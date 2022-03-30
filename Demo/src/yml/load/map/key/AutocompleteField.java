package yml.load.map.key;

import yml.load.map.TestMapHelp;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class AutocompleteField {
    static Map<String, Object> mapTemp = TestMapHelp.map;

    public static void main(String[] args) throws Exception {
        // 设置外观
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // 创建窗体
        JFrame frame = new JFrame();
        // 设置窗体
        frame.setTitle("Auto Completion Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 500, 400);

        // 设置帮助内容
        Collection<String> items = MapKeyList.getKeysList();

        // 创建文本框
        JTextField txtInput = new JTextField();
        // 给文本框设置自动内容提示
        setupAutoComplete(txtInput, items);
        // 设置文本框的列数
        txtInput.setColumns(30);
        // 设置窗体的布局管理器
        frame.getContentPane().setLayout(new FlowLayout());
        // 把文本框添加到窗体中
        frame.getContentPane().add(txtInput, BorderLayout.NORTH);
        // 显示窗体
        frame.setVisible(true);
    }

    private static boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }

    private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }

    /**
     * 给文本框设置自动补全功能
     *
     * @param txtInput 要设置自动补全的文本框
     * @param items    自动提示的内容列表
     */
    public static void setupAutoComplete(final JTextField txtInput, final Collection<String> items) {
        // 创建JComboBox模型
        final DefaultComboBoxModel model = new DefaultComboBoxModel();
        // 使用JComboBox模型创建JComboBox
        final JComboBox cbInput = new JComboBox(model) {
            public Dimension getPreferredSize() {
                // 设置位置
                return new Dimension(super.getPreferredSize().width, 0);
            }

        };
        // 设置JComboBox
        setAdjusting(cbInput, false);

        Iterator<String> it = items.iterator();
        while (it.hasNext()) {
            model.addElement(it.next());
        }


        // 设置JComboBox默认不选择任何选项
        cbInput.setSelectedItem(null);
        // JComboxBox事件监听器
        cbInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                if (!isAdjusting(cbInput)) {

                    if (cbInput.getSelectedItem() != null) {
                        // 文本框中填写JComboBox的内容。
                        txtInput.setText(cbInput.getSelectedItem().toString());

                    }
                }
            }
        });
        // 文本框监听器
        txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // 设置JComboBox为真
                setAdjusting(cbInput, true);
                // 如果按下空格
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // 如果JComboBox弹出内容的话
                    if (cbInput.isPopupVisible()) {
                        // 当成回车键
                        e.setKeyCode(KeyEvent.VK_ENTER);
                    }
                }
                // 如果是回车键，或者上箭头 或者下箭头
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    //
                    e.setSource(cbInput);
                    cbInput.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtInput.setText(cbInput.getSelectedItem().toString());
                        cbInput.setPopupVisible(false);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cbInput.setPopupVisible(false);
                }
                setAdjusting(cbInput, false);
            }
        });
        // 监听文本框内容变化
        txtInput.getDocument().addDocumentListener(new DocumentListener() {
            // 当有内容插入的时候
            @Override
            public void insertUpdate(DocumentEvent e) {
                //
                updateList();
            }

            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            //
            private void updateList() {
                setAdjusting(cbInput, true);
                // 移除JComboBox中的全部内容
                model.removeAllElements();
                // 读取文本框中的全部内容
                String input = txtInput.getText();
                // 如果文本框中有内容的话
                if (!input.isEmpty()) {
                    // String inputTemp = input.trim();
                    // String[] keyStrs = inputTemp.split(" ");
                    Iterator<String> it = items.iterator();
                    while (it.hasNext()) {
                        String item = it.next();
                        // 如果输入的内容能在选项列表中查找到的话
                        if (item.toLowerCase().startsWith(input.toLowerCase())) {
                            // 就把符合的加入到JComboBox中
                            model.addElement(item);
                        }
                    }
                }
                // 如果JComboBox中的元素大于0，则弹出显示。
                cbInput.setPopupVisible(model.getSize() > 0);
                // 不调整
                setAdjusting(cbInput, false);
            }
        });
        // 设置文本框的布局管理器
        txtInput.setLayout(new BorderLayout());
        // 在文本框中添加JComboBox,
        txtInput.add(cbInput, BorderLayout.SOUTH);
    }
}