package ui.icon;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Demo1 {
	public static void main(String[] args) {
			JFrame jf = new JFrame("给JButon添加图片，并填充。");
			jf.setLayout(null);
			jf.setSize(1000, 1000);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel jp = new JPanel();
			jp.setBackground(Color.black);
			jp.setLayout(null);
			
			
			/*--------------------------------------------------------*/
			JButton jb = new JButton();
			jb.setSize(100,100);//设置按钮大小
			// String path = "demoGather/image/mood.png";
			String path = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\image\\方向-左.png";
			//设置图片路径，实践的话根据自己的图片路径另加设置；我这个图片是个笑脸
			ImageIcon icon = new ImageIcon(path);//根据路径创建图标
			Image temp1 = icon.getImage().getScaledInstance(jb.getWidth(),
					jb.getHeight(), icon.getImage().SCALE_DEFAULT);
			//新建图片，大小调制成和按钮大小一样大
			//getScaledInstance()方法返回的是一个图片，后面的参数在程序下有注解。
			icon = new ImageIcon(temp1);
			//将图片另引用为图标
			jb.setIcon(icon);
			//将图标加载到按钮之上
			jp.add(jb);
			/*--------------------------------------------------------*/
			
			jf.setContentPane(jp);
			jf.setVisible(true);
	}
}

