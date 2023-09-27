package adbs.main.ui.jpanels.tools.example;

import java.awt.*;
import javax.swing.*;

public class FocusListenerJTextFieldInfoTest extends JFrame {
    public static void main(String[] args) {
        new FocusListenerJTextFieldInfoTest();
    }

    // jdk版本1.8测试通过.如果是1.8以下版本,有可能需要把变量修改成final类型的
    JButton jb1, jb2;
    JTextField jtf1, jtf2;

    public FocusListenerJTextFieldInfoTest() {// 初始化界面
        JPanel jp1 = new JPanel();
        JLabel jl1 = new JLabel("账号:", SwingConstants.CENTER);// 文字居中
        jtf1 = new JTextField(10);
        jp1.add(jl1);
        jp1.add(jtf1);
        JPanel jp2 = new JPanel();
        JLabel jl2 = new JLabel("密码:", SwingConstants.CENTER);
        jtf2 = new JTextField(10);
        jp2.add(jl2);
        jp2.add(jtf2);
        JPanel jp3 = new JPanel();
        jb1 = new JButton("确定");
        jb2 = new JButton("取消");
        jp3.add(jb1);
        jp3.add(jb2);
        setLayout(new GridLayout(3, 1, 20, 10));// 表格布局 3行1列 水平间距20 垂直间距10
        add(jp1);
        add(jp2);
        add(jp3);
        this.setSize(230, 170);// 大小
        setLocationRelativeTo(null);// 居中
        this.setResizable(false);// 不可缩放
        this.setTitle("LoginFrame");// 标题名
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);// 退出就关闭界面
        this.setVisible(true);
        String info1 = "请输入 [账号]";
        String info2 = "请输入 <密码>";
        jtf1.setText(info1);
        jtf2.setText(info2);
        jtf1.addFocusListener(new FocusListenerJTextFieldInfo(info1, jtf1));//添加焦点事件反映
        jtf2.addFocusListener(new FocusListenerJTextFieldInfo(info2, jtf2));
    }
}
