// package adbs.main.ui.jpanels.tools;
//
// import javax.swing.*;
// import java.awt.event.FocusEvent;
// import java.awt.event.FocusListener;
//
// public class FocusListenerJTextFieldInfo2 implements FocusListener {
//     /**
//      * 提示信息
//      */
//     String[] infos;
//     /**
//      * 文本框
//      */
//     JTextField jTextField;
//
//     public FocusListenerJTextFieldInfo2(String[] infos, JTextField jTextField) {
//         this.infos = infos;
//         this.jTextField = jTextField;
//     }
//
//     @Override
//     public void focusGained(FocusEvent e) {
//         //获得焦点的时候,清空提示文字
//         String jTextFieldText = jTextField.getText();
//         for (String info : infos) {
//             if (jTextFieldText.equals(info)) {
//                 jTextField.setText("");
//             }
//         }
//     }
//
//     @Override
//     public void focusLost(FocusEvent e) {
//         //失去焦点的时候,判断如果为空,就显示提示文字
//         String temp = jTextField.getText();
//         for (String info : infos) {
//             if (temp.equals("")) {
//                 jTextField.setText(info);
//             }
//         }
//
//     }
// }