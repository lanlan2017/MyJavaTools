package blue.commands.demo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JRadioButtonExample {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;

   public JRadioButtonExample(){
      prepareGUI();
   }
   public static void main(String[] args){
       JRadioButtonExample  swingControlDemo = new JRadioButtonExample();      
      swingControlDemo.showRadioButtonDemo();
   }
   private void prepareGUI(){
      mainFrame = new JFrame("Java Swing JRadioButton示例");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));

      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(350,100);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }
   private void showRadioButtonDemo(){
      headerLabel.setText("Control in action: RadioButton"); 

      final JRadioButton radApple = new JRadioButton("Java/Swing", true);
      final JRadioButton radMango = new JRadioButton("Python");
      final JRadioButton radPeer = new JRadioButton("MySQL");

      radApple.setMnemonic(KeyEvent.VK_C);
      radMango.setMnemonic(KeyEvent.VK_M);
      radPeer.setMnemonic(KeyEvent.VK_P);

      radApple.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            statusLabel.setText("Java/Swing RadioButton: " 
               + (e.getStateChange()==1?"checked":"unchecked"));
         }           
      });
      radMango.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {             
            statusLabel.setText("Python RadioButton: " 
               + (e.getStateChange()==1?"checked":"unchecked")); 
         }           
      });
      radPeer.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {             
            statusLabel.setText("MySQL RadioButton: " 
               + (e.getStateChange()==1?"checked":"unchecked"));
         }           
      });

      //Group the radio buttons.
      ButtonGroup group = new ButtonGroup();

      group.add(radApple);
      group.add(radMango);
      group.add(radPeer);

      controlPanel.add(radApple);
      controlPanel.add(radMango);
      controlPanel.add(radPeer);       

      mainFrame.setVisible(true);  
   }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/swing/swing_jradiobutton.html

