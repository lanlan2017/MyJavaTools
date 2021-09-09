package auto.demo2;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AutoJTextField extends JTextField {
    private JComboBox<String> comboBox;
    // private Set<String> itemsSet = new HashSet<>(30);
    private Set<String> itemsSet = new TreeSet<String>();

    public AutoJTextField() {
        setVisible(true);
        initComboBox();
        initTextField();
    }

    //当鼠标点击聚焦的时候，会弹出来。
    @Override
    public void requestFocus() {
        super.requestFocus();
        if (getText() == null || getText().equals(""))
            setText("");
        setText(getText());
    }

    public void addItem(String str) {
        //判断一下是否重复
        if (itemsSet.contains(str)) {
            return;
        }
        comboBox.addItem(str);
        itemsSet.add(str);
        setText("");//保证初始化完成后TextField中内容是空。
    }

    public void removeItem(String str) {
        if (itemsSet.contains(str)) {
            itemsSet.remove(str);
        }
    }


    public void addItem(String[] strings) {
        for (String str : strings) {
            addItem(str);
        }

    }

    public void addItem(ArrayList<String> strings) {
        for (String str : strings) {
            addItem(str);
        }
    }

    /**
     * 通过集合添加
     *
     * @param strings
     */
    public void addItem(Collection<String> strings) {
        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            addItem(it.next());
        }
    }


    private void initComboBox() {
        final DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        comboBox = new JComboBox(comboBoxModel) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (comboBox.getSelectedItem() == null) {
                        return;
                    }
                    if (!isAdjusting(comboBox)) {
                        setText(comboBox.getSelectedItem().toString());
                    }
                }
            }
        });
        //comboBox.addItem("");这里可以加入默认的一些选项
        comboBox.setSelectedItem(null);
        comboBox.setEditable(true);
        comboBox.setEnabled(true);
    }

    private void initTextField() {
        setLayout(new BorderLayout());
        add(comboBox, BorderLayout.SOUTH);
        //获取文本编辑的时候的信息
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            private void updateList() {
                setAdjusting(comboBox, true);
                comboBox.removeAllItems();
                String containerStr = getText().trim();
                if (containerStr != null && containerStr != "") {
                    for (String str : itemsSet) {
                        //通过String的contains进行判断，简洁很多。否则用正则也行
                        if (str.toLowerCase().contains(containerStr.toLowerCase())) {
                            comboBox.addItem(str);
                        }
                    }
                }
                //父组件是TextField，父父组件没有说明JTextField没有设置好位置，此时弹出会抛出异常
                if (comboBox.getParent().getParent() != null) {
                    //重新弹出来一次，否则候选框的总数不知为何不会扩容只会缩容。
                    comboBox.setPopupVisible(false);
                    comboBox.setPopupVisible(true);
                    comboBox.setSelectedItem(null);
                }
                setAdjusting(comboBox, false);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setAdjusting(comboBox, true);
                super.mousePressed(e);
                comboBox.setPopupVisible(true);
                setAdjusting(comboBox, false);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                setAdjusting(comboBox, true);
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                        e.setSource(comboBox);
                        comboBox.dispatchEvent(e);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (comboBox.getSelectedItem() != null) {
                            if (comboBox.getSelectedItem() != null) {
                                setText(comboBox.getSelectedItem().toString());
                            }
                            comboBox.setPopupVisible(false);
                        }
                    }
                }
                setAdjusting(comboBox, false);
            }
        });
    }

    private static void setAdjusting(JComboBox jComboBox, Boolean adjusting) {
        jComboBox.putClientProperty("isAdjusting", adjusting);
    }

    private static Boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("isAdjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("isAdjusting");
        }
        return false;
    }
}
