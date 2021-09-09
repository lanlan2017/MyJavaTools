// package blue.commands.ui;
//
// import tools.config.ConfigTools;
// import ui.key.MapKeyList;
//
// import javax.swing.*;
// import javax.swing.event.DocumentEvent;
// import javax.swing.event.DocumentListener;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
// import java.util.Collection;
// import java.util.Iterator;
//
// public class AutocompleteField {
//
//     public static void main(String[] args) throws Exception {
//         // 设置外观
//         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//         // 创建窗体
//         JFrame frame = new JFrame();
//         // 设置窗体
//         frame.setTitle("Auto");
//
//         // 如果有传入参数的话
//         if (args.length > 0) {
//             // 不显示任务栏的情况
//             if ("notaskbar".equals(args[0]) || "no".equals(args[0])) {
//                 // 设置窗体不显示任务栏
//                 frame.setType(Window.Type.UTILITY);
//             }
//         }
//
//
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         // frame.setBounds(200, 200, 500, 400);
//         // 设置帮助内容
//         Collection<String> items = MapKeyList.getKeysList();
//         // 创建文本框
//         JTextField txtInput = new JTextField();
//         // 给文本框设置自动内容提示
//         setupAutoComplete(txtInput, items);
//         // 设置文本框的列数
//         txtInput.setColumns(30);
//         // 设置窗体的布局管理器
//         frame.getContentPane().setLayout(new FlowLayout());
//         // 把文本框添加到窗体中
//         frame.getContentPane().add(txtInput, BorderLayout.NORTH);
//         frame.pack();
//         // 显示窗体
//         frame.setVisible(true);
//     }
//
//     private static boolean isAdjusting(JComboBox jComboBox) {
//         if (jComboBox.getClientProperty("is_adjusting") instanceof Boolean) {
//             return (Boolean) jComboBox.getClientProperty("is_adjusting");
//         }
//         return false;
//     }
//
//     private static void setAdjusting(JComboBox jComboBox, boolean adjusting) {
//         jComboBox.putClientProperty("is_adjusting", adjusting);
//     }
//
//     /**
//      * 给文本框设置自动补全功能
//      *
//      * @param txtInput 要设置自动补全的文本框
//      * @param items    自动提示的内容列表
//      */
//     public static void setupAutoComplete(final JTextField txtInput, final Collection<String> items) {
//         // 创建JComboBox模型
//         final DefaultComboBoxModel model = new DefaultComboBoxModel();
//         // 使用JComboBox模型创建JComboBox
//         // final JComboBox jComboBox = new JComboBox(model) {
//         final JComboBox jComboBox = new JComboBox(model) {
//             @Override
//             public Dimension getPreferredSize() {
//                 // 设置位置
//                 return new Dimension(super.getPreferredSize().width, 0);
//             }
//         };
//
//         // 设置JComboBox
//         setAdjusting(jComboBox, false);
//         // 给JComBox添加选项
//         Iterator<String> it = items.iterator();
//         while (it.hasNext()) {
//             model.addElement(it.next());
//         }
//
//         // 设置JComboBox默认不选择任何选项
//         jComboBox.setSelectedItem(null);
//         // JComboxBox事件监听器
//         jComboBox.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 //
//                 if (!isAdjusting(jComboBox)) {
//
//                     if (jComboBox.getSelectedItem() != null) {
//                         // 文本框中填写JComboBox的内容。
//                         txtInput.setText(jComboBox.getSelectedItem().toString());
//
//                     }
//                 }
//             }
//         });
//         // 文本框监听器
//         txtInput.addKeyListener(new KeyAdapter() {
//             @Override
//             public void keyPressed(KeyEvent e) {
//                 // 设置JComboBox为真
//                 setAdjusting(jComboBox, true);
//
//                 // 如果是回车键，或者上箭头 或者下箭头
//                 if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
//                     //
//                     e.setSource(jComboBox);
//                     jComboBox.dispatchEvent(e);
//                     if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                         // 填充选项到文本框
//                         txtInput.setText(jComboBox.getSelectedItem().toString());
//                         // 隐藏文本框
//                         jComboBox.setPopupVisible(false);
//                         // 执行命令，打印结果
//                         System.out.println(doTextField(txtInput.getText()));
//                     }
//                 }
//                 // 如果按下esc键
//                 if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                     jComboBox.setPopupVisible(false);
//                 }
//                 setAdjusting(jComboBox, false);
//             }
//
//             /**
//              * 处理输入的内容
//              *
//              * @param input 文本框中输入的文本
//              * @return 对文本框中的文本处理的结果
//              */
//             private String doTextField(String input) {
//                 String output;
//                 // 按空格分隔得到命令
//                 String[] args = input.split(" ");
//                 // 执行命令，返回执行结果
//                 output = ConfigTools.getInstance().forward(args);
//                 // 复制到系统剪贴板
//                 ConfigTools.getInstance().copyToSysClipboard(output);
//                 return output;
//             }
//         });
//         // 监听文本框内容变化
//         txtInput.getDocument().addDocumentListener(new DocumentListener() {
//             // 当有内容插入的时候
//             @Override
//             public void insertUpdate(DocumentEvent e) {
//                 updateList();
//             }
//
//             public void removeUpdate(DocumentEvent e) {
//                 updateList();
//             }
//
//             public void changedUpdate(DocumentEvent e) {
//                 updateList();
//             }
//
//             private void updateList() {
//                 setAdjusting(jComboBox, true);
//                 // 移除JComboBox中的全部内容
//                 model.removeAllElements();
//                 // 读取文本框中的全部内容
//                 String input = txtInput.getText();
//                 // 如果文本框中有内容的话
//                 if (!input.isEmpty()) {
//                     // String inputTemp = input.trim();
//                     // String[] keyStrs = inputTemp.split(" ");
//                     Iterator<String> it = items.iterator();
//                     while (it.hasNext()) {
//                         String item = it.next();
//                         // 如果输入的内容能在选项列表中查找到的话
//                         if (item.toLowerCase().startsWith(input.toLowerCase())) {
//                             // 就把符合的加入到JComboBox中
//                             model.addElement(item);
//                         }
//                     }
//                 }
//                 // 如果JComboBox中的元素大于0，则弹出显示。
//                 jComboBox.setPopupVisible(model.getSize() > 0);
//                 // 不调整
//                 setAdjusting(jComboBox, false);
//             }
//         });
//         // 设置文本框的布局管理器
//         txtInput.setLayout(new BorderLayout());
//         // 在文本框中添加JComboBox,
//         txtInput.add(jComboBox, BorderLayout.SOUTH);
//     }
// }